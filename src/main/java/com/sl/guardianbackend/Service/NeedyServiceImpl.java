package com.sl.guardianbackend.Service;

import com.sl.guardianbackend.Model.Code;
import com.sl.guardianbackend.Model.DTO.NeedyDTO;
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
  public NeedyDTO addNeedy(NeedyDTO needy, String generateCode) {
    Optional<Needy> needy1 = needyRepository.findByBankAccount(needy.getBankAccount());
    Optional<Code> code = codesService.findByGenerateCodeAndRegistration(generateCode, "N");

    if (code.isPresent()) {
      if (needy1.isPresent()) {
        needy.setStatusResponese("K");
      } else {
        Code codeUpdate = new Code();
        codeUpdate.setId(code.get().getId());
        codeUpdate.setGenerateCode(code.get().getGenerateCode());
        codeUpdate.setUpdate(LocalDate.now());
        codeUpdate.setRegistration("Y");
        codeUpdate.setCreation(code.get().getCreation());

        codesService.updateCode(codeUpdate);
        Needy addNeedy = new Needy();
        addNeedy.setName(needy.getName());
        addNeedy.setCityUA(needy.getCityUA());
        addNeedy.setCityPL(needy.getCityPL());
        addNeedy.setBankAccount(needy.getBankAccount());
        addNeedy.setDescription(needy.getDescription());
        addNeedy.setCreation(LocalDate.now());
        addNeedy.setStatus("O");
        addNeedy.setEmail(needy.getEmail());
        needyRepository.save(addNeedy);

        needy.setStatusResponese("D");
      }
    }else {
      needy.setStatusResponese("C");
    }
    return needy;
    }
  }
