package com.evnit.ttpm.AuthApp.model.category.SuCo;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuCoCreateDto {
    private String PROBLEMID;
    private String PROBLEMID_ORG;
    private String PSTATUSID;
    private Date STARTDTIME;
    private Date ENDDTIME;
    private String TYPEOBJID;
    private String PCONTENT;
    private String PCONSEQUENCE;
    private String PCAUSE	;
    private String PREMEDIES;
    private String PRESULT_PROCESS;
    private Date MRERESULT_PROCESS;
    private String USER_CR_ID;
    private Date USER_CR_DTIME;
    private Integer USER_MDF_ID;
    private Date USER__MDF_DTIME;
    private String ORGID;
    private String ASSETID;
    private Boolean KQ_REPLACE;
    private String PCALCULATEID;
    private Boolean ISDELETE;
    private Integer TYPE;
    private Integer COUNT;

    private Boolean NQL_XNHAN;
    private Boolean LD_XNHAN;
    private Date FromDate;
    private Date EndDate;
    private String tbaId;
    private String tbaName;
    private String typeName;
    private String fromDateStr;
    private String endDateStr;

    private String detailDD;
    private String detailCT;
    private String detailTU;
    private String detailTI;

    private Boolean checkNQL;
    private Boolean checkLD;
    private Integer tbaStatus;
    private String content;
    private String consequence;
    private String cause;
    private String remedies;
    private String result;
    private List<FileData> fileDataCreate;
    private List<FileData> FILEDATA;
    private List<FileData> lstFileDelete;
    private List<String> listDiemDo;
    private List<String> listCongTo;
    private List<String> listTU;
    private List<String> listTI;
    private List<String> listDiemDoOld;
    private List<String> listCongToOld;
    private List<String> listTUOld;
    private List<String> listTIOld;
    private List<FileData> fileData1;



}
