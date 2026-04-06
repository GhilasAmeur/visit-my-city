package com.example.backend.entities;

import com.example.backend.enums.AppRole;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "utilisateurs")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class AppUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    private String username;
    private String email;
    //@JsonIgnore // quand je retourne un user j'expose pas son mot de passe même hashé mais il sera persister en bdd
    private String password;
    @OneToMany(mappedBy = "user")
    @JsonIgnore // avec ça quand je retourne une ville je ne retourne pas les favoris de user
    private List<Favorite> favorite;
    @Enumerated(EnumType.STRING)
    private AppRole role;

}
