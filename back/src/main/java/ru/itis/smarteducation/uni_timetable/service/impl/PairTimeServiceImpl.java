package ru.itis.smarteducation.uni_timetable.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.smarteducation.uni_timetable.dto.PairTimeDto;
import ru.itis.smarteducation.uni_timetable.mapper.PairMapper;
import ru.itis.smarteducation.uni_timetable.repository.PairTimeRepository;
import ru.itis.smarteducation.uni_timetable.service.PairTimeService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PairTimeServiceImpl implements PairTimeService {

    private final PairTimeRepository timeRepository;
    private final PairMapper pairMapper;

    @Override
    public List<PairTimeDto> findAll() {
        return timeRepository.findAll()
            .stream()
            .map(pairMapper::pairTimeToPairTimeDto)
            .collect(Collectors.toList());
    }
}
