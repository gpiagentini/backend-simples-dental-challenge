package br.com.gpiagentini.api.application.port.out;

import br.com.gpiagentini.api.application.dto.NewContactData;
import br.com.gpiagentini.api.application.dto.UpdateContactData;
import br.com.gpiagentini.api.domain.model.Contact;

import java.util.List;

public interface IContactRepository {
    List<Contact> getAllContacts(String searchText);
    Contact getContactById(Long id);
    Contact saveNewContact(NewContactData newContactData);
    void deleteContactById(Long id);
    void updateContactInformation(Long id, UpdateContactData updateContactData);
}
