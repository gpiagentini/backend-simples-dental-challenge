package br.com.gpiagentini.api.application.dto;

import br.com.gpiagentini.api.domain.model.Contact;
import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@JsonFilter("DynamicFilter")
public record RetrieveContactData(
        @JsonProperty("id")
        Long id,
        @JsonProperty("nome")
        String name,
        @JsonProperty("contato")
        String contact,
        @JsonProperty("created_date")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime createdDate,
        @JsonProperty("profissional")
        RetrieveProfessionalData professionalData
) {
    public RetrieveContactData(Contact contact) {
        this(contact.getId(), contact.getName(), contact.getContactData(),
                contact.getCreationDate(), new RetrieveProfessionalData(contact.getProfessional()));
    }
}
