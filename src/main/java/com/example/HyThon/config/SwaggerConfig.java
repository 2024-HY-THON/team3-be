package com.example.HyThon.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
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

        // OpenAPI 객체 생성 및 구성
        return new OpenAPI()
                .addServersItem(new Server().url("/"))
                .info(apiInfo);
    }
}