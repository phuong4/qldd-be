package com.evnit.ttpm.AuthApp.model.category.WarningDevice;

import com.evnit.ttpm.AuthApp.model.payload.PagingParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchWarningDeviceList extends PagingParam {
    private List<Integer> type;
    private List<Integer> month;

}
