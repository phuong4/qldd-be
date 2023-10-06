package com.evnit.ttpm.AuthApp.repository.device.congto;

import com.evnit.ttpm.AuthApp.entity.device.congto.ZAG_CongTo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZAGCongToRepository extends JpaRepository<ZAG_CongTo,String> {
}
