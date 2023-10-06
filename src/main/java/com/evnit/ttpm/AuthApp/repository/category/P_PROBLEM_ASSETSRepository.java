package com.evnit.ttpm.AuthApp.repository.category;

import com.evnit.ttpm.AuthApp.entity.category.A_ASSET;
import com.evnit.ttpm.AuthApp.entity.category.P_PROBLEM_ASSETS;
import com.evnit.ttpm.AuthApp.model.category.SuCo.MapSuCoDiemDoCreateDto;
import com.evnit.ttpm.AuthApp.model.category.SuCo.MapSuCoDiemDoKey;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface P_PROBLEM_ASSETSRepository extends JpaRepository<P_PROBLEM_ASSETS, MapSuCoDiemDoKey> {

}
