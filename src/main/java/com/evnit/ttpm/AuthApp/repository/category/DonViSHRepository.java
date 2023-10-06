package com.evnit.ttpm.AuthApp.repository.category;

import com.evnit.ttpm.AuthApp.entity.category.SCategoryDonViSH;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;



@Repository
public interface DonViSHRepository extends JpaRepository<SCategoryDonViSH, Integer>, JpaSpecificationExecutor<SCategoryDonViSH> {
    @Query("Select c from SCategoryDonViSH c where c.is_Delete = false")
    List<SCategoryDonViSH> findAllByIsDeleteFalse();

    //Chỉ lấy các đơn vị cấp 1 và 2
    @Query("Select c from SCategoryDonViSH c where c.is_Delete = false and c.type in (1,2)")
    List<SCategoryDonViSH> findAllByIsDeleteFalseType12();
}