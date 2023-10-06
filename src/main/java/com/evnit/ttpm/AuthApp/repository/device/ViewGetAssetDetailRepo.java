package com.evnit.ttpm.AuthApp.repository.device;

import com.evnit.ttpm.AuthApp.entity.device.ViewGetAssetDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ViewGetAssetDetailRepo extends JpaRepository<ViewGetAssetDetail, String> {
    List<ViewGetAssetDetail> findByIdDiemDo(String idDiemDo);
}
