package com.evnit.ttpm.AuthApp.util;

import java.util.ResourceBundle;

public class ResourcesFactory {
	protected ResourceBundle m_resource = null;
	protected ResourceBundle m_resourceSetting = null;
	private final String m_SettingsFile = "resources/Settings";

	public ResourcesFactory() {
		m_resourceSetting = ResourceBundle.getBundle(m_SettingsFile);
	}

	public ResourcesFactory(ResourceBundle resource) {
		this.m_resource = resource;
		m_resourceSetting = ResourceBundle.getBundle(m_SettingsFile);
	}

	/**
	 * @param Đường dẫn đầy đủ file (ví dụ tầng
	 *              EJB:evnit/fmg/entity/language/FuelCalCalConsume_vni)
	 **/
	public ResourcesFactory(String propertiesFile) {
		try {
			m_resource = ResourceBundle.getBundle(propertiesFile);
			m_resourceSetting = ResourceBundle.getBundle(m_SettingsFile);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	/**
	 * @param keyName
	 * @return message
	 */
	public String getMessage(String keyName) {
		try {
			if (m_resource == null) {
				return "";
			}
			return m_resource.getString(keyName);
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public String getRowsPage() {
		try {
			return "50";
		} catch (Exception e) {
			e.printStackTrace();
			return "50";
		}
	}

	public String getRowsPageNoLazy() {
		try {
			return "500";
		} catch (Exception e) {
			e.printStackTrace();
			return "500";
		}
	}

	public String getRowsPerPageTemplate() {
		try {
			return "50,100,200,500";
		} catch (Exception e) {
			e.printStackTrace();
			return "50,100,200,500";
		}
	}

	public String getRowsPerPageTemplateNoLazy() {
		try {
			return "50,100,200,500";
		} catch (Exception e) {
			e.printStackTrace();
			return "50,100,200,500";
		}
	}

	/**
	 * @category Lấy định dạng ngày tháng năm
	 **/
	public String getDateMask() {
		try {
			return m_resourceSetting.getString("DateMask");
		} catch (Exception e) {
			e.printStackTrace();
			return "MM/dd/yyyy";
		}
	}

	/**
	 * @category Lấy định dạng ngày tháng năm
	 **/
	public String getTimeMask() {
		try {
			return m_resourceSetting.getString("TimeMask");
		} catch (Exception e) {
			e.printStackTrace();
			return "HH:mm:ss";
		}
	}

	public String getTimeShort() {
		try {
			return "HH:mm";
		} catch (Exception e) {
			e.printStackTrace();
			return "HH:mm";
		}
	}

	/**
	 * @category Lấy định dạng ngày tháng năm giờ phút giây
	 **/
	public String getDateTimeMask() {
		try {
			// return m_resourceSetting.getString("DateTimeMask");
			return "dd/MM/yyyy HH:mm";
		} catch (Exception e) {
			e.printStackTrace();
			return "MM/dd/yyyy HH:mm:ss";
		}
	}

	public String getDateTimeFullMask() {
		try {
			// return m_resourceSetting.getString("DateTimeMask");
			return "dd/MM/yyyy HH:mm:ss";
		} catch (Exception e) {
			e.printStackTrace();
			return "MM/dd/yyyy HH:mm:ss";
		}
	}

	/**
	 * @category Lấy định dạng ngày tháng năm giờ phút giây
	 **/
	public String getDateLocale() {
		try {
			return m_resourceSetting.getString("DateLocale");
		} catch (Exception e) {
			e.printStackTrace();
			return "en";
		}
	}

	/**
	 * Cấu hình time zone
	 * 
	 * @return
	 */
	public String getTimeZone() {
		try {
			return m_resourceSetting.getString("TimeZone");
		} catch (Exception e) {
			e.printStackTrace();
			return "GMT+7:00";
		}
	}

	/**
	 * Định dạng nhập số
	 * 
	 * @return
	 */
	public String getDecimalFormat_EN() {
		try {
			return m_resourceSetting.getString("DecimalFormat_EN");
		} catch (Exception e) {
			e.printStackTrace();
			return "###,###,###,##0.0#####";
		}
	}

	public String getFormatNumber() {
		try {
			return "###,###,###,###.#0";
		} catch (Exception e) {
			e.printStackTrace();
			return "###,###,###,###.#0";
		}
	}

	public String getFormatNumber_TL() {
		try {
			return "###,###,###,###.##";
		} catch (Exception e) {
			e.printStackTrace();
			return "###,###,###,###.##";
		}
	}

	public String getFormatNumber_SL() {
		try {
			return "###,###,###,###";
		} catch (Exception e) {
			e.printStackTrace();
			return "###,###,###,###";
		}
	}

	/**
	 * Ký tự phân tách nhóm
	 * 
	 * @return
	 */
	public String getDecimalGroupingSeparator() {
		try {
			return m_resourceSetting.getString("DecimalGroupingSeparator");
		} catch (Exception e) {
			e.printStackTrace();
			return ",";
		}
	}

	/**
	 * Ký tự phân tách phần thập phân
	 * 
	 * @return
	 */
	public String getDecimalSeparator() {
		try {
			return m_resourceSetting.getString("DecimalSeparator");
		} catch (Exception e) {
			e.printStackTrace();
			return ".";
		}
	}

	public boolean getCompileReport() {
		try {
			String s;
			s = m_resourceSetting.getString("CompileReport");
			if (s == null) {
				s = "";
			}
			if (s.equalsIgnoreCase("false")) {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;

	}

	/**
	 * @category Lấy cấu hình menu
	 * @return disable/hidden
	 **/
	public String getMenuConfig() {
		try {
			return m_resourceSetting.getString("MenuConfig");
		} catch (Exception e) {
			e.printStackTrace();
			return "hidden";
		}
	}

	public String getEamJobConfig() {
		try {
			return m_resourceSetting.getString("eam_job");
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
	}

	public String getVattuTonConfig() {
		try {
			return m_resourceSetting.getString("vattu_ton");
		} catch (Exception e) {
			e.printStackTrace();
			return "0";
		}
	}
}
