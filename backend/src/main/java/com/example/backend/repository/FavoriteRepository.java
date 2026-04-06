package com.example.backend.repository;
import com.example.backend.entities.AppUser;
import com.example.backend.entities.Building;
import com.example.backend.entities.City;
import com.example.backend.entities.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface FavoriteRepository extends JpaRepository<Favorite, Long> {


    //SELECT COUNT(*) > 0
    //FROM mes_favoris
    //WHERE user_id = ? AND city_id = ?
    // Est-ce que cet utilisateur a déjà ajouté cette ville en favori ?
    boolean existsByUserAndCity(AppUser user, City city);
    boolean existsByUserAndBuilding(AppUser user, Building building);
    Optional<List<Favorite>> findByUser(AppUser user);

}
