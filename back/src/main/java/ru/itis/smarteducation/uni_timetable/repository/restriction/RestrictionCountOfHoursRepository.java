package ru.itis.smarteducation.uni_timetable.repository.restriction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.smarteducation.uni_timetable.entity.restriction.RestrictionCountOfHours;

@Repository
public interface RestrictionCountOfHoursRepository extends JpaRepository<RestrictionCountOfHours, Long> {

}
