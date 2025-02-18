package br.com.gpiagentini.api.infraestructure.persistence.respository;

import br.com.gpiagentini.api.infraestructure.persistence.entity.PositionEntity;
import br.com.gpiagentini.api.infraestructure.persistence.respository.interfaces.PositionEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public class PositionRepository implements PositionEntityRepository {

    @Autowired
    private JpaPositionRepository jpaPositionRepository;

    @Override
    public PositionEntity getPositionByName(String position) throws IllegalArgumentException {
        var positionEntity = jpaPositionRepository.findByName(position);
        if (positionEntity == null)
            throw new IllegalArgumentException("Cargo " + position + " não é válido.");
        return positionEntity;
    }
}

interface JpaPositionRepository extends JpaRepository<PositionEntity, Long> {
    PositionEntity findByName(String name);
}