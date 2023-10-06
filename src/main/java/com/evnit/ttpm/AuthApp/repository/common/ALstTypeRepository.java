package com.evnit.ttpm.AuthApp.repository.common;

import com.evnit.ttpm.AuthApp.entity.common.ALstType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ALstTypeRepository extends JpaRepository<ALstType,String> {
    List<ALstType> findByTypeIdParentAndIsVisibleIsFalse(String typeParent);
}
