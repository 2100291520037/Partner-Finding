package com.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.document.entity.PartnerPreferences;

public interface PartnerPreferencesRepository extends JpaRepository<PartnerPreferences, Long> {

    PartnerPreferences findByUserId(Long userId);
}
