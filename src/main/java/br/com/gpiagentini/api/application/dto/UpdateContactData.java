package br.com.gpiagentini.api.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateContactData(
        @JsonProperty("nome") String name,
        @JsonProperty("contato") String contact,
        @JsonProperty("profissional") Long idProfessional
) {
}
