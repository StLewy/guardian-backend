package com.sl.guardianbackend.security.repository;

import com.sl.guardianbackend.security.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
