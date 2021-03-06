package ru.itis.smarteducation.uni_timetable.entity.restriction;

import lombok.Data;
import ru.itis.smarteducation.uni_timetable.entity.Subject;
import ru.itis.smarteducation.uni_timetable.entity.Teacher;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name="restriction_count_of_groups")
public class RestrictionCountOfGroups extends BaseRestriction {

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Column(name = "number_of_groups")
    private byte numberOfGroups;
}
