package com.evnit.ttpm.AuthApp.repository.device;

import com.evnit.ttpm.AuthApp.entity.device.View_HTDD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import javax.swing.text.View;
import java.util.List;

@Repository
public interface ViewHTDDRepository extends JpaRepository<View_HTDD,String>, JpaSpecificationExecutor<View_HTDD> {

}
