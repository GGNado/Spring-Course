package com.example.demo.rest;

import com.example.demo.common.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    private Coach coach;
    private Coach coach2;

    @Autowired
    // Con @Qualifier possiamo specificare il bean da iniettare
    public DemoController(Coach coach, @Qualifier("footballCoach") Coach coach2) {
        this.coach = coach;
        this.coach2 = coach2;
    }

    @GetMapping("/dailyWorkout")
    public String getDailyWorkout() {
        return "Daily workout: " + coach.getDailyWorkout();
    }
}
