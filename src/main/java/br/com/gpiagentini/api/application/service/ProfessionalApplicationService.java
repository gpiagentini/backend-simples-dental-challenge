package br.com.gpiagentini.api.application.service;

import br.com.gpiagentini.api.application.dto.NewProfessionalData;
import br.com.gpiagentini.api.application.dto.RetrieveProfessionalData;
import br.com.gpiagentini.api.application.dto.UpdateProfessionalData;
import br.com.gpiagentini.api.application.port.in.IProfessionalApplicationService;
import br.com.gpiagentini.api.application.port.out.IPositionRepository;
import br.com.gpiagentini.api.application.port.out.IProfessionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class ProfessionalApplicationService implements IProfessionalApplicationService {

    @Autowired
    private IProfessionalRepository professionalRepository;

    @Autowired
    private IPositionRepository positionRepository;

    @Override
    @Transactional
    public String createNewProfessional(NewProfessionalData newProfessionalData) {
        return professionalRepository.createNewProfessional(newProfessionalData);
    }

    @Override
    public RetrieveProfessionalData getProfessionalById(Long id) {
        var professional = professionalRepository.getProfessionalById(id);
        return new RetrieveProfessionalData(professional.getId(), professional.getName(), professional.getPosition(), professional.getBirthDate(), professional.getCreationDate(), professional.getActive());
    }

    @Override
    public List<RetrieveProfessionalData> getAllProfessionals(String searchText) {
        var professionalList = professionalRepository.getAllProfessionals(searchText);
        return professionalList
                .stream()
                .map(professional -> new RetrieveProfessionalData(professional.getId(), professional.getName(), professional.getPosition(), professional.getBirthDate(), professional.getCreationDate(), professional.getActive()))
                .toList();
    }

    @Override
    @Transactional
    public void deleteProfessionalById(Long id) {
        professionalRepository.deleteProfessional(id);
    }

    @Override
    public void updateProfessionalData(Long id, UpdateProfessionalData updateProfessionalData) {
        var professional = professionalRepository.getProfessionalById(id);

        if(updateProfessionalData.position() != null)
            professional.setPosition(updateProfessionalData.position());
        if(updateProfessionalData.name() != null)
            professional.setName(updateProfessionalData.name());
        if(updateProfessionalData.birthDate() != null)
            professional.setBirthDate(updateProfessionalData.birthDate());

        professionalRepository.updateProfessionalData(professional);
    }


}
