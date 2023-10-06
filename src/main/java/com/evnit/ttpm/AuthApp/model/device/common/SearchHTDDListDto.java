package com.evnit.ttpm.AuthApp.model.device.common;

import com.evnit.ttpm.AuthApp.model.payload.PagingParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchHTDDListDto extends PagingParam {
    private String assetIdParent;
    private List<String> statusDiemDo;
    private String diemDoId;
    private List<String> ignoreDiemDoId;
}
