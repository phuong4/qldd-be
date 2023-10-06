package com.evnit.ttpm.AuthApp.repository.category;

import com.evnit.ttpm.AuthApp.entity.category.DMCXNK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;


@Repository
public interface QuanLyDDRepository extends JpaRepository<DMCXNK, Integer>, JpaSpecificationExecutor<DMCXNK> {
    @Query("Select case when count(c)> 0 then true else false end from DMCXNK c where c.PTGN1DVGN = :id or c.PTGN2DVGN = :id ")
    boolean existsByUnitId(String id);
}
