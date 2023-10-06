package com.evnit.ttpm.AuthApp.repository.device.ti;

import com.evnit.ttpm.AuthApp.entity.device.ti.ViewTIDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewTIDeviceRepository extends JpaRepository<ViewTIDevice, String>, JpaSpecificationExecutor<ViewTIDevice> {
}
