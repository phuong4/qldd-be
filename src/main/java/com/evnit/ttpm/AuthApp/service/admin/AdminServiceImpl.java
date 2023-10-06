package com.evnit.ttpm.AuthApp.service.admin;

import com.evnit.ttpm.AuthApp.entity.admin.Function;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.admin.S_Organization;
import com.evnit.ttpm.AuthApp.repository.admin.OrgRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AdminServiceImpl implements AdminService {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	OrgRepository orgRepository;



	@Override
	public List<Function> getAllQ_Function() {
		List<Function> lstReturn = new ArrayList<>();
		List<Function> lstParent = getAllQ_FunctionParent();
		int level = 1;
		for (Function collParent : lstParent) {
			// add cha
			// collParent.setLevel(level);

			// Lay con
			List<Function> lst = loadQ_FunctionChild(collParent);
			collParent.setListChild(lst);
			lstReturn.add(collParent);
		}
		return lstReturn;
	}

	public List<Function> getAllQ_FunctionParent() {
		String queryString = null;
		queryString = "SELECT FUNCTIONID,\n" + "       FUNCTIONNAME,\n"
				+ "       FUNCTION_PARENT_ID FUNCTIONPARENTID,\n" + "       IS_LAST,\n" + "       ENABLE,\n"
				+ "       HAVING_UPDATE,\n" + "       URL,\n" + "       URL_MOBILE,\n" + "       ICON,\n"
				+ "       ISAPP,\n" + "       ISPUPLIC,\n" + "       ISLOGIN,\n" + "       ISMENU,\n"
				+ "       ISMOBILE,\n" + "       CALLFUNCTION,\n" + "       FUNCTIONORD,\n" + "       USER_CR_ID,\n"
				+ "       USER_CR_DTIME,\n" + "       ISFLOW,\n" + "       COLOR,\n" + "       URL_EXTCHILD,\n"
				+ "       ISEXT,\n" + "       ISEXTCHILD,\n" + "       AUTH_LOCAL,\n" + "       AUTH_INTERNET\n"
				+ "  FROM Q_FUNCTION WHERE FUNCTION_PARENT_ID IS NULL ORDER BY FUNCTIONORD,FUNCTIONID";
		List<Function> lst = jdbcTemplate.query(queryString, new BeanPropertyRowMapper(Function.class));
		return lst;

	}

	public List<Function> loadQ_FunctionChild(List<Function> lstReturn, Function collParent, int level) {
		List<Function> lstChild = getQ_FunctionChild(collParent.getFunctionid());
		for (Function collChild : lstChild) {
			// Add con
			// collChild.setLevel(level);
			lstReturn.add(collChild);
			// De quy load con
			List<Function> lst = loadQ_FunctionChild(lstReturn, collChild, level + 1);
		}
		return lstReturn;
	}

	public List<Function> loadQ_FunctionChild(Function collParent) {
		List<Function> lstReturn = new ArrayList<>();
		List<Function> lstChild = getQ_FunctionChild(collParent.getFunctionid());
		for (Function collChild : lstChild) {
			// Add con
			// collChild.setLevel(level);
			// lstReturn.add(collChild);
			// De quy load con
			List<Function> lst = loadQ_FunctionChild(collChild);
			collChild.setListChild(lst);
			lstReturn.add(collChild);

		}
		return lstReturn;
	}

	public List<Function> getQ_FunctionChild(String parentid) {
		String queryString = "SELECT FUNCTIONID,FUNCTIONNAME,FUNCTION_PARENT_ID FUNCTIONPARENTID,ENABLE,URL,ISPUPLIC,ISLOGIN,ISMENU FROM Q_FUNCTION a WHERE a.FUNCTION_PARENT_ID = ? ORDER BY a.FUNCTIONORD,a.FUNCTIONID";
		List<Function> lst = jdbcTemplate.query(queryString, new Object[] { parentid },
				new BeanPropertyRowMapper(Function.class));
		return lst;
	}

	@Override
	public List<Function> getLstFuncByUserId(String userID, boolean bWithRole, boolean authNetworkType) {
		try {
			String s;
			s = "SELECT K.FUNCTIONID, SUM(R_VIEW) R_VIEW, COUNT(*) C" + ", SUM(R_CREATOR) R_CREATOR"
					+ ", SUM(R_EDIT) R_EDIT" + ", SUM(R_DELETE) R_DELETE,MAX(CAST(K.ISEXT AS int)) ISEXT FROM (";
			s += "SELECT FUNCTIONID, CASE WHEN ISNULL(R_VIEW,0)>=1 THEN 1 ELSE 0 END R_VIEW"
					+ ", CASE WHEN ISNULL(R_CREATOR,0)>=1 THEN 1 ELSE 0 END R_CREATOR "
					+ ", CASE WHEN ISNULL(R_EDIT,0)>=1 THEN 1 ELSE 0 END R_EDIT "
					+ ", CASE WHEN ISNULL(R_DELETE,0)>=1 THEN 1 ELSE 0 END R_DELETE " + " FROM Q_PQFUNCTION_USER";
			s += " WHERE USERID=?";
			if (!authNetworkType) {
				s += " AND AUTH_LOCAL=1";
			} else {
				s += " AND AUTH_INTERNET=1";
			}
			if (bWithRole) {
				s += " UNION";
				s += " SELECT FUNCTIONID, CASE WHEN ISNULL(R_VIEW,0)>=1 THEN 1 ELSE 0 END R_VIEW"
						+ ", CASE WHEN ISNULL(R_CREATOR,0)>=1 THEN 1 ELSE 0 END R_CREATOR "
						+ ", CASE WHEN ISNULL(R_EDIT,0)>=1 THEN 1 ELSE 0 END R_EDIT "
						+ ", CASE WHEN ISNULL(R_DELETE,0)>=1 THEN 1 ELSE 0 END R_DELETE "
						+ "FROM Q_PQFUNCTION_ROLE R INNER JOIN Q_USER_ROLE U ON R.ROLEID=U.ROLEID";
				s += " WHERE U.ENABLE=1 AND U.USERID=?";
				if (!authNetworkType) {
					s += " AND R.AUTH_LOCAL=1";
				} else {
					s += " AND R.AUTH_INTERNET=1";
				}
			}
			s += ") T INNER JOIN Q_FUNCTION K ON (T.FUNCTIONID=K.FUNCTIONID) " + " WHERE K.ENABLE=1";
			if (!authNetworkType) {
				s += " AND K.AUTH_LOCAL=1";
			} else {
				s += " AND K.AUTH_INTERNET=1";
			}
			s += " GROUP BY K.FUNCTIONID";

			// List<Object[]> lst = jdbcTemplate.query(s, new Object[]{userID}, Object[]);
			List<Function> lst = jdbcTemplate.query(s, new Object[] { userID, userID },
					new BeanPropertyRowMapper(Function.class));
			return lst;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<S_Organization> getALLOrg() {
		return orgRepository.findAll();
	}

	@Override
	public List<S_Organization> getALLOrgByParentID(String orgIDParent) {
		return orgRepository.getAllByOrgidParent(orgIDParent);
	}

	@Override
	public ResponseData getALLOrgByUserID(String userid) {
		ResponseData responseData = new ResponseData();
		try {
			String queryString = "SELECT *\n" + "FROM   S_ORGANIZATION so\n"
					+ "WHERE  SO.ORGID IN (SELECT DISTINCT org.ORGID\n"
					+ "                        FROM   S_ORGANIZATION org\n"
					+ "                        WHERE  org.active = 1\n"
					+ "                               AND org.orgid IN (SELECT orgid\n"
					+ "                                                 FROM   Q_Org_Grant_User\n"
					+ "                                                 WHERE  userid = ? \n"
					+ "                                                UNION SELECT sr.orgid\n"
					+ "                                                      FROM   Q_Org_Grant_Role \n"
					+ "                                                             sr\n"
					+ "                                                             INNER JOIN \n"
					+ "                                                                  Q_User_Role \n"
					+ "                                                                  ur\n"
					+ "                                                                  ON  sr.roleid = \n"
					+ "                                                                      ur.roleid\n"
					+ "                                                      WHERE  ur.userid = \n"
					+ "                                                             ?\n"
					+ "                                                             AND ur.enable = 1))\n"
					+ "ORDER BY\n" + "       so.orgord,\n" + "       so.orgdesc";
			List<S_Organization> lst = jdbcTemplate.query(queryString, new Object[] { userid, userid },
					new BeanPropertyRowMapper(S_Organization.class));
			responseData.setState(ResponseData.STATE.OK.toString());
			responseData.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
			responseData.setData(lst);

		} catch (Exception ex) {
			responseData.setState(ResponseData.STATE.FAIL.toString());
			responseData.setMessage(ex.getMessage());
		}
		return responseData;
	}

	@Override
	public S_Organization getOrgCurrent(String userid) {
		try {
			ResponseData responseData = getALLOrgByUserID(userid);
			List<S_Organization> lst = (List<S_Organization>) responseData.getData();
			if (lst.size() == 0)
				return null;
			else
				return lst.get(0);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
