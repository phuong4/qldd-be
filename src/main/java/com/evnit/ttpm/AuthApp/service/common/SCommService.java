package com.evnit.ttpm.AuthApp.service.common;

import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

@Service
public class SCommService {
    @Autowired
    private EntityManager em;

    // Hàm kiểm tra quyền chức năng
    public boolean checkRightFunc(String sUserId, String sFunctionId) {
        if (sUserId==null || sUserId.isEmpty()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            sUserId=((CustomUserDetails) authentication.getPrincipal()).getUserid();
        }

        String sql="SELECT FUNCTIONID FROM Q_PQFUNCTION_USER\n" +
                "\tWHERE USERID=?1 and functionid=?2\n" +
                "\tUNION\n" +
                "    SELECT FUNCTIONID FROM Q_PQFUNCTION_ROLE R INNER JOIN Q_USER_ROLE U ON R.ROLEID=U.ROLEID\n" +
                "\tWHERE U.ENABLE=1 AND U.USERID=?1 and R.functionid=?2\n";
        Query qr=em.createNativeQuery(sql);
        qr.setParameter(1,sUserId);
        qr.setParameter(2, sFunctionId);
        List lst=qr.getResultList();
        if (lst==null || lst.isEmpty())
            return  false;
        return true;

    }

    // Hàm kiểm tra quyền chức năng có cập nhật
    public boolean checkRightFuncWithWrite(String sUserId, String sFunctionId) {
        if (sUserId==null || sUserId.isEmpty()) {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            sUserId=((CustomUserDetails) authentication.getPrincipal()).getUserid();
        }

        String s;
        s = "SELECT FUNCTIONID, SUM(R) R, COUNT(*) C FROM (";
        s += "SELECT FUNCTIONID, CASE WHEN READONLY>=1 THEN 1 ELSE 0 END R FROM Q_PQFUNCTION_USER";
        s += " WHERE USERID=?1 and functionid=?2";
        s += " UNION";
        s += " SELECT FUNCTIONID, CASE WHEN READONLY>=1 THEN 1 ELSE 0 END R FROM Q_PQFUNCTION_ROLE R INNER JOIN Q_USER_ROLE U ON R.ROLEID=U.ROLEID";
        s += " WHERE U.ENABLE=1 AND U.USERID=?1 and R.functionid=?2";
        s += ") T GROUP BY FUNCTIONID";
        Query qr=em.createNativeQuery(s);
        qr.setParameter(1, sUserId);
        qr.setParameter(2, sFunctionId);
        List lst=qr.getResultList();
        if (lst==null || lst.isEmpty())
            return  false;
        Object[] oRow = (Object[]) lst.get(0);
        int r, c;
        r = Integer.parseInt(oRow[1].toString());
        c = Integer.parseInt(oRow[2].toString());
        if (r == c)
            return false;

        return true;
    }

}