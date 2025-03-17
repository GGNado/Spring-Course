package com.example.demo.rest;

import com.example.demo.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private Coach coach;

    // Autowired è un'annotazione che dice a Spring di iniettare una dipendenza
    // Opzionale se c'è un solo costruttore
    @Autowired
    public DemoController(Coach coach) {
        this.coach = coach;
    }

    @GetMapping("/dailyWorkout")
    public String getDailyWorkout() {
        return coach.getDailyWorkout();
    }
}
