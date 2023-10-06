package com.evnit.ttpm.AuthApp.repository.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.evnit.ttpm.AuthApp.entity.category.SElectricFactory;


@Repository
public interface NhaMayDienRepository extends JpaRepository<SElectricFactory,String>, JpaSpecificationExecutor<SElectricFactory> {

}
