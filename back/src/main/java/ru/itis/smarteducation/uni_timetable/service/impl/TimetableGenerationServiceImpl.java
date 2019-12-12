package ru.itis.smarteducation.uni_timetable.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.smarteducation.uni_timetable.entity.*;
import ru.itis.smarteducation.uni_timetable.entity.restriction.RestrictionCountOfGroups;
import ru.itis.smarteducation.uni_timetable.entity.restriction.RestrictionTeachersSlots;
import ru.itis.smarteducation.uni_timetable.repository.*;
import ru.itis.smarteducation.uni_timetable.repository.restriction.RestrictionCountOfGroupsRepository;
import ru.itis.smarteducation.uni_timetable.repository.restriction.RestrictionCountOfHoursRepository;
import ru.itis.smarteducation.uni_timetable.service.TimetableGenerationService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TimetableGenerationServiceImpl implements TimetableGenerationService {

    private final Logger log = Logger.getLogger(TimetableGenerationService.class.getSimpleName());

    private final GroupRepository groupRepository;
    private final DayOfWeekRepository dayOfWeekRepository;
    private final AuditoryRepository auditoryRepository;
    private final PairTimeRepository pairTimeRepository;
    private final SubjectRepository subjectRepository;
    private final RestrictionCountOfHoursRepository restrictionCountOfHoursRepository;
    private final RestrictionCountOfGroupsRepository restrictionCountOfGroupsRepository;


    private final List<Pair> allKindOfPair = new ArrayList<>();

    @Override
    @Transactional
    public Timetable generate() {
        placementTeacher();
        return null;
    }

    private List<Pair> placementTeacher() {
        List<Subject> subjectList = subjectRepository.findAll();

        List<Pair> generatePair = new ArrayList<>();

        subjectList.forEach(subject -> {
            int groupCountBySubject = groupRepository.getCountGroupsBySubject(subject.getId());
            byte weekCount = restrictionCountOfHoursRepository.findBySubject(subject).getNumberOfHours();

            List<Teacher> satisfyingTeacherList = subject.getTeachers().stream()
                    .filter(teacher -> isTeacherHasFreeSlotsForSubject(teacher, generatePair, weekCount))
                    .collect(Collectors.toList());
            for (int j = 0; j < groupCountBySubject; j ++) {
                Collections.shuffle(satisfyingTeacherList);

                boolean subjectTeacherSet = false;

                for (Teacher teacher : satisfyingTeacherList) {
                    byte countOfGroupsForTeacherBySubject =
                            restrictionCountOfGroupsRepository.findByTeacherAndSubject(teacher.getId(), subject.getId())
                                    .orElse(new RestrictionCountOfGroups()).getNumberOfGroups();

                    if (countOfGroupsForTeacherBySubject == 0) {
                        continue;
                    }

                    if (isFreeTeacherBySubject(generatePair, countOfGroupsForTeacherBySubject, teacher, subject)) {

                        /**
                         *  на этом этапе будет преподаватель, у которого
                         *  - есть такой предмет
                         *  - есть свободное место для группы
                         *  - есть слоты для того, чтобы вставить туда пары
                         *
                         */

                        Collections.shuffle(allKindOfPair);

                        boolean setPair = false;

                        for (int i = 0; i < allKindOfPair.size() && !setPair; i++) {
                            Pair pair = allKindOfPair.get(i);
                            setPair = isTeacherFreeThisPairTimeWeek(pair, teacher, generatePair)
                                    && isFreeAuditory(pair, generatePair);

                            if (setPair) {
                                allKindOfPair.remove(pair);
                                pair.setSubject(subject);
                                pair.setTeacher(teacher);
                                generatePair.add(pair);
                            }
                        }

                        subjectTeacherSet = setPair;
                    }

                    if (subjectTeacherSet) {
                        break;
                    }
                }

                if (!subjectTeacherSet) {
                    String msg = "Не удалось сгенерировать расписание";
                    log.warning(msg);
                    throw new IllegalArgumentException(msg);
                }
            }
        });

        return generatePair;
    }


    //кажется не нужно, в список allKindOfPair нет пересекающихся данных
    private boolean isFreeAuditory(Pair pairCandidate, List<Pair> generatePairList) {
        return generatePairList.stream()
                .noneMatch(generatePair -> generatePair.getDayOfWeek().equals(pairCandidate.getDayOfWeek())
                        && generatePair.getPairTime().equals(pairCandidate.getPairTime())
                        && generatePair.getAuditory().equals(pairCandidate.getAuditory()));
    }

    private boolean isTeacherFreeThisPairTimeWeek(Pair pairCandidate, Teacher teacher,
                                                  List<Pair> generatePairs) {
        List<Pair> generatePairByTeacher = generatePairs.stream()
                .filter(generatePair -> generatePair.getTeacher().equals(teacher))
                .collect(Collectors.toList());

        List<RestrictionTeachersSlots> s = teacher.getSlotList().stream()
                .filter(slot -> generatePairByTeacher.stream()
                        .noneMatch(item -> item.getPairTime().equals(slot.getPairTime())
                                && item.getDayOfWeek().equals(slot.getDayOfWeek())))
                .collect(Collectors.toList());
        boolean res = s.stream()
                .anyMatch(slot -> slot.getDayOfWeek().equals(pairCandidate.getDayOfWeek())
                        && slot.getPairTime().equals(pairCandidate.getPairTime()));

        return res;
    }

    private boolean isFreeTeacherBySubject(List<Pair> generatePairList, byte countOfGroups,
                                           Teacher teacher, Subject subject) {
        return generatePairList.stream()
                .filter(item -> item.getTeacher().equals(teacher)
                        && item.getSubject().equals(subject))
                .count() < countOfGroups;
    }

    private boolean isTeacherHasFreeSlotsForSubject(Teacher teacher, List<Pair> generatePairList, int subjectWeekCount) {
        return teacher.getSlotList().size() - generatePairList.stream()
                .filter(generatePar -> generatePar.getTeacher().equals(teacher))
                .count() >= subjectWeekCount;
    }

    @PostConstruct
    private void generateAllKindOfPair() {
        List<DayOfWeek> weeks = dayOfWeekRepository.findAll();
        List<PairTime> pairTimes = pairTimeRepository.findAll();
        List<Auditory> auditoryList = auditoryRepository.findAll();

        //без воскресенье
        weeks.remove(weeks.size() - 1);

        weeks.forEach(week ->
                pairTimes.forEach(
                        pairTime -> auditoryList.forEach(auditory -> {
                            Pair pair = new Pair()
                                    .setAuditory(auditory)
                                    .setPairTime(pairTime)
                                    .setDayOfWeek(week);
                            allKindOfPair.add(pair);
                        }))
        );

        Collections.shuffle(allKindOfPair);
    }
}
