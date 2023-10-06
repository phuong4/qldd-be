package com.evnit.ttpm.AuthApp.repository.acceptance;

import com.evnit.ttpm.AuthApp.entity.acceptance.ViewReplaceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewReplaceDetailRepo extends JpaRepository<ViewReplaceDetail, String> {
    List<ViewReplaceDetail> findByAcceptId(String id);
}
