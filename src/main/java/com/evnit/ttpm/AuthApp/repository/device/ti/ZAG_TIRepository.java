package com.evnit.ttpm.AuthApp.repository.device.ti;

import com.evnit.ttpm.AuthApp.entity.device.ti.ZAG_TI;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZAG_TIRepository extends JpaRepository<ZAG_TI, String> {
}
