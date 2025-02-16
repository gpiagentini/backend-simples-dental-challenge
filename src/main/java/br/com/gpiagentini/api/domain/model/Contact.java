package br.com.gpiagentini.api.domain.model;

import java.time.LocalDateTime;

public class Contact {
    private final Long id;
    private String name;
    private String contactData;
    private final LocalDateTime creationDate;
    private Professional professional;

    public Contact(Long id, String name, String contactData, LocalDateTime creationDate, Professional professional) {
        this.id = id;
        this.name = name;
        this.contactData = contactData;
        this.professional = professional;
        this.creationDate = creationDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContactData() {
        return contactData;
    }

    public Professional getProfessional() {
        return professional;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContactData(String contactData) {
        this.contactData = contactData;
    }

    public void setProfessional(Professional professional) {
        this.professional = professional;
    }
}
