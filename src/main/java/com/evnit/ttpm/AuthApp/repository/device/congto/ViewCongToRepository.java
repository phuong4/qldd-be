package com.evnit.ttpm.AuthApp.repository.device.congto;

import com.evnit.ttpm.AuthApp.entity.device.congto.ViewCongTo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ViewCongToRepository extends JpaRepository<ViewCongTo,String>, JpaSpecificationExecutor<ViewCongTo> {
    ViewCongTo findByASSETIDAndCATEGORYID(String assetId, String categoryType);

    List<ViewCongTo> findAllByCATEGORYID(String categoryId);

    @Query("Select c from ViewCongTo  c where c.SERIALNUM =:serialNumber and c.ISDELETE = false  ")
    Optional<ViewCongTo> findBySerialNumber(String serialNumber);
}
