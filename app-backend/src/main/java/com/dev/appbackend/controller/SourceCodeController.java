package com.dev.appbackend.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/source")
public class SourceCodeController {

    @GetMapping("/")
    public ResponseEntity getSourceCode() {
        return ResponseEntity.status(HttpStatus.OK).body("https://github.com/DimiGhost/dev-java-angular-docker");
    }

    @GetMapping("")
    public ResponseEntity getSourceCodeAlternative() {
        return ResponseEntity.status(HttpStatus.OK).body("https://github.com/DimiGhost/dev-java-angular-docker");
    }
}
