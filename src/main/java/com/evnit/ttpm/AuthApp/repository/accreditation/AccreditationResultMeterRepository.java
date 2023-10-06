package com.evnit.ttpm.AuthApp.repository.accreditation;


import com.evnit.ttpm.AuthApp.entity.accreditation.M_ACCREDITATION_RESULT_METER;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AccreditationResultMeterRepository extends JpaRepository<M_ACCREDITATION_RESULT_METER, String>, JpaSpecificationExecutor<M_ACCREDITATION_RESULT_METER> {
}
