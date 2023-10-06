package com.evnit.ttpm.AuthApp.service.report;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseDataReport;
import com.evnit.ttpm.AuthApp.entity.admin.S_Organization;
import com.evnit.ttpm.AuthApp.entity.system.S_Report;
import com.evnit.ttpm.AuthApp.entity.system.S_Report_Datalist;
import com.evnit.ttpm.AuthApp.entity.system.S_Report_Form;
import com.evnit.ttpm.AuthApp.repository.admin.OrgRepository;
import com.evnit.ttpm.AuthApp.repository.admin.UserRepository;
import com.evnit.ttpm.AuthApp.repository.report.ReportDataListRepository;
import com.evnit.ttpm.AuthApp.repository.report.ReportFormRepository;
import com.evnit.ttpm.AuthApp.repository.report.ReportRepository;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaClass;
import org.apache.commons.beanutils.DynaProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedCaseInsensitiveMap;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureQuery;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.sql.Types;
import java.util.*;

@Component
public class ReportViewServiceImpl implements ReportViewService {

	@Autowired
	ReportRepository reportRepository;
	@Autowired
	ReportFormRepository reportFormRepository;
	@Autowired
	ReportDataListRepository reportDataListRepository;

	@Autowired
	OrgRepository orgRepository;

	@Autowired
	UserRepository userRepository;

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	EntityManager entityManager;

	private SimpleJdbcCall simpleJdbcCall;

	@Override
	public ResponseData getListReportSystem() {
		ResponseData responseData = new ResponseData();
		try {
			List<S_Report> lst = reportRepository.getALLSystem();
			responseData.setState(ResponseData.STATE.OK.toString());
			responseData.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
			responseData.setData(lst);
			return responseData;
		} catch (Exception ex) {
			responseData.setState(ResponseData.STATE.FAIL.toString());
			responseData.setMessage(ex.getMessage());
			return responseData;
		}
	}

	@Override
	public ResponseData getListReportByUser(String rptGroupID,String userid) {
		ResponseData responseData = new ResponseData();
		try {
			List<S_Report> lst = reportRepository.getALLByUser(rptGroupID,userid);
			responseData.setState(ResponseData.STATE.OK.toString());
			responseData.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
			responseData.setData(lst);
			return responseData;
		} catch (Exception ex) {
			responseData.setState(ResponseData.STATE.FAIL.toString());
			responseData.setMessage(ex.getMessage());
			return responseData;
		}
	}


	@Override
	public ResponseData getListReportByGroup(String rptGroupID) {
		ResponseData responseData = new ResponseData();
		try {
			List<S_Report> lst = reportRepository.getALLByGroup(rptGroupID);
			responseData.setState(ResponseData.STATE.OK.toString());
			responseData.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
			responseData.setData(lst);
			return responseData;
		} catch (Exception ex) {
			responseData.setState(ResponseData.STATE.FAIL.toString());
			responseData.setMessage(ex.getMessage());
			return responseData;
		}
	}

