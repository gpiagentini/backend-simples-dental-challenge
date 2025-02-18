package br.com.gpiagentini.api.application.service;

import br.com.gpiagentini.api.application.dto.NewProfessionalData;
import br.com.gpiagentini.api.application.dto.RetrieveProfessionalData;
import br.com.gpiagentini.api.application.dto.UpdateProfessionalData;
import br.com.gpiagentini.api.application.port.in.IProfessionalApplicationService;
import br.com.gpiagentini.api.application.port.out.IProfessionalRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public class ProfessionalApplicationService implements IProfessionalApplicationService {

    private final IProfessionalRepository professionalRepository;

    public ProfessionalApplicationService(IProfessionalRepository professionalRepository) {
        this.professionalRepository = professionalRepository;
    }

    @Override
    @Transactional
    public String createNewProfessional(NewProfessionalData newProfessionalData) {
        return professionalRepository.createNewProfessional(newProfessionalData);
    }

    @Override
    public RetrieveProfessionalData getProfessionalById(Long id) {
        var professional = professionalRepository.getProfessionalById(id);
        return new RetrieveProfessionalData(professional);
    }

    @Override
    public List<RetrieveProfessionalData> getAllProfessionals(String searchText) {
        var professionalList = professionalRepository.getAllProfessionals(searchText);
        return professionalList.stream().map(RetrieveProfessionalData::new).toList();
    }

    @Override
    @Transactional
    public void deleteProfessionalById(Long id) {
        professionalRepository.deleteProfessional(id);
    }

    @Override
    @Transactional
    public void updateProfessionalData(Long id, UpdateProfessionalData updateProfessionalData) {
        professionalRepository.updateProfessionalData(id, updateProfessionalData);
    }


}
