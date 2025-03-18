package com.giggi.firstproject.repository;

import com.giggi.firstproject.entity.LoginRegistry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRegistryRepository extends JpaRepository<LoginRegistry, Integer> {
}
