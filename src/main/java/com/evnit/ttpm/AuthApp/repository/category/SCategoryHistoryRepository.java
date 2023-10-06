package com.evnit.ttpm.AuthApp.repository.category;

import com.evnit.ttpm.AuthApp.entity.category.SCategoryHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SCategoryHistoryRepository extends JpaRepository<SCategoryHistory,Integer> {
    List<SCategoryHistory> findByCategoryTypeAndCategoryId(String categoryType, Integer categoryId);
}
