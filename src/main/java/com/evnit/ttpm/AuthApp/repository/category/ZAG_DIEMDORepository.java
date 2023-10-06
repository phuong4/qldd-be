package com.evnit.ttpm.AuthApp.repository.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.evnit.ttpm.AuthApp.entity.category.A_ASSET;
import com.evnit.ttpm.AuthApp.entity.category.SDeliveryUnit;
import com.evnit.ttpm.AuthApp.entity.category.ZAG_DIEMDO;

@Repository
public interface ZAG_DIEMDORepository extends JpaRepository<ZAG_DIEMDO,String>{
}
