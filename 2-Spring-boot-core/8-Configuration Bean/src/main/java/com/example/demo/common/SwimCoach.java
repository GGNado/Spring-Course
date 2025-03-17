package com.example.demo.common;

import org.springframework.stereotype.Component;

// Non usiamo @Component per creare un bean, ma lo creiamo manualmente
public class SwimCoach implements Coach{

    // Solo per dimostrare che il costruttore viene chiamato
    public SwimCoach() {
        System.out.println("SwimCoach: inside default constructor");
    }

    @Override
    public String getDailyWorkout() {
        return "Swim 1 km";
    }

    @Override
    public String getDailyFortune() {
        return "";
    }
}
