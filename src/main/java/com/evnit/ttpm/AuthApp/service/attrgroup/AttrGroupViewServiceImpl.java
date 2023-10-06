package com.evnit.ttpm.AuthApp.service.attrgroup;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.attr.S_Attribute;
import com.evnit.ttpm.AuthApp.entity.common.OptionSelect;
import com.evnit.ttpm.AuthApp.model.request.attrgroup.AttrView;
import com.evnit.ttpm.AuthApp.repository.admin.OrgRepository;
import com.evnit.ttpm.AuthApp.repository.attr.AttrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class AttrGroupViewServiceImpl implements AttrGroupViewService {

	@Autowired
	AttrRepository attrRepository;
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	OrgRepository orgRepository;

	@Override
	public ResponseData getAttGroupView(String objID, String objTypeID, String orgidCurrent) {
		ResponseData responseData = new ResponseData();
		try {
//            S_Organization tempOrganization = orgRepository.getOne(orgidCurrent);

			SimpleDateFormat tempFormatDay = new SimpleDateFormat("dd");
			SimpleDateFormat tempFormatMonth = new SimpleDateFormat("M");
			SimpleDateFormat tempFormatYear = new SimpleDateFormat("yyyy");

			OptionSelect optionSelect;

			AttrView attrView;
			List<AttrView> listAttrView = new ArrayList<>();
			List<S_Attribute> lstAttr = attrRepository.getAttrGroupView(objID, objTypeID);
			for (S_Attribute s : lstAttr) {
				attrView = new AttrView();
				attrView.setId(s.getColname());
				attrView.setName(s.getAttrdesc());
				attrView.setType(s.getAtttypeid());
				attrView.setDatatype(s.getColdatatypeid());

				if (s.getColdefault() == null)
					attrView.setDefaultvalue("");
				else
					attrView.setDefaultvalue(s.getColdefault());

				if (s.getAtttypeid().equals("INPUT")) {
					if (s.getColdatatypeid().equals("DATE")) {
						attrView.setValue(new Date());
					} else {
						if (s.getColdefault() == null)
							attrView.setValue("");
						else
							attrView.setValue(s.getColdefault());
					}
				} else if (s.getAtttypeid().equals("CBLST")) {
					// Chạy câu lệnh để lấy data
					Object lstObj = runQueryList(s.getDataquerylst(), null);
					attrView.setOptions(lstObj);

					switch (attrView.getDefaultvalue()) {
//                        case "ORGID":
//                            attrView.setValue(tempOrganization.getOrgid());
//                            break;
//                        case "ORGID_PARENT":
//                            //obj.setColStr4(tempOrganization.getsOrgidParent().getOrgid() + " - " + tempOrganization.getsOrgidParent().getOrgdesc());
//                            break;
					case "DAY":
						attrView.setValue(tempFormatDay.format(new Date()));
						break;
					case "QUARTER":
						Integer curMonth = Integer.parseInt(tempFormatMonth.format(new Date()));
						Integer curQuarter = 1;
						if (curMonth <= 3) {
							curQuarter = 1;
						} else if (curMonth > 3 && curMonth <= 6) {
							curQuarter = 2;
						} else if (curMonth > 6 && curMonth <= 9) {
							curQuarter = 3;
						} else if (curMonth > 9 && curMonth <= 12) {
							curQuarter = 4;
						}
						attrView.setValue((curQuarter.toString()));
						break;
					case "MONTH":
						attrView.setValue(tempFormatMonth.format(new Date()));
						break;
					case "YEAR":
						attrView.setValue((tempFormatYear.format(new Date())));
						break;
					}
				}

				listAttrView.add(attrView);
			}

			responseData.setState(ResponseData.STATE.OK.toString());
			responseData.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
			responseData.setData(listAttrView);
		} catch (Exception ex) {
			responseData.setState(ResponseData.STATE.FAIL.toString());
			responseData.setMessage(ex.getMessage());
		}
		return responseData;
	}

	@Override
	public Object runQueryList(String sql, Object[] para) {

		try {
			Object lst = jdbcTemplate.queryForList(sql, para);
			/// List<OptionSelect> lst = jdbcTemplate.query(sql,para,OptionSelect.class);

			return lst;
		} catch (Exception ex) {
			return null;
		}
	}

}
