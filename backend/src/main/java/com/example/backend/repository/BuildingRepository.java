package com.example.backend.repository;

import com.example.backend.entities.Building;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BuildingRepository extends JpaRepository<Building, Long> {

    List<Building> findByCity_Id(Long id);
    List<Building> findByCity_Name(String name);
    List<Building> findByCategories_Id(Long id);
    List<Building> getBuildingsDtoByCityName(Long id);
}
