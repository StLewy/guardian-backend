package com.sl.guardianbackend.Service;

import com.sl.guardianbackend.Model.Code;

import java.util.Optional;

public interface CodesService {
  String generateCode();

  String getCode();

  boolean checkCode(String code);

  Optional<Code> findByGenerateCodeAndRegistration(String generateCode, String registration);

  void updateCode(Code code);
}
