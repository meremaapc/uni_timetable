package ru.itis.smarteducation.uni_timetable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.smarteducation.uni_timetable.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
