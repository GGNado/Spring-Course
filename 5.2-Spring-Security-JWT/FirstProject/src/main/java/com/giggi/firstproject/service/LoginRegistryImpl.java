package com.giggi.firstproject.service;

import com.giggi.firstproject.entity.LoginRegistry;
import com.giggi.firstproject.repository.LoginRegistryRepository;
import com.giggi.firstproject.service.def.LoginRegistryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginRegistryImpl implements LoginRegistryService {

    private LoginRegistryRepository loginRegistryRepository;

    @Autowired
    public LoginRegistryImpl(LoginRegistryRepository loginRegistryRepository) {
        this.loginRegistryRepository = loginRegistryRepository;
    }

    @Override
    public void registerLogin(LoginRegistry loginRegistry) {
        loginRegistryRepository.save(loginRegistry);
    }
}
