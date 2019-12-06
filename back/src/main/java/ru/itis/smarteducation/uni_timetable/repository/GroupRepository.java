package ru.itis.smarteducation.uni_timetable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.smarteducation.uni_timetable.entity.UniGroup;

public interface GroupRepository extends JpaRepository<UniGroup, Long> {
}
