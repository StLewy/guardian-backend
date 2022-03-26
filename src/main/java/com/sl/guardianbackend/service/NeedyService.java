package com.sl.guardianbackend.service;

import com.sl.guardianbackend.model.DTO.NeedyDTO;
import com.sl.guardianbackend.model.Needy;

import java.util.List;

public interface NeedyService {
  public List<Needy> getAllNeedyByStatus();

  public NeedyDTO addNeedy(NeedyDTO needy, String generateCode);

  public Needy updateNeedy(Needy needy);

  public Needy findNeedyById(Long id);
}
