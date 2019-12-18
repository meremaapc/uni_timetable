package ru.itis.smarteducation.uni_timetable.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table
public class Timetable extends BasicEntity<Long> {

    @Column(name = "create_date")
    private Date createDate;

    @Transient
    private List<Pair> pairList;

    @Transient
    private Double perfectPercent;


}
