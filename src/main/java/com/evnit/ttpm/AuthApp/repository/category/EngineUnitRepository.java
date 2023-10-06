package com.evnit.ttpm.AuthApp.repository.category;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.evnit.ttpm.AuthApp.entity.category.SEngineUnit;
import com.evnit.ttpm.AuthApp.model.category.EngineUnit.EngineUnitCreateDto;
import org.springframework.lang.Nullable;

@Repository
public interface EngineUnitRepository extends JpaRepository<SEngineUnit,Integer>{
	@Query("SELECT e FROM SEngineUnit e WHERE e.nmdId = :nmdId")
	List<SEngineUnit> findByNmdIdIsNull(@Param("nmdId") String nmdId);
	@Query("SELECT e FROM SEngineUnit e WHERE e.nmdId is null")
	List<SEngineUnit> findByNmdIdNull();
}
