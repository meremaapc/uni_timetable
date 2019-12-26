package ru.itis.smarteducation.uni_timetable.service;

import ru.itis.smarteducation.uni_timetable.dto.GroupDto;
import ru.itis.smarteducation.uni_timetable.dto.TeacherDto;

import java.util.List;

public interface GroupService {

    List<GroupDto> findAll();

}
