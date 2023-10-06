package com.evnit.ttpm.AuthApp.service.system;

import com.evnit.ttpm.AuthApp.entity.system.S_Key_Control;
import com.evnit.ttpm.AuthApp.model.system.S_Key_ControlInfo;
import com.evnit.ttpm.AuthApp.repository.system.KeyControlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
public class SystemCommonServiceImpl implements SystemCommonService {

	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	KeyControlRepository repository;

	@Override
	public S_Key_ControlInfo getGenKeyID(S_Key_ControlInfo keyControlInfo) {

		try {
			// ThảoDD: Thêm lệnh này để xử lý tránh bị lấy lại dữ liệu cũ khi gọi hàm này
			// nhiều lần: AssetBL.loadMeterTemplateToAssetMeterDyn
			// entityManager.flush();
			// entityManager.lock(S_Key_Control.class, LockModeType.OPTIMISTIC);
			S_Key_ControlInfo keyReturn = new S_Key_ControlInfo();
			// Lấy Svalue hiện tại
			// KeyControlDAL dal = new KeyControlDAL(entityManager);
			S_Key_Control s_Key_Control = null;
			// if (keyControlInfo!=null)
			Optional<S_Key_Control> optional = repository.findById(keyControlInfo.getTable().toUpperCase());
			if (optional.isPresent()) {
				s_Key_Control = optional.get();
			} else {
				return null;
			}

//            s_Key_Control = (S_Key_Control) dal.findById(keyControlInfo.getTable().toUpperCase());
//            if (s_Key_Control == null) {
//                return null;
//            }
			// Lock read/write
			// entityManager.lock(s_Key_Control, LockModeType.PESSIMISTIC_WRITE);
			String pattern = "^[\\w.]+$";
			Pattern r = Pattern.compile(pattern);
			keyReturn.setSvalue(Integer.parseInt(s_Key_Control.getSvalue().trim()));
			keyControlInfo.setColID(s_Key_Control.getPram1());
			if (keyControlInfo.getInputValue() == null || keyControlInfo.getInputValue().isEmpty())// Neu khong nhap
			{
				// Sinh mã và gán svalue + 1
				int svalue = keyReturn.getSvalue() + 1;
				keyReturn.setOutputValue(getKeyID(s_Key_Control, String.valueOf(svalue)));
				s_Key_Control.setSvalue(String.valueOf(svalue));
				keyReturn.setGenStatus(0);
				keyReturn.setSvalue(svalue);
				return keyReturn;
			} else if (isInteger(keyControlInfo.getInputValue()))// Nếu nhập số
			{
				// Now create matcher object.
				Matcher m = r.matcher(keyControlInfo.getInputValue());
				if (!m.matches()) {
					keyReturn.setGenStatus(4);
					// throw new EJBException("Kí tự nhập không hợp lệ, không có dấu cách và các kí
					// tự đặc biệt");
					return keyReturn;
				}
				int inputValue = Integer.parseInt(keyControlInfo.getInputValue());
				if (inputValue <= 0 || inputValue == keyReturn.getSvalue()) {
					keyReturn.setGenStatus(1);
					return keyReturn;
				}
				if (inputValue > keyReturn.getSvalue()) {
					// So sanh delta
					int delta = inputValue - keyReturn.getSvalue();
					if (delta > S_Key_ControlInfo.DELTA) {
						keyReturn.setGenStatus(2);
						return keyReturn;
					}
					// Sinh mã và gán svalue = inputValue
					keyReturn.setOutputValue(getKeyID(s_Key_Control, String.valueOf(inputValue)));
					s_Key_Control.setSvalue(String.valueOf(inputValue));
					keyReturn.setGenStatus(0);
					keyReturn.setSvalue(inputValue);
					return keyReturn;
				} else {
					String keyID = getKeyID(s_Key_Control, String.valueOf(inputValue));
					// Tim key da ton tai chua
					if (checkIDExist(keyID, keyControlInfo)) {
						keyReturn.setGenStatus(1);
						return keyReturn;
					} else {
						keyReturn.setOutputValue(keyID);
						keyReturn.setGenStatus(0);
						keyReturn.setSvalue(inputValue);
						return keyReturn;
					}
				}
			} else// Neu nhap chu
			{
				// Kiem tra dinh dang
				// Now create matcher object.
				Matcher m = r.matcher(keyControlInfo.getInputValue());
				if (!m.matches()) {
					// return null;
					keyReturn.setGenStatus(4);
					return keyReturn;
					// throw new EJBException("Kí tự nhập không hợp lệ, không có dấu cách và các kí
					// tự đặc biệt");
				}
				String keyFormat = getKeyID(s_Key_Control, "abc");
				if (isFormat(keyControlInfo.getInputValue(), keyFormat))// Dung dinh dang
				{
					// ThảoDD sửa
					int i = keyFormat.lastIndexOf(".");
					String sKeyPre = "", sInputPre = "";
					if (i >= 0) {
						sKeyPre = keyFormat.substring(0, i);
					}
					i = keyControlInfo.getInputValue().lastIndexOf(".");
					if (i >= 0) {
						sInputPre = keyControlInfo.getInputValue().substring(0, i);
					}

					// Lay svalue nhap
					String[] inputs = keyControlInfo.getInputValue().split("\\.");
					if (sKeyPre.equalsIgnoreCase(sInputPre) && isInteger(inputs[inputs.length - 1]))// Nếu day cuối là
																									// nhập số
					{
						int inputValue = Integer.parseInt(inputs[inputs.length - 1]);
						if (inputValue <= 0 || inputValue == keyReturn.getSvalue()) {
							keyReturn.setGenStatus(1);
							return keyReturn;
						}
						// ThảoDD sửa lại: vẫn giữ nguyên ID người dùng nhập vào, không sinh lại gắn
						// prefix nữa
						// Gỏ GetID đi, lấy giá trị nhập vào keyControlInfo.getInputValue()
						keyReturn.setOutputValue(keyControlInfo.getInputValue());
						if (inputValue > keyReturn.getSvalue()) {
							// So sanh delta
							int delta = inputValue - keyReturn.getSvalue();
							if (delta > S_Key_ControlInfo.DELTA) {
								keyReturn.setGenStatus(2);
								return keyReturn;
							}
							// Sinh mã và gán svalue = inputValue
							// keyReturn.setOutputValue(getKeyID(dal,s_Key_Control,
							// String.valueOf(inputValue)));
							s_Key_Control.setSvalue(String.valueOf(inputValue));
							keyReturn.setGenStatus(0);
							keyReturn.setSvalue(inputValue);
							return keyReturn;
						} else {
							String keyID = keyControlInfo.getInputValue();// getKeyID(dal,
																			// s_Key_Control,String.valueOf(inputValue));
							// Tim key da ton tai chua
							if (checkIDExist(keyID, keyControlInfo)) {
								keyReturn.setGenStatus(1);
								return keyReturn;
							} else {
								keyReturn.setOutputValue(keyID);
								keyReturn.setGenStatus(0);
								keyReturn.setSvalue(inputValue);
								return keyReturn;
							}
						}
					} else {
						if (checkIDExist(keyControlInfo.getInputValue(), keyControlInfo)) {
							keyReturn.setGenStatus(3);
							return keyReturn;
						} else {
							keyReturn.setOutputValue(keyControlInfo.getInputValue());
							keyReturn.setGenStatus(0);
							return keyReturn;
						}
					}
				} else {
					// ThảoDD sửa lại: vẫn giữ nguyên ID người dùng nhập vào, không sinh lại gắn
					// prefix nữa
					String keyID = keyControlInfo.getInputValue();// getKeyID(dal, s_Key_Control,
																	// keyControlInfo.getInputValue());
					if (checkIDExist(keyID, keyControlInfo)) {
						keyReturn.setGenStatus(3);
						return keyReturn;
					} else {
						keyReturn.setOutputValue(keyID);
						keyReturn.setGenStatus(0);
						return keyReturn;
					}
				}
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	private String getKeyID(S_Key_Control controlClass, String svalue) {
		try {
			String code = "";
			// Lay DBID
			Optional<S_Key_Control> optional = repository.findById("_DBID");
			if (optional.isPresent()) {
				S_Key_Control controlDBID = optional.get();
				// Chỉ gắn DBID nếu có thuộc tính HaveDBID
				if (controlDBID != null && controlDBID.getSvalue() != null && !controlDBID.getSvalue().isEmpty()
						&& controlClass.getHavedbid() != null && controlClass.getHavedbid()) {
					code = controlDBID.getSvalue().trim();
				}
			}
			// S_Key_Control controlDBID = (S_Key_Control) dal.findById("_DBID");
			// Kiểm tra gắn với Prefix
			if (controlClass != null && controlClass.getPrefix() != null
					&& !controlClass.getPrefix().trim().isEmpty()) {
				if (code.isEmpty()) {
					code = controlClass.getPrefix().trim();
				} else {
					code += "." + controlClass.getPrefix().trim();
				}
			}
			if (code.isEmpty()) {
				return svalue;
			} else {
				return code + "." + svalue;
			}
		} catch (Exception ex) {
			return "";
		}
	}

	private boolean isInteger(String input) {
		try {
			int value = Integer.parseInt(input);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	private boolean isFormat(String input, String format) {
		try {
			// ThảoDD sửa lại đoạn dưới: check không đúng
			String s;
			int i = format.lastIndexOf(".");
			if (i < 0) {
				return true; // Không có định dạng nào, trả về đúng
			}
			s = format.substring(0, i);
            return input.startsWith(s);
        } catch (Exception ex) {
			return false;
		}
	}

	public boolean checkIDExist(String id, S_Key_ControlInfo keyControlInfo) {

		String sql = "Select dm." + keyControlInfo.getColID() + " from " + keyControlInfo.getTable() + "  dm where dm."
				+ keyControlInfo.getColID() + " = '" + id + "'";
		// Query query = entityManager.createNativeQuery(sql);
		List lst = jdbcTemplate.queryForList(sql);
		// List lst = query.getResultList();
		return lst.size() > 0;
	}
}
