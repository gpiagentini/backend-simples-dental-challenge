package br.com.gpiagentini.api.application.port.in;

import br.com.gpiagentini.api.application.dto.NewContactData;
import br.com.gpiagentini.api.application.dto.RetrieveContactData;
import br.com.gpiagentini.api.application.dto.UpdateContactData;

import java.util.List;

public interface IContactApplicationService {
    /**
     * Get a list of contacts filtered by the search text.
     * 
     * @param searchText to filter the results with that contains the text.
     * @return a list of all contacts.
     */
    List<RetrieveContactData> getAllContacts(String searchText);

    /**
     * Get a contact by its id.
     * 
     * @param id of the contact to get.
     * @return the contact with the given id.
     */
    RetrieveContactData getContactById(Long id);

    /**
     * Save a new contact.
     * 
     * @param newContactData with contact data.
     * @return the id of the new contact.
     */
    Long saveNewContact(NewContactData newContactData);

    /**
     * Delete a contact by its id.
     * 
     * @param id of the contact to delete.
     */
    void deleteContactById(Long id);

    /**
     * Update the contact information.
     * 
     * @param id                of the contact to update.
     * @param updateContactData with the new contact information.
     */
    void updateContactInformation(Long id, UpdateContactData updateContactData);
}
