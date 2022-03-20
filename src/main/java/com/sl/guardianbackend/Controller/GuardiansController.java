package com.sl.guardianbackend.Controller;

import com.sl.guardianbackend.Model.DTO.GuardianDTO;
import com.sl.guardianbackend.Service.GuardiansService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("guardian")
@CrossOrigin(origins = "https://guardian-frontend-api.herokuapp.com/")
//@CrossOrigin(origins = "http://localhost:9090/")
@AllArgsConstructor
public class GuardiansController {

  private final GuardiansService guardiansService;

  @PostMapping("add")
  ResponseEntity<GuardianDTO> addGuardian(@RequestBody GuardianDTO guardianDTO){
    GuardianDTO addGuardian = guardiansService.addGuardian(guardianDTO);
    return ResponseEntity.ok().body(addGuardian);
  }
}
