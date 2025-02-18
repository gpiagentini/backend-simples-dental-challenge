package br.com.gpiagentini.api.infraestructure.configuration.application;

import br.com.gpiagentini.api.application.port.in.IContactApplicationService;
import br.com.gpiagentini.api.application.port.in.IProfessionalApplicationService;
import br.com.gpiagentini.api.application.port.out.IContactRepository;
import br.com.gpiagentini.api.application.port.out.IProfessionalRepository;
import br.com.gpiagentini.api.application.service.ContactApplicationService;
import br.com.gpiagentini.api.application.service.ProfessionalApplicationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public IProfessionalApplicationService professionalApplicationService(IProfessionalRepository professionalRepository) {
        return new ProfessionalApplicationService(professionalRepository);
    }

    @Bean
    public IContactApplicationService contactApplicationService(IContactRepository contactRepository) {
        return new ContactApplicationService(contactRepository);
    }

}