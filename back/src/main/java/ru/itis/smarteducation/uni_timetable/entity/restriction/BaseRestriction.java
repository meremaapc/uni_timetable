package ru.itis.smarteducation.uni_timetable.entity.restriction;

import lombok.Data;
import ru.itis.smarteducation.uni_timetable.entity.BasicEntity;

import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@Data
@MappedSuperclass
public class BaseRestriction extends BasicEntity<Long> {

    @Transient
    private boolean isBreak;

}
