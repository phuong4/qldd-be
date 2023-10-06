package com.evnit.ttpm.AuthApp.util;

public class SqlQuery {
	public static String sql_view_giaonhan = "select a.MA_GN,a.TEN_GN,c.MA_PTHUC,c.TEN_PTHUC,e.MA_DIEMDO,e.TEN_DIEMDO,f.MA_NHOM_DD,f.TEN_NHOM_DD,f.TYPEID from M_GNDN a join M_GNDN_PT b on a.MA_GN = b.MA_GN\n"
			+ "join M_PTHUC c on b.MA_PTHUC = c.MA_PTHUC\n" + "join M_PTHUC_ATTR d on c.MA_PTHUC = d.MA_PTHUC\n"
			+ "JOIN A_DIEMDO e on d.MA_DIEMDO = e.MA_DIEMDO\n" + "JOIN A_DIEMDO_NHOM f on e.MA_NHOM_DD = f.MA_NHOM_DD";
}
