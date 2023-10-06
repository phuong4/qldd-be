package com.evnit.ttpm.AuthApp.repository.category;

import com.evnit.ttpm.AuthApp.entity.category.KDLDD;
import com.evnit.ttpm.AuthApp.entity.category.WarningDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface KDLDDRepository extends JpaRepository<KDLDD,String>, JpaSpecificationExecutor<KDLDD> {
}
