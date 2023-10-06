package com.evnit.ttpm.AuthApp.repository.category;

import com.evnit.ttpm.AuthApp.entity.category.PlanAccreditation;
import com.evnit.ttpm.AuthApp.entity.category.View_Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewPlanRepository extends JpaRepository<PlanAccreditation,String>, JpaSpecificationExecutor<PlanAccreditation> {
}
