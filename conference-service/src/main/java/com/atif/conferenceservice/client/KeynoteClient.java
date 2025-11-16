package com.atif.conferenceservice.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "keynote-service", path = "/api/keynotes")
public interface KeynoteClient {

    @GetMapping("/{id}")
    KeynoteDTO getKeynote(@PathVariable("id") Long id);
}
