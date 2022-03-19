package com.sl.guardianbackend.Service;

import com.sl.guardianbackend.Model.Needy;

import java.util.List;

public interface NeedyService {
  public List<Needy> getAllNeedyByStatus();

  public String addNeedy(Needy needy, String generateCode);
}
