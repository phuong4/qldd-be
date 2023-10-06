package com.evnit.ttpm.AuthApp.repository.category;

import com.evnit.ttpm.AuthApp.entity.category.SCategoryTbaRgl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;


@Repository

public interface TbaRglRepository extends JpaRepository<SCategoryTbaRgl, String>, JpaSpecificationExecutor<SCategoryTbaRgl> {
    @Query("Select case when count(c)> 0 then true else false end from SCategoryTbaRgl c where  c.isDelete = false and c.type=:type and (lower(c.tbaRglCode) like lower(:code) or lower(c.tbaRglName) like lower(:name))")
    boolean existsByCodeOrName(String code, String name, Integer type);

    @Query("Select  c from SCategoryTbaRgl c where c.isDelete = false and c.id != :id and c.type =:type and (lower(c.tbaRglCode) like lower(:code) or lower(c.tbaRglName) like lower(:name))")
    List<SCategoryTbaRgl> findByTbaRglCode(String code, String name, String id, Integer type);
}
