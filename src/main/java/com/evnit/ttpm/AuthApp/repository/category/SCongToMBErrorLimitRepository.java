package com.evnit.ttpm.AuthApp.repository.category;

import com.evnit.ttpm.AuthApp.entity.category.SCongToErrorLimitMB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SCongToMBErrorLimitRepository extends JpaRepository<SCongToErrorLimitMB,Integer> {
    List<SCongToErrorLimitMB> findByExactLevel(Integer exactLevel);
}
