package com.sl.guardianbackend.Repository;

import com.sl.guardianbackend.Model.Code;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CodesRepository extends JpaRepository<Code,Long> {

  Optional<Code> findByGenerateCode(String code);

  Optional<Code> findByGenerateCodeAndRegistration(String code, String registration);
}
