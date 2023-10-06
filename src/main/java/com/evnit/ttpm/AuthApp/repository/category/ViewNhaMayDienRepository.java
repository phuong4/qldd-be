package com.evnit.ttpm.AuthApp.repository.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.evnit.ttpm.AuthApp.entity.category.ViewElectricFactory;


@Repository
public interface ViewNhaMayDienRepository extends JpaRepository<ViewElectricFactory,String>, JpaSpecificationExecutor<ViewElectricFactory> {

}
