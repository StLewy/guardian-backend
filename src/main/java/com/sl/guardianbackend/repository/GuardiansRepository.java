package com.sl.guardianbackend.repository;

import com.sl.guardianbackend.model.Guardian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuardiansRepository extends JpaRepository<Guardian, Long> {

  Optional<Guardian> findByEmail(String email);
}
