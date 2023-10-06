package com.evnit.ttpm.AuthApp.repository.device.tu;

import com.evnit.ttpm.AuthApp.entity.device.tu.ViewTUDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewTUDeviceRepository extends JpaRepository<ViewTUDevice,String>, JpaSpecificationExecutor<ViewTUDevice> {
    @Query("Select c from ViewTUDevice c where c.isDelete = false and c.idBoTU =: id")
    List<String> getListDiemDoId(String id);
}
