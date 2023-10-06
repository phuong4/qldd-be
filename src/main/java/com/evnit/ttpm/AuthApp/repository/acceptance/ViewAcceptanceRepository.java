package com.evnit.ttpm.AuthApp.repository.acceptance;

import com.evnit.ttpm.AuthApp.entity.acceptance.ViewAcceptance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewAcceptanceRepository extends JpaRepository<ViewAcceptance, String> {
    @Query("Select c from ViewAcceptance  c where c.idDevice =:id order by c.actionEndDate desc")
    List<ViewAcceptance> findByIdDevice(String id);
}
