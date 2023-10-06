package com.evnit.ttpm.AuthApp.repository.category;

import com.evnit.ttpm.AuthApp.entity.category.SCategoryTinhTP;
import com.evnit.ttpm.AuthApp.entity.category.ViewTinhTP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ViewTinhTPRepository extends JpaRepository<ViewTinhTP,Integer> , JpaSpecificationExecutor<ViewTinhTP> {
}