	@Override
	public byte[] getViewReportFileExcel(String rptID, HashMap<String, Object> pramReport, String orgid,
			String userid) {

		ResponseDataReport responseData = new ResponseDataReport();
		try {

			String txtLog = "";
			// HashMap<String, Object> pramReport = new HashMap<>();
			List<Object[]> tempDatalist = new ArrayList<>();
			List<DynaBean> tempDatalistBean = new ArrayList<DynaBean>();
			HashMap<String, Object> resultReport = new HashMap<>();
			int numberRecord = 0;
			String reportHtmlView = "";
			List<S_Report_Form> lstReportForm = reportFormRepository.findAllByRptid(rptID);
			List<S_Report_Datalist> lstReportDataList = reportDataListRepository.findAllByRptid(rptID);
			S_Report report = reportRepository.findById(rptID).get();

			// Tham số dùng chung
			S_Organization tempOrg = orgRepository.findById(orgid).get();
			pramReport.put("MaDVQL", tempOrg.getOrgid());
			pramReport.put("TenDVQL", tempOrg.getOrgdesc());
			String g_TenDViCapTren = "";

			if (tempOrg.getOrgidParent() != null) {
				S_Organization g_so = orgRepository.findById(tempOrg.getOrgidParent()).get();
				if (g_so != null) {
					g_TenDViCapTren = g_so.getOrgdesc();
				}
			}

			pramReport.put("TenDVQLCapTren", g_TenDViCapTren);
			pramReport.put("UserName", userid);
			pramReport.put("UserDesc", userRepository.findByUserid(userid));
			pramReport.put("SysDate", new Date());

			// Get dataSource
			// Lay data list
			String strSQLTemp;
			String sQuery;
			String sQueryStand = "";
			Boolean bFlag;
			String tempParam;
			int iIndex;
			txtLog += "4. Chuẩn hóa câu lệnh và tổng hợp dữ liệu" + "\r\n";
			HashMap<Integer, String> pramTemp = new HashMap<>();
			for (int i = 0; i < lstReportDataList.size(); i++) {
				try {

					bFlag = false;
					tempParam = "";
					sQueryStand = "";
					iIndex = 1;
					sQuery = lstReportDataList.get(i).getQuery();
					sQuery = sQuery.replaceAll("(\r\n|\n|\t)", " ");
					sQuery = sQuery.replaceAll("\\s+", " ");
					for (int k = 0; k < sQuery.length(); k++) {
						if (sQuery.charAt(k) == '?') {
							bFlag = true;
						}
						if (bFlag && (sQuery.charAt(k) == ';' || sQuery.charAt(k) == ' '
								|| sQuery.charAt(k) == ',' || sQuery.charAt(k) == ')'
								|| sQuery.charAt(k) == '(' || k == sQuery.length() - 1)) {
							sQueryStand += "?" + iIndex;
							if (k == sQuery.length() - 1) {
								pramTemp.put(iIndex, tempParam);
								sQueryStand += sQuery.substring(k, k + 1);
								break;
							} else {
								pramTemp.put(iIndex, tempParam);
							}
							bFlag = false;
							tempParam = "";
							iIndex += 1;

						}
						if (bFlag && sQuery.charAt(k) != '?') {
							tempParam += sQuery.substring(k, k + 1);
						}
						if (!bFlag) {
							sQueryStand += sQuery.substring(k, k + 1);
						}
					}
				} catch (Exception e) {
					txtLog += "-" + i + ":" + e.getMessage() + "\r\n";
				}

				try {

					if (lstReportDataList.get(i).getCommandtypeid().equals("TEXT")) {
						tempDatalistBean = new ArrayList<DynaBean>();
						if (lstReportDataList.get(i).isLoadtype()) {
							tempDatalist = getListResult(sQueryStand, pramReport, pramTemp);
							if (tempDatalist.size() > 0) {
								DynaProperty[] proTy = new DynaProperty[tempDatalist.get(0).length];
								for (int m = 0; m < tempDatalist.get(0).length; m++) {
									proTy[m] = new DynaProperty("s" + m, Object.class);
								}
								DynaClass dBeanClass = new BasicDynaClass("DBean", null, proTy);

								DynaBean dBean;
								Object tempValue;
								for (int n = 0; n < tempDatalist.size(); n++) {
									dBean = dBeanClass.newInstance();

									for (int p = 0; p < tempDatalist.get(n).length; p++) {
										if (tempDatalist.get(n)[p] == null) {
											tempValue = "";
										} else {
											tempValue = tempDatalist.get(n)[p];
										}
										dBean.set("s" + p, tempValue);
									}
									tempDatalistBean.add(dBean);
								}
								resultReport.put(lstReportDataList.get(i).getDatalistdesc(), tempDatalistBean);
								tempDatalist = null;
								// tempDatalistBean = null;
							} else {
								resultReport.put(lstReportDataList.get(i).getDatalistdesc(), new ArrayList<Object[]>());
							}
							// Day du lieu vao DBEAN

						} else {
							// getIReportConfigRemote().execResult(sQueryStand, pramReport, pramTemp);
						}
					}
					if (lstReportDataList.get(i).getCommandtypeid().equals("PROC")) {
						tempDatalistBean = new ArrayList<DynaBean>();
						tempDatalist = getListResultProc(sQueryStand, pramReport, pramTemp, pramReport);
						if (lstReportDataList.get(i).isLoadtype()) {
							if (tempDatalist.size() > 0) {
								int len = tempDatalist.get(0).length;
								DynaProperty[] properties = new DynaProperty[tempDatalist.get(0).length];
								for (int m = 0; m < tempDatalist.get(0).length; m++) {
									properties[m] = new DynaProperty("s" + m, Object.class);
								}
								DynaClass dBeanClass = new BasicDynaClass("DBean", null, properties);
								DynaBean dBean;
								Object tempValue;
								for (int n = 0; n < tempDatalist.size(); n++) {
									dBean = dBeanClass.newInstance();
									for (int p = 0; p < tempDatalist.get(n).length; p++) {
										if (tempDatalist.get(n)[p] == null) {
											tempValue = "";
										} else {
											tempValue = tempDatalist.get(n)[p];
										}
										dBean.set("s" + p, tempValue);
									}
									tempDatalistBean.add(dBean);
								}
								resultReport.put(lstReportDataList.get(i).getDatalistdesc(), tempDatalistBean);
//                                tempDatalist = null;
								// tempDatalistBean = null;
							} else {
								resultReport.put(lstReportDataList.get(i).getDatalistdesc(), new ArrayList<Object[]>());
							}
						} else {
							// getIReportConfigRemote().execResultProc(sQueryStand, pramReport, pramTemp);
						}
					}
				} catch (Exception e) {
					txtLog += "-" + i + ":" + e.getMessage() + "\r\n";
				}
			}

			for (String key : pramReport.keySet()) {
				try {
					resultReport.put(key, pramReport.get(key));
				} catch (Exception e) {
				}
			}
			// End
			// Xuất excel
			S_Report_Form tempFile = lstReportForm.get(0);
			XLSTransformer transformer = new XLSTransformer();
			File tempFileOutput = File.createTempFile("tempFile", ".temp1");
			File tempFileInput = File.createTempFile("tempFile", ".temp2");
			FileOutputStream fos = new FileOutputStream(tempFileInput);

			fos.write(tempFile.getFiledata());
			transformer.transformXLS(tempFileInput.getAbsolutePath(), resultReport, tempFileOutput.getAbsolutePath());
			// ConvertExcelToHtml iConvertExcelToHtml = new ConvertExcelToHtml();
			// reportHtmlView = iConvertExcelToHtml.ConvertExcelToHtml(new
			// FileInputStream(tempFileOutput), false).toString();

			byte[] dataFile = Files.readAllBytes(tempFileOutput.toPath());
			// Đóng và xóa
			fos.close();
			tempFileInput.delete();
			tempFileOutput.deleteOnExit();

			return dataFile;

		} catch (Exception ex) {
			return null;
		}

	}

