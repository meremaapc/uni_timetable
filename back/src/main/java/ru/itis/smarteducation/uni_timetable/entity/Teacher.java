package ru.itis.smarteducation.uni_timetable.entity;

import lombok.Data;
import ru.itis.smarteducation.uni_timetable.entity.restriction.RestrictionTeachersSlots;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "teacher")
public class Teacher extends BasicEntity<Long>{

    @Column(name = "name")
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "teacher_subject",
            joinColumns = @JoinColumn(name="teacher_id"),
            inverseJoinColumns = @JoinColumn(name="subject_id"))
    private List<Subject> subjects;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "teacher_id")
    private List<RestrictionTeachersSlots> slotList;

}
