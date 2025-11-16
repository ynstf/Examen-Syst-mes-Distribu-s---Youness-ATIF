package com.atif.conferenceservice.service;

import com.atif.conferenceservice.dto.ConferenceDTO;
import com.atif.conferenceservice.dto.ReviewDTO;
import com.atif.conferenceservice.client.KeynoteDTO;

import java.util.List;

public interface ConferenceService {
    ConferenceDTO create(ConferenceDTO dto);
    ConferenceDTO update(Long id, ConferenceDTO dto);
    ConferenceDTO get(Long id);
    List<ConferenceDTO> list();
    void delete(Long id);

    // Reviews
    ReviewDTO addReview(Long conferenceId, ReviewDTO review);
    List<ReviewDTO> listReviews(Long conferenceId);

    // Call keynote service via Feign
    KeynoteDTO getKeynoteInfo(Long keynoteId);
}