	@Override
	public ResponseDataReport getViewReport(String rptID, HashMap<String, Object> pramReport, String orgid,
			String userid) {

		ResponseDataReport responseData = new ResponseDataReport();
		try {

			String txtLog = "";
			// HashMap<String, Object> pramReport = new HashMap<>();
			List<Object[]> tempDatalist = new ArrayList<>();
			List<DynaBean> tempDatalistBean = new ArrayList<DynaBean>();
			HashMap<String, Object> resultReport = new HashMap<>();
			int numberRecord = 0;
			String reportHtmlView = "";
			List<S_Report_Form> lstReportForm = reportFormRepository.findAllByRptid(rptID);
			List<S_Report_Datalist> lstReportDataList = reportDataListRepository.findAllByRptid(rptID);
			S_Report report = reportRepository.findById(rptID).get();

			// Tham số dùng chung
			S_Organization tempOrg = orgRepository.findById(orgid).get();
			pramReport.put("MaDVQL", tempOrg.getOrgid());
			pramReport.put("TenDVQL", tempOrg.getOrgdesc());
			String g_TenDViCapTren = "";

			if (tempOrg.getOrgidParent() != null) {
				S_Organization g_so = orgRepository.findById(tempOrg.getOrgidParent()).get();
				if (g_so != null) {
					g_TenDViCapTren = g_so.getOrgdesc();
				}
			}

			pramReport.put("TenDVQLCapTren", g_TenDViCapTren);
			pramReport.put("UserName", userid);
			pramReport.put("UserDesc", userRepository.findByUserid(userid));
			pramReport.put("SysDate", new Date());

			// Get dataSource
			// Lay data list
			String strSQLTemp;
			String sQuery;
			String sQueryStand = "";
			Boolean bFlag;
			String tempParam;
			int iIndex;
			txtLog += "4. Chuẩn hóa câu lệnh và tổng hợp dữ liệu" + "\r\n";
			HashMap<Integer, String> pramTemp = new HashMap<>();
			for (int i = 0; i < lstReportDataList.size(); i++) {
				try {

					bFlag = false;
					tempParam = "";
					sQueryStand = "";
					iIndex = 1;
					sQuery = lstReportDataList.get(i).getQuery();
					sQuery = sQuery.replaceAll("(\r\n|\n|\t)", " ");
					sQuery = sQuery.replaceAll("\\s+", " ");
					for (int k = 0; k < sQuery.length(); k++) {
						if (sQuery.charAt(k) == '?') {
							bFlag = true;
						}
						if (bFlag && (sQuery.charAt(k) == ';' || sQuery.charAt(k) == ' '
								|| sQuery.charAt(k) == ',' || sQuery.charAt(k) == ')'
								|| sQuery.charAt(k) == '(' || k == sQuery.length() - 1)) {
							sQueryStand += "?" + iIndex;
							if (k == sQuery.length() - 1) {
								pramTemp.put(iIndex, tempParam);
								sQueryStand += sQuery.substring(k, k + 1);
								break;
							} else {
								pramTemp.put(iIndex, tempParam);
							}
							bFlag = false;
							tempParam = "";
							iIndex += 1;

						}
						if (bFlag && sQuery.charAt(k) != '?') {
							tempParam += sQuery.substring(k, k + 1);
						}
						if (!bFlag) {
							sQueryStand += sQuery.substring(k, k + 1);
						}
					}
				} catch (Exception e) {
					txtLog += "-" + i + ":" + e.getMessage() + "\r\n";
				}

				try {

					if (lstReportDataList.get(i).getCommandtypeid().equals("TEXT")) {
						tempDatalistBean = new ArrayList<DynaBean>();
						if (lstReportDataList.get(i).isLoadtype()) {
							tempDatalist = getListResult(sQueryStand, pramReport, pramTemp);
							if (tempDatalist.size() > 0) {
								DynaProperty[] proTy = new DynaProperty[tempDatalist.get(0).length];
								for (int m = 0; m < tempDatalist.get(0).length; m++) {
									proTy[m] = new DynaProperty("s" + m, Object.class);
								}
								DynaClass dBeanClass = new BasicDynaClass("DBean", null, proTy);

								DynaBean dBean;
								Object tempValue;
								for (int n = 0; n < tempDatalist.size(); n++) {
									dBean = dBeanClass.newInstance();

									for (int p = 0; p < tempDatalist.get(n).length; p++) {
										if (tempDatalist.get(n)[p] == null) {
											tempValue = "";
										} else {
											tempValue = tempDatalist.get(n)[p];
										}
										dBean.set("s" + p, tempValue);
									}
									tempDatalistBean.add(dBean);
								}
								resultReport.put(lstReportDataList.get(i).getDatalistdesc(), tempDatalistBean);
								tempDatalist = null;
								// tempDatalistBean = null;
							} else {
								resultReport.put(lstReportDataList.get(i).getDatalistdesc(), new ArrayList<Object[]>());
							}
							// Day du lieu vao DBEAN

						} else {
							// getIReportConfigRemote().execResult(sQueryStand, pramReport, pramTemp);
						}
					}
					if (lstReportDataList.get(i).getCommandtypeid().equals("PROC")) {
						tempDatalistBean = new ArrayList<DynaBean>();
						tempDatalist = getListResultProc(sQueryStand, pramReport, pramTemp, pramReport);
						if (lstReportDataList.get(i).isLoadtype()) {
							if (tempDatalist.size() > 0) {
								int len = tempDatalist.get(0).length;
								DynaProperty[] properties = new DynaProperty[tempDatalist.get(0).length];
								for (int m = 0; m < tempDatalist.get(0).length; m++) {
									properties[m] = new DynaProperty("s" + m, Object.class);
								}
								DynaClass dBeanClass = new BasicDynaClass("DBean", null, properties);
								DynaBean dBean;
								Object tempValue;
								for (int n = 0; n < tempDatalist.size(); n++) {
									dBean = dBeanClass.newInstance();
									for (int p = 0; p < tempDatalist.get(n).length; p++) {
										if (tempDatalist.get(n)[p] == null) {
											tempValue = "";
										} else {
											tempValue = tempDatalist.get(n)[p];
										}
										dBean.set("s" + p, tempValue);
									}
									tempDatalistBean.add(dBean);
								}
								resultReport.put(lstReportDataList.get(i).getDatalistdesc(), tempDatalistBean);
//                                tempDatalist = null;
								// tempDatalistBean = null;
							} else {
								resultReport.put(lstReportDataList.get(i).getDatalistdesc(), new ArrayList<Object[]>());
							}
						} else {
							// getIReportConfigRemote().execResultProc(sQueryStand, pramReport, pramTemp);
						}
					}
				} catch (Exception e) {
					txtLog += "-" + i + ":" + e.getMessage() + "\r\n";
				}
			}

			for (String key : pramReport.keySet()) {
				try {
					resultReport.put(key, pramReport.get(key));
				} catch (Exception e) {
				}
			}
			// End
			// Xuất excel
			S_Report_Form tempFile = lstReportForm.get(0);
			XLSTransformer transformer = new XLSTransformer();
			File tempFileOutput = File.createTempFile("tempFile", ".temp1");
			File tempFileInput = File.createTempFile("tempFile", ".temp2");
			FileOutputStream fos = new FileOutputStream(tempFileInput);

			fos.write(tempFile.getFiledata());
			transformer.transformXLS(tempFileInput.getAbsolutePath(), resultReport, tempFileOutput.getAbsolutePath());
			ConvertExcelToHtml iConvertExcelToHtml = new ConvertExcelToHtml();
			reportHtmlView = iConvertExcelToHtml.ConvertExcelToHtml(new FileInputStream(tempFileOutput), false)
					.toString();

			byte[] dataFile = Files.readAllBytes(tempFileOutput.toPath());

			// FileInputStream in = new FileInputStream(tempFileOutput);
			responseData.setData(tempDatalist);
			responseData.setDataFile(dataFile);
			responseData.setDataView(reportHtmlView);
			tempDatalist = null;

			// Đóng và xóa
			fos.close();
			tempFileInput.delete();
			tempFileOutput.deleteOnExit();

			responseData.setState(ResponseData.STATE.OK.toString());
			responseData.setMessage(ResponseData.MESSAGE.SUCCESS.toString());

		} catch (Exception ex) {
			responseData.setState(ResponseData.STATE.FAIL.toString());
			responseData.setMessage(ex.getMessage());
		}
		return responseData;
	}

