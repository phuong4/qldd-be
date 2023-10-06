/*
****************************************************
*
*			TTPM-EVNICT
*
****************************************************
 */
package com.evnit.ttpm.AuthApp.util;

import com.evnit.ttpm.AuthApp.model.system.S_Key_ControlInfo;

import java.util.UUID;

public class Util {

	private Util() {
		throw new UnsupportedOperationException("Không thể khởi tạo lớp Util");
	}

	public static String generateRandomUuid() {
		return UUID.randomUUID().toString();
	}

	public static boolean checkIDStandard(String sID) {
		if (sID == null || sID.isEmpty()) {
			return true;
		}
		CharSequence cs1 = "'", cs2 = ";", cs3 = ",", cs4 = "|", cs5 = " ", cs6 = "-";
        return !sID.contains(cs1) && !sID.contains(cs2) && !sID.contains(cs3) && !sID.contains(cs4) && !sID.contains(cs5)
                && !sID.contains(cs6);
    }

	public static String showErrorFromGenKey(S_Key_ControlInfo controlInfo) {
		ResourcesFactory rf = new ResourcesFactory("commonMessages");

		if (controlInfo == null) {
			return rf.getMessage("msgIDNotDefine");
		} else if (controlInfo.getGenStatus() == 1) {
			return rf.getMessage("msgIDSmallerSValue") + controlInfo.getSvalue();
		} else if (controlInfo.getGenStatus() == 2) {
			return rf.getMessage("msgIDTooBig") + S_Key_ControlInfo.DELTA;
		} else if (controlInfo.getGenStatus() == 3) {
			return rf.getMessage("msgIDExist");
		} else if (controlInfo.getGenStatus() == 4) {
			return rf.getMessage("msgIDIncorrectForm");
		}
		return "";
	}

	public static boolean checkNullOrEmpty(String obj) {
		if (obj==null || obj.isEmpty()) return true;
		return false;
	}
}
