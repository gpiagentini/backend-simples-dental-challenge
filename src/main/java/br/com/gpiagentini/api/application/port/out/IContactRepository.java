package br.com.gpiagentini.api.application.port.out;

import br.com.gpiagentini.api.infraestructure.persistence.entity.ContactEntity;

import java.util.List;

public interface IContactRepository {
    List<ContactEntity> getAllContacts();
}
