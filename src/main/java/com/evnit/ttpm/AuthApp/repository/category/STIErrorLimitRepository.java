package com.evnit.ttpm.AuthApp.repository.category;

import com.evnit.ttpm.AuthApp.entity.category.STIErrorLimit;
import com.evnit.ttpm.AuthApp.entity.category.STUErrorLimit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface STIErrorLimitRepository extends JpaRepository<STIErrorLimit,Integer> {
}
