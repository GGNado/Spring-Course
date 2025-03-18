package com.giggi.firstproject.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data


@Entity
@Table(name = "login_registries")
public class LoginRegistry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "date")
    private String date;

    @Column(name = "time")
    private String time;

    @Column(name = "ip")
    private String ip;

    @Column(name = "success")
    private boolean success;

}
