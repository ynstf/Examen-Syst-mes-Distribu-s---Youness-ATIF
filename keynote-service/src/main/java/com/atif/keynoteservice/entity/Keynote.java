package com.atif.keynoteservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "keynotes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Keynote {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // last name
    @Column(nullable = false)
    private String nom;

    // first name
    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false, unique = true)
    private String email;

    private String fonction;
}
