package com.evnit.ttpm.AuthApp.repository.device.ti;

import com.evnit.ttpm.AuthApp.entity.device.ti.ViewSetTI;
import com.evnit.ttpm.AuthApp.entity.device.tu.ViewSetTU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewSetTIRepository extends JpaRepository<ViewSetTI, String>, JpaSpecificationExecutor<ViewSetTI> {
}
