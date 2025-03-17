package com.example.demo.common;

import org.springframework.stereotype.Component;

@Component
public class BaseBallCoach implements Coach {
    @Override
    public String getDailyWorkout() {
        return "Spend 30 minutes on batting practice";
    }

    @Override
    public String getDailyFortune() {
        return "";
    }
}
