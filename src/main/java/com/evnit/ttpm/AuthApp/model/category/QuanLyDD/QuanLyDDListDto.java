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
public class QuanLyDDListDto {
	    private Integer ID;
	    private Integer TYPE;
	    private String TYPENAME;

	    private String TBAID;
	    private String TBANAME;

	    private Integer UNIT;
	    private String UNITNAME;

	    private String CODE;
	    private String NAME;
	    private Integer STATUS;
	    private String STATUSNAME;

	    private String PTGN1TCNAME;
	    private String PTGN1TC;

	    private String PTGN2TC;
	    private String PTGN2TCNAME;

	    private String PTGN1DVGN;
	    private String PTGN1DVGNNAME;

	    private String PTGN2DVGN;
	    private String PTGN2DVGNNAME;

	    private Date DATESTATIC;
	    private Date DATELOAD;
	    private Date DATEINCOME;
	    private String XNK; 
	    private String DTXNK;
	     private boolean CheckXNK ;

	    private String XNKNAME;
	    private Integer TBASTATUS;

	    private Integer PARTNERXNK;
	    private String NOTE;
	    private Integer TBASTATUSID;
	    private String TBASTATUSNAME;
		private String KeyId;



	private String CCXCT;
	private String VLTDTU;
	private String TSBTU;
	private String CCXTU;
	private String DLTU;
	private String MNTTU;
	private String VLTDTI;
	private String TSBTI;
	private String CCXTI;
	private String DLTI;
	private String MNTTI;




}
