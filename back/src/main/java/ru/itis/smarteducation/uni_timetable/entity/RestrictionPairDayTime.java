package ru.itis.smarteducation.uni_timetable.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "pair")
public class RestrictionPairDayTime {

    @Column(name = "day_of_week_id")
    private DayOfWeek dayOfWeek;

    @Column(name = "pair_time_id")
    private PairTime pairTime;

}
