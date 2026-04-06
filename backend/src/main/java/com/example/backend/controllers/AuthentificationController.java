package com.example.backend.controllers;

import com.example.backend.entities.AppUser;
import com.example.backend.repository.AppUserRepository;
import com.example.backend.services.AuthentificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthentificationController {



    private final AuthentificationService authentificationService;
    private final AppUserRepository appUserRepository;


    public AuthentificationController(AuthentificationService authentificationService, AppUserRepository appUserRepository) {
        this.authentificationService = authentificationService;
        this.appUserRepository = appUserRepository;
    }

    @PostMapping("/register")
    public ResponseEntity<AppUser> register(@RequestBody AppUser appUser){

       return  new ResponseEntity<>(this.authentificationService.register(appUser), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody AppUser appUser){

        String token = authentificationService.login(appUser.getEmail(), appUser.getPassword());
        AppUser user = appUserRepository.findByEmail(appUser.getEmail())
                .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

        Map<String, Object> response = new HashMap<>();
        response.put("access_token", token);

        Map<String, Object> userInfo = new HashMap<>();
        userInfo.put("username", user.getUsername());
        userInfo.put("email", user.getEmail());
        userInfo.put("role", user.getRole());
        response.put("user", userInfo);

        return ResponseEntity.ok(response);

    }

}
