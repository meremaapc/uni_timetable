package ru.itis.smarteducation.uni_timetable.service;

import ru.itis.smarteducation.uni_timetable.dto.PairTimeDto;
import ru.itis.smarteducation.uni_timetable.dto.SubjectDto;

import java.util.List;

public interface PairTimeService {

    List<PairTimeDto> findAll();

}
