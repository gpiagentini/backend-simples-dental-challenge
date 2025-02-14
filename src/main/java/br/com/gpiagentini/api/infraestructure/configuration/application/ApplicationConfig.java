package br.com.gpiagentini.api.infraestructure.configuration.application;

import br.com.gpiagentini.api.application.port.in.IFooAppService;
import br.com.gpiagentini.api.application.port.out.FooServiceRepository;
import br.com.gpiagentini.api.application.service.FooAppService;
import br.com.gpiagentini.api.domain.service.FooService;
import br.com.gpiagentini.api.domain.service.IFooService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public IFooAppService fooAppService(FooServiceRepository fooServiceRepository, IFooService fooService) {
        return new FooAppService(fooServiceRepository, fooService);
    }

    @Bean
    public IFooService fooService() {
        return new FooService();
    }



}