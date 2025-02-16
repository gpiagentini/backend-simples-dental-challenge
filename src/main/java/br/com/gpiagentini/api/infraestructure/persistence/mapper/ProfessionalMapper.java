package br.com.gpiagentini.api.infraestructure.persistence.mapper;

import br.com.gpiagentini.api.application.port.out.IPositionRepository;
import br.com.gpiagentini.api.domain.model.Professional;
import br.com.gpiagentini.api.infraestructure.persistence.entity.PositionEntity;
import br.com.gpiagentini.api.infraestructure.persistence.entity.ProfessionalEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProfessionalMapper {

    @Autowired
    private IPositionRepository positionRepository;

    public Professional mapEntityToDomain(ProfessionalEntity entity) {
        return new Professional(entity.getId(), entity.getName(), entity.getPosition().getName(), entity.getBirthDate(), entity.getCreatedDate(), entity.getActive());
    }

}
