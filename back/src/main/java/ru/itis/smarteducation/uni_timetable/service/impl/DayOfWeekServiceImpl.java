package ru.itis.smarteducation.uni_timetable.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.smarteducation.uni_timetable.dto.DayOfWeekDto;
import ru.itis.smarteducation.uni_timetable.mapper.CommonMapper;
import ru.itis.smarteducation.uni_timetable.repository.DayOfWeekRepository;
import ru.itis.smarteducation.uni_timetable.service.DayOfWeekService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DayOfWeekServiceImpl implements DayOfWeekService {

    private final DayOfWeekRepository dayOfWeekRepository;
    private final CommonMapper commonMapper;


    @Override
    public List<DayOfWeekDto> findAll() {
        return dayOfWeekRepository.findAll()
            .stream()
            .map(commonMapper::dayOfWeekToDayOfWeekDto)
            .collect(Collectors.toList());
    }
}
