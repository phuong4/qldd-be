package com.evnit.ttpm.AuthApp.repository.acceptance;

import com.evnit.ttpm.AuthApp.entity.acceptance.Acceptance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcceptanceRepository extends JpaRepository<Acceptance, String> {
    boolean existsByAssetId(String id);
}
