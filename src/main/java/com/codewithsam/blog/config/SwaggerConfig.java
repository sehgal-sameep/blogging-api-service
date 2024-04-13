package com.codewithsam.blog.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Sameep Sehgal",
                        email = "sehgal.sameep21@gmail.com",
                        url = "https://www.linkedin.com/in/sameepsehgal/"
                ),
                description = "This interface provides documentation of various REST APIs to create, manage, delete blogs posted by various users. As the APIs are secured, add JWT Bearer token in the AUTHORIZE section( Get JWT by entering email and password using LOGIN API",
                title = "API Documentation of Blogging Service",
                version = "1.0"
        ),
        servers = {
                @Server(
                        description = "PROD ENV",
                        url = "http://blogging.ap-southeast-2.elasticbeanstalk.com"
                ),
                @Server(
                        description = "DEV ENV",
                        url = "http://localhost:9090"
                )
        },
        security = {
                @SecurityRequirement(name = "Authorization")
        }
)
@SecurityScheme(
        name = "Authorization",
        description = "JWT Auth Description",
        scheme = "Bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class SwaggerConfig {
}
