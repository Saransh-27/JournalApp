package com.company.journalApp.controller;

import com.company.journalApp.service.GoogleAuthService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/auth/google")
@Slf4j
public class GoogleAuthController {

    @Autowired
    private GoogleAuthService googleAuthService;

    @Operation(
            summary = "Callback",
            description = "Handle Google OAuth callback"
    )
    @GetMapping("/callback")
    public ResponseEntity<?> handleGoogleCallback(@RequestParam String code) {
        try {
            String jwtToken = googleAuthService.authenticateWithGoogle(code);
            return ResponseEntity.ok(Collections.singletonMap("token", jwtToken));
        } catch (Exception e) {
            log.error("Exception occurred while handling Google OAuth callback", e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
