package com.evnit.ttpm.AuthApp.model.device.tu;

import com.evnit.ttpm.AuthApp.model.payload.PagingParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchTUList extends PagingParam {
    private List<String> typeId;
    //private List<String> assetIdParent;
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


    private List<Integer> parentStatus;
    private List<Integer> deviceStatus;
    private String assetIdParent;
    private String statusBTU;
    private String statusTU;
    private List<String> cuonIds;
    private String setId;
    private String tuId;
    private Integer setType;
    private List<String> ignoreTUId;
}
