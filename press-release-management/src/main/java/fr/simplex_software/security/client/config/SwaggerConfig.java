package fr.simplex_software.security.client.config;

import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.*;
import springfox.documentation.builders.*;
import springfox.documentation.service.*;
import springfox.documentation.spi.*;
import springfox.documentation.spi.service.contexts.*;
import springfox.documentation.spring.web.plugins.*;
import springfox.documentation.swagger.web.*;
import springfox.documentation.swagger2.annotations.*;

import java.util.*;

@Configuration
@EnableSwagger2
public class SwaggerConfig
{
  @Value("${keycloak.realm}")
  private String realm;
  @Value("${swagger.auth.token-url:}")
  private String authTokenUrl;
  @Value("${swagger.auth.client-id:}")
  private String authClientId;
  private static final String PATH = "/api/.*";

  @Bean
  public Docket api()
  {
    return new Docket(DocumentationType.SWAGGER_2)
      .apiInfo(apiInfo())
      .select()
      .apis(RequestHandlerSelectors.basePackage("fr.simplex_software.security"))
      .paths(PathSelectors.regex(PATH))
      .build()
      .securityContexts(Collections.singletonList(securityContext()))
      .securitySchemes(Collections.singletonList(securitySchema()));
  }

  private ApiInfo apiInfo()
  {
    return new ApiInfoBuilder().title("Press Release Operation API")
      .description("Rest API for demonstrating the Keycloak integration with SpringBoot.")
      .contact(new Contact("Simplex Software EURL", "http://www.simplex-software.fr", "nicolas.duminil@simplex-software.fr"))
      .license("Copyright Simplex Software 2020 - All Rights Reserved")
      .licenseUrl("http://www.simplex-software.fr")
      .version("1.0-SNAPSHOT (12th june 2020)")
      .build();
  }

  @Bean
  public SecurityConfiguration securityInfo()
  {
    return new SecurityConfiguration(authClientId, "", realm, "", "", Collections.emptyMap(), false);
  }

  private OAuth securitySchema()
  {
    List<GrantType> grantTypes = Collections.singletonList(new ResourceOwnerPasswordCredentialsGrant(authTokenUrl));
    return new OAuth("oauth2", Collections.EMPTY_LIST, grantTypes);
  }

  private SecurityContext securityContext()
  {
    return SecurityContext.builder()
      .securityReferences(Collections.singletonList(new SecurityReference("oauth2", new AuthorizationScope[0])))
      .forPaths(PathSelectors.regex(PATH))
      .build();
  }
}
