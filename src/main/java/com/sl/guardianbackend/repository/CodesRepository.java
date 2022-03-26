package com.sl.guardianbackend.repository;

import com.sl.guardianbackend.model.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CodesRepository extends JpaRepository<Code,Long> {

  Optional<Code> findByGenerateCode(String code);

  Optional<Code> findByGenerateCodeAndRegistration(String code, String registration);
}
