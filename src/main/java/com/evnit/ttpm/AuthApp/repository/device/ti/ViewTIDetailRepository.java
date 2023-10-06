package com.evnit.ttpm.AuthApp.repository.device.ti;

import com.evnit.ttpm.AuthApp.entity.device.ti.ViewTIDetail;
import com.evnit.ttpm.AuthApp.entity.device.tu.ViewTU;
import com.evnit.ttpm.AuthApp.entity.device.tu.ViewTUDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ViewTIDetailRepository extends JpaRepository<ViewTIDetail,String>, JpaSpecificationExecutor<ViewTIDetail> {
    @Query("Select distinct c.idDiemDo from ViewTIDetail  c where c.ISDELETE = false and c.assetIdParent =: assetIdParent")
    List<String> getListDiemDoIdByParentId(String assetIdParent);
    @Query("Select distinct c.idDiemDo from ViewTIDetail  c where c.ISDELETE = false and c.assetId =: id")
    List<String> getListDiemDoId(String id);
    @Query("Select c from ViewTIDetail  c where c.serialNum =:serialNumber and c.ISDELETE = false  ")
    Optional<ViewTIDetail> findBySerialNumber(String serialNumber);
}
