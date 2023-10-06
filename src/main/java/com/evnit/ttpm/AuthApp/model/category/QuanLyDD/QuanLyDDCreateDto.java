package com.evnit.ttpm.AuthApp.model.category.QuanLyDD;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuanLyDDCreateDto {
    private Integer id;
    private Integer type;
    private String tbaid;
    private String unit;
    private String code;
    private String name;
    private String status;
    //private String ptgn1tc;
    //private String ptgn2tc; 
    //private String ptgn1dvgn;
    //private String ptgn2dvgn;
    private String ptgn1TC;
    private String keyId;
    private String userId;

    private String ptgn2TC; 
    private String ptgn1DVGN;
    private String ptgn2DVGN;
    private Date datestatic;
    private Date dateload;
    private Date dateincome;
    private String xnk; 
    private String dtxnk; 
    private Integer partnerxnk;
    private String note;

    private String typeName;
    private String tbaName;
    private String unitname;
    private String statusname;
    private String ptgn1DVGNNAME;
    private String ptgn2DVGNNAME;
    private String datestaticStr;
    private String dateloadStr;
    private String dateincomeStr;
    private String xnkName;
    private String dtxnkName;

  
	
    
}
