package com.evnit.ttpm.AuthApp.repository.category;

import com.evnit.ttpm.AuthApp.entity.category.SCategorySuCoBatThuong;
import com.evnit.ttpm.AuthApp.entity.category.SCategoryThoaThuan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface SuCoRepository extends JpaRepository<SCategorySuCoBatThuong, String> {
    boolean existsByASSETIDAndISDELETEIsFalse(String id);
}
