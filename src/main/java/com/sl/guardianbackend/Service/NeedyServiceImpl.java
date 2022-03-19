package com.sl.guardianbackend.Service;

import com.sl.guardianbackend.Model.Code;
import com.sl.guardianbackend.Model.Needy;
import com.sl.guardianbackend.Repository.NeedyRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class NeedyServiceImpl implements NeedyService {

  private final NeedyRepository needyRepository;
  private final CodesService codesService;

  @Override
  public List<Needy> getAllNeedyByStatus() {
    return needyRepository.findAll();
  }

  @Override
  public String addNeedy(Needy needy, String generateCode) {
    String result;
    Optional<Needy> needy1 = needyRepository.findByBankAccount(needy.getBankAccount());
    Optional<Code> code = codesService.findByGenerateCodeAndRegistration(generateCode, "N");

    if (code.isPresent()) {
      if (needy1.isPresent()) {
        result = "Z";
      } else {
        Code codeUpdate = new Code();
        codeUpdate.setId(code.get().getId());
        codeUpdate.setGenerateCode(code.get().getGenerateCode());
        codeUpdate.setUpdate(LocalDate.now());
        codeUpdate.setRegistration("Y");
        codeUpdate.setCreation(code.get().getCreation());

        codesService.updateCode(codeUpdate);

        needy.setCreation(LocalDate.now());
        needyRepository.save(needy);
        result = "D";
      }
    }else {
      result = "Z";
    }
    return result;
    }
  }
