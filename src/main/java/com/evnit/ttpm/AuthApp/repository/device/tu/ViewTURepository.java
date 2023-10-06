package com.evnit.ttpm.AuthApp.repository.device.tu;

import com.evnit.ttpm.AuthApp.entity.device.tu.ViewTU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ViewTURepository extends JpaRepository<ViewTU, String>, JpaSpecificationExecutor<ViewTU> {
    @Query("Select c from ViewTU  c where c.serialNum =:serialNumber and c.ISDELETE = false  ")
    Optional<ViewTU> findBySerialNumber(String serialNumber);
    @Query("Select c from ViewTU  c where c.assetIdParent =:parentId and c.ISDELETE = false  ")
    List<ViewTU> findByParentId(String parentId);
    @Query("Select c from ViewTU  c where c.assetId =:id and c.ISDELETE = false  ")
    Optional<ViewTU> findViewTuById(String id);
}
