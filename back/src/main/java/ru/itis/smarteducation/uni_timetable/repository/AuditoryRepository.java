package ru.itis.smarteducation.uni_timetable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.smarteducation.uni_timetable.entity.Auditory;

public interface AuditoryRepository extends JpaRepository<Auditory, Long> {
}
