package com.example.backend.controllers;
import com.example.backend.entities.AppUser;
import com.example.backend.entities.Building;
import com.example.backend.entities.City;
import com.example.backend.repository.AppUserRepository;
import com.example.backend.services.FavoriteSrviceImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoriteController {

    private final FavoriteSrviceImpl favoriteSrvice;

    private final AppUserRepository appUserRepository;

    public FavoriteController(FavoriteSrviceImpl favoriteSrvice, AppUserRepository appUserRepository) {
        this.favoriteSrvice = favoriteSrvice;
        this.appUserRepository = appUserRepository;
    }


    //ici c'est faux car spring lors de l'authentification nous renvoi un userDetails
    //donc quand je met AppUser ce n'est pas ca quon recuperer car @AuthenticationPrincipal nous donne le user connecter or
    //ce nest pas un AppUser c'est un UserDetails donc ca devient :
    /*
    @PostMapping("/cites/{city_id}")
    public void addCityToFavorite(@PathVariable Long city_id, @AuthenticationPrincipal AppUser user){
        System.out.println("mon user " + user);
        this.favoriteSrvice.addCityToFavorite(user, city_id);
    }
     */
    @PostMapping("/cites/add/{city_id}")
    public void addCityToFavorite(@PathVariable Long city_id, @AuthenticationPrincipal UserDetails userDetails){
        System.out.println("Mon user " + userDetails);
        AppUser user = appUserRepository.findByEmail(userDetails.getUsername()).
                orElseThrow(() -> new UsernameNotFoundException("Utilisateur n'exisite pas"));
        this.favoriteSrvice.addCityToFavorite(user, city_id);
    }

    @PostMapping("/buildings/add/{building_id}")
    public void addBuildingToFavorite(@PathVariable Long building_id, @AuthenticationPrincipal UserDetails userDetails){
       // System.out.println("Mon user " + userDetails);
        AppUser user = appUserRepository.findByEmail(userDetails.getUsername()).
                orElseThrow(() -> new UsernameNotFoundException("Utilisateur n'exisite pas."));
        this.favoriteSrvice.addBuildingToFavorite(user, building_id);
    }

    @GetMapping("/cities")
    public List<City> getFavoriteCities(@AuthenticationPrincipal UserDetails userDetails){
        AppUser user = appUserRepository.findByEmail(userDetails.getUsername()).
                orElseThrow(() ->new UsernameNotFoundException("Utilisateur n'existe pas."));

        return this.favoriteSrvice.getFavoriteCities(user);
    }

    @GetMapping("/buildings")
    public List<Building> getFavoriteBuildings(@AuthenticationPrincipal UserDetails userDetails){
        AppUser user = appUserRepository.findByEmail(userDetails.getUsername()).
                orElseThrow(() -> new UsernameNotFoundException("Utilisateur n'existe pas."));

        return this.favoriteSrvice.getFavoriteBuildings(user);
    }



}
