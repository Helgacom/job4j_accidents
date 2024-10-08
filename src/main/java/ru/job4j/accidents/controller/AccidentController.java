package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@AllArgsConstructor
@RequestMapping("/accidents")
public class AccidentController {

    private final AccidentJdbcService accidentService;
    private final AccidentTypeJdbcService typesService;
    private final RuleJdbcService rulesService;

    @GetMapping("/createAccident")
    public String viewCreateAccident(Model model) {
        model.addAttribute("types", typesService.findAll());
        model.addAttribute("rules", rulesService.findAll());
        return "accidents/createAccident";
    }

    @PostMapping("/saveAccident")
    public String save(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("rIds");
        accidentService.save(accident);
        return "redirect:/index";
    }

    @GetMapping("/editAccident")
    public String update(@RequestParam("id") Long id, Model model) {
        var accident = accidentService.findById(id);
        if (accident.isEmpty()) {
            model.addAttribute("message", "Происшествие с указанным идентификатором не найдено");
            return "error/error";
        }
        model.addAttribute("accident", accident.get());
        return "editAccident";
    }

    @PostMapping("/updateAccident")
    public String update(@ModelAttribute Accident accident, Model model) {
        var isUpdated = accidentService.update(accident);
        if (!isUpdated) {
            model.addAttribute("message", "Происшествие с указанным идентификатором не найдено");
            return "error/error";
        }
        return "redirect:/index";
    }
}