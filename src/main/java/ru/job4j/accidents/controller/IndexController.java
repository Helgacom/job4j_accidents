package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accidents.service.AccidentMemService;

@Controller
@AllArgsConstructor
public class IndexController {

    private final AccidentMemService accidentService;

    @GetMapping({"/", "/index"})
    public String getIndex(Model model) {
        model.addAttribute("user", "Ivan Ivanov");
        model.addAttribute("accidents", accidentService.findAll());
        return "index";
    }
}
