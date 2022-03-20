package com.sl.guardianbackend.Service;

import com.sl.guardianbackend.Model.DTO.NeedyDTO;
import com.sl.guardianbackend.Model.Needy;

import java.util.List;

public interface NeedyService {
  public List<Needy> getAllNeedyByStatus();

  public NeedyDTO addNeedy(NeedyDTO needy, String generateCode);
}
