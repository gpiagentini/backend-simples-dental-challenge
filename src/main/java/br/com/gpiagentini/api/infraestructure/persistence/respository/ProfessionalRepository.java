package br.com.gpiagentini.api.infraestructure.persistence.respository;

import br.com.gpiagentini.api.application.dto.NewProfessionalData;
import br.com.gpiagentini.api.application.port.out.IProfessionalRepository;
import br.com.gpiagentini.api.domain.model.Professional;
import br.com.gpiagentini.api.infraestructure.persistence.entity.PositionEntity;
import br.com.gpiagentini.api.infraestructure.persistence.entity.ProfessionalEntity;
import br.com.gpiagentini.api.infraestructure.persistence.mapper.ProfessionalMapper;
import br.com.gpiagentini.api.infraestructure.persistence.specifications.ProfessionalSpecifications;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProfessionalRepository implements IProfessionalRepository {

    @Autowired
    private JpaProfessionalRepository jpaProfessionalRepository;

    @Autowired
    private PositionRepository positionRepository;

    @Autowired
    private ProfessionalMapper professionalMapper;

    @Override
    public String createNewProfessional(NewProfessionalData newProfessionalData) {
        var position = positionRepository.getPositionByName(newProfessionalData.position());
        if (position == null)
            throw new IllegalArgumentException("Cargo " + newProfessionalData.position() + " não é válido.");
        var newProfessional = new ProfessionalEntity(newProfessionalData.name(), newProfessionalData.bornDate(), position);
        return jpaProfessionalRepository.save(newProfessional).getId().toString();
    }

    @Override
    public Professional getProfessionalById(Long id) {
        var entity = jpaProfessionalRepository.findById(id).orElseThrow();
        return professionalMapper.mapEntityToDomain(entity);
    }

    @Override
    public List<Professional> getAllProfessionals(String searchText) {
        var entityList = jpaProfessionalRepository.findAll(ProfessionalSpecifications.searchByText(searchText));
        return entityList.stream().map(entity -> professionalMapper.mapEntityToDomain(entity)).toList();
    }

    @Override
    public void deleteProfessional(Long id) {
        jpaProfessionalRepository.deactivateProfessionalById(id);
    }

    @Override
    public void updateProfessionalData(Professional professional) {
        var entity = professionalMapper.mapDomainToEntity(professional);
        jpaProfessionalRepository.save(entity);
    }

}

interface JpaProfessionalRepository extends JpaRepository<ProfessionalEntity, Long>, JpaSpecificationExecutor<ProfessionalEntity> {
    @Modifying
    @Query("UPDATE ProfessionalEntity p SET p.active = false WHERE p.id = :id AND p.active = true")
    void deactivateProfessionalById(Long id);
}

