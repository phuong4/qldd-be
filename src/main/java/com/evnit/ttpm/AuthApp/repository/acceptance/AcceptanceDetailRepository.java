package com.evnit.ttpm.AuthApp.repository.acceptance;

import com.evnit.ttpm.AuthApp.entity.acceptance.AcceptanceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AcceptanceDetailRepository extends JpaRepository<AcceptanceDetail,String> {
    List<AcceptanceDetail> findByAcceptId(String id);
    List<AcceptanceDetail> findByAssetId(String assetId);
}
