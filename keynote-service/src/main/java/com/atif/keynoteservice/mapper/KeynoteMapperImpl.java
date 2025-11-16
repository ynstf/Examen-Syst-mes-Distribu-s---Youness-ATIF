package com.atif.keynoteservice.mapper;

import com.atif.keynoteservice.dto.KeynoteDTO;
import com.atif.keynoteservice.entity.Keynote;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class KeynoteMapperImpl {

    // Convert entity -> DTO
    public KeynoteDTO toDto(Keynote entity) {
        if (entity == null) {
            return null;
        }
        KeynoteDTO dto = KeynoteDTO.builder()
                .id(entity.getId())
                .nom(entity.getNom())
                .prenom(entity.getPrenom())
                .email(entity.getEmail())
                .fonction(entity.getFonction())
                .build();
        return dto;
    }

    // Convert DTO -> entity (new entity)
    public Keynote toEntity(KeynoteDTO dto) {
        if (dto == null) {
            return null;
        }
        Keynote entity = Keynote.builder()
                .id(dto.getId())
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .email(dto.getEmail())
                .fonction(dto.getFonction())
                .build();
        return entity;
    }

    // Optional: update existing entity from DTO (avoid changing id if null)
    public void updateEntityFromDto(KeynoteDTO dto, Keynote entity) {
        if (dto == null || entity == null) return;
        if (dto.getNom() != null) entity.setNom(dto.getNom());
        if (dto.getPrenom() != null) entity.setPrenom(dto.getPrenom());
        if (dto.getEmail() != null) entity.setEmail(dto.getEmail());
        if (dto.getFonction() != null) entity.setFonction(dto.getFonction());
    }

    // map list entity -> list dto
    public List<KeynoteDTO> toDtoList(List<Keynote> entities) {
        if (entities == null) return new ArrayList<>();
        List<KeynoteDTO> list = new ArrayList<>(entities.size());
        for (Keynote e : entities) {
            list.add(toDto(e));
        }
        return list;
    }
}
