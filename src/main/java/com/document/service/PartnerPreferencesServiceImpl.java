package com.document.service;




import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.document.dto.PartnerPreferencesDto;
import com.document.entity.PartnerPreferences;
import com.document.entity.User;
import com.document.repository.PartnerPreferencesRepository;
import com.document.repository.UserRepository;

@Service
@Transactional
public class PartnerPreferencesServiceImpl  {

    private final PartnerPreferencesRepository partnerPreferencesRepository;
    private final UserRepository userRepository;

    public PartnerPreferencesServiceImpl(PartnerPreferencesRepository partnerPreferencesRepository,
                                         UserRepository userRepository) {
        this.partnerPreferencesRepository = partnerPreferencesRepository;
        this.userRepository = userRepository;
    }

    //@Override
    public PartnerPreferencesDto createPartnerPreferences(Long userId, PartnerPreferencesDto dto) {
        User user =userRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found."));
        		PartnerPreferences preferences = new PartnerPreferences();
        preferences.setPreferredAgeStart(dto.getPreferredAgeStart());
        preferences.setPreferredAgeEnd(dto.getPreferredAgeEnd());
        preferences.setPreferredGender(dto.getPreferredGender());
        preferences.setUser(user);

        PartnerPreferences saved = partnerPreferencesRepository.save(preferences);

        dto.setId(saved.getId());
        dto.setUserId(userId);
        return dto;
    }

   // @Override
    public PartnerPreferencesDto getPartnerPreferences(Long userId) {
        PartnerPreferences preferences = partnerPreferencesRepository.findByUserId(userId);
        if (preferences == null) {
            throw new RuntimeException("Partner Preferences not found.");
        }
        return convertToDto(preferences);
    }

   // @Override
    public PartnerPreferencesDto updatePartnerPreferences(Long userId, PartnerPreferencesDto dto) {
        PartnerPreferences preferences = partnerPreferencesRepository.findByUserId(userId);
        if (preferences == null) {
            throw new RuntimeException("Partner Preferences not found.");
        }

        preferences.setPreferredAgeStart(dto.getPreferredAgeStart());
        preferences.setPreferredAgeEnd(dto.getPreferredAgeEnd());
        preferences.setPreferredGender(dto.getPreferredGender());

        PartnerPreferences updated = partnerPreferencesRepository.save(preferences);
        return convertToDto(updated);
    }

  //  @Override
    public void deletePartnerPreferences(Long userId) {
        PartnerPreferences preferences = partnerPreferencesRepository.findByUserId(userId);
        if (preferences != null) {
            partnerPreferencesRepository.delete(preferences);
        }
    }

    private PartnerPreferencesDto convertToDto(PartnerPreferences preferences) {
        PartnerPreferencesDto dto = new PartnerPreferencesDto();
        dto.setId(preferences.getId());
        dto.setPreferredAgeStart(preferences.getPreferredAgeStart());
        dto.setPreferredAgeEnd(preferences.getPreferredAgeEnd());
        dto.setPreferredGender(preferences.getPreferredGender());
        dto.setUserId(preferences.getUser().getId());
        return dto;
    }
}

