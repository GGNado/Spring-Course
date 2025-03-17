package com.giggi.firstproject.security;


import com.giggi.firstproject.dao.AuthoritieRepository;
import com.giggi.firstproject.dao.EmployeeRepository;
import com.giggi.firstproject.entity.Authoritie;
import com.giggi.firstproject.entity.Employee;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(EmployeeRepository employeeRepository,
                                   AuthoritieRepository authoritieRepository,
                                   PasswordEncoder passwordEncoder) {
        return args -> {
            if (employeeRepository.count() == 0) {  // Controllo se il DB è vuoto

                // Creazione utenti
                Employee admin = new Employee(0, "Mario", "Rossi", "admin@example.com",true,
                        passwordEncoder.encode("admin123"), null);
                Employee user = new Employee(0, "Luca", "Verdi", "user@example.com",true,
                        passwordEncoder.encode("user123"), null);
                Employee manager = new Employee(0, "Anna", "Bianchi", "manager@example.com",true,
                        passwordEncoder.encode("manager123"), null);

                // Salvataggio utenti nel database
                admin = employeeRepository.save(admin);
                user = employeeRepository.save(user);
                manager = employeeRepository.save(manager);

                // Creazione ruoli
                Authoritie adminRole = new Authoritie(0, admin.getEmail(), "ROLE_ADMIN", admin);
                Authoritie userRole = new Authoritie(0, user.getEmail(), "ROLE_USER", user);
                Authoritie managerRole = new Authoritie(0, manager.getEmail(), "ROLE_MANAGER", manager);

                // Salvataggio ruoli nel database
                authoritieRepository.saveAll(List.of(adminRole, userRole, managerRole));

                System.out.println("Database inizializzato con 3 utenti e ruoli associati.");
            }
        };
    }
}
