package ru.itis.smarteducation.uni_timetable.service;

import ru.itis.smarteducation.uni_timetable.dto.PairDto;
import ru.itis.smarteducation.uni_timetable.model.TeacherSubjectGroup;

import java.util.List;

public interface CheckRestrictionService {

    boolean isFreeTeacherBySubject(List<TeacherSubjectGroup> generateTeacherSubjectList,
                                   byte countOfGroups, Long teacherId, Long subjectId);

    boolean isTeacherFreeThisPairTimeWeek(PairDto pairCandidate);

}
