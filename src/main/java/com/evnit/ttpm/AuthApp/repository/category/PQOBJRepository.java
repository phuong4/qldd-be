package com.evnit.ttpm.AuthApp.repository.category;

import com.evnit.ttpm.AuthApp.entity.category.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
    public interface PQOBJRepository extends JpaRepository<Q_PQOBJ_USER, Q_PQOBJ_USERKEY> {
    }
