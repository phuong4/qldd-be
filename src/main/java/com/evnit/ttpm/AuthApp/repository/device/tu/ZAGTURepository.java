package com.evnit.ttpm.AuthApp.repository.device.tu;

import com.evnit.ttpm.AuthApp.entity.device.tu.ZAG_TU;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZAGTURepository extends JpaRepository<ZAG_TU,String> {
}
