package com.giggi.firstproject.dao;

import com.giggi.firstproject.entity.Authoritie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthoritieRepository extends JpaRepository<Authoritie, Integer> {
}
