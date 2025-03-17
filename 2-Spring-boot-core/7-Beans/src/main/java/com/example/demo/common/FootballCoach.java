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

// Con @Lazy il bean viene creato solo quando viene richiesto, quindi non viene creato subito all'avvio dell'applicazione
@Lazy

// Con @Scope possiamo specificare lo scope del bean, di base è singleton quidi viene creato un'unica istanza
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
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

    // Questo metodo viene eseguito dopo la creazione del bean
    @PostConstruct
    public void doMyStartupStuff() {
        System.out.println("FootballCoach: inside doMyStartupStuff");
    }

    // Questo metodo viene eseguito prima della distruzione del bean
    @PreDestroy
    public void doMyCleanupStuff() {
        System.out.println("FootballCoach: inside doMyCleanupStuff");
    }
}
