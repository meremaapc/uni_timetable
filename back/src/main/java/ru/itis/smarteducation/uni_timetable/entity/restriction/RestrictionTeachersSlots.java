package ru.itis.smarteducation.uni_timetable.entity.restriction;

import lombok.Data;
import ru.itis.smarteducation.uni_timetable.entity.BasicEntity;
import ru.itis.smarteducation.uni_timetable.entity.DayOfWeek;
import ru.itis.smarteducation.uni_timetable.entity.PairTime;
import ru.itis.smarteducation.uni_timetable.entity.Teacher;

import javax.persistence.*;

@Data
@Entity
@Table(name = "restriction_teachers_slots")
public class RestrictionTeachersSlots extends BasicEntity<Long> {

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "pair_time_id")
    private PairTime pairTime;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "day_of_week_id")
    private DayOfWeek dayOfWeek;
}
