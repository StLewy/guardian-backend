package com.sl.guardianbackend.Repository;

import com.sl.guardianbackend.Model.Needy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NeedyRepository extends JpaRepository<Needy, Long> {

  @Query("SELECT n FROM Needy n WHERE n.status = 'O' ")
  @Override
  List<Needy> findAll();

  Optional<Needy> findByBankAccount(String bankAccount);

}
