package ru.itis.smarteducation.uni_timetable.service.impl;

import org.springframework.stereotype.Service;
import ru.itis.smarteducation.uni_timetable.dto.PairDto;
import ru.itis.smarteducation.uni_timetable.dto.RestrictionTeachersSlotsDto;
import ru.itis.smarteducation.uni_timetable.dto.TeacherDto;
import ru.itis.smarteducation.uni_timetable.model.TeacherSubjectGroup;
import ru.itis.smarteducation.uni_timetable.service.CheckRestrictionService;

import java.util.List;

@Service
public class CheckRestrictionServiceImpl implements CheckRestrictionService {


    @Override
    public boolean isFreeTeacherBySubject(List<TeacherSubjectGroup> generateTeacherSubjectList,
                                          byte countOfGroups, Long teacherId, Long subjectId) {
        return generateTeacherSubjectList.stream()
            .filter(item -> item.getTeacher().getId().equals(teacherId)
                && item.getSubject().getId().equals(subjectId))
            .count() < countOfGroups;
    }

    @Override
    public boolean isTeacherFreeThisPairTimeWeek(PairDto pairCandidate) {
        TeacherDto teacherDto = pairCandidate.getTeacher();
        List<RestrictionTeachersSlotsDto> teachersSlotsDtos = teacherDto.getSlotList();

        return teachersSlotsDtos.stream()
            .anyMatch(slot -> slot.getDayOfWeek().equals(pairCandidate.getDayOfWeek())
                && slot.getPairTime().equals(pairCandidate.getPairTime()));
    }

}
