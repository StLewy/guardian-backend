package com.sl.guardianbackend.Repository;

import com.sl.guardianbackend.Model.Pairing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PairingRepository extends JpaRepository<Pairing, Long> {
}
