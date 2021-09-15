package xyz.dvnlabs.serviceorder.core

import io.swagger.annotations.ApiOperation
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.builders.RequestHandlerSelectors
import springfox.documentation.service.Contact
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {
    @Bean
    fun api(): Docket {
        return Docket(DocumentationType.SWAGGER_2)
            .apiInfo(
                ApiInfoBuilder()
                    .title("Microservice User Service")
                    .description("API For Microservice")
                    .contact(
                        Contact(
                            "Davin Alfarizky Putra Basudewa",
                            "https://dvnlabs.xyz",
                            "dbasudewa@gmail.com"
                        )
                    )
                    .version("Mager gue!!!")
                    .build()
            )
            .select()
            .apis(RequestHandlerSelectors.basePackage("xyz.dvnlabs.serviceorder.controller"))
            .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation::class.java))
            .paths(PathSelectors.any())
            .build()
    }
}