package com.evnit.ttpm.AuthApp.model.acceptance;

import com.evnit.ttpm.AuthApp.model.payload.PagingParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchAcceptanceList extends PagingParam {
    private List<String> type;
    private List<String> assetId;
    private List<Integer> trangThaiNMD;
    private String startDate;
    private String endDate;
    private List<String> typeNT;
    private List<String> expression;
    private List<String> typeObj;
    private String detailObj;
}
