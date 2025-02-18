package br.com.gpiagentini.api.application.port.out;

import br.com.gpiagentini.api.application.dto.NewContactData;
import br.com.gpiagentini.api.application.dto.UpdateContactData;
import br.com.gpiagentini.api.domain.model.Contact;

import java.util.List;

public interface IContactRepository {
    /**
     * Get a list of contacts filtered by the search text.
     *
     * @param searchText to filter the results with that contains the text.
     * @return a list of all contacts.
     */
    List<Contact> getAllContacts(String searchText);

    /**
     * Get a contact by its id.
     *
     * @param id of the contact to get.
     * @return the contact with the given id.
     */
    Contact getContactById(Long id);

    /**
     * Save a new contact.
     *
     * @param newContactData with contact data.
     * @return the new contact.
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
