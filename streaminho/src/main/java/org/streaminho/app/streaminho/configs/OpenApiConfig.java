package org.streaminho.app.streaminho.configs;

import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI streamingApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Streaminho API")
                        .version("v1")
                        .description("REST API para la plataforma de streaming"));
    }
}
