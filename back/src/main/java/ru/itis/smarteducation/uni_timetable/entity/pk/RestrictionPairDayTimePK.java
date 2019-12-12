package ru.itis.smarteducation.uni_timetable.entity.pk;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import ru.itis.smarteducation.uni_timetable.entity.DayOfWeek;
import ru.itis.smarteducation.uni_timetable.entity.PairTime;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Embeddable
@Data
@NoArgsConstructor
@Accessors(chain = true)
public class RestrictionPairDayTimePK implements Serializable {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "day_of_week_id")
    private DayOfWeek dayOfWeek;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "pair_time_id")
    private PairTime pairTime;

}
