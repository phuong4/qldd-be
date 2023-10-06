package com.evnit.ttpm.AuthApp.repository.acceptance;

import com.evnit.ttpm.AuthApp.entity.acceptance.ViewAcceptAcceptChange;
import com.evnit.ttpm.AuthApp.entity.acceptance.ViewAcceptAcceptDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewAcceptAcceptChangeRepo extends JpaRepository<ViewAcceptAcceptChange, String> {
    @Query("Select c from ViewAcceptAcceptChange c where c.idDevice =:id order by c.actionEndDate desc ")
    List<ViewAcceptAcceptChange> findByIdDevice(String id);
}
