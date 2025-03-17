package com.example.demo.common;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.apache.naming.factory.BeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
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
