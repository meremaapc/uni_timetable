package ru.itis.smarteducation.uni_timetable.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.itis.smarteducation.uni_timetable.dto.GroupDto;
import ru.itis.smarteducation.uni_timetable.dto.SubjectDto;
import ru.itis.smarteducation.uni_timetable.dto.TeacherDto;
import ru.itis.smarteducation.uni_timetable.entity.Group;
import ru.itis.smarteducation.uni_timetable.entity.Subject;
import ru.itis.smarteducation.uni_timetable.entity.Teacher;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TeacherSubjectGroup {

    private TeacherDto teacher;
    private SubjectDto subject;
    private GroupDto group;
    private int hours;

}
