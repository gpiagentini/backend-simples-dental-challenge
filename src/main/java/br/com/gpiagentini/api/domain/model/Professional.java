package br.com.gpiagentini.api.domain.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Professional {
    private final Long id;
    private String name;
    private String position;
    private LocalDate birthDate;
    private final LocalDateTime creationDate;
    private final Boolean active;

    public Professional(Long id, String name, String position, LocalDate birthDate, LocalDateTime creationDate, Boolean active) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.birthDate = birthDate;
        this.creationDate = creationDate;
        this.active = active;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public Boolean getActive() {
        return active;
    }


}
