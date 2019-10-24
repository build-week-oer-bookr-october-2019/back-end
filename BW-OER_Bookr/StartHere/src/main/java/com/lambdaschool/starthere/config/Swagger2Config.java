package com.lambdaschool.starthere.config;

import com.fasterxml.classmate.TypeResolver;
import com.lambdaschool.starthere.models.APIOpenLibrary;
import com.lambdaschool.starthere.models.ErrorDetail;
import com.lambdaschool.starthere.models.TokenModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

// http://localhost:2019/swagger-ui.html
@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class Swagger2Config
{
    @Autowired
    private TypeResolver resolver;

    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2).select()
                                                      //                .apis(RequestHandlerSelectors.any())
                                                      .apis(RequestHandlerSelectors.basePackage("com.lambdaschool"))
                                                      .paths(PathSelectors.any())
                                                      .build()
                                                      .useDefaultResponseMessages(false) // Allows only my exception responses
                                                      .ignoredParameterTypes(Pageable.class) // allows only my paging parameter list
                                                      .apiInfo(apiEndPointsInfo())
                                                      .pathMapping("/")
                                                      .additionalModels(resolver.resolve(APIOpenLibrary.class),
                                                                        resolver.resolve(TokenModel.class),
                                                                        resolver.resolve(ErrorDetail.class))
                                                      .ignoredParameterTypes(SimpleGrantedAuthority.class);
    }

    private ApiInfo apiEndPointsInfo()
    {
        return new ApiInfoBuilder().title("OER-Bookr")
                                   .description("OER-Bookr is a teacher's source for open educational resources.")
                                   .contact(new Contact("Samir Lilienfeld",
                                                        "https://github.com/samir-hub",
                                                        "samirlilienfeld@hotmail.com"))
                                   .license("MIT")
                                   .licenseUrl("https://github.com/build-week-oer-bookr-october-2019/back-end/blob/master/LICENSE")
                                   .version("1.0.0")
                                   .build();
    }
}
