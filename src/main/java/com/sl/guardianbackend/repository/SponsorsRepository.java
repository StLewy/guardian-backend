package com.sl.guardianbackend.repository;

import com.sl.guardianbackend.model.Sponsor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SponsorsRepository extends JpaRepository<Sponsor, Long> {
}
