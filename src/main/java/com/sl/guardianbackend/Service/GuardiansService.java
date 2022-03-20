package com.sl.guardianbackend.Service;

import com.sl.guardianbackend.Model.DTO.GuardianDTO;
import org.springframework.stereotype.Service;

public interface GuardiansService {
  GuardianDTO addGuardian(GuardianDTO guardianDTO);
}
