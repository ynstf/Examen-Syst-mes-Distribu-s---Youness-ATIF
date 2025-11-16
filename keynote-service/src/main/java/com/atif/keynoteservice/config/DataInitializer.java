package com.atif.keynoteservice.config;


import com.atif.keynoteservice.entity.Keynote;
import com.atif.keynoteservice.repository.KeynoteRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initKeynoteData(KeynoteRepository repo) {
        return args -> {

            if (repo.count() > 0) return; // to avoid duplicates

            repo.save(Keynote.builder()
                    .nom("Youness")
                    .prenom("Atif")
                    .email("y.atif@example.com")
                    .fonction("Software Engineer")
                    .build());

            repo.save(Keynote.builder()
                    .nom("Sara")
                    .prenom("Amrani")
                    .email("sara.amrani@example.com")
                    .fonction("Data Scientist")
                    .build());

            repo.save(Keynote.builder()
                    .nom("Hassan")
                    .prenom("El Alaoui")
                    .email("hassan.alaoui@example.com")
                    .fonction("Professor")
                    .build());

            repo.save(Keynote.builder()
                    .nom("Imane")
                    .prenom("Fassi")
                    .email("imane.fassi@example.com")
                    .fonction("Cloud Architect")
                    .build());
        };
    }
}
