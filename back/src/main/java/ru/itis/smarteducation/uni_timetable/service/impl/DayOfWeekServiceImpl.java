package ru.itis.smarteducation.uni_timetable.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.smarteducation.uni_timetable.dto.DayOfWeekDto;
import ru.itis.smarteducation.uni_timetable.dto.SubjectDto;
import ru.itis.smarteducation.uni_timetable.entity.DayOfWeek;
import ru.itis.smarteducation.uni_timetable.mapper.PairMapper;
import ru.itis.smarteducation.uni_timetable.repository.DayOfWeekRepository;
import ru.itis.smarteducation.uni_timetable.repository.SubjectRepository;
import ru.itis.smarteducation.uni_timetable.service.DayOfWeekService;
import ru.itis.smarteducation.uni_timetable.service.SubjectService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DayOfWeekServiceImpl implements DayOfWeekService {

    private final DayOfWeekRepository dayOfWeekRepository;
    private final PairMapper pairMapper;


    @Override
    public List<DayOfWeekDto> findAll() {
        return dayOfWeekRepository.findAll()
            .stream()
            .map(pairMapper::dayOfWeekToDayOfWeekDto)
            .collect(Collectors.toList());
    }
}
