package com.sl.guardianbackend.Service;

import com.sl.guardianbackend.Model.DTO.GuardianDTO;
import com.sl.guardianbackend.Model.Guardian;
import com.sl.guardianbackend.Repository.GuardiansRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@AllArgsConstructor
public class GuardiansServiceImpl implements GuardiansService {

  private final GuardiansRepository guardiansRepository;

  @Override
  public GuardianDTO addGuardian(GuardianDTO guardianDTO) {

    Optional<Guardian> guardian = guardiansRepository.findByEmail(guardianDTO.getEmail());

    if (guardian.isPresent()){
      guardianDTO.setResponseStatus("E");
    } else {
      Guardian addGuardian = new Guardian();
      addGuardian.setName(guardianDTO.getName());
      addGuardian.setSurname(guardianDTO.getSurname());
      addGuardian.setPhone(guardianDTO.getPhone());
      addGuardian.setEmail(guardianDTO.getEmail());
      addGuardian.setCreation(LocalDate.now());
      guardiansRepository.save(addGuardian);

      guardianDTO.setResponseStatus("C");
    }
    return guardianDTO;
  }
}
