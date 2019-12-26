package ru.itis.smarteducation.uni_timetable.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.sql.Time;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class PairTimeDto {

    private Long id;
    private Time time;
}
