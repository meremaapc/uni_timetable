package ru.itis.smarteducation.uni_timetable.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.smarteducation.uni_timetable.dto.PairDto;
import ru.itis.smarteducation.uni_timetable.service.CalculateRestrictionFunctionValueService;
import ru.itis.smarteducation.uni_timetable.service.CheckRestrictionService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CalculateRestrictionFunctionValueServiceImpl implements CalculateRestrictionFunctionValueService {

    private final CheckRestrictionService checkRestrictionService;
    private final int RESTRICTION_COUNT = 3;

    @Override
    public double calculate(List<PairDto> pairList) {
        double partOfPercent = 100.0 / RESTRICTION_COUNT;
        return 0;
    }
}
