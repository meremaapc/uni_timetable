package ru.itis.smarteducation.uni_timetable.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "day_of_week")
public class DayOfWeek extends BasicEntity<Long>{

    @Column(name = "name")
    private String name;

}
