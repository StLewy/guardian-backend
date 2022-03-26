package com.sl.guardianbackend.service;

import com.sl.guardianbackend.model.DTO.PairingDTO;
import com.sl.guardianbackend.model.Needy;
import com.sl.guardianbackend.model.Pairing;
import com.sl.guardianbackend.repository.PairingRepository;
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
    pairingDTO.setStatusResponse("S");
    return pairingDTO;
  }
}
