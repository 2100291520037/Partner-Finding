package com.document.dto;


import jakarta.validation.constraints.NotNull;

public class PartnerPreferencesDto {

    private Long id;

    @NotNull(message = "Preferred age start cannot be null")
    private Integer preferredAgeStart;

    @NotNull(message = "Preferred age end cannot be null")
    private Integer preferredAgeEnd;

    @NotNull(message = "Preferred gender cannot be null")
    private String preferredGender;

    private Long userId;

    // getters & setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPreferredAgeStart() {
        return preferredAgeStart;
    }

    public void setPreferredAgeStart(Integer preferredAgeStart) {
        this.preferredAgeStart = preferredAgeStart;
    }

    public Integer getPreferredAgeEnd() {
        return preferredAgeEnd;
    }

    public void setPreferredAgeEnd(Integer preferredAgeEnd) {
        this.preferredAgeEnd = preferredAgeEnd;
    }

    public String getPreferredGender() {
        return preferredGender;
    }

    public void setPreferredGender(String preferredGender) {
        this.preferredGender = preferredGender;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}

