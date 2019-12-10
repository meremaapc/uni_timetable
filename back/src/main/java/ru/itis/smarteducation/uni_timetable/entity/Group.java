package ru.itis.smarteducation.uni_timetable.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="uni_group")
public class Group extends BasicEntity<Long>{

    @Column(name = "number")
    private String number;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id")
    private List<Student> studentList;

    @ManyToMany
    @JoinTable(
        name = "group_subject",
        joinColumns = @JoinColumn(name = "subject_id"),
        inverseJoinColumns = @JoinColumn(name = "group_id")
    )
    private List<Subject> subjectList;

}
