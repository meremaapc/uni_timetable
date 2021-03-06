package ru.itis.smarteducation.uni_timetable.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class TeacherDto {

    private Long id;
    private String name;
    private List<RestrictionTeachersSlotsDto> slotList;

}
