package com.sl.guardianbackend.Controller;

import com.sl.guardianbackend.Service.CodesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("code")
@AllArgsConstructor
public class CodesController {
  private final CodesService codesService;

  @GetMapping("generate")
  public ResponseEntity<String> getCode(){
    String code = codesService.getCode();
    return ResponseEntity.ok().body(code);
  }
}
