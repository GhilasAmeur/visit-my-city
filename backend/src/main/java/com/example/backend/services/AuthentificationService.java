package com.example.backend.services;

import com.example.backend.entities.AppUser;
import com.example.backend.enums.AppRole;
import com.example.backend.exceptions.AuthentificationNotFoundException;
import com.example.backend.repository.AppUserRepository;
import com.example.backend.security.UserLoader;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthentificationService {

    private final AppUserRepository appUserRepository;
    private final PasswordEncoder passwordEncoder;
    public final AuthenticationManager authenticationManager;
    private final UserLoader userLoader;
    private final JwtService jwtService;

    public AuthentificationService(AppUserRepository appUserRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, UserLoader userLoader, JwtService jwtService) {
        this.appUserRepository = appUserRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.userLoader = userLoader;
        this.jwtService = jwtService;
    }

    public AppUser register(AppUser appUser){
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
        appUser.setRole(AppRole.ROLE_VISITEUR);
        return this.appUserRepository.save(appUser);
    }

    public String login(String email, String password){

try{
    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));

    UserDetails userDetails = userLoader.loadUserByUsername(email);
    return jwtService.generateToken(userDetails);
}catch(UsernameNotFoundException | BadCredentialsException e  ){
    throw new AuthentificationNotFoundException(e.getMessage());
}


    }
}
