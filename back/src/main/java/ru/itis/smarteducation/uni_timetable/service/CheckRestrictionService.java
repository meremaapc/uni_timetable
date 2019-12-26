package ru.itis.smarteducation.uni_timetable.service;

import ru.itis.smarteducation.uni_timetable.entity.Pair;
import ru.itis.smarteducation.uni_timetable.entity.Subject;
import ru.itis.smarteducation.uni_timetable.entity.Teacher;
import ru.itis.smarteducation.uni_timetable.model.TeacherSubjectGroup;

import java.util.List;

public interface CheckRestrictionService {

    boolean isTeacherHasFreeSlotsForSubject(Teacher teacher, List<Pair> generatePairList,
                                            int subjectWeekCount);

    boolean isFreeTeacherBySubject(List<TeacherSubjectGroup> generateTeacherSubjectList,
                                   byte countOfGroups, Long teacherId, Long subjectId);

    boolean isTeacherFreeThisPairTimeWeek(Pair pairCandidate, Teacher teacher,
                                          List<Pair> generatePairs);

    boolean isFreeAuditory(Pair pairCandidate, List<Pair> generatePairList);

}
