package br.com.gpiagentini.api.infraestructure.persistence.respository;

import br.com.gpiagentini.api.application.port.out.IContactRepository;
import br.com.gpiagentini.api.infraestructure.persistence.entity.ContactEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ContactRepository implements IContactRepository {

    @Autowired
    private JpaContactRepository jpaContactRepository;

    @Override
    public List<ContactEntity> getAllContacts() {
        return jpaContactRepository.findAll();
    }
}

interface JpaContactRepository extends JpaRepository<ContactEntity, Long> {
}
