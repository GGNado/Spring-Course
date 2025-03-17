package com.example.demo.common;

import org.springframework.stereotype.Component;

@Component
public class FootballCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Run 5 km";
    }

    @Override
    public String getDailyFortune() {
        return "";
    }
}
