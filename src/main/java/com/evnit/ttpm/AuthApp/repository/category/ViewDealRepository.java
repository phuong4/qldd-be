package com.evnit.ttpm.AuthApp.repository.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.evnit.ttpm.AuthApp.entity.category.View_Deal;

@Repository
public interface ViewDealRepository extends JpaRepository<View_Deal,String>, JpaSpecificationExecutor<View_Deal> {
}
