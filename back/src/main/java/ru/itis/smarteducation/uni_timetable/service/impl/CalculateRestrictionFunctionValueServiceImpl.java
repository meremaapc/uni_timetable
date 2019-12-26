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

    @Override
    public double calculate(List<PairDto> pairList) {
        long violationCount = pairList.size() - pairList.stream()
            .filter(checkRestrictionService::isTeacherFreeThisPairTimeWeek)
            .count();

        /*
            Процентная доля плохо подобранных дней для преподавателей
         */
        return (double) violationCount / pairList.size();
    }
}
