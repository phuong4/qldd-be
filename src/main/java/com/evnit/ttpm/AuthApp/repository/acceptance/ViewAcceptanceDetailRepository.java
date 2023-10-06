package com.evnit.ttpm.AuthApp.repository.acceptance;

import com.evnit.ttpm.AuthApp.entity.acceptance.ViewAcceptDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewAcceptanceDetailRepository extends JpaRepository<ViewAcceptDetail, String> {
    List<ViewAcceptDetail> findByAcceptId(String acceptId);

    @Query("Select c from ViewAcceptDetail  c where c.assetId =:id and c.hTTTSLAccept = true order by c.actionEndDate desc")
    List<ViewAcceptDetail> findByAssetId(String id);
}
