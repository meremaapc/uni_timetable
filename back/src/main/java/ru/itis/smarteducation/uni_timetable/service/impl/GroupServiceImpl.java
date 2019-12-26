package ru.itis.smarteducation.uni_timetable.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.itis.smarteducation.uni_timetable.dto.GroupDto;
import ru.itis.smarteducation.uni_timetable.mapper.PairMapper;
import ru.itis.smarteducation.uni_timetable.repository.GroupRepository;
import ru.itis.smarteducation.uni_timetable.service.GroupService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final PairMapper pairMapper;


    @Override
    public List<GroupDto> findAll() {
        return groupRepository.findAll()
            .stream()
            .map(pairMapper::groupToGroupDto)
            .collect(Collectors.toList());
    }
}
