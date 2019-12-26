package ru.itis.smarteducation.uni_timetable.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.smarteducation.uni_timetable.dto.SubjectDto;
import ru.itis.smarteducation.uni_timetable.mapper.CommonMapper;
import ru.itis.smarteducation.uni_timetable.repository.SubjectRepository;
import ru.itis.smarteducation.uni_timetable.service.SubjectService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final CommonMapper commonMapper;


    @Override
    public List<SubjectDto> findAll() {
        return subjectRepository.findAll()
            .stream()
            .map(commonMapper::subjectToSubjectDto)
            .collect(Collectors.toList());
    }
}
