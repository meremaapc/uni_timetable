package ru.itis.smarteducation.uni_timetable.entity;

import lombok.Data;
import ru.itis.smarteducation.uni_timetable.entity.pk.RestrictionPairDayTimePK;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "restriction_pair_day_time")
public class RestrictionPairDayTime {

    @EmbeddedId
    private RestrictionPairDayTimePK pk;

}