	public List<Object[]> getListResult(String sql, HashMap<String, Object> pram, HashMap<Integer, String> pramV) {
		String queryString = "call " + sql;
		List<Object[]> lstObj = new ArrayList<>();
		Map<Integer, Object> parameters = new HashMap<Integer, Object>();
		for (Integer key : pramV.keySet()) {
			try {
				parameters.put(key, pram.get(pramV.get(key)));
			} catch (Exception e) {
			}
		}
		Object[] mPara = parameters.values().toArray();
		try {
			Object lst = jdbcTemplate.queryForList(sql, mPara);
			lstObj.add(new Object[] { lst });

		} catch (Exception e) {
			return null;
		}
		return lstObj;
	}

	public List<Object[]> getListResultProc(String sql, HashMap<String, Object> pram, HashMap<Integer, String> pramV,
			HashMap<String, Object> pramTypeReport) {
		String queryString = "";
		String[] procName = sql.split("\\(");
		List<Object[]> lst;
		if (procName.length > 0) {
			queryString = procName[0];
		} else {
			queryString = sql;
		}

		StoredProcedureQuery query = entityManager.createStoredProcedureQuery(queryString);
		String dataType = "";
		int nPara = 0;
		for (Integer key : pramV.keySet()) {
			try {

				dataType = pramTypeReport.get(pramV.get(key)).toString();
				nPara++;
				if (dataType.equals("DATE")) {
					query.registerStoredProcedureParameter(nPara, Date.class, ParameterMode.IN);
				} else if (dataType.equals("NUM")) {
					query.registerStoredProcedureParameter(nPara, Number.class, ParameterMode.IN);
				} else {
					query.registerStoredProcedureParameter(nPara, String.class, ParameterMode.IN);
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

		int indexPara = 0;
		for (Integer key : pramV.keySet()) {
			try {
				Object obj = pram.get(pramV.get(key));
				if (obj != null)
					query.setParameter(key, obj.toString());
				else {
					indexPara++;

					pramTypeReport.put(key.toString(), obj);
					query.registerStoredProcedureParameter(key, String.class, ParameterMode.IN);
					query.setParameter(key, pramV.get(key));
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		try {
			query.registerStoredProcedureParameter(pramV.size() + 1, void.class, ParameterMode.REF_CURSOR);
			if (query.execute()) {
				lst = query.getResultList();
			} else {
				lst = null;
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return lst;
	}

	public List<Object[]> getListResultProc1(String sql, HashMap<String, Object> pram, HashMap<Integer, String> pramV,
			HashMap<String, Object> pramTypeReport) {

		simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
		String queryString = "";
		String[] procName = sql.split("\\(");
		List<Object[]> lst = new ArrayList<>();
		if (procName.length > 0) {
			queryString = procName[0];
		} else {
			queryString = sql;
		}

		String[] pName = queryString.split("\\.");
		if (pName.length > 1) {
			simpleJdbcCall.withCatalogName(pName[0]);
			simpleJdbcCall.withProcedureName(pName[1]);
		} else {
			simpleJdbcCall.withProcedureName(pName[0]);
		}

		String dataType = "";
		int nindex = 0;

		SqlParameter para;
		for (Integer key : pramV.keySet()) {
			try {

				// para = new SqlParameter(pramV.get(key), Types.VARCHAR);
				para = new SqlParameter(nindex, Types.VARCHAR);
				simpleJdbcCall.addDeclaredParameter(para);
				nindex++;

			} catch (Exception e) {
			}
		}

		MapSqlParameterSource paramMap = new MapSqlParameterSource();

		SqlOutParameter outParameter = new SqlOutParameter("pCur", Types.REF_CURSOR);
		simpleJdbcCall.addDeclaredParameter(outParameter);

		for (Integer key : pramV.keySet()) {
			try {
				paramMap.addValue(pramV.get(key), pram.get(pramV.get(key)));
			} catch (Exception e) {
			}
		}

		try {
			Map<String, Object> resultMap = simpleJdbcCall.execute(paramMap);
			List<LinkedCaseInsensitiveMap<Object>> lstResult = (List<LinkedCaseInsensitiveMap<Object>>) resultMap
					.get("pCur");
			List<Object> lstObjValues = new ArrayList<>();

			for (int i = 0; i < lstResult.size(); i++) {
				LinkedCaseInsensitiveMap<Object> m_object = lstResult.get(i);
				Object[] arr = m_object.keySet().toArray();
				lstObjValues = new ArrayList<>();
				for (Object obj : arr) {
					Object objValue = m_object.get(obj);
					lstObjValues.add(objValue);
				}
				lst.add(lstObjValues.toArray());
			}

		} catch (Exception e) {
			return null;
		}
		return lst;
	}
}
