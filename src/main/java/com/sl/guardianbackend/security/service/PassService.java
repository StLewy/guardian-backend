package com.sl.guardianbackend.security.service;

public interface PassService {
    String generatePassword();

    String changePassword(String username, String oldPassword, String newPassword);

//    void recoveryPassword();

}
