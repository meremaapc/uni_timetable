package ru.itis.smarteducation.uni_timetable.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "student")
public class Student extends BasicEntity<Long> {

    @Column(name="name")
    private String name;

    @Column(name="year_of_receipt")
    private Integer yerOfReceipt;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "group_id")
    private UniGroup group;



}
