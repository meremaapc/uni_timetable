package ru.itis.smarteducation.uni_timetable.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.itis.smarteducation.uni_timetable.entity.Group;

public interface GroupRepository extends JpaRepository<Group, Long> {

    @Query(nativeQuery = true,
      value = "select count(id) from uni_group " +
        "join group_subject gs on uni_group.id = gs.group_id " +
        "where subject_id = :subject_id")
    int getCountGroupsBySubject(@Param("subject_id") Long subjectId);

}
