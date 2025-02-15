package br.com.gpiagentini.api.infraestructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "profissionais")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProfessionalEntity {

    public ProfessionalEntity(String name, LocalDate birthDate, PositionEntity position) {
        this.name = name;
        this.birthDate = birthDate;
        this.position = position;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @Column(name = "nome")
    private String name;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cargo", referencedColumnName = "id")
    private PositionEntity position;
    @Column(name = "nascimento")
    private LocalDate birthDate;
    @Column(name = "created_date")
    private LocalDateTime createdDate = LocalDateTime.now();
    @Column(name = "ativo")
    private Boolean active = true;
}
