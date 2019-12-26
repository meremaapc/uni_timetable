package ru.itis.smarteducation.uni_timetable.entity;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table
@Accessors(chain = true)
public class Timetable extends BasicEntity<Long> {

    @Column(name = "create_date")
    private LocalDateTime createDate;

}
