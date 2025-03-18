⸻

Spring Boot Security - Users & Authorities

📌 Descrizione

Questo progetto utilizza Spring Security per gestire autenticazione e autorizzazione basandosi sulle tabelle standard `users` e `authorities`, richieste da Spring Security.
Gli utenti possono accedere alle risorse protette utilizzando email e password, senza bisogno di una classe `UserDetailsService` personalizzata.
Le credenziali vengono verificate direttamente dal `JdbcUserDetailsManager` di Spring Security.

⸻

🚀 Tecnologie Utilizzate
•	Spring Boot
•	Spring Security
•	Spring Data JPA
•	MySQL
•	Jakarta Persistence API (JPA)
•	Lombok
•	BCrypt (per l’hashing delle password)

⸻

📂 Struttura del Database

Spring Security si aspetta le seguenti due tabelle per autenticare e autorizzare gli utenti:

## 1️⃣ Tabella `users`

Contiene le credenziali degli utenti.

| Campo    | Tipo  | Descrizione                        |
|----------|-------|------------------------------------|
| id       | INT   | (PK) Identificativo univoco dell’utente |
| username | VARCHAR | L’email usata per il login         |
| password | VARCHAR | Password criptata (BCrypt)         |
| enabled  | BOOLEAN | Indica se l’utente è attivo        |

## Entità `Employee.java`

```java
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString

@Entity
@Table(name = "users")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(name = "username", nullable = false)
    private String email;

    @Column(name = "enabled", nullable = false)
    private boolean enabled;

    @Column(name = "password", nullable = false)
    private String password;

    // RELAZIONE 1-N: un Employee può avere molte Authorities
    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Authoritie> authorities;
}
```

⚠️ **Nota Bene** ⚠️
- Stiamo usando la configuraione standard di spring security, quindi non abbiamo bisogno di un `UserDetailsService` personalizzato.
- Spring Security si aspetta che le tabelle siano chiamate `users` e `authorities`.


## 2️⃣ Tabella `authorities`

Contiene i ruoli assegnati agli utenti.

| Campo     | Tipo    | Descrizione                                             |
|-----------|---------|---------------------------------------------------------|
| id        | INT     | (PK) Identificativo univoco del ruolo                   |
| user_id   | INT     | (FK) Collega l’utente alla tabella `users`              |
| username  | VARCHAR | L'username dell'utente che deve corrispondere all'altro |
| authority | VARCHAR | Il ruolo dell’utente (es. ROLE_ADMIN)                   |

Entità `Authority.java`

```java
package com.giggi.firstproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString
@Entity
@Table(name = "authorities")
public class Authoritie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "authority", nullable = false)
    private String authority;


    // RELAZIONE N-1: molte Authoritie si riferiscono a un singolo Employee
    @ManyToOne
    @JoinColumn(name = "users_id")
    @JsonIgnore
    private Employee users;
}
```

⚠️ **Nota Bene** ⚠️
- Stiamo usando la configuraione standard di spring security, quindi non abbiamo bisogno di un `UserDetailsService` personalizzato.
- Spring Security si aspetta che le tabelle siano chiamate `users` e `authorities`.

⸻

🔐 Configurazione di Spring Security

Spring Security utilizza la configurazione predefinita con il `JdbcUserDetailsManager` per autenticare gli utenti da `users` e `authorities`.
Praticamente prende i dati direttamente dal database per verificare le credenziali.
🔹 Configurazione di `SecurityConfig.java`

```java
@Configuration
public class DemoSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers(HttpMethod.GET, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.GET, "/api/employees/**").hasRole("EMPLOYEE")
                        .requestMatchers(HttpMethod.POST, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.PUT, "/api/employees").hasRole("MANAGER")
                        .requestMatchers(HttpMethod.DELETE, "/api/employees/**").hasRole("ADMIN")
        );

        http.httpBasic(Customizer.withDefaults());

        // In generale non è richiesto per le API REST
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }
}
```

⸻

📥 Inserimento Utenti e Ruoli nel Database

Per testare l’autenticazione, possiamo pre-popolare il database con alcuni utenti e ruoli.

CommandLineRunner per Popolare il Database

```java
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
```

⸻

🔑 Autenticazione

Spring Security gestisce l’autenticazione direttamente utilizzando:
•	L’email (username) come identificativo
•	BCrypt per l’hashing delle password
•	Il database MySQL per verificare username/password

Per accedere a un’API protetta, è necessario inviare una richiesta HTTP con Basic Authentication o gestire l’autenticazione tramite Spring Security login form.

Esempio di autenticazione con Postman

```
GET http://localhost:8080/api/protected-resource
Authorization: Basic admin@example.com:admin123
```

Se l’autenticazione ha successo, l’utente riceve accesso alla risorsa protetta.

⸻

🔐 Autorizzazione e Protezione delle API

Per limitare l’accesso alle risorse in base al ruolo, possiamo configurare un filtro di sicurezza in Spring Boot.
(come sopra)
Esempio:

```java
@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/admin/**").hasRole("ADMIN")
            .requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
            .anyRequest().authenticated()
        )
        .formLogin(withDefaults())
        .logout(withDefaults());
    return http.build();
}
```

⸻

📌 Conclusione

✅ Spring Security autentica gli utenti utilizzando le tabelle `users` e `authorities`.
✅ Non abbiamo bisogno di un `UserDetailsService` personalizzato perché utilizziamo la configurazione predefinita di Spring Security con `JdbcUserDetailsManager`.
✅ Gli utenti si autenticano con email e password e possono accedere solo alle risorse autorizzate in base ai ruoli.
✅ Le password sono crittografate con BCrypt per maggiore sicurezza.

⸻

📚 Possibili miglioramenti futuri
•	✅ Aggiungere un sistema di JWT Authentication per l’autenticazione senza stato.
•	✅ Creare una registrazione utente con form e validazioni.
•	✅ Implementare un sistema di recupero password.

⸻
```