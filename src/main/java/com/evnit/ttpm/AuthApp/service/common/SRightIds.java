package com.evnit.ttpm.AuthApp.service.common;

/*
--Câu lệnh sql khởi tạo class
select '    public static final String ' + replace(url,'-','_') + ' = "' + functionid + '";// ' + FUNCTIONNAME rights from Q_FUNCTION
where url is not null

select * from Q_USER
select * from Q_USER_ROLE
--Khởi tạo quyền theo role
insert into Q_PQFUNCTION_ROLE(FUNCTIONID, roleid)
select FUNCTIONID, 'ROLE_ADMIN' ROLEID from Q_FUNCTION where FUNCTIONID not in (
    select FUNCTIONID from Q_PQFUNCTION_ROLE where ROLEID='ROLE_ADMIN'
)
 */

public class SRightIds {
     public static final String danh_muc_chung = "11.01";// Danh mục chung

     public static final String customer = "12";

}