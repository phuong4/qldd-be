package com.evnit.ttpm.AuthApp.repository.category;

import com.evnit.ttpm.AuthApp.entity.category.SCategoryTbaRgl;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.evnit.ttpm.AuthApp.entity.category.SPairForwardingUnits;
import com.evnit.ttpm.AuthApp.entity.category.ViewSPairForwardingUnits;

@Repository
/*public interface PairForwardingUnitsRepository extends JpaRepository<ViewSPairForwardingUnits,Integer>, JpaSpecificationExecutor<ViewSPairForwardingUnits> {
    @Query("Select case when count(c)> 0 then true else false end from ViewSPairForwardingUnits c where c.unit1 = :id or c.unit2 = :id and c.is_Delete = false")
    boolean existsByUnitId(Integer id);
}*/
public interface PairForwardingUnitsRepository extends JpaRepository<SPairForwardingUnits,Integer>, JpaSpecificationExecutor<SPairForwardingUnits> {
    @Query("Select case when count(c)> 0 then true else false end from SPairForwardingUnits c where c.unit1 = :id or c.unit2 = :id and c.is_Delete = false")
    boolean existsByUnitId(Integer id);
}


