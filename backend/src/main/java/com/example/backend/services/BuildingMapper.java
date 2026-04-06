package com.example.backend.services;

import com.example.backend.dto.BuildingDTO;
import com.example.backend.entities.Building;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class BuildingMapper {

    private final ObjectMapper mapper = new ObjectMapper();

    public BuildingDTO toDTO(Building building) {
        if (building == null) return null;

        // --- Coords ---
        BuildingDTO.CoordsDTO coordsDTO = new BuildingDTO.CoordsDTO();
        coordsDTO.setLatitude(building.getLatitude() != null ? building.getLatitude() : 0.0);
        coordsDTO.setLongitude(building.getLongitude() != null ? building.getLongitude() : 0.0);
        coordsDTO.setLatitudeDelta(0.01);
        coordsDTO.setLongitudeDelta(0.01);

        // --- Schedules ---
        BuildingDTO.ScheduleDTO scheduleDTO = new BuildingDTO.ScheduleDTO();
        scheduleDTO.setType("Horaires");
        scheduleDTO.setNote("Fermé le mardi. La dernière admission est 1h avant la fermeture, l'évacuation 30 min avant.");
        scheduleDTO.setOfficialHoursUrl("https://www.louvre.fr/visiter");

        try {
            if (building.getSchedules() != null) {
                BuildingDTO.ScheduleDTO parsed =
                        mapper.readValue(building.getSchedules(), BuildingDTO.ScheduleDTO.class);

                scheduleDTO.setType(parsed.getType());
                scheduleDTO.setDays(parsed.getDays());
                scheduleDTO.setNote(parsed.getNote());
                scheduleDTO.setOfficialHoursUrl(parsed.getOfficialHoursUrl());
            } else {
                scheduleDTO.setDays(new HashMap<>());
            }
        } catch (Exception e) {
            scheduleDTO.setDays(new HashMap<>());
        }

        // --- BuildingDTO ---
        BuildingDTO dto = new BuildingDTO();
        dto.setId(building.getId() != null ? building.getId().toString() : null);
        dto.setCityId(building.getCity() != null ? building.getCity().getId().toString() : null);
        dto.setCity(building.getCity() != null ? building.getCity().getName() : null);
        dto.setCountry(building.getCity() != null ? building.getCity().getCountry() : null);
        dto.setName(building.getName());
        dto.setImage(building.getImage());
        dto.setAddress(building.getAddress());
        dto.setPostalCode(building.getPostalCode());
        dto.setConstructionYear(building.getConstructionYear());
        dto.setArchitect(building.getArchitect());
        dto.setStyle(building.getStyle());
        dto.setDescription(building.getDescription());
        dto.setTicketPrice(building.getTicketPrice());
        dto.setVisitDuration(building.getVisitDuration());
        dto.setBooking(building.getBooking());
        dto.setAccessStatus(building.getAccessStatus());
        dto.setAccessiblePRM(building.isAccessiblePRM());
        dto.setCoords(coordsDTO);
        dto.setSchedules(scheduleDTO);

        return dto;
    }
}