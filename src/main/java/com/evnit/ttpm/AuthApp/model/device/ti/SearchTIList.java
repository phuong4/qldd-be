package com.evnit.ttpm.AuthApp.model.device.ti;

import com.evnit.ttpm.AuthApp.model.payload.PagingParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchTIList extends PagingParam {
    private List<String> powerSystemType;
    private List<String> powerSystemName;
    private List<String> powerSystemStatus;
    private List<Integer> statusTU1;

    private String nameTUCuon;
    private List<Integer> statusTU2;
    private List<String> tinhChat;
    private String diemDoName;
    private List<String> machNThu;
    private List<String> pManufacturerId;
    private String serialNum;

    private String assetIdParent;
    private String statusBTI;
    private String statusTI;
    private List<String> cuonIds;
    private String setId;
    private String tiId;
    private Integer setType;
    private List<String> ignoreTIId;
}
