package ru.itis.smarteducation.uni_timetable.repository.restriction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itis.smarteducation.uni_timetable.entity.Subject;
import ru.itis.smarteducation.uni_timetable.entity.restriction.RestrictionCountOfHours;

@Repository
public interface RestrictionCountOfHoursRepository extends JpaRepository<RestrictionCountOfHours, Long> {

    @Query("FROM RestrictionCountOfHours r WHERE r.subject.id = :subjectId")
    RestrictionCountOfHours findBySubjectId(@Param("subjectId") Long subjectId);

}
