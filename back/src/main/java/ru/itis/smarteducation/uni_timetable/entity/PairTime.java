package ru.itis.smarteducation.uni_timetable.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.sql.Time;

@Data
@Entity
@Table(name = "pair_time")
public class PairTime extends BasicEntity<Long>{

    @Column(name="time")
    private Time time;
}
