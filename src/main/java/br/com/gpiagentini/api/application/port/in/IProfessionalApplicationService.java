package br.com.gpiagentini.api.application.port.in;

import br.com.gpiagentini.api.application.dto.NewProfessionalData;
import br.com.gpiagentini.api.application.dto.RetrieveProfessionalData;
import br.com.gpiagentini.api.application.dto.UpdateProfessionalData;

import java.util.List;

public interface IProfessionalApplicationService {
    /**
     * Get a list of professionals filtered by the search text.
     * 
     * @param searchText to filter the results with that contains the text.
     * @return a list of all professionals.
     */
    List<RetrieveProfessionalData> getAllProfessionals(String searchText);

    /**
     * Get a professional by its id.
     * 
     * @param id of the professional to get.
     * @return the professional with the given id.
     */
    RetrieveProfessionalData getProfessionalById(Long id);

    /**
     * Create a new professional.
     * 
     * @param newProfessionalData with professional data.
     * @return the id of the new professional.
     */
    String createNewProfessional(NewProfessionalData newProfessionalData);

    /**
     * Delete a professional by its id.
     * 
     * @param id of the professional to delete.
     */
    void deleteProfessionalById(Long id);

    /**
     * Update the professional information.
     * 
     * @param id                     of the professional to update.
     * @param updateProfessionalData with the new professional information.
     */
    void updateProfessionalData(Long id, UpdateProfessionalData updateProfessionalData);
}
