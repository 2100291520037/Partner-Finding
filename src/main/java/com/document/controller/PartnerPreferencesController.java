package com.document.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.document.dto.PartnerPreferencesDto;
import com.document.service.PartnerPreferencesServiceImpl;

@RestController
@RequestMapping("/api")
public class PartnerPreferencesController {

    private final PartnerPreferencesServiceImpl partnerPreferencesService;

    public PartnerPreferencesController(PartnerPreferencesServiceImpl partnerPreferencesService) {
        this.partnerPreferencesService = partnerPreferencesService;
    }

    @PostMapping("/preferences")
    public ResponseEntity<PartnerPreferencesDto> createPreferences(
            @RequestParam Long userId,
            @Valid @RequestBody PartnerPreferencesDto dto) {
        return ResponseEntity.ok(
                partnerPreferencesService.createPartnerPreferences(userId, dto)
        );
    }

    @GetMapping("/preferences")
    public ResponseEntity<PartnerPreferencesDto> getPreferences(@RequestParam Long userId) {
        return ResponseEntity.ok(
                partnerPreferencesService.getPartnerPreferences(userId)
        );
    }

    @PutMapping("/preferences")
    public ResponseEntity<PartnerPreferencesDto> updatePreferences(
            @RequestParam Long userId,
            @Valid @RequestBody PartnerPreferencesDto dto) {
        return ResponseEntity.ok(
                partnerPreferencesService.updatePartnerPreferences(userId, dto)
        );
    }

    @DeleteMapping("/preferences")
    public ResponseEntity<?> deletePreferences(@RequestParam Long userId) {
        partnerPreferencesService.deletePartnerPreferences(userId);
        return ResponseEntity.ok().build();
    }
}
