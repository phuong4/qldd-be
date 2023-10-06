package com.evnit.ttpm.AuthApp.model.category.SuCo;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SuCoListDto {
    private Integer Id;
    private Integer Type;
    private Integer Count;
    private String TbaId;
    private boolean CheckNQL;
    private boolean CheckLD;
    private Integer TbaStatus;
    private String TbaName	;
    private String TbaStatusName;
    private Date FromDate;
    private Date EndDate;
    private String TypeObjId	;
    private String Cause	;
    private String Content	;
    private String Detail	;
    private String StatusName	;
    private String Result	;
    private Integer Status	;
    private String TypeName	;
    private String Consequence	;
    private String Remedies	;
    private String KeyId	;
    private String IdType;
    private String FileData;
    private String IdTypeCT	;
    private String IdTypeDD	;
    private String IdTypeTU	;
    private String IdTypeTI	;
    private String DetailCT	;
    private String DetailTU	;
    private String DetailTI	;
    private String DetailDD	;
    private String TypeObjIdCT	;
    private String TypeObjIdTI	;
    private String TypeObjIdDD	;
    private String TypeObjIdTU	;







}
