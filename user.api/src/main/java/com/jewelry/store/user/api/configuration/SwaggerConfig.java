package com.jewelry.store.user.api.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info=@Info(title = "Titulo da minha doc", description = "Microsserviço de autenticação", version = "v1", license = @License(name = "MIT", url = "http://localhost")))
public class SwaggerConfig {


}
