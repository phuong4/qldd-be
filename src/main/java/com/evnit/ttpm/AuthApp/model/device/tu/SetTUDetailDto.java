package com.evnit.ttpm.AuthApp.model.device.tu;

import com.evnit.ttpm.AuthApp.entity.device.tu.ViewTU;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SetTUDetailDto {
    private String assetId;
    private String assetIdParent;
    private String assetDesc;
    private String categoryId;
    private String powerTypeId;
    private String powerSystemName;
    private String orgId;
    private Integer setType;
    private Integer totalCancel;
    private Integer totalStatus;
    private Integer statusBTU;
    @ToString.Exclude
    private List<ViewTU> tuList;
}
