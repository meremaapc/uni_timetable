package ru.itis.smarteducation.uni_timetable.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.smarteducation.uni_timetable.entity.Pair;
import ru.itis.smarteducation.uni_timetable.service.TimetableGenerationService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TimetableController {

    private final TimetableGenerationService timetableGenerationService;

    @GetMapping("/timetable")
    @ResponseBody
    public List<Pair> getTimetable() {
        return timetableGenerationService.placementTeacher();
    }

    @GetMapping("/pairs")
    public String generate() {
        System.out.println("A");
        return "index";
    }
}
