package com.evnit.ttpm.AuthApp.entity.device;

import lombok.Data;

import java.io.Serializable;
@Data
public class SAttrGroupAssObjId implements Serializable {
    private String objId;
    private String objTypeId;
    private String attrGroupId;
}
