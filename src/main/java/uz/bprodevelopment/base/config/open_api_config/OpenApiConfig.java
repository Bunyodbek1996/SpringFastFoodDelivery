package uz.bprodevelopment.base.config.open_api_config;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Bunyod",
                        email = "bunyodguru@gmail.com",
                        url = "http://bunyodbek.uz"
                ),
                description = "MCargo Api Documentation",
                title = "MCargo API",
                version = "1.0",
                license = @License (
                        name = "License name",
                        url = "http://some-url.com"
                ),
                termsOfService = "Terms of services"
        ),
        servers = {
                @Server(
                        description = "Local Development",
                        url = "http://localhost:9091"
                )
        },
        security = @SecurityRequirement(
                name = "bearerAuth"
        )
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
