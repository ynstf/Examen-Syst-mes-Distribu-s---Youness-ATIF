package com.atif.conferenceservice.config;

import com.atif.conferenceservice.entity.Conference;
import com.atif.conferenceservice.entity.Review;
import com.atif.conferenceservice.repository.ConferenceRepository;
import com.atif.conferenceservice.repository.ReviewRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initConferenceData(
            ConferenceRepository conferenceRepo,
            ReviewRepository reviewRepo) {

        return args -> {

            if (conferenceRepo.count() > 0) return;

            // ---- Conference 1 ----
            Conference c1 = conferenceRepo.save(
                    Conference.builder()
                            .titre("AI Trends 2025")
                            .type("academique")
                            .date(LocalDate.now().plusDays(10))
                            .duree(120)
                            .nbInscrits(150)
                            .score(4.5)
                            .keynoteId(1L)   // maps to keynote-service
                            .build()
            );

            reviewRepo.save(Review.builder()
                    .conference(c1)
                    .date(LocalDate.now())
                    .note(5)
                    .texte("Excellent talk about AI and ML!")
                    .build());

            reviewRepo.save(Review.builder()
                    .conference(c1)
                    .date(LocalDate.now())
                    .note(4)
                    .texte("Very interesting insights.")
                    .build());

            // ---- Conference 2 ----
            Conference c2 = conferenceRepo.save(
                    Conference.builder()
                            .titre("Cloud Native Architecture")
                            .type("commerciale")
                            .date(LocalDate.now().plusDays(30))
                            .duree(90)
                            .nbInscrits(80)
                            .score(4.0)
                            .keynoteId(4L)
                            .build()
            );

            reviewRepo.save(Review.builder()
                    .conference(c2)
                    .date(LocalDate.now())
                    .note(4)
                    .texte("Useful session for DevOps engineers.")
                    .build());

            // ---- Conference 3 ----
            Conference c3 = conferenceRepo.save(
                    Conference.builder()
                            .titre("Quantum Computing Basics")
                            .type("academique")
                            .date(LocalDate.now().plusDays(5))
                            .duree(60)
                            .nbInscrits(45)
                            .score(3.8)
                            .keynoteId(3L)
                            .build()
            );

            // ---- Conference 4 ----
            Conference c4 = conferenceRepo.save(
                    Conference.builder()
                            .titre("Big Data Pipelines")
                            .type("commerciale")
                            .date(LocalDate.now().plusDays(15))
                            .duree(110)
                            .nbInscrits(120)
                            .score(4.2)
                            .keynoteId(2L)
                            .build()
            );

            reviewRepo.save(Review.builder()
                    .conference(c4)
                    .date(LocalDate.now())
                    .note(5)
                    .texte("Loved it! Clear explanation of pipelines.")
                    .build());
        };
    }
}
