package com.evnit.ttpm.AuthApp.repository.acceptance;

import com.evnit.ttpm.AuthApp.entity.acceptance.ViewAcceptanceFinal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewAcceptanceFinalRepository extends JpaRepository<ViewAcceptanceFinal, String>, JpaSpecificationExecutor<ViewAcceptanceFinal> {
}
