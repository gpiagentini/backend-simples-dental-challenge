package br.com.gpiagentini.api.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;

public record UpdateProfessionalData(
        @JsonProperty("nome") String name,
        @JsonProperty("cargo") String position,
        @JsonProperty("nascimento") LocalDate birthDate
) {
}
