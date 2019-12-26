package ru.itis.smarteducation.uni_timetable.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class GroupDto {

    private Long id;
    private String number;
    private List<StudentDto> studentList;
    private List<SubjectDto> subjectList;

}

