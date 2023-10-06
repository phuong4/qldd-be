package com.evnit.ttpm.AuthApp.repository.accreditation;

import com.evnit.ttpm.AuthApp.entity.accreditation.VIEW_KIEM_DINH_FINAL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ViewKiemDinhTableRepository extends JpaRepository<VIEW_KIEM_DINH_FINAL,String>, JpaSpecificationExecutor<VIEW_KIEM_DINH_FINAL> {
}
