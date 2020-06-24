package fr.simplex_software.security.client.services;

import fr.simplex_software.security.client.model.*;

import java.util.*;

public interface PressReleaseService
{
  Optional<PressRelease> getPressReleaseById (int pressReleaseId);
  List<PressRelease> getPressReleases();
  boolean deletePressReleaseById(int pressReleaseId);
  Optional<PressRelease> saveOrUpdatePressRelease(PressRelease pressRelease);
}
