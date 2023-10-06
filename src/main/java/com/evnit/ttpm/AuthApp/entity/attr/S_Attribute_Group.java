package com.evnit.ttpm.AuthApp.entity.attr;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "S_ATTRIBUTE_GROUP", uniqueConstraints = {})
public class S_Attribute_Group {

	private static final long serialVersionUID = 1L;
	@Id
	@Basic(optional = false)
	@Column(name = "ATTRGROUPID")
	private String attrgroupid;
	@Column(name = "ATTRGROUPDESC")
	private String attrgroupdesc;
	@Column(name = "ORGID")
	private String orgid;
	@Column(name = "USINGBY")
	private String usingby;
	@Column(name = "DEFAULTTOALL")
	private String defaulttoall;
	@Column(name = "TABLENAME_DATA")
	private String tablenameData;
	@Column(name = "ISFIX")
	private Boolean isfix;
	@Column(name = "ENABLE")
	private Boolean enable;
	@Column(name = "USER_CR_ID")
	private String userCrId;
	@Column(name = "USER_CR_DTIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date userCrDtime;
	@Column(name = "USER_MDF_ID")
	private String userMdfId;
	@Column(name = "USER_MDF_DTIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date userMdfDtime;
}
