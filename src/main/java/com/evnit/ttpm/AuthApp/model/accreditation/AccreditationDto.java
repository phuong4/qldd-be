package com.evnit.ttpm.AuthApp.model.accreditation;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AccreditationDto {
    private String accredId;
    private String accredDesc;
    private String assetId;
    private Date accredtationStartDate;
    private Date accredtationEndDate;
    private String accredtationType;
    private String accredtationResult;
    private String note;
    private String user_cr_id;
    private Date user_cr_dtime;
    private String user_mdf_id;
    private Date user_mdf_dtime;
    private Boolean nql_xnhan;
    private Boolean ld_xnhan;
    private List<FileData> fileData;
    private List<FileData> lstFileDelete;
    private List<AccreditationDetailDto> lstCongto;
    private List<AccreditationDetailDto> lstTU;
    private List<AccreditationDetailDto> lstTI;
    private List<AccreditationDetailDto> lstCongtoDelete;
    private List<AccreditationDetailDto> lstTUDelete;
    private List<AccreditationDetailDto> lstTIDelete;

    private String categoryId;
    private String batdauStr;
    private String ketthucStr;
    private String kieu;
    private String hinhthuc;
    private String ten;
    private String chitietketquathuchien;
    private String chitiet;
    private String chitietCT;
    private String chitietTU;
    private String chitietTI;

}
