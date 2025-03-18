package com.giggi.firstproject.dto;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class LoginResponse {
    private String token;
    private String username;
    private String message;
    private int tokenExpiration;
    private List<String> roles;


    public LoginResponse(String token, String username, List<String> roles, int tokenExpiration) {
        this.token = token;
        this.username = username;
        this.roles = roles;
        this.tokenExpiration = tokenExpiration;

        int hours = (tokenExpiration / (1000 * 60 * 60));
        int minutes = (tokenExpiration / (1000 * 60)) % 60;

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        this.message = "L'utente " + this.username + " ha effettuato il login con successo alle " + LocalDateTime.now().format(formatter) +
                        ", la sessione scadrà alle " + LocalDateTime.now().plusHours(hours).plusMinutes(minutes).format(formatter);
    }
}
