package com.evnit.ttpm.AuthApp.repository.accreditation;

import com.evnit.ttpm.AuthApp.entity.accreditation.M_ACCREDITATION_DETAILS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AccreditationDetailRepository extends JpaRepository<M_ACCREDITATION_DETAILS, String>, JpaSpecificationExecutor<M_ACCREDITATION_DETAILS> {
}

