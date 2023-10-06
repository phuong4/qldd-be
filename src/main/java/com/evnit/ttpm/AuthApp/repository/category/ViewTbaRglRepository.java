package com.evnit.ttpm.AuthApp.repository.category;

import com.evnit.ttpm.AuthApp.entity.category.ViewTbaRgl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewTbaRglRepository extends JpaRepository<ViewTbaRgl, String>, JpaSpecificationExecutor<ViewTbaRgl> {
}
