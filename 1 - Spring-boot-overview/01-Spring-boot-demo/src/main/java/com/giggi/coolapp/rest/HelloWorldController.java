package com.giggi.coolapp.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

    @Value("${team.name}")
    private String teamName;

    @Value("${coach.name}")
    private String coachName;

    @GetMapping("/hello")
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("/workout")
    public String workout() {
        return "Workout!";
    }

    @GetMapping("/customValue")
    public String customValue() {
        return "Team Name: " + teamName + " Coach Name: " + coachName;
    }


}
