package com.sl.guardianbackend.repository;

import com.sl.guardianbackend.model.Pairing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PairingRepository extends JpaRepository<Pairing, Long> {
}
