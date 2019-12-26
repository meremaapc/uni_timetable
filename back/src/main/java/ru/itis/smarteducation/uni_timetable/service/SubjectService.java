package ru.itis.smarteducation.uni_timetable.service;

import ru.itis.smarteducation.uni_timetable.dto.SubjectDto;

import java.util.List;

public interface SubjectService {

    List<SubjectDto> findAll();

}
