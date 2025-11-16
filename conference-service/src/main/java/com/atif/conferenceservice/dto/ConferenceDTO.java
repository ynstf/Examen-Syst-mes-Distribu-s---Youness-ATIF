package com.atif.conferenceservice.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
public class ConferenceDTO {
    private Long id;
    private String titre;
    private String type;
    private LocalDate date;
    private Integer duree;
    private Integer nbInscrits;
    private Double score;
    private Long keynoteId;
    private List<ReviewDTO> reviews;
}
