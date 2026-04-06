package com.example.backend.services;

import com.example.backend.dto.BuildingCreateDTO;
import com.example.backend.dto.BuildingDTO;
import com.example.backend.entities.Building;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;

public interface IBuildingService {
    Building getBuildingById(Long id);
    List<Building> getAllBuildings();
    List<Building> getBuildingsByCityId(Long id);
    List<Building> getBuildingsByCityName(String name);
    Building updateBuilding(Long id, Building building);
    void deleteBuilding(Long id);
    List<Building> getBuildingsByCategorieId(Long id);

    void saveBuilding(BuildingCreateDTO dto) throws JsonProcessingException;
    List<BuildingDTO> getAllBuildingsDTO();
    BuildingDTO getBuildingDtoById(Long id);
    List<BuildingDTO> getBuildingsDtoByCityId(Long id);
    List<BuildingDTO> getBuildingsByCategoryId(Long id);
    List<BuildingDTO> getBuildingsDtoByCityName(String cityName);

}
