package com.evnit.ttpm.AuthApp.repository.device.cuon;

import com.evnit.ttpm.AuthApp.entity.device.cuon.ZAG_CUON;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZAGCuonRepository extends JpaRepository<ZAG_CUON, String> {
}
