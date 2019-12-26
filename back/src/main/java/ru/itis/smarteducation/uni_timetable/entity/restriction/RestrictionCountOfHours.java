package ru.itis.smarteducation.uni_timetable.entity.restriction;

import lombok.Data;
import ru.itis.smarteducation.uni_timetable.entity.Subject;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "restriction_count_of_hours")
public class RestrictionCountOfHours extends BaseRestriction {

    @OneToOne(fetch = FetchType.EAGER, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "subject_id")
    private Subject subject;

    @Column(name = "number_of_hours")
    private byte numberOfHours;

}
