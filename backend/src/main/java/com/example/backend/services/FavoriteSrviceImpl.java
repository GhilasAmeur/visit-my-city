package com.example.backend.services;

import com.example.backend.entities.AppUser;
import com.example.backend.entities.Building;
import com.example.backend.entities.City;
import com.example.backend.entities.Favorite;
import com.example.backend.exceptions.BuildingExistInFavoriesException;
import com.example.backend.exceptions.BuildingNotFoundException;
import com.example.backend.exceptions.CityExistInFavoriesException;
import com.example.backend.repository.AppUserRepository;
import com.example.backend.repository.CityRepository;
import com.example.backend.repository.FavoriteRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class FavoriteSrviceImpl implements IFavoriteService{


private final CityServiceImpl cityService;
private final FavoriteRepository favoriteRepository;
private final BuildingServiceImpl buildingService;

    public FavoriteSrviceImpl(CityServiceImpl cityService, FavoriteRepository favoriteRepository, BuildingServiceImpl buildingService) {

        this.cityService = cityService;
        this.favoriteRepository = favoriteRepository;
        this.buildingService = buildingService;
    }

    @Override
    public void addCityToFavorite(AppUser user, Long city_id) {

        City city = cityService.getCityById(city_id);
        if(favoriteRepository.existsByUserAndCity(user, city)){
            throw new CityExistInFavoriesException("Ville existe déjà dans les favoris");
        }
        Favorite fCity = new Favorite();
        fCity.setCity(city);
        fCity.setUser(user);
       favoriteRepository.save(fCity);
    }

    @Override
    public void addBuildingToFavorite(AppUser user, Long building_id) {

        Building building = buildingService.getBuildingById(building_id);
        if(favoriteRepository.existsByUserAndBuilding(user, building)){
            throw new BuildingExistInFavoriesException("Batiment existe déjà dans les favoris");
        }
        Favorite fBuilding = new Favorite();
        fBuilding.setBuilding(building);
        fBuilding.setUser(user);
        favoriteRepository.save(fBuilding);
    }


    //distinct pour éviter les doublons, récupérer que les villes unique
    //voici un beug, ici quand j'ajoute une ville au favoris, ca se passe bien , aucun problème, mais si cette utilisateur
    // essaie d'ajouter un batiment, le batiment sera ajouter comme c affichier dans la console
    // fav : com.example.backend.entities.Favorite@15e49f8b par contre
    //car quand j'ajoute une batiment ville est null,
    //donc oubliger de filtrer et renvoyer que les villes qui ne sont pas null
    //  System.out.println("mes favoris " + favorites);
    //        for(Favorite fav : favorites){
    //            System.out.println("fav : "+fav);
    //        }
    // for(City city : cities){
    //            System.out.println("city "+city);
    //        }
    @Override
    public List<City> getFavoriteCities(AppUser user) {

        List<Favorite> favorites = this.favoriteRepository.findByUser(user).orElseThrow(() ->new RuntimeException("cette utilisateur n'à rien dans ces favoris"));
        List<City>  cities =  favorites.stream()
                 .map(favorite -> favorite.getCity())
                .filter(city -> city!= null).toList();

        return cities;
    }



    @Override
    public List<Building> getFavoriteBuildings(AppUser user) {

        List<Favorite> favorites = this.favoriteRepository.findByUser(user).orElseThrow(() ->new RuntimeException("cette utilisateur n'à rien dans ces favoris"));
        List<Building>  buildings =  favorites.stream()
                .map(favorite -> favorite.getBuilding())
                .filter(building -> building != null).toList();
        return buildings;
    }
}
