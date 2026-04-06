package com.example.backend.dto;

import lombok.Data;

@Data
public class BuildingCreateDTO {
    private String name;
    private String image;
    private String address;
    private String postalCode;
    private String country;
    private String constructionYear;
    private String architect;
    private String style;
    private String description;
    private Integer ticketPrice;
    private String visitDuration;
    private String booking;
    private String accessStatus;
    private boolean accessiblePRM;
    private Double latitude;
    private Double longitude;

    private Object schedules;
    private Object coords;

    private Long cityId;
    private Long categoriesId;
}