package com.example.demo.config;

import com.example.demo.common.Coach;
import com.example.demo.common.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Questa annotazione rende la classe una classe di configurazione
@Configuration
public class SportConfig {

    // Configurazione i bean
    @Bean
    // Fai questa cosa quando hai bisogno di un classe di un jar esterno
    public Coach acquatic() {
        return new SwimCoach();
    }
}
