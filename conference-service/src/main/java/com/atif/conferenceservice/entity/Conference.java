package com.atif.conferenceservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "conferences")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Conference {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String type; // academique / commerciale
    private LocalDate date;
    private Integer duree;
    private Integer nbInscrits;
    private Double score;

    // the keynote (speaker) that holds the conference
    private Long keynoteId;

    @OneToMany(mappedBy = "conference", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<Review> reviews = new ArrayList<>();
}
