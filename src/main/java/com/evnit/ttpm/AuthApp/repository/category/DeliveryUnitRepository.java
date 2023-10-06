package com.evnit.ttpm.AuthApp.repository.category;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import com.evnit.ttpm.AuthApp.entity.category.SDeliveryUnit;

@Repository
public interface DeliveryUnitRepository extends JpaRepository<SDeliveryUnit,Integer>,JpaSpecificationExecutor<SDeliveryUnit>{

}
