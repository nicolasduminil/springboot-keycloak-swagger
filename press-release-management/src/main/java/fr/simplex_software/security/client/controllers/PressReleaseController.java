package fr.simplex_software.security.client.controllers;

import fr.simplex_software.security.client.model.*;
import fr.simplex_software.security.client.services.*;
import io.swagger.annotations.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.*;
import javax.validation.*;
import java.util.*;

@RestController
@RequestMapping("/api")
@Slf4j
@Api(value = "Press Release API", description = "Operations to CRUD press releases")
@RolesAllowed({"prmanager"})
public class PressReleaseController
{
  private final PressReleaseService pressReleaseService;

  @Autowired
  public PressReleaseController(PressReleaseService pressReleaseService)
  {
    this.pressReleaseService = pressReleaseService;
  }

  @GetMapping("/all")
  @ApiOperation(value = "Returns the list of all the registred press releases", response = PressRelease.class, responseContainer = "Collection", authorizations = {@Authorization(value = "oauth2")})
  @ApiResponses(value =
    {
      @ApiResponse(code = 200, message = "Successfully retrieved list", response = PressRelease.class),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
  public Collection<PressRelease> getAllPressReleases()
  {
    return pressReleaseService.getPressReleases();
  }

  @GetMapping("/pressRelease/{id}")
  @ApiOperation(value = "Find a press release by its ID", response = PressRelease.class, responseContainer = "ResponseEntity", authorizations = {@Authorization(value = "oauth2")})
  @ApiResponses(value =
    {
      @ApiResponse(code = 200, message = "Successfully retrieved the press release record", response = PressRelease.class),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
  public ResponseEntity<PressRelease> getPressRelease(@ApiParam(value = "The press release ID", required = true) @Valid @PathVariable Integer id)
  {
    return pressReleaseService.getPressReleaseById(id).map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
  }

  @PostMapping("/add")
  @ApiOperation(value = "Create a new press release", response = PressRelease.class, responseContainer = "ResponseEntity", authorizations = {@Authorization(value = "oauth2")})
  @ApiResponses(value =
    {
      @ApiResponse(code = 201, message = "Successfully added the new press release record", response = PressRelease.class),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
  public ResponseEntity<PressRelease> addPressRelease(@RequestBody PressRelease pressRelease)
  {
    return pressReleaseService.saveOrUpdatePressRelease(pressRelease).map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED));
  }

  @PutMapping("/update")
  @ApiOperation(value = "Update a press release by its ID", response = PressRelease.class, responseContainer = "ResponseEntity", authorizations = {@Authorization(value = "oauth2")})
  @ApiResponses(value =
    {
      @ApiResponse(code = 200, message = "Successfully updated the press release record", response = PressRelease.class),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
  public ResponseEntity<PressRelease> editPressRelease(@RequestBody PressRelease pressRelease)
  {
    return pressReleaseService.saveOrUpdatePressRelease(pressRelease).map(ResponseEntity::ok).orElseGet(() -> new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED));
  }

  @DeleteMapping("/delete/{id}")
  @ApiOperation(value = "Remove a press release by its ID", response = String.class, responseContainer = "ResponseEntity", authorizations = {@Authorization(value = "oauth2")})
  @ApiResponses(value =
    {
      @ApiResponse(code = 200, message = "Successfully removed the press release", response = PressRelease.class),
      @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
      @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
      @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
  public ResponseEntity<String> deletePressRelease(@ApiParam(value = "The press release ID", required = true) @Valid @PathVariable("id") int pressReleaseId)
  {
    return pressReleaseService.deletePressReleaseById(pressReleaseId) ? ResponseEntity.ok("PressRelease is removed") : new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
  }
}
