package com.evnit.ttpm.AuthApp.repository.system;

import com.evnit.ttpm.AuthApp.entity.system.M_WORDER_HIS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WorderHisRepository extends JpaRepository<M_WORDER_HIS, String>, JpaSpecificationExecutor<M_WORDER_HIS> {
}
