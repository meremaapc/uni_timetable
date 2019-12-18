package ru.itis.smarteducation.uni_timetable.service;

import ru.itis.smarteducation.uni_timetable.entity.Pair;
import ru.itis.smarteducation.uni_timetable.entity.Timetable;

import java.util.List;

public interface TimetableGenerationService {
    Timetable generate();
    List<Pair> placementTeacher();
}
