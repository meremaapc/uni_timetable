package ru.itis.smarteducation.uni_timetable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.smarteducation.uni_timetable.entity.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Long> {
}
