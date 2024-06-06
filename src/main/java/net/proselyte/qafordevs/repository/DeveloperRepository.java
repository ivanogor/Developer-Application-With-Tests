package net.proselyte.qafordevs.repository;


import net.proselyte.qafordevs.entity.DeveloperEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface DeveloperRepository extends JpaRepository<DeveloperEntity, Integer> {
    DeveloperEntity findByEmail(String email);

    @Query("SELECT d FROM DeveloperEntity d WHERE d.specialty = ?1 AND d.status = 'ACTIVE'")
    List<DeveloperEntity> findAllActiveBySpecialty(String specialty);
}
