/*
 ****************************************************
 *
 *			TTPM-EVNICT
 *
 ****************************************************
 */
package com.evnit.ttpm.AuthApp.repository.system;

import com.evnit.ttpm.AuthApp.entity.system.S_Key_Control;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KeyControlRepository extends JpaRepository<S_Key_Control, String> {

}
