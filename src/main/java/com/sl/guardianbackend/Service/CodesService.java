package com.sl.guardianbackend.Service;

public interface CodesService {
  String generateCode();

  String getCode();

  boolean checkCode(String code);
}
