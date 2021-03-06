package ru.itis.smarteducation.uni_timetable.repository.restriction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.smarteducation.uni_timetable.entity.restriction.RestrictionTeachersSlots;

@Repository
public interface RestrictionTeachersSlotsRepository extends JpaRepository<RestrictionTeachersSlots, Long> {

}
