package br.com.gpiagentini.api.infraestructure.persistence.mapper;

import br.com.gpiagentini.api.domain.model.Contact;
import br.com.gpiagentini.api.infraestructure.persistence.entity.ContactEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ContactMapper {

    @Autowired
    private ProfessionalMapper professionalMapper;

    /**
     * Map an entity object to a doamin object.
     * 
     * @param entity as Contact Entity
     * @return a Contact domain object.
     */
    public Contact mapEntityToDomain(ContactEntity entity) {
        var professional = professionalMapper.mapEntityToDomain(entity.getProfessional());
        return new Contact(entity.getId(), entity.getName(), entity.getContact(), entity.getCreatedDate(),
                professional);
    }

}
