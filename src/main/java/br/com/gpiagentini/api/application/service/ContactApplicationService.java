package br.com.gpiagentini.api.application.service;

import br.com.gpiagentini.api.application.dto.NewContactData;
import br.com.gpiagentini.api.application.dto.RetrieveContactData;
import br.com.gpiagentini.api.application.dto.UpdateContactData;
import br.com.gpiagentini.api.application.port.in.IContactApplicationService;
import br.com.gpiagentini.api.application.port.out.IContactRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ContactApplicationService implements IContactApplicationService {

    private final IContactRepository contactRepository;

    public ContactApplicationService(IContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public List<RetrieveContactData> getAllContacts(String searchText) {
        var contactList = contactRepository.getAllContacts(searchText);
        return contactList.stream().map(RetrieveContactData::new).toList();
    }

    @Override
    public RetrieveContactData getContactById(Long id) {
        var contact = contactRepository.getContactById(id);
        return new RetrieveContactData(contact);
    }

    @Override
    @Transactional
    public Long saveNewContact(NewContactData newContactData) {
        return contactRepository.saveNewContact(newContactData);
    }

    @Override
    @Transactional
    public void deleteContactById(Long id) {
        contactRepository.deleteContactById(id);
    }

    @Override
    @Transactional
    public void updateContactInformation(Long id, UpdateContactData updateContactData) {
        contactRepository.updateContactInformation(id, updateContactData);
    }
}
