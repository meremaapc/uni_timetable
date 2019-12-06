package ru.itis.smarteducation.uni_timetable.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name="uni_group")
public class UniGroup extends BasicEntity<Long>{

    @Column(name = "number")
    private String number;

}
