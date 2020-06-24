package fr.simplex_software.security.services;

import org.keycloak.admin.client.*;
import org.springframework.stereotype.*;

@Service
public class OAuthService
{
  public String getAccessToken(String serverUrl, String realm, String userName, String password, String clientId)
  {
    return Keycloak.getInstance(serverUrl, realm, userName, password, clientId).tokenManager().getAccessToken().getToken();
  }
}
