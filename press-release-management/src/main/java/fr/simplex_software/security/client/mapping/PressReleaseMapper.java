package fr.simplex_software.security.client.mapping;

import fr.simplex_software.security.client.data.*;
import fr.simplex_software.security.client.model.*;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface PressReleaseMapper
{
  @Mappings({@Mapping(source = "pressReleaseName", target = "name")})
  public PressRelease toPressRelease(PressReleaseEntity PressReleaseEntity);
  @InheritInverseConfiguration
  public PressReleaseEntity fromPressRelease(PressRelease PressRelease);
}
