package br.com.gpiagentini.api.infraestructure.persistence.entity;

import jakarta.persistence.*;
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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "nome")
    private String name;
    @Column(name = "contato")
    private String contact;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @ManyToOne
    @JoinColumn(name = "profissional")
    private ProfessionalEntity professional;
}
