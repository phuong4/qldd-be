package com.evnit.ttpm.AuthApp.repository.category;

import com.evnit.ttpm.AuthApp.entity.category.WarningDevice;
import com.evnit.ttpm.AuthApp.entity.category.WarningSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewWarningDeviceRepository extends JpaRepository<WarningDevice,String>, JpaSpecificationExecutor<WarningDevice> {
}
