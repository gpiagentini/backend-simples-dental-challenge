package br.com.gpiagentini.api.application.dto;

import br.com.gpiagentini.api.domain.model.Professional;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;

@JsonFilter("DynamicFilter")
public record RetrieveProfessionalData(
        @JsonProperty("id")
        Long id,
        @JsonProperty("nome")
        String name,
        @JsonProperty("cargo")
        String position,
        @JsonProperty("data_nascimento")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate birthDate,
        @JsonProperty("created_date")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime createdDate,
        @JsonProperty("ativo")
        Boolean active
) {
    public RetrieveProfessionalData(Professional professional) {
        this(professional.getId(), professional.getName(), professional.getPosition(), professional.getBirthDate(),
                professional.getCreationDate(), professional.getActive());
    }

}
