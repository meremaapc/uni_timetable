package ru.itis.smarteducation.uni_timetable.service.impl;

import org.springframework.stereotype.Service;
import ru.itis.smarteducation.uni_timetable.dto.PairDto;
import ru.itis.smarteducation.uni_timetable.dto.RestrictionTeachersSlotsDto;
import ru.itis.smarteducation.uni_timetable.dto.TeacherDto;
import ru.itis.smarteducation.uni_timetable.model.TeacherSubjectGroup;
import ru.itis.smarteducation.uni_timetable.service.CheckRestrictionService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CheckRestrictionServiceImpl implements CheckRestrictionService {

    @Override
    public boolean isTeacherHasFreeSlotsForSubject(TeacherDto teacher, List<PairDto> generatePairList, int subjectWeekCount) {
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
    public boolean isTeacherFreeThisPairTimeWeek(PairDto pairCandidate, TeacherDto teacher, List<PairDto> generatePairs) {
        List<PairDto> generatePairByTeacher = generatePairs.stream()
            .filter(generatePair -> generatePair.getTeacher().equals(teacher))
            .collect(Collectors.toList());

        List<RestrictionTeachersSlotsDto> s = teacher.getSlotList().stream()
            .filter(slot -> generatePairByTeacher.stream()
                .noneMatch(item -> item.getPairTime().equals(slot.getPairTime())
                    && item.getDayOfWeek().equals(slot.getDayOfWeek())))
            .collect(Collectors.toList());

        return s.stream()
            .anyMatch(slot -> slot.getDayOfWeek().equals(pairCandidate.getDayOfWeek())
                && slot.getPairTime().equals(pairCandidate.getPairTime()));
    }

    @Override
    public boolean isFreeAuditory(PairDto pairCandidate, List<PairDto> generatePairList) {
        return generatePairList.stream()
            .noneMatch(generatePair -> generatePair.getDayOfWeek().equals(pairCandidate.getDayOfWeek())
                && generatePair.getPairTime().equals(pairCandidate.getPairTime())
                && generatePair.getAuditory().equals(pairCandidate.getAuditory()));
    }
}
