package com.evnit.ttpm.AuthApp.repository.category;

import com.evnit.ttpm.AuthApp.entity.category.SCongToErrorLimitMB;
import com.evnit.ttpm.AuthApp.entity.category.SCongToErrorLimitMT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SCongToMTErrorLimitRepository extends JpaRepository<SCongToErrorLimitMT,Integer> {
    List<SCongToErrorLimitMT> findByExactLevel(Integer exactLevel);
}
