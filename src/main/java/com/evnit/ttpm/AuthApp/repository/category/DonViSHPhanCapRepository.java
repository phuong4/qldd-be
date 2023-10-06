package com.evnit.ttpm.AuthApp.repository.category;

import com.evnit.ttpm.AuthApp.entity.category.SCategoryDonViSH;
import com.evnit.ttpm.AuthApp.entity.category.v_DONVISH_PHANCAP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;


@Repository
public interface DonViSHPhanCapRepository extends JpaRepository<v_DONVISH_PHANCAP,Integer>, JpaSpecificationExecutor<v_DONVISH_PHANCAP> {
}
