package ru.itis.smarteducation.uni_timetable.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.itis.smarteducation.uni_timetable.dto.PairDto;
import ru.itis.smarteducation.uni_timetable.service.TimetableGenerationService;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class TimetableController {

    private final TimetableGenerationService timetableGenerationService;

    @GetMapping("/timetable")
    @ResponseBody
    public List<PairDto> getTimetable() {
        return timetableGenerationService.generate().getPairList();
    }

    @GetMapping("/pairs")
    public String generate() {
        return "index";
    }
}
