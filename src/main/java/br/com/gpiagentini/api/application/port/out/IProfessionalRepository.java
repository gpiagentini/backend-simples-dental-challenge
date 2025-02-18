package br.com.gpiagentini.api.application.port.out;

import br.com.gpiagentini.api.application.dto.NewProfessionalData;
import br.com.gpiagentini.api.application.dto.UpdateProfessionalData;
import br.com.gpiagentini.api.domain.model.Professional;

import java.util.List;
import java.util.NoSuchElementException;

public interface IProfessionalRepository {
    /**
     * Get a professional by its id.
     * 
     * @param id of the professional to get.
     * @return the professional with the given id.
     */
    Professional getProfessionalById(Long id) throws NoSuchElementException;

    /**
     * Get a list of professionals filtered by the search text.
     * 
     * @param searchText to filter the results with that contains the text.
     * @return a list of all professionals.
     */
    List<Professional> getAllProfessionals(String searchText);

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
    void deleteProfessional(Long id);

    /**
     * Update the professional information.
     * 
     * @param id                     of the professional to update.
     * @param updateProfessionalData with the new professional information.
     */
    void updateProfessionalData(Long id, UpdateProfessionalData updateProfessionalData);
}
