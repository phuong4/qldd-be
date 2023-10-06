package com.evnit.ttpm.AuthApp.model.common;

import com.evnit.ttpm.AuthApp.model.payload.PagingParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchHisMountUnDto extends PagingParam {
    private String categoryType;
    private String idDevice;
    private String idDiemDo;
}
