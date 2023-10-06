package com.evnit.ttpm.AuthApp.repository.accreditation;

import com.evnit.ttpm.AuthApp.entity.accreditation.M_ACCREDITATION;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface AccreditationRepository extends JpaRepository<M_ACCREDITATION, String>, JpaSpecificationExecutor<M_ACCREDITATION> {
    boolean existsByASSETID(String id);
}
