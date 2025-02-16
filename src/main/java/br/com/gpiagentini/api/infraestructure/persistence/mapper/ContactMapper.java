package br.com.gpiagentini.api.infraestructure.persistence.mapper;

import br.com.gpiagentini.api.domain.model.Contact;
import br.com.gpiagentini.api.infraestructure.persistence.entity.ContactEntity;
import br.com.gpiagentini.api.infraestructure.persistence.entity.ProfessionalEntity;
import br.com.gpiagentini.api.infraestructure.persistence.respository.interfaces.ReferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {

    @Autowired
    private ProfessionalMapper professionalMapper;

    @Autowired
    private ReferenceRepository<ProfessionalEntity> professionalReferenceRepository;

    public Contact mapEntityToDomain(ContactEntity entity) {
        var professional = professionalMapper.mapEntityToDomain(entity.getProfessional());
        return new Contact(entity.getId(), entity.getName(), entity.getContact(), entity.getCreatedDate(), professional);
    }

}
