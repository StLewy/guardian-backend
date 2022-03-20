package com.sl.guardianbackend.Controller;

import com.sl.guardianbackend.Model.DTO.NeedyDTO;
import com.sl.guardianbackend.Model.Needy;
import com.sl.guardianbackend.Service.NeedyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("needy")
@CrossOrigin(origins = "https://guardian-frontend-api.herokuapp.com/")
//@CrossOrigin(origins = "http://localhost:9090/")
@AllArgsConstructor
public class NeedyController {

  private final NeedyService needyService;

  @GetMapping("all-by-status")
  public ResponseEntity<List<Needy>> getAll(){
    List<Needy> needyList = needyService.getAllNeedyByStatus();
    return ResponseEntity.ok().body(needyList);
  }
  @PostMapping("add")
  public ResponseEntity<NeedyDTO> addNeedy(@RequestBody NeedyDTO needy){
    NeedyDTO addNeedy = needyService.addNeedy(needy, needy.getGenerateCode());
    return ResponseEntity.ok().body(addNeedy);
  }
}
