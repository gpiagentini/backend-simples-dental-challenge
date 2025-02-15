package br.com.gpiagentini.api.infraestructure.configuration.docs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Value("${application.name}")
    private String projectName;

//    @Bean
//    GroupedOpenApi publicApi() {
//        return GroupedOpenApi.builder()
//                .group("public-apis")
//                .pathsToMatch("/**")
//                .build();
//    }

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title(projectName)
                        .version("1.0")
                        .description("Simples Dental Challenge")
                );
//                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
//                .components(
//                        new Components()
//                                .addSecuritySchemes("bearerAuth", new SecurityScheme()
//                                        .type(SecurityScheme.Type.HTTP)
//                                        .scheme("bearer")
//                                        .bearerFormat("JWT")));
    }
}
