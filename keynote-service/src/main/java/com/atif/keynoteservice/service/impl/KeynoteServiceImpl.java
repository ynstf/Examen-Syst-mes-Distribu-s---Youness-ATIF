package com.atif.keynoteservice.service.impl;
import com.atif.keynoteservice.dto.KeynoteDTO;
import com.atif.keynoteservice.entity.Keynote;
import com.atif.keynoteservice.mapper.KeynoteMapperImpl;
import com.atif.keynoteservice.repository.KeynoteRepository;
import com.atif.keynoteservice.service.KeynoteService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class KeynoteServiceImpl implements KeynoteService {

    private final KeynoteRepository repository;
    private final KeynoteMapperImpl mapper;

    public KeynoteServiceImpl(KeynoteRepository repository, KeynoteMapperImpl mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public KeynoteDTO create(KeynoteDTO dto) {
        repository.findByEmail(dto.getEmail()).ifPresent(k -> {
            throw new IllegalArgumentException("Email already exists: " + dto.getEmail());
        });
        Keynote entity = mapper.toEntity(dto);
        Keynote saved = repository.save(entity);
        return mapper.toDto(saved);
    }

    @Override
    public KeynoteDTO update(Long id, KeynoteDTO dto) {
        Keynote existing = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Keynote not found: " + id));
        mapper.updateEntityFromDto(dto, existing);
        Keynote updated = repository.save(existing);
        return mapper.toDto(updated);
    }

    @Override
    @Transactional(readOnly = true)
    public KeynoteDTO getById(Long id) {
        return repository.findById(id).map(mapper::toDto)
                .orElseThrow(() -> new IllegalArgumentException("Keynote not found: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public List<KeynoteDTO> listAll() {
        return repository.findAll().stream().map(mapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
