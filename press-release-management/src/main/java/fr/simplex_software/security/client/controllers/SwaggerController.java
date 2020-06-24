package fr.simplex_software.security.client.controllers;

import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;

@Controller
public class SwaggerController
{
  @GetMapping("/")
  public String index()
  {
    return "redirect:swagger-ui.html";
  }
}
