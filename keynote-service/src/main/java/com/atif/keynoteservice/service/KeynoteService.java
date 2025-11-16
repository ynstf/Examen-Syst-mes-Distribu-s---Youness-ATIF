package com.atif.keynoteservice.service;

import com.atif.keynoteservice.dto.KeynoteDTO;

import java.util.List;

public interface KeynoteService {
    KeynoteDTO create(KeynoteDTO dto);
    KeynoteDTO update(Long id, KeynoteDTO dto);
    KeynoteDTO getById(Long id);
    List<KeynoteDTO> listAll();
    void delete(Long id);
}
