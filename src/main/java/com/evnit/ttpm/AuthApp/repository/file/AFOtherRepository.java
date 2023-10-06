package com.evnit.ttpm.AuthApp.repository.file;

import com.evnit.ttpm.AuthApp.entity.file.AF_OTHER;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AFOtherRepository extends JpaRepository<AF_OTHER,String> {

}
