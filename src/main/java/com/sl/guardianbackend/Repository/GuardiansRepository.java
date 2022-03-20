package com.sl.guardianbackend.Repository;

import com.sl.guardianbackend.Model.Guardian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GuardiansRepository extends JpaRepository<Guardian, Long> {

  Optional<Guardian> findByEmail(String email);
}
