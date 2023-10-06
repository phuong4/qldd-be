package com.evnit.ttpm.AuthApp.repository.category;

import com.evnit.ttpm.AuthApp.entity.category.SPairForwardingUnits;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import com.evnit.ttpm.AuthApp.entity.category.ViewSPairForwardingUnits;
import org.springframework.stereotype.Repository;

@Repository
public interface View_PairForwardingUnit extends JpaRepository<ViewSPairForwardingUnits,String>,JpaSpecificationExecutor<ViewSPairForwardingUnits> {
}
