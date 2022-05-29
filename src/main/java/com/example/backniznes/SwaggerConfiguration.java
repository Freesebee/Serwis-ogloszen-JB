package com.example.backniznes;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicate;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;
import static com.google.common.base.Predicates.or;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket SwaggerApi() {
        // @formatter:off
        //Register the controllers to swagger
        //Also it is configuring the Swagger Docket
        return new Docket(DocumentationType.SWAGGER_2).select()
            // .apis(RequestHandlerSelectors.any())
            .apis(Predicates.not(RequestHandlerSelectors.basePackage("org.springframework.boot")))
            // .paths(PathSelectors.any())
            // .paths(PathSelectors.ant("/swagger2-demo"))
            .build();
        // @formatter:on
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Demo with Swagger")
                .description("Spring Demo with Swagger")
                .contact(new Contact("Krzysztof Chrusciel",
                        "http://codecouple.pl",
                        "email@here.pl"))
                .license("License name here")
                .licenseUrl("URL to license")
                .version("1.0.1")
                .build();
    }

    private Predicate<String> getSwaggerPaths() {
        return or(
                regex("/api.*"),
                regex("/test.*"));
    }

}