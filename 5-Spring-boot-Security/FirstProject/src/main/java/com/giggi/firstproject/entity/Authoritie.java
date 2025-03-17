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
