package com.atif.conferenceservice.service.impl;



import com.atif.conferenceservice.client.KeynoteClient;
import com.atif.conferenceservice.client.KeynoteDTO;
import com.atif.conferenceservice.dto.ConferenceDTO;
import com.atif.conferenceservice.dto.ReviewDTO;
import com.atif.conferenceservice.entity.Conference;
import com.atif.conferenceservice.entity.Review;
import com.atif.conferenceservice.mapper.ConferenceMapperImpl;
import com.atif.conferenceservice.repository.ConferenceRepository;
import com.atif.conferenceservice.repository.ReviewRepository;
import com.atif.conferenceservice.service.ConferenceService;
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class ConferenceServiceImpl implements ConferenceService {

    private final ConferenceRepository confRepo;
    private final ReviewRepository reviewRepo;
    private final ConferenceMapperImpl mapper;
    private final KeynoteClient keynoteClient;

    public ConferenceServiceImpl(ConferenceRepository confRepo,
                                 ReviewRepository reviewRepo,
                                 ConferenceMapperImpl mapper,
                                 KeynoteClient keynoteClient) {
        this.confRepo = confRepo;
        this.reviewRepo = reviewRepo;
        this.mapper = mapper;
        this.keynoteClient = keynoteClient;
    }

    @Override
    public ConferenceDTO create(ConferenceDTO dto) {
        Conference e = mapper.toEntity(dto);
        Conference saved = confRepo.save(e);
        return mapper.toDto(saved);
    }

    @Override
    public ConferenceDTO update(Long id, ConferenceDTO dto) {
        Conference e = confRepo.findById(id).orElseThrow(() -> new RuntimeException("Conference not found"));
        mapper.updateEntityFromDto(dto, e);
        Conference saved = confRepo.save(e);
        return mapper.toDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public ConferenceDTO get(Long id) {
        return confRepo.findById(id).map(mapper::toDto).orElseThrow(() -> new RuntimeException("Conference not found"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<ConferenceDTO> list() {
        return mapper.toDtoList(confRepo.findAll());
    }

    @Override
    public void delete(Long id) {
        confRepo.deleteById(id);
    }

    @Override
    public ReviewDTO addReview(Long conferenceId, ReviewDTO reviewDto) {
        Conference conf = confRepo.findById(conferenceId).orElseThrow(() -> new RuntimeException("Conference not found"));
        Review r = mapper.toReviewEntity(reviewDto);
        r.setDate(r.getDate() == null ? LocalDate.now() : r.getDate());
        r.setConference(conf);
        Review saved = reviewRepo.save(r);
        // keep bidirectional list
        conf.getReviews().add(saved);
        confRepo.save(conf);
        return mapper.toReviewDto(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ReviewDTO> listReviews(Long conferenceId) {
        return mapper.toReviewDtoList(reviewRepo.findByConferenceId(conferenceId));
    }

    // Feign call with circuit-breaker fallback
    @Override
    //@CircuitBreaker(name = "keynoteClient", fallbackMethod = "keynoteFallback")
    public KeynoteDTO getKeynoteInfo(Long keynoteId) {
        return keynoteClient.getKeynote(keynoteId);
    }

    // fallback signature: same return type + exception param
    public KeynoteDTO keynoteFallback(Long keynoteId, Throwable t) {
        return KeynoteDTO.builder()
                .id(keynoteId)
                .nom("Unknown")
                .prenom("")
                .email("")
                .fonction("unknown")
                .build();
    }
}
