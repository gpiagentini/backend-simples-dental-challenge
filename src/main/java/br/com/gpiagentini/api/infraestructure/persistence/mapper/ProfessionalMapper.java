package br.com.gpiagentini.api.infraestructure.persistence.mapper;

import br.com.gpiagentini.api.domain.model.Professional;
import br.com.gpiagentini.api.infraestructure.persistence.entity.ProfessionalEntity;
import org.springframework.stereotype.Component;

@Component
public class ProfessionalMapper {

    /**
     * Map an entity object to a doamin object.
     * 
     * @param entity as Professional Entity
     * @return a Professional domain object.
     */
    public Professional mapEntityToDomain(ProfessionalEntity entity) {
        return new Professional(entity.getId(), entity.getName(), entity.getPosition().getName(), entity.getBirthDate(),
                entity.getCreatedDate(), entity.getActive());
    }

}
