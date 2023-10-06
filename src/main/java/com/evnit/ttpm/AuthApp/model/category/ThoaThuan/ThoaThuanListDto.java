package com.evnit.ttpm.AuthApp.model.category.ThoaThuan;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ThoaThuanListDto {
    private Number Id;
    private Number Type;
    private String TbaId;
    private boolean CheckNQL;
    private boolean CheckLD;
    private Number TbaStatus;
    private String TypeNMD;
    private String TinhTp;
    private String TbaName	;
    private String TbaStatusName;
    private boolean XnkName;
    private String NumDoc;
    private Date EndDateDoc;
    private String TypeDeal;
    private Date FromDate;
    private Date EndDate;
    private Number Factor;
    private String ValidBase;
    private String FormSynPower;
    private Date FromDateSL;
    private Date EndDateSL;
    private boolean CheckTick;
    private String DEALID;
    private String DealContent;
    private String TypeDealCode;
    private int FormSynPowerInt;
    private String Note;
    private String FileData;
    private String TypeName;

    private String Unit;


}
