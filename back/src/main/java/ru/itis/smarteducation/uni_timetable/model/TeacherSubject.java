package ru.itis.smarteducation.uni_timetable.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.itis.smarteducation.uni_timetable.entity.Subject;
import ru.itis.smarteducation.uni_timetable.entity.Teacher;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class TeacherSubject {

    private Teacher teacher;
    private Subject subject;
    private Short week_time;

}
