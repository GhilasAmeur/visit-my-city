package com.example.backend.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "batiment")
public class Building {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

       // private String cityId;
        private String name;
        private String image;
        private String address;
        private String postalCode;
       // private String city;
        private String country;
        private String constructionYear;
        private String architect;
        private String style;

        @Column(columnDefinition = "TEXT")
        private String description;

        private Integer ticketPrice;
        private String visitDuration;
        private String booking;
        private String accessStatus;
        private boolean accessiblePRM;
        private Double latitude;
        private Double longitude;

//type TEXT pour pouvoir unjson de plusieursoctects jusqau 65ko, c nn un varchar c que 255 char
        @Column(columnDefinition = "TEXT")
        private String schedules;

        @Column(columnDefinition = "TEXT")
        private String coords;

        @ManyToOne
        @JoinColumn(name = "city_id")
        private City city;
        @ManyToOne//bcp de batiement appartiennt a une seul categorie, lors de creation de batiement je donne la categorie
        @JsonIgnore
        private Category categories;
        @OneToMany(mappedBy = "building")
        @JsonIgnore
        private List<Favorite> favorites;


}

