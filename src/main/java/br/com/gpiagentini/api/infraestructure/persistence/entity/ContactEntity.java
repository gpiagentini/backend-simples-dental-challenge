package br.com.gpiagentini.api.infraestructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "contatos")
@Getter
@Setter
@NoArgsConstructor
public class ContactEntity {

    public ContactEntity(String name, String contact, ProfessionalEntity professional) {
        this.name = name;
        this.contact = contact;
        this.professional = professional;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "nome")
    private String name;
    @Column(name = "contato")
    private String contact;
    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "profissional")
    private ProfessionalEntity professional;
}
