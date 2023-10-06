package com.evnit.ttpm.AuthApp.repository.category;

import com.evnit.ttpm.AuthApp.entity.category.SCategoryTinhTP;
import com.evnit.ttpm.AuthApp.entity.category.SDeliveryUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@Repository
public interface TinhTPRepository extends JpaRepository<SCategoryTinhTP,Integer> , JpaSpecificationExecutor<SCategoryTinhTP> {
    @Query("Select c from SCategoryTinhTP  c where c.isdelete = false")
    List<SCategoryTinhTP> findAllByIsDelete();
    String FILTER_TINHTP_ON_NAME_QUERY = "select b from SCategoryTinhTP b where b.name like CONCAT('%',UPPER(?1),'%') and b.domain = ?2";

    @Query(FILTER_TINHTP_ON_NAME_QUERY)
    Page<SCategoryTinhTP> findByNameLike(String name, int domain,Pageable pageable);
}
