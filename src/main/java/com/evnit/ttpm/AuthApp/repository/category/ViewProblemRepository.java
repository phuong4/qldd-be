package com.evnit.ttpm.AuthApp.repository.category;

import com.evnit.ttpm.AuthApp.entity.category.View_Deal;
import com.evnit.ttpm.AuthApp.entity.category.View_Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewProblemRepository extends JpaRepository<View_Problem,String>, JpaSpecificationExecutor<View_Problem> {
}
