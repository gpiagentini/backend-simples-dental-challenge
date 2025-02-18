package br.com.gpiagentini.api.infraestructure.persistence.respository;

import br.com.gpiagentini.api.application.dto.NewProfessionalData;
import br.com.gpiagentini.api.application.dto.UpdateProfessionalData;
import br.com.gpiagentini.api.application.port.out.IProfessionalRepository;
import br.com.gpiagentini.api.domain.model.Professional;
import br.com.gpiagentini.api.infraestructure.persistence.entity.ProfessionalEntity;
import br.com.gpiagentini.api.infraestructure.persistence.mapper.ProfessionalMapper;
import br.com.gpiagentini.api.infraestructure.persistence.respository.interfaces.PositionEntityRepository;
import br.com.gpiagentini.api.infraestructure.persistence.respository.interfaces.ReferenceRepository;
import br.com.gpiagentini.api.infraestructure.persistence.specifications.ProfessionalSpecifications;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;

@Repository
public class ProfessionalRepository implements IProfessionalRepository, ReferenceRepository<ProfessionalEntity> {

    @Autowired
    private JpaProfessionalRepository jpaProfessionalRepository;

    @Autowired
    private PositionEntityRepository positionRepository;

    @Autowired
    private ProfessionalMapper professionalMapper;

    @Override
    public String createNewProfessional(NewProfessionalData newProfessionalData) {
        var position = positionRepository.getPositionByName(newProfessionalData.position());
        var newProfessional = new ProfessionalEntity(newProfessionalData.name(), newProfessionalData.bornDate(), position);
        return jpaProfessionalRepository.save(newProfessional).getId().toString();
    }

    @Override
    public Professional getProfessionalById(Long id) throws NoSuchElementException {
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
    public void updateProfessionalData(Long id, UpdateProfessionalData updateProfessionalData) {
        var entity = getReferenceById(id);
        if(updateProfessionalData.position() != null)
            entity.setPosition(positionRepository.getPositionByName(updateProfessionalData.position()));
        if(updateProfessionalData.name() != null)
            entity.setName(updateProfessionalData.name());
        if(updateProfessionalData.birthDate() != null)
            entity.setBirthDate(updateProfessionalData.birthDate());
        jpaProfessionalRepository.save(entity);
    }

    @Override
    public ProfessionalEntity getReferenceById(Long id) throws IllegalArgumentException {
        if (!jpaProfessionalRepository.existsById(id))
            throw new IllegalArgumentException("Profissional com id " + id + " não existe.");
        return jpaProfessionalRepository.getReferenceById(id);
    }
}

interface JpaProfessionalRepository extends JpaRepository<ProfessionalEntity, Long>, JpaSpecificationExecutor<ProfessionalEntity> {
    @Modifying
    @Query("UPDATE ProfessionalEntity p SET p.active = false WHERE p.id = :id AND p.active = true")
    void deactivateProfessionalById(Long id);
}

