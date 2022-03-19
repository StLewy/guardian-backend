package com.sl.guardianbackend.Controller;

import com.sl.guardianbackend.Model.Code;
import com.sl.guardianbackend.Service.CodesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("code")
@CrossOrigin(origins = "https://guardian-frontend-api.herokuapp.com/")
//@CrossOrigin(origins = "http://localhost:9090/")
@AllArgsConstructor
public class CodesController {
  private final CodesService codesService;

  @GetMapping("generate")
  public ResponseEntity<Code> getCode(){
    Code code = new Code();
    code.setGenerateCode(codesService.getCode()); ;
    return ResponseEntity.ok().body(code);
  }
}
