package com.evnit.ttpm.AuthApp.repository.common;

import com.evnit.ttpm.AuthApp.entity.common.ViewHisMountUnDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewHisMountUnDeviceRepo extends JpaRepository<ViewHisMountUnDevice, String>, JpaSpecificationExecutor<ViewHisMountUnDevice> {
}
