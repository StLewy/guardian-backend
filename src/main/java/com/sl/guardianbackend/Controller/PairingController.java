package com.sl.guardianbackend.Controller;

import com.sl.guardianbackend.Model.DTO.PairingDTO;
import com.sl.guardianbackend.Service.PairingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pairing")
@AllArgsConstructor
@CrossOrigin(origins = "https://guardian-frontend-api.herokuapp.com/")
//@CrossOrigin(origins = "http://localhost:9090/")
public class PairingController {
  private final PairingService pairingService;
  @PostMapping("add")
  ResponseEntity<PairingDTO> addPairing(@RequestBody PairingDTO pairingDTO){
    PairingDTO addPairing = pairingService.addPairing(pairingDTO);
    return ResponseEntity.ok().body(addPairing);
  }
}
