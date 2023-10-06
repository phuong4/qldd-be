package com.evnit.ttpm.AuthApp.model.category.ThoaThuan;

import java.sql.Date;
import java.util.List;

import com.evnit.ttpm.AuthApp.model.category.SuCo.FileData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThoaThuanCreateDto {
    private String DEALID;
    private String DEALDESC;
    private String DEALCONTENT;
    private String ASSETID;
    private String NUMDOC;
    private Date DATEDOC;
    private Date STARTDATE_EFFECT;
    private Date ENDDATE_EFFECT;
    private String TYPE_DEAL	;
    private Double FACTOR;
    private String FORM_SYN_POWER;
    private Date STARTDATE_DOC;
    private Date ENDDATE_DOC;
    private String USER_CR_ID;
    private Date USER_CR_DTIME;
    private Integer USER_MDF_ID;
    private Date USER__MDF_DTIME;
    private String VALID_BASE;
    private Boolean NQL_XNHAN;
    private Boolean LD_XNHAN;
    private Boolean CHECK;
    private Boolean CheckTick;
    private int TYPE;

    private Number id;
    private String tbaidcreate;
    private int typecreate;
    private String numdoccreate;
    private String dealcontentcreate;

    private Date enddatedoccreate;
    private Date fromdatecreate;
    private Date enddatecreate;
    private String typedealcreate;
    private String formsynpowercreate;
    private Boolean Check;
    private Number factorcreate;
    private String validbasecreate;
    private Date fromdateslcreate;
    private Date enddateslcreate;
    private String note;
    private List<FileData> fileData;
    private List<FileData> lstFileDelete;
    private List<FileData> fileDataCreate;
    private List<FileData> fileData1;

    private boolean ISDELETE;
    private String userId;

    private  String numDoc;
    private  String validBase;
    private  String dealContent;
    private  String tbaName;
    private  String typeName;
    private  String typeDeal;
    private String formSynPower;
    private String endDateDocStr;
    private String endDateSLStr;
    private String endDateStr;
    private String fromDateStr;
    private String fromDateSLStr;




}
