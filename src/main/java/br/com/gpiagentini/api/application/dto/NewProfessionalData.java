package br.com.gpiagentini.api.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record NewProfessionalData(
        @JsonProperty("nome")
        @NotBlank(message = "Campo 'nome' é obrigatório")
        String name,
        @NotBlank(message = "Campo 'cargo' é obrigatório")
        @JsonProperty("cargo")
        String position,
        @NotNull(message = "Campo 'data_nascimento' é obrigatório")
        @JsonProperty("data_nascimento")
        LocalDate bornDate) {
}
