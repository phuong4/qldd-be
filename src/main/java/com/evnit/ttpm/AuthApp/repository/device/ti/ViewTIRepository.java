package com.evnit.ttpm.AuthApp.repository.device.ti;

import com.evnit.ttpm.AuthApp.entity.device.ti.ViewTI;
import com.evnit.ttpm.AuthApp.entity.device.tu.ViewTU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ViewTIRepository extends JpaRepository<ViewTI, String>, JpaSpecificationExecutor<ViewTI> {
    @Query("Select c from ViewTI  c where c.serialNum =:serialNumber and c.ISDELETE = false  ")
    Optional<ViewTI> findBySerialNumber(String serialNumber);
    @Query("Select c from ViewTI  c where c.assetIdParent =:parentId and c.ISDELETE = false  ")
    List<ViewTI> findByParentId(String parentId);
    @Query("Select c from ViewTI  c where c.assetId =:id and c.ISDELETE = false  ")
    Optional<ViewTI> findViewTiById(String id);
}
