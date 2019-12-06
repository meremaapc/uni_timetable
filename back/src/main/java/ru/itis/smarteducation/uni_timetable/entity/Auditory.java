package ru.itis.smarteducation.uni_timetable.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name="auditory")
public class Auditory extends BasicEntity<Long>{

    @Column(name="number", columnDefinition = "bpchar")
    private String number;

}
