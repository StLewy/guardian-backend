package com.sl.guardianbackend.Repository;

import com.sl.guardianbackend.Model.Needy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NeedyRepository extends JpaRepository<Needy, Long> {
}
