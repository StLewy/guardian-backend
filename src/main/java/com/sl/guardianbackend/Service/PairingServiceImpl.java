package com.sl.guardianbackend.Service;

import com.sl.guardianbackend.Model.DTO.PairingDTO;
import com.sl.guardianbackend.Model.Needy;
import com.sl.guardianbackend.Model.Pairing;
import com.sl.guardianbackend.Repository.PairingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class PairingServiceImpl implements PairingService{

private final PairingRepository pairingRepository;
private final NeedyService needyService;

@Override
  public PairingDTO addPairing(PairingDTO pairingDTO) {
    Pairing addPairing = new Pairing();
    Needy findNeedy = needyService.findNeedyById(pairingDTO.getIdNeed());

    addPairing.setPeriod(pairingDTO.getPeriod());
    addPairing.setAmount(pairingDTO.getAmount());
    addPairing.setNeedy(findNeedy);
    addPairing.setSponsor(null);
    addPairing.setCreation(LocalDate.now());

    pairingRepository.save(addPairing);
    findNeedy.setStatus("W");
    needyService.updateNeedy(findNeedy);
    pairingDTO.setStatusResponese("S");
    return pairingDTO;
  }
}
