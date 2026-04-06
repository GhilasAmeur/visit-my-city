package com.example.backend.controllers;

import com.example.backend.dto.BuildingCreateDTO;
import com.example.backend.dto.BuildingDTO;
import com.example.backend.entities.Building;
import com.example.backend.services.BuildingServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/building") //prends le JSON envoyer par le client et transforme le en objet Java
public class BuildingController {

    private final BuildingServiceImpl buildingServiceImpl;


    public BuildingController(BuildingServiceImpl buildingServiceImpl) {
        this.buildingServiceImpl = buildingServiceImpl;

    }
//-------------------------------------Ici building dto-----------------------------------------------------

@GetMapping("/buildingsdto")
   public ResponseEntity<List<BuildingDTO>> getAllBuildingsDTO() {
     return new ResponseEntity<>(
             this.buildingServiceImpl.getAllBuildingsDTO(), HttpStatus.OK);
    }


@GetMapping("/buildingdto/{id}")
public ResponseEntity<BuildingDTO> getBDTOById(@PathVariable Long id){

        return new ResponseEntity<>(this.buildingServiceImpl.getBuildingDtoById(id), HttpStatus.OK);
}

@GetMapping("/buildingsdto/city/{id}")
public ResponseEntity<List<BuildingDTO>>
getBuildingsDtoByCityId(@PathVariable Long id){
return new ResponseEntity<>(this.buildingServiceImpl.getBuildingsDtoByCityId(id), HttpStatus.OK);
}

@GetMapping("/buildingsdto/category/{id}")
public ResponseEntity<List<BuildingDTO>> getBuildingsDtoByCategorieId(@PathVariable Long id){
    return new ResponseEntity<>(this.buildingServiceImpl.getBuildingsByCategoryId(id),HttpStatus.OK);
}

    @GetMapping("/buildingdto/cityname/{name}")
    public ResponseEntity<List<BuildingDTO>> getBuildingsDtoByCityName(@PathVariable String name){
        return new ResponseEntity<>(this.buildingServiceImpl.getBuildingsDtoByCityName(name), HttpStatus.OK);
    }



    //-------------------------------------Ici building tout cours-----------------------------------------------------
    @PostMapping("/add/add")
    public ResponseEntity<Void> addBuilding(@RequestBody BuildingCreateDTO dto) throws JsonProcessingException {
        this.buildingServiceImpl.saveBuilding(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/buildings")
    public ResponseEntity<List<Building>> getAllBuildings(){
        return new ResponseEntity<>(this.buildingServiceImpl.getAllBuildings(), HttpStatus.OK);
    }

    @GetMapping("/city/{id}")
    public ResponseEntity<List<Building>> getBuildingsByCityId(@PathVariable Long id){
        return new ResponseEntity<>(this.buildingServiceImpl.getBuildingsByCityId(id), HttpStatus.OK);
    }

    @GetMapping("/cityname/{name}")
    public ResponseEntity<List<Building>> getBuildingsByCityName(@PathVariable String name){
        return new ResponseEntity<>(this.buildingServiceImpl.getBuildingsByCityName(name), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBuilding(@PathVariable Long id){
        this.buildingServiceImpl.deleteBuilding(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Building> getBuildingById(@PathVariable Long id){
        return new ResponseEntity<>(this.buildingServiceImpl.getBuildingById(id),HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Building> updateBuilding(@PathVariable Long id, @RequestBody Building building){
        return new ResponseEntity<>(this.buildingServiceImpl.updateBuilding(id,building), HttpStatus.OK);
    }

    @GetMapping("/categorie/{id}")
    public ResponseEntity<List<Building>> getBuildingsByCategorieId(@PathVariable Long id){
        return new ResponseEntity<>(this.buildingServiceImpl.getBuildingsByCategorieId(id),HttpStatus.OK );
    }

}
