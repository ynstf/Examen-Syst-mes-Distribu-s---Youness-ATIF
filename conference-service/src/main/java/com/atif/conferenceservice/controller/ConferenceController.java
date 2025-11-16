package com.atif.conferenceservice.controller;



import com.atif.conferenceservice.client.KeynoteDTO;
import com.atif.conferenceservice.dto.ConferenceDTO;
import com.atif.conferenceservice.dto.ReviewDTO;
import com.atif.conferenceservice.service.ConferenceService;
//import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/conferences")
//@Tag(name = "Conferences")
public class ConferenceController {

    private final ConferenceService service;

    public ConferenceController(ConferenceService service) {
        this.service = service;
    }

    @GetMapping
    public List<ConferenceDTO> list() { return service.list(); }

    @GetMapping("/{id}")
    public ConferenceDTO get(@PathVariable Long id) { return service.get(id); }

    @PostMapping
    public ResponseEntity<ConferenceDTO> create(@RequestBody ConferenceDTO dto) {
        ConferenceDTO created = service.create(dto);
        return ResponseEntity.created(URI.create("/api/conferences/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ConferenceDTO update(@PathVariable Long id, @RequestBody ConferenceDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Reviews
    @PostMapping("/{id}/reviews")
    public ResponseEntity<ReviewDTO> addReview(@PathVariable Long id, @RequestBody ReviewDTO dto) {
        ReviewDTO r = service.addReview(id, dto);
        return ResponseEntity.created(URI.create("/api/conferences/" + id + "/reviews/" + r.getId())).body(r);
    }

    @GetMapping("/{id}/reviews")
    public List<ReviewDTO> listReviews(@PathVariable Long id) {
        return service.listReviews(id);
    }

    // get keynote info for a conference
    @GetMapping("/{id}/keynote")
    public KeynoteDTO getKeynote(@PathVariable Long id) {
        ConferenceDTO conf = service.get(id);
        Long keynoteId = conf.getKeynoteId();
        if (keynoteId == null) throw new RuntimeException("No keynoteId linked to conference");
        return service.getKeynoteInfo(keynoteId);
    }
}
