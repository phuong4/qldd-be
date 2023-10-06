package com.evnit.ttpm.AuthApp.repository.category;

import com.evnit.ttpm.AuthApp.entity.category.SCategoryThoaThuan;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ThoaThuanRepository extends JpaRepository<SCategoryThoaThuan, String> {
    boolean existsByASSETIDAndISDELETEIsFalse(String id);
}
