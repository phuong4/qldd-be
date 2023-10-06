package com.evnit.ttpm.AuthApp.model.device.congto;

import com.evnit.ttpm.AuthApp.model.payload.PagingParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchCongToList extends PagingParam {
    private List<String> typeId;
    private List<String> powerId;
    private List<String> assetIdParent;
    private List<String> parentStatus;
    private List<String> deviceStatus;
    private List<String> ignoreCTId;

    private String nameTUCuon;
    private List<String> tinhChat;
    private String diemDoName;
    private List<String> loaiCto;
    private String serialNum;
}
