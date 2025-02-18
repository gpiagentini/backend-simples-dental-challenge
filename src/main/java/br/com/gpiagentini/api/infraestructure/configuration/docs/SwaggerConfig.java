package br.com.gpiagentini.api.infraestructure.configuration.docs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${application.name}")
    private String projectName;

    
    /**
     * Custom OpenAPI Swagger configuration.
     */
    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(projectName)
                        .version("1.0")
                        .description("Simples Dental Challenge"));
    }
}
