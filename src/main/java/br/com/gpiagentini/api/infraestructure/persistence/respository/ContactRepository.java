package br.com.gpiagentini.api.infraestructure.persistence.respository;

import br.com.gpiagentini.api.application.dto.NewContactData;
import br.com.gpiagentini.api.application.dto.UpdateContactData;
import br.com.gpiagentini.api.application.port.out.IContactRepository;
import br.com.gpiagentini.api.domain.model.Contact;
import br.com.gpiagentini.api.infraestructure.persistence.entity.ContactEntity;
import br.com.gpiagentini.api.infraestructure.persistence.entity.ProfessionalEntity;
import br.com.gpiagentini.api.infraestructure.persistence.mapper.ContactMapper;
import br.com.gpiagentini.api.infraestructure.persistence.respository.interfaces.ReferenceRepository;
import br.com.gpiagentini.api.infraestructure.persistence.specifications.ContactSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class ContactRepository implements IContactRepository, ReferenceRepository<ContactEntity> {

    @Autowired
    private JpaContactRepository jpaContactRepository;

    @Autowired
    private ReferenceRepository<ProfessionalEntity> professionalReferenceRepository;

    @Autowired
    private ContactMapper contactMapper;

    @Override
    public List<Contact> getAllContacts(String searchText) {
        var entityList = jpaContactRepository.findAll(ContactSpecification.searchByText(searchText));
        return entityList.stream().map(contactMapper::mapEntityToDomain).toList();
    }

    @Override
    public Contact getContactById(Long id) {
        var entity = jpaContactRepository.findById(id).orElseThrow();
        return contactMapper.mapEntityToDomain(entity);
    }

    @Override
    public Contact saveNewContact(NewContactData newContactData) {
        try {
            var professionalEntity = professionalReferenceRepository.getReferenceById(newContactData.idProfessional());
            var newContact = new ContactEntity(newContactData.name(), newContactData.contactData(), professionalEntity);
            return contactMapper.mapEntityToDomain(jpaContactRepository.save(newContact));
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("Profissional com o id " + newContactData.idProfessional() + " não existe.");
        }
    }

    @Override
    public void deleteContactById(Long id) {
        jpaContactRepository.deleteById(id);
    }

    @Override
    public void updateContactInformation(Long id, UpdateContactData updateContactData) {
        var entity = getReferenceById(id);
        if (updateContactData.name() != null)
            entity.setName(updateContactData.name());
        if (updateContactData.contact() != null)
            entity.setContact(updateContactData.contact());
        if (updateContactData.idProfessional() != null) {
            entity.setProfessional(professionalReferenceRepository.getReferenceById(updateContactData.idProfessional()));
        }
        jpaContactRepository.save(entity);
    }

    @Override
    public ContactEntity getReferenceById(Long id) {
        if (!jpaContactRepository.existsById(id))
            throw new IllegalArgumentException("Contato com id " + id + " não existe.");
        return jpaContactRepository.getReferenceById(id);
    }
}

interface JpaContactRepository extends JpaRepository<ContactEntity, Long>, JpaSpecificationExecutor<ContactEntity> {
}
