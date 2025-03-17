package com.example.demo.common;

import org.springframework.stereotype.Component;

// Questa annotazione rende la classe un bean e la rende disponibile per l'iniezione
@Component
public class CricketCoach implements Coach {

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes";
    }

    @Override
    public String getDailyFortune() {
        return "";
    }
}
