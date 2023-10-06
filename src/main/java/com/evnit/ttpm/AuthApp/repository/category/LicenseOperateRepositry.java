package com.evnit.ttpm.AuthApp.repository.category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.evnit.ttpm.AuthApp.entity.category.SLicenseOperate;

@Repository
public interface LicenseOperateRepositry extends JpaRepository<SLicenseOperate,Integer> {
	@Query("SELECT e FROM SLicenseOperate e WHERE e.nmdId = :nmdId")
	List<SLicenseOperate> findByNmdIdIsNull(@Param("nmdId") String nmdId);
	@Query("SELECT e FROM SLicenseOperate e WHERE e.nmdId is null")
	List<SLicenseOperate> findByNmdIdNull();
}
