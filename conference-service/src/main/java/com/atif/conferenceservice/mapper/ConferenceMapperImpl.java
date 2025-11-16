package com.atif.conferenceservice.mapper;


import com.atif.conferenceservice.dto.ConferenceDTO;
import com.atif.conferenceservice.dto.ReviewDTO;
import com.atif.conferenceservice.entity.Conference;
import com.atif.conferenceservice.entity.Review;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConferenceMapperImpl {

    public ConferenceDTO toDto(Conference e) {
        if (e == null) return null;
        ConferenceDTO dto = ConferenceDTO.builder()
                .id(e.getId())
                .titre(e.getTitre())
                .type(e.getType())
                .date(e.getDate())
                .duree(e.getDuree())
                .nbInscrits(e.getNbInscrits())
                .score(e.getScore())
                .keynoteId(e.getKeynoteId())
                .build();
        dto.setReviews(toReviewDtoList(e.getReviews()));
        return dto;
    }

    public Conference toEntity(ConferenceDTO dto) {
        if (dto == null) return null;
        Conference e = Conference.builder()
                .id(dto.getId())
                .titre(dto.getTitre())
                .type(dto.getType())
                .date(dto.getDate())
                .duree(dto.getDuree())
                .nbInscrits(dto.getNbInscrits())
                .score(dto.getScore())
                .keynoteId(dto.getKeynoteId())
                .build();
        return e;
    }

    public void updateEntityFromDto(ConferenceDTO dto, Conference entity) {
        if (dto == null || entity == null) return;
        if (dto.getTitre() != null) entity.setTitre(dto.getTitre());
        if (dto.getType() != null) entity.setType(dto.getType());
        if (dto.getDate() != null) entity.setDate(dto.getDate());
        if (dto.getDuree() != null) entity.setDuree(dto.getDuree());
        if (dto.getNbInscrits() != null) entity.setNbInscrits(dto.getNbInscrits());
        if (dto.getScore() != null) entity.setScore(dto.getScore());
        if (dto.getKeynoteId() != null) entity.setKeynoteId(dto.getKeynoteId());
    }

    public ReviewDTO toReviewDto(Review r) {
        if (r == null) return null;
        return ReviewDTO.builder()
                .id(r.getId())
                .date(r.getDate())
                .texte(r.getTexte())
                .note(r.getNote())
                .build();
    }

    public Review toReviewEntity(ReviewDTO dto) {
        if (dto == null) return null;
        return Review.builder()
                .id(dto.getId())
                .date(dto.getDate())
                .texte(dto.getTexte())
                .note(dto.getNote())
                .build();
    }

    public List<ReviewDTO> toReviewDtoList(List<Review> list) {
        List<ReviewDTO> out = new ArrayList<>();
        if (list != null) for (Review r : list) out.add(toReviewDto(r));
        return out;
    }

    public List<ConferenceDTO> toDtoList(List<Conference> list) {
        List<ConferenceDTO> out = new ArrayList<>();
        if (list != null) for (Conference c : list) out.add(toDto(c));
        return out;
    }
}
