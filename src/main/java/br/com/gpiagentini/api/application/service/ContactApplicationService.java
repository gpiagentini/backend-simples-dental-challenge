package br.com.gpiagentini.api.application.service;

import br.com.gpiagentini.api.application.port.in.IContactApplicationService;
import br.com.gpiagentini.api.application.port.out.IContactRepository;
import br.com.gpiagentini.api.infraestructure.persistence.entity.ContactEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ContactApplicationService implements IContactApplicationService {

    @Autowired
    private IContactRepository contactRepository;

    @Override
    public List<ContactEntity> getAllContacts() {
        return contactRepository.getAllContacts();
    }
}
