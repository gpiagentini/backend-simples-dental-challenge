package br.com.gpiagentini.api.application.port.in;

import br.com.gpiagentini.api.infraestructure.persistence.entity.ContactEntity;

import java.util.List;

public interface IContactApplicationService {
    List<ContactEntity> getAllContacts();
}
