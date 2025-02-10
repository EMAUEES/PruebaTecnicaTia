package com.prueba.demoVentas.config;


import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-07-02T14:50:01.213-05:00")

@Configuration
public class SwaggerDocumentationConfig {

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("API VENTAS TIA")
            .description("")
            .license("")
            .licenseUrl("")
            .termsOfServiceUrl("")
            .version("1.0.0")
            //.contact(new Contact("","", ""))
            .build();
    }

    @Bean
    public Docket customImplementation(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .paths(Predicates.not(PathSelectors.regex("/error")))
                .paths(Predicates.not(PathSelectors.regex("/")))
                    .build()
                .directModelSubstitute(org.threeten.bp.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(org.threeten.bp.OffsetDateTime.class, java.util.Date.class)
                .apiInfo(apiInfo());
    }

}
