package ru.itis.smarteducation.uni_timetable.service;

import ru.itis.smarteducation.uni_timetable.dto.PairDto;
import ru.itis.smarteducation.uni_timetable.dto.TeacherDto;
import ru.itis.smarteducation.uni_timetable.model.TeacherSubjectGroup;

import java.util.List;

public interface CheckRestrictionService {

    boolean isTeacherHasFreeSlotsForSubject(TeacherDto teacher, List<PairDto> generatePairList,
                                            int subjectWeekCount);

    boolean isFreeTeacherBySubject(List<TeacherSubjectGroup> generateTeacherSubjectList,
                                   byte countOfGroups, Long teacherId, Long subjectId);

    boolean isTeacherFreeThisPairTimeWeek(PairDto pairCandidate, TeacherDto teacher,
                                          List<PairDto> generatePairs);

    boolean isFreeAuditory(PairDto pairCandidate, List<PairDto> generatePairList);

}
