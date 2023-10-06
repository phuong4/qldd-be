package com.evnit.ttpm.AuthApp.repository.category;

import com.evnit.ttpm.AuthApp.entity.category.PlanAccreditation;
import com.evnit.ttpm.AuthApp.entity.category.WarningSystem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewWarningSystemRepository extends JpaRepository<WarningSystem,String>, JpaSpecificationExecutor<WarningSystem> {
}
