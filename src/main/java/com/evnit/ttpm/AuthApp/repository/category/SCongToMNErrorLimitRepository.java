package com.evnit.ttpm.AuthApp.repository.category;

import com.evnit.ttpm.AuthApp.entity.category.SCongToErrorLimitMN;
import com.evnit.ttpm.AuthApp.entity.category.SCongToErrorLimitMT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SCongToMNErrorLimitRepository extends JpaRepository<SCongToErrorLimitMN,Integer> {
    List<SCongToErrorLimitMN> findByExactLevel(Integer exactLevel);
}
