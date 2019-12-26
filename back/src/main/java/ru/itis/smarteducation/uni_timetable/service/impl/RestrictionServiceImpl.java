package ru.itis.smarteducation.uni_timetable.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.smarteducation.uni_timetable.dto.SubjectDto;
import ru.itis.smarteducation.uni_timetable.entity.restriction.RestrictionCountOfGroups;
import ru.itis.smarteducation.uni_timetable.repository.restriction.RestrictionCountOfGroupsRepository;
import ru.itis.smarteducation.uni_timetable.repository.restriction.RestrictionCountOfHoursRepository;
import ru.itis.smarteducation.uni_timetable.service.RestrictionService;

@Service
@RequiredArgsConstructor
public class RestrictionServiceImpl implements RestrictionService {

    private final RestrictionCountOfHoursRepository restrictionCountOfHoursRepository;
    private final RestrictionCountOfGroupsRepository restrictionCountOfGroupsRepository;

    @Override
    public byte getHoursBydSubject(SubjectDto subject) {
        return restrictionCountOfHoursRepository.findBySubjectId(subject.getId()).getNumberOfHours();
    }

    @Override
    public byte findByTeacherAndSubject(Long teacherId, Long subjectId) {
        return restrictionCountOfGroupsRepository.findByTeacherAndSubject(teacherId, subjectId)
            .orElse(new RestrictionCountOfGroups())
            .getNumberOfGroups();
    }
}
