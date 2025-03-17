package com.example.demo.common;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component

// Con @Lazy il bean viene creato solo quando viene richiesto, quindi non viene creato subito all'avvio dell'applicazione
@Lazy
public class FootballCoach implements Coach {

    public FootballCoach() {
        System.out.println("FootballCoach: inside default constructor");
    }

    @Override
    public String getDailyWorkout() {
        return "Run 5 km";
    }

    @Override
    public String getDailyFortune() {
        return "";
    }
}
