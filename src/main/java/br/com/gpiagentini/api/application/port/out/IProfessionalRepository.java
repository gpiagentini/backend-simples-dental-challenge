package br.com.gpiagentini.api.application.port.out;

import br.com.gpiagentini.api.application.dto.NewProfessionalData;
import br.com.gpiagentini.api.application.dto.UpdateProfessionalData;
import br.com.gpiagentini.api.domain.model.Professional;

import java.util.List;
import java.util.NoSuchElementException;

public interface IProfessionalRepository {
    String createNewProfessional(NewProfessionalData newProfessionalData);
    Professional getProfessionalById(Long id) throws NoSuchElementException;
    List<Professional> getAllProfessionals(String searchText);
    void deleteProfessional(Long id);
    void updateProfessionalData(Long id, UpdateProfessionalData updateProfessionalData);
}
