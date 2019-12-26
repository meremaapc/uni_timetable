package ru.itis.smarteducation.uni_timetable.service.impl;

import org.springframework.stereotype.Service;
import ru.itis.smarteducation.uni_timetable.entity.Pair;
import ru.itis.smarteducation.uni_timetable.entity.Subject;
import ru.itis.smarteducation.uni_timetable.entity.Teacher;
import ru.itis.smarteducation.uni_timetable.entity.restriction.RestrictionTeachersSlots;
import ru.itis.smarteducation.uni_timetable.model.TeacherSubjectGroup;
import ru.itis.smarteducation.uni_timetable.service.CheckRestrictionService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckRestrictionServiceImpl implements CheckRestrictionService {

    @Override
    public boolean isTeacherHasFreeSlotsForSubject(Teacher teacher, List<Pair> generatePairList, int subjectWeekCount) {
        return teacher.getSlotList().size() - generatePairList.stream()
            .filter(generatePar -> generatePar.getTeacher().equals(teacher))
            .count() >= subjectWeekCount;
    }

    @Override
    public boolean isFreeTeacherBySubject(List<TeacherSubjectGroup> generateTeacherSubjectList,
                                          byte countOfGroups, Long teacherId, Long subjectId) {
        return generateTeacherSubjectList.stream()
            .filter(item -> item.getTeacher().getId().equals(teacherId)
                && item.getSubject().getId().equals(subjectId))
            .count() < countOfGroups;
    }

    @Override
    public boolean isTeacherFreeThisPairTimeWeek(Pair pairCandidate, Teacher teacher, List<Pair> generatePairs) {
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

    @Override
    public boolean isFreeAuditory(Pair pairCandidate, List<Pair> generatePairList) {
        return generatePairList.stream()
            .noneMatch(generatePair -> generatePair.getDayOfWeek().equals(pairCandidate.getDayOfWeek())
                && generatePair.getPairTime().equals(pairCandidate.getPairTime())
                && generatePair.getAuditory().equals(pairCandidate.getAuditory()));
    }
}
