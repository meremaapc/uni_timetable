package ru.itis.smarteducation.uni_timetable.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.smarteducation.uni_timetable.dto.AuditoryDto;
import ru.itis.smarteducation.uni_timetable.mapper.PairMapper;
import ru.itis.smarteducation.uni_timetable.repository.AuditoryRepository;
import ru.itis.smarteducation.uni_timetable.service.AuditoryService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuditoryServiceImpl implements AuditoryService {

    private final AuditoryRepository auditoryRepository;
    private final PairMapper pairMapper;


    @Override
    public List<AuditoryDto> findAll() {
        return auditoryRepository.findAll()
            .stream()
            .map(pairMapper::auditoryToAuditoryDto)
            .collect(Collectors.toList());
    }
}
