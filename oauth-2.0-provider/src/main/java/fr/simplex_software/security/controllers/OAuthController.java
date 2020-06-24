package fr.simplex_software.security.controllers;

import fr.simplex_software.security.services.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Slf4j
public class OAuthController
{
  @Value("${keycloak.auth-server-url}")
  private String serverUrl;
  @Value("${keycloak.realm}")
  private String realm;
  @Value("${oauth-2.0-provider.user-name}")
  private String userName;
  @Value("${oauth-2.0-provider.user-password}")
  private String userPassword;
  @Value("${oauth-2.0-provider.client-id}")
  private String clientId;
  private final OAuthService ks;
  public OAuthController(OAuthService ks)
  {
    this.ks = ks;
  }

  @RequestMapping(value = "/accessToken", method = RequestMethod.GET)
  public String getAccessToken()
  {
    log.debug ("*** KeycloakRestController.getAccessToken: Entry {}, {}, {}, {}, {}", serverUrl, realm, userName, userPassword, clientId);
    String accessToken = ks.getAccessToken(serverUrl, realm, userName, userPassword, clientId);
    log.debug ("*** KeycloakRestController.getAccessToken: access token: {}", accessToken);
    return accessToken;
  }
}
