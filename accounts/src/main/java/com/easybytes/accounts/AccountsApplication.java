package com.easybytes.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;


/* If below components are not in this package hierarchy then below annotations are mandatory
@ComponentScans({ @ComponentScan("com.easybytes.accounts") })
@EnableJpaRepositories("com.easybytes.accounts")
@EntityScan("com.easybytes.accounts") */
@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
    info = @Info(
        title = "Accounts microservice Rest API Documentation",
        description = "Easy Bytes Accounts Microservice Rest API Documentation",
        version = "v1",
        contact = @Contact(
            name = "Niyank Bam",
            email = "jainniyank@gmail.com",
            url = "https://github.com/jainniyank"
        ),
        license = @License(
            name = "Apache 2.0",
            url = "https://www.apache.org/licenses/LICENSE-2.0"
        )
    ),
    externalDocs = @ExternalDocumentation(
        description = "Easy Bytes Accounts Microservice Rest API Documentation",
        url ="https://github.com/jainniyank"
    )
)
public class AccountsApplication {

  public static void main(String[] args) {
    SpringApplication.run(AccountsApplication.class, args);
  }

}
