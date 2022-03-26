package com.sl.guardianbackend.security.repository;

import com.sl.guardianbackend.security.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findAppUserByUsername(String username);

    void deleteAppUserByUsername(String username);
}
