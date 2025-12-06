package com.document.entity;


import jakarta.persistence.*;

@Entity
@Table(name = "partner_preferences")
public class PartnerPreferences {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "preferred_age_start")
    private Integer preferredAgeStart;

    @Column(name = "preferred_age_end")
    private Integer preferredAgeEnd;

    @Column(name = "preferred_gender")
    private String preferredGender;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
