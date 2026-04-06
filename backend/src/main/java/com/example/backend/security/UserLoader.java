package com.example.backend.security;

import com.example.backend.entities.AppUser;
import com.example.backend.repository.AppUserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserLoader implements UserDetailsService {

    private final AppUserRepository appUserRepository;

    public UserLoader(AppUserRepository appUserRepository) {
        this.appUserRepository = appUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        AppUser appUser = appUserRepository.findByEmail(email).orElseThrow(() ->
                new UsernameNotFoundException("Email invalide"));

       return new User(appUser.getEmail(),
               appUser.getPassword(),
               List.of(new SimpleGrantedAuthority(appUser.getRole().name())
               ));
    }
}
