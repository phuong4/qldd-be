package com.evnit.ttpm.AuthApp.repository.device;

import com.evnit.ttpm.AuthApp.entity.device.ViewDiemDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewDiemDoRepository extends JpaRepository<ViewDiemDo, String> {
}
