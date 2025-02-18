package br.com.gpiagentini.api.application.port.out;

import br.com.gpiagentini.api.infraestructure.persistence.entity.PositionEntity;

public interface IPositionRepository {
    /**
     * Get a position by its name.
     * 
     * @param position to get.
     * @return the position with the given name.
     * @throws IllegalArgumentException if the position does not exist.
     */
    PositionEntity getPositionByName(String position) throws IllegalArgumentException;
}
