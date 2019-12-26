package ru.itis.smarteducation.uni_timetable.service;

import ru.itis.smarteducation.uni_timetable.dto.PairDto;

import java.util.List;

public interface CalculateRestrictionFunctionValueService {

    double calculate(List<PairDto> pairList);

}
