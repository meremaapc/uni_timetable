package ru.itis.smarteducation.uni_timetable.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.smarteducation.uni_timetable.dto.AuditoryDto;
import ru.itis.smarteducation.uni_timetable.dto.DayOfWeekDto;
import ru.itis.smarteducation.uni_timetable.dto.GroupDto;
import ru.itis.smarteducation.uni_timetable.dto.PairDto;
import ru.itis.smarteducation.uni_timetable.dto.PairTimeDto;
import ru.itis.smarteducation.uni_timetable.dto.SubjectDto;
import ru.itis.smarteducation.uni_timetable.dto.TeacherDto;
import ru.itis.smarteducation.uni_timetable.dto.TimetableDto;
import ru.itis.smarteducation.uni_timetable.model.TeacherSubjectGroup;
import ru.itis.smarteducation.uni_timetable.service.AuditoryService;
import ru.itis.smarteducation.uni_timetable.service.CalculateRestrictionFunctionValueService;
import ru.itis.smarteducation.uni_timetable.service.CheckRestrictionService;
import ru.itis.smarteducation.uni_timetable.service.DayOfWeekService;
import ru.itis.smarteducation.uni_timetable.service.GroupService;
import ru.itis.smarteducation.uni_timetable.service.PairTimeService;
import ru.itis.smarteducation.uni_timetable.service.RestrictionService;
import ru.itis.smarteducation.uni_timetable.service.SubjectService;
import ru.itis.smarteducation.uni_timetable.service.TimetableGenerationService;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TimetableGenerationServiceImpl implements TimetableGenerationService {

    private final Logger log = Logger.getLogger(TimetableGenerationService.class.getSimpleName());

    private final CheckRestrictionService checkRestrictionService;
    private final CalculateRestrictionFunctionValueService calculateRestrictionFunctionValueService;
    private final RestrictionService restrictionService;
    private final GroupService groupService;
    private final SubjectService subjectService;
    private final DayOfWeekService dayOfWeekService;
    private final PairTimeService pairTimeService;
    private final AuditoryService auditoryService;


    private final List<PairDto> allKindOfPair = new ArrayList<>();

    @Override
    @Transactional
    public TimetableDto generate() {
        List<PairDto> generatedPair = placementTeacherSubjectGroup();
        double perfectPercent = calculateRestrictionFunctionValueService.calculate(generatedPair);
        return new TimetableDto()
            .setPairList(generatedPair)
            .setCreateDate(LocalDateTime.now())
            .setPerfectPercent(perfectPercent);
    }

    private List<PairDto> placementTeacherSubjectGroup() {

        List<PairDto> generatePair = new ArrayList<>();
        List<SubjectDto> subjectList = subjectService.findAll();
        List<GroupDto> groupList = groupService.findAll();

        List<TeacherSubjectGroup> teacherSubjectGroups = setSubjectAndGroupToTeacher(subjectList, groupList);
        Collections.shuffle(allKindOfPair);

        teacherSubjectGroups.forEach(teacherSubjectGroup -> {
            for (int i = 0; i < teacherSubjectGroup.getHours(); i ++) {
                PairDto pairCandidate = allKindOfPair.stream()
                    .findFirst()
                    .orElseThrow(() ->
                        new IndexOutOfBoundsException("Закончились пары!"));
                 /*
                    todo: ? Строгие ограничения: отуствие пар в одной аудитории
                */
                allKindOfPair.remove(pairCandidate);
                pairCandidate.setSubject(teacherSubjectGroup.getSubject());
                pairCandidate.setTeacher(teacherSubjectGroup.getTeacher());
                pairCandidate.setGroup(teacherSubjectGroup.getGroup());
                generatePair.add(pairCandidate);
            }
        });

        return generatePair;
    }

    private List<TeacherSubjectGroup> setSubjectAndGroupToTeacher(List<SubjectDto> subjectList,
                                                                  List<GroupDto> groups) {
        List<TeacherSubjectGroup> teacherSubjectGroups = new ArrayList<>();
        /*
          Строгие ограничения:
          1. Преподаватель ведет только те предметы, которые обозначены
          2. Преподаватель берет количество групп, не превышащее ограничения
          3. У всех групп есть преподаватель
          4. Аудитория не занята больше чем одной группой
         */
        Collections.shuffle(subjectList);
        subjectList.forEach(subject -> {
            List<GroupDto> groupsBySubject = groups.stream()
                .filter(item -> item.getSubjectList().contains(subject))
                .collect(Collectors.toList());
            List<TeacherDto> satisfyingTeacherList = subject.getTeachers();

            Collections.shuffle(groupsBySubject);

            for (GroupDto group : groupsBySubject) {
                Collections.shuffle(satisfyingTeacherList);

                int hourCount = restrictionService.getHoursBydSubject(subject);
                boolean teacherFind = false;
                for (TeacherDto teacher : satisfyingTeacherList) {
                    byte countOfGroupsForTeacherBySubject = restrictionService
                        .findByTeacherAndSubject(teacher.getId(), subject.getId());
                    boolean isTeacherFreeBySubject = checkRestrictionService.isFreeTeacherBySubject(teacherSubjectGroups,
                        countOfGroupsForTeacherBySubject, teacher.getId(), subject.getId());

                    if (isTeacherFreeBySubject) {
                        teacherFind = true;
                        TeacherSubjectGroup teacherSubjectGroup = new TeacherSubjectGroup()
                            .setGroup(group)
                            .setSubject(subject)
                            .setTeacher(teacher)
                            .setHours(hourCount);
                        teacherSubjectGroups.add(teacherSubjectGroup);
                        break;
                    }
                }

                if (!teacherFind) {
                    log.warning("Teacher for group " + group.getId() + " for subject " + subject.getId() + " not found");
                }
            }
        });

        return teacherSubjectGroups;
    }


    @PostConstruct
    private void generateAllKindOfPair() {
        List<DayOfWeekDto> weeks = dayOfWeekService.findAll();
        List<PairTimeDto> pairTimes = pairTimeService.findAll();
        List<AuditoryDto> auditoryList = auditoryService.findAll();

        //без воскресенье
        weeks.remove(weeks.size() - 1);

        weeks.forEach(week ->
            pairTimes.forEach(
                pairTime -> auditoryList.forEach(auditory -> {
                    PairDto pair = new PairDto()
                        .setAuditory(auditory)
                        .setPairTime(pairTime)
                        .setDayOfWeek(week);
                    allKindOfPair.add(pair);
                }))
        );

        Collections.shuffle(allKindOfPair);
    }
}
