package ru.itis.smarteducation.uni_timetable.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity
@Table
public class Timetable extends BasicEntity<Long> {

    @Column(name = "create_date")
    private Date createDate;

}
