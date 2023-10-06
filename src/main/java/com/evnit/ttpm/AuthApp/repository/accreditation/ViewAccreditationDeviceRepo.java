package com.evnit.ttpm.AuthApp.repository.accreditation;

import com.evnit.ttpm.AuthApp.entity.accreditation.ViewAccreditationDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewAccreditationDeviceRepo extends JpaRepository<ViewAccreditationDevice, String> {
    List<ViewAccreditationDevice> findByAssetIdDevice(String assetDeviceId);
}
