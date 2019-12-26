package ru.itis.smarteducation.uni_timetable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.itis.smarteducation.uni_timetable.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Long> {
    @Query(nativeQuery = true,
        value = "SELECT sum(number_of_hours) from subject join group_subject gs on subject.id = gs.subject_id " +
            "join restriction_count_of_hours rcoh on subject.id = rcoh.subject_id")
    int findPairsByWeek();
}
