package com.example.backend.services;

import com.example.backend.entities.AppUser;
import com.example.backend.enums.AppRole;
import com.example.backend.repository.AppUserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


public class AppUserDetailsService implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    public AppUserDetailsService(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        AppUser user = appUserRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("Email n'existe pas"));

        return User.withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(user.getRole().name()).build();

    }
}
