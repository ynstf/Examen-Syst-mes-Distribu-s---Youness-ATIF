package com.atif.conferenceservice.repository;


import com.atif.conferenceservice.entity.Conference;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConferenceRepository extends JpaRepository<Conference, Long> { }
