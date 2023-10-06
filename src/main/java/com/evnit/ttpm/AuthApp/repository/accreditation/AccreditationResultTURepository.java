package com.evnit.ttpm.AuthApp.repository.accreditation;

import com.evnit.ttpm.AuthApp.entity.accreditation.M_ACCREDITATION_RESULT_TU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AccreditationResultTURepository extends JpaRepository<M_ACCREDITATION_RESULT_TU, String>, JpaSpecificationExecutor<M_ACCREDITATION_RESULT_TU> {
}
