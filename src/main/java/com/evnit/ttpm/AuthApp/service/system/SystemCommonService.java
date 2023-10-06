package com.evnit.ttpm.AuthApp.service.system;

import com.evnit.ttpm.AuthApp.model.system.S_Key_ControlInfo;
import org.springframework.stereotype.Service;

@Service
public interface SystemCommonService {
	S_Key_ControlInfo getGenKeyID(S_Key_ControlInfo keyControlInfo);

}
