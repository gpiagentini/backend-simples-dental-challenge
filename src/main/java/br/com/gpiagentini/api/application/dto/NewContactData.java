package br.com.gpiagentini.api.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NewContactData(
        @JsonProperty("nome")
        @NotBlank(message = "Campo 'nome' é obrigatório.")
        String name,
        @JsonProperty("contato")
        @NotBlank(message = "Campo 'contato' é obrigatório.")
        String contactData,
        @JsonProperty("profissional")
        @NotNull(message = "Campo 'profissional' é obrigatório.")
        Long idProfessional
        ) {
}
