package com.example.HyThon.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI HythonOpenAPI() {
        // API 정보 설정
        Info apiInfo = new Info()
                .title("HY-THON team3")
                .description("HY-THON team3 API 명세서")
                .version("1.0.0");

        String jwtSchemeName = "Authorization";
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);
        Components components = new Components()
                .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
                        .name(jwtSchemeName)
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT"));

        // OpenAPI 객체 생성 및 구성
        return new OpenAPI()
                .info(apiInfo)
                .addServersItem(new Server().url("/"))
                .components(components)
                .addSecurityItem(securityRequirement);
    }
}