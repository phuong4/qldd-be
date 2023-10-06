package com.evnit.ttpm.AuthApp.entity.file;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Table(name = "AF_OTHER")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AF_OTHER {
    @Id
    @Column(name = "AFFILEID")
    private String afFileId;
    @Column(name = "OBJTYPEID")
    private String objTypeId;
    @Column(name = "OBJID")
    private String objId;
    @Column(name = "SUMDESC")
    private String sumDesc;
    @Column(name = "ATTTYPE")
    private String attType;
    @Column(name = "URL")
    private String url;
    @Column(name = "LIBITEMID")
    private String libItemId;
    @Column(name = "FILENAME")
    private String fileName;
    @Column(name = "FILESIZEB")
    private Integer fileSizeB;
    @Column(name = "CRDTIME")
    private Date crdTime;
    @Column(name = "MDFDTIME")
    private Date mdfDTime;
    //    @Column(name = "USER_MDF")
//    private String userMdf;
    @Column(name = "FILETYPEID")
    private String fileTypeId;
    @Column(name = "USERID")
    private String userId;
    @Column(name = "FILEPATH")
    private String filePath;
    @Column(name = "ORGID")
    private String orgId;

    public enum AF_TYPE {
        NT, KD, KDTU, KDTI, KDCT, A, TT, SC
    }
}
