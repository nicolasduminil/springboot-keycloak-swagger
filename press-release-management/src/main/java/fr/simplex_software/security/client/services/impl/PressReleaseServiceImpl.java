package fr.simplex_software.security.client.services.impl;

import fr.simplex_software.security.client.data.*;
import fr.simplex_software.security.client.mapping.*;
import fr.simplex_software.security.client.model.*;
import fr.simplex_software.security.client.repositories.*;
import fr.simplex_software.security.client.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;
import java.util.stream.*;

@Service
public class PressReleaseServiceImpl implements PressReleaseService
{
  private PressReleaseRepository pressReleaseRepository;
  private PressReleaseMapper pressReleaseMapper;

  @Autowired
  public PressReleaseServiceImpl(PressReleaseRepository pressReleaseRepository, PressReleaseMapper pressReleaseMapper)
  {
    this.pressReleaseRepository = pressReleaseRepository;
    this.pressReleaseMapper = pressReleaseMapper;
  }

  @Override
  public Optional<PressRelease> getPressReleaseById(int pressReleaseId)
  {
    return pressReleaseRepository.findById(pressReleaseId).map(pressReleaseEntity -> pressReleaseMapper.toPressRelease(pressReleaseEntity));
  }

  @Override
  public List<PressRelease> getPressReleases()
  {
    return pressReleaseRepository.findAll().parallelStream().map(pressReleaseEntity -> pressReleaseMapper.toPressRelease(pressReleaseEntity)).collect(Collectors.toList());
  }

  @Override
  public boolean deletePressReleaseById(int pressReleaseId)
  {
    pressReleaseRepository.deleteById(pressReleaseId);
    return true;
  }

  @Override
  public Optional<PressRelease> saveOrUpdatePressRelease(PressRelease pressRelease)
  {
    if (pressRelease.getPressReleaseId() == 0 || pressReleaseRepository.existsById(pressRelease.getPressReleaseId()))
    {
      PressReleaseEntity pressReleaseEntity = pressReleaseRepository.save(pressReleaseMapper.fromPressRelease(pressRelease));
      return Optional.of(pressReleaseMapper.toPressRelease(pressReleaseEntity));
    }
    else
    {
      return Optional.empty();
    }
  }
}

