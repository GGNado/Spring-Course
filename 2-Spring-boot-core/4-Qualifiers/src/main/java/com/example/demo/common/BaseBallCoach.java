package com.example.demo.common;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component

// Con @Primary indichiamo a Spring di utilizzare questa classe come bean di default
@Primary
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
