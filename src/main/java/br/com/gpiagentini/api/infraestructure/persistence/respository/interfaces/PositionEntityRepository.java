package br.com.gpiagentini.api.infraestructure.persistence.respository.interfaces;

import br.com.gpiagentini.api.infraestructure.persistence.entity.PositionEntity;

public interface PositionEntityRepository {
    /**
     * Get a position entity by its name.
     *
     * @param position name to fetch.
     * @return the position with the given name.
     * @throws IllegalArgumentException if the position does not exist.
     */
    PositionEntity getPositionByName(String position) throws IllegalArgumentException;
}
