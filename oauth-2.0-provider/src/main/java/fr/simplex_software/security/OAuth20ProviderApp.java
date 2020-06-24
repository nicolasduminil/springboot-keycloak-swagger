package fr.simplex_software.security;

import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.context.annotation.*;
import org.springframework.web.client.*;

@SpringBootApplication
public class OAuth20ProviderApp
{
  @Bean
  public RestTemplate getRestTemplate()
  {
    return new RestTemplate();
  }

  public static void main(String[] args)
  {
    SpringApplication.run(OAuth20ProviderApp.class, args);
  }
}
