package br.com.gpiagentini.api.application.port.in;

import br.com.gpiagentini.api.application.dto.NewContactData;
import br.com.gpiagentini.api.application.dto.RetrieveContactData;
import br.com.gpiagentini.api.application.dto.UpdateContactData;
import br.com.gpiagentini.api.infraestructure.persistence.entity.ContactEntity;

import java.util.List;

public interface IContactApplicationService {
    List<RetrieveContactData> getAllContacts(String searchText);
    RetrieveContactData getContactById(Long id);
    Long saveNewContact(NewContactData newContactData);
    void deleteContactById(Long id);
    void updateContactInformation(Long id, UpdateContactData updateContactData);
}
