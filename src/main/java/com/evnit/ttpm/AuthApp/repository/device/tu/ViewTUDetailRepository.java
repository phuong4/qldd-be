package com.evnit.ttpm.AuthApp.repository.device.tu;

import com.evnit.ttpm.AuthApp.entity.device.tu.ViewTUDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewTUDetailRepository extends JpaRepository<ViewTUDetail,String>, JpaSpecificationExecutor<ViewTUDetail> {
    @Query("Select distinct c.idDiemDo from ViewTUDetail  c where c.ISDELETE = false and c.assetIdParent =: assetIdParent")
    List<String> getListDiemDoIdByParentId(String assetIdParent);
    @Query("Select distinct c.idDiemDo from ViewTUDetail  c where c.ISDELETE = false and c.assetId =: id")
    List<String> getListDiemDoId(String id);

}
