package com.sl.guardianbackend.service;

import com.sl.guardianbackend.model.Code;

import java.util.Optional;

public interface CodesService {
  String generateCode();

  String getCode();

  boolean checkCode(String code);

  Optional<Code> findByGenerateCodeAndRegistration(String generateCode, String registration);

  void updateCode(Code code);
}
