package com.evnit.ttpm.AuthApp.repository.category;

import com.evnit.ttpm.AuthApp.entity.category.PARAMATER;
import com.evnit.ttpm.AuthApp.entity.category.P_PROBLEM_ASSETS;
import com.evnit.ttpm.AuthApp.entity.category.SCategorySuCoBatThuong;
import com.evnit.ttpm.AuthApp.model.category.SuCo.MapSuCoDiemDoKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PARAMATERRepository extends JpaRepository<PARAMATER, Integer> {



    }
