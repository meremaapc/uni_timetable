package ru.itis.smarteducation.uni_timetable.repository.restriction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.itis.smarteducation.uni_timetable.entity.restriction.RestrictionCountOfGroups;

import java.util.Optional;

@Repository
public interface RestrictionCountOfGroupsRepository extends JpaRepository<RestrictionCountOfGroups, Long> {

    @Query("FROM RestrictionCountOfGroups r WHERE r.teacher.id = :teacher_id AND r.subject.id = :subject_id ")
    Optional<RestrictionCountOfGroups> findByTeacherAndSubject(@Param("teacher_id") Long teacherId,
                                                               @Param("subject_id") Long subjectId);

}
