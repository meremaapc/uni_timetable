package ru.itis.smarteducation.uni_timetable.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.itis.smarteducation.uni_timetable.dto.TimetableDto;
import ru.itis.smarteducation.uni_timetable.service.TimetableGenerationService;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimetableGenerationServiceImplTest {

    @Autowired
    private TimetableGenerationService timetableGenerationService;

    @Test
    public void placementTeachersTest() {
        TimetableDto timetable = timetableGenerationService.generate();
        assertNotNull(timetable);
        assertNotEquals(timetable.getPairList().size(), 0);
    }
}