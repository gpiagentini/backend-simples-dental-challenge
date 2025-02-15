package br.com.gpiagentini.api.application.port.out;

import br.com.gpiagentini.api.infraestructure.persistence.entity.PositionEntity;

public interface IPositionRepository {
    PositionEntity getPositionByName(String position);
}
