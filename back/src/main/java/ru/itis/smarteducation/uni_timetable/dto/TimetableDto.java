package ru.itis.smarteducation.uni_timetable.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class TimetableDto {

    private LocalDateTime createDate;
    private List<PairDto> pairList;
    private double perfectPercent;

}
