package com.sl.guardianbackend.Controller;

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
  public ResponseEntity<String> addNeedy(@RequestBody Needy needy, @RequestParam String generateCode){
    String isAdd = needyService.addNeedy(needy, generateCode);
    return ResponseEntity.ok().body(isAdd);
  }
}
