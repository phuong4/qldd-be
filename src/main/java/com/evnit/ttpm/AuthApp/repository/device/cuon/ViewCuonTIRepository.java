package com.evnit.ttpm.AuthApp.repository.device.cuon;

import com.evnit.ttpm.AuthApp.entity.device.cuon.ViewCuon;
import com.evnit.ttpm.AuthApp.entity.device.cuon.ViewCuonTI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewCuonTIRepository extends JpaRepository<ViewCuonTI,String> {
}
