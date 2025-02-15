package br.com.gpiagentini.api.application.port.out;

import br.com.gpiagentini.api.application.dto.NewProfessionalData;
import br.com.gpiagentini.api.domain.model.Professional;
import br.com.gpiagentini.api.infraestructure.persistence.entity.PositionEntity;

import java.util.List;

public interface IProfessionalRepository {
    String createNewProfessional(NewProfessionalData newProfessionalData);
    Professional getProfessionalById(Long id);
    List<Professional> getAllProfessionals(String searchText);
    void deleteProfessional(Long id);
    void updateProfessionalData(Professional professional);
}
