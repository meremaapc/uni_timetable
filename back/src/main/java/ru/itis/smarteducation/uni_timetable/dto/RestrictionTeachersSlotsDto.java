package ru.itis.smarteducation.uni_timetable.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class RestrictionTeachersSlotsDto {

    private PairTimeDto pairTime;
    private DayOfWeekDto dayOfWeek;

}
