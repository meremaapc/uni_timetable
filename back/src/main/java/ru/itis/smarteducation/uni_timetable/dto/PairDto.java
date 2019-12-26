package ru.itis.smarteducation.uni_timetable.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class PairDto {

    private GroupDto group;
    private SubjectDto subject;
    private AuditoryDto auditory;
    private DayOfWeekDto dayOfWeek;
    private TeacherDto teacher;
    private PairTimeDto pairTime;

}
