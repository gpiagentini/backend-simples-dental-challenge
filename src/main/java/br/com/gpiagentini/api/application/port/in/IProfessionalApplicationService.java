package br.com.gpiagentini.api.application.port.in;

import br.com.gpiagentini.api.application.dto.NewProfessionalData;
import br.com.gpiagentini.api.application.dto.RetrieveProfessionalData;
import br.com.gpiagentini.api.application.dto.UpdateProfessionalData;

import java.util.List;

public interface IProfessionalApplicationService {
    String createNewProfessional(NewProfessionalData newProfessionalData);
    RetrieveProfessionalData getProfessionalById(Long id);
    List<RetrieveProfessionalData> getAllProfessionals(String searchText);
    void deleteProfessionalById(Long id);
    void updateProfessionalData(Long id, UpdateProfessionalData updateProfessionalData);
}
