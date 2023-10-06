package com.evnit.ttpm.AuthApp.repository.device.tu;

import com.evnit.ttpm.AuthApp.entity.device.tu.ViewTuChildren;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewTUChildrenRepository extends JpaRepository<ViewTuChildren,String>, JpaSpecificationExecutor<ViewTuChildren> {
}
