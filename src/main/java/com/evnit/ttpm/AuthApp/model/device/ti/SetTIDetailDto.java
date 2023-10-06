package com.evnit.ttpm.AuthApp.model.device.ti;

import com.evnit.ttpm.AuthApp.entity.device.ti.ViewTI;
import com.evnit.ttpm.AuthApp.entity.device.tu.ViewTU;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Column;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetTIDetailDto {
    private String assetId;
    private String assetIdParent;
    private String assetDesc;
    private String categoryId;
    private String powerTypeId;
    private String powerSystemName;
    private String orgId;
    private Integer setType;
    private Integer statusBTI;
    private Integer totalCancel;
    private Integer totalStatus;
    @ToString.Exclude
    private List<ViewTI> tiList;
}
