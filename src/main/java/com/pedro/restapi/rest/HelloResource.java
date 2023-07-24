package com.pedro.restapi.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class HelloResource {


    private final Logger log = LoggerFactory.getLogger(HelloResource.class);


    @GetMapping("")
    public ResponseEntity<String> getPendingTasks() {
        log.info("Welcome to the project");
        return ResponseEntity.ok("Boas vindas ao projeto");
    }
}
