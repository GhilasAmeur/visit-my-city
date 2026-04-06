package com.example.backend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ville")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String name;

    @Column(name = "pays")
    private String country;

    private String description;

    @Column(name = "code_postal")
    private String postalCode;
    @Column(name = "image_url")
    private String image ;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL) //ici cascade All car quand je supprime une ville tout les batiment vont etre supprimer
    @JsonIgnore
    private List<Building> buildings;
    @OneToMany(mappedBy = "city")
    @JsonIgnore
    private List<Favorite> favorites;


}
