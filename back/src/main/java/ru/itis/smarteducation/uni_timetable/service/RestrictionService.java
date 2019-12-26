package ru.itis.smarteducation.uni_timetable.service;

import ru.itis.smarteducation.uni_timetable.dto.SubjectDto;
import ru.itis.smarteducation.uni_timetable.entity.Subject;

public interface RestrictionService {

    byte getHoursBydSubject(SubjectDto subject);

    byte findByTeacherAndSubject(Long teacherId, Long subjectId);
}
