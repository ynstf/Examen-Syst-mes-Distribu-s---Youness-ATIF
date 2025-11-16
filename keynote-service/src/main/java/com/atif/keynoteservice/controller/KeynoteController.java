package com.atif.keynoteservice.controller;

import com.atif.keynoteservice.dto.KeynoteDTO;
import com.atif.keynoteservice.service.KeynoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/keynotes")
@Tag(name = "Keynotes", description = "Gestion des keynotes (speakers)")
public class KeynoteController {

    private final KeynoteService service;

    public KeynoteController(KeynoteService service) {
        this.service = service;
    }

    @GetMapping
    @Operation(summary = "List all keynotes")
    public ResponseEntity<List<KeynoteDTO>> listAll() {
        return ResponseEntity.ok(service.listAll());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get keynote by id")
    public ResponseEntity<KeynoteDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    @Operation(summary = "Create keynote")
    public ResponseEntity<KeynoteDTO> create(@RequestBody KeynoteDTO dto) {
        KeynoteDTO created = service.create(dto);
        return ResponseEntity.created(URI.create("/api/keynotes/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update keynote")
    public ResponseEntity<KeynoteDTO> update(@PathVariable Long id, @RequestBody KeynoteDTO dto) {
        return ResponseEntity.ok(service.update(id, dto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete keynote")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}
