package com.example.demo;

import org.springframework.stereotype.Component;

// Questa annotazione rende la classe un bean e la rende disponibile per l'iniezione
@Component
public class CricketCoach implements Coach {

    @Override
    public String getDailyWorkout() {
        return "Corsa di 5 km";
    }

    @Override
    public String getDailyFortune() {
        return "";
    }
}
