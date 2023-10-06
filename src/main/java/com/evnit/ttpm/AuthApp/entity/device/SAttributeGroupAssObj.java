package com.evnit.ttpm.AuthApp.entity.device;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table
@Data
@IdClass(SAttrGroupAssObjId.class)
public class SAttributeGroupAssObj {
    @Id
    @Column(name = "OBJID")
    private String objId;
    @Id
    @Column(name = "OBJTYPEID")
    private String objTypeId;
    @Id
    @Column(name = "ATTRGROUPID")
    private String attrGroupId;
    @Column(name = "ATTRGROUPDESC")
    private String attrGroupDesc;
    @Column(name = "ATTRGROUPORD")
    private Integer attrGroupOrd;
    @Column(name = "ATTRDATAID")
    private String attrDataId;
}
