package org.example.trading.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI tradingOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Trading Platform API")
                        .description("RESTful API for trading platform operations")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Trading Platform Team")
                                .email("api@trading.com")
                                .url("https://trading.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .servers(List.of(
                        new Server().url("http://localhost:8080").description("Development Server"),
                        new Server().url("https://api.trading.com").description("Production Server")
                ));
    }
}
