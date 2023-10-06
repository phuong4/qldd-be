package com.evnit.ttpm.AuthApp.model.category.Plan;

import com.evnit.ttpm.AuthApp.model.payload.PagingParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchPlanList extends PagingParam {
    private List<Integer> type;
    private List<String> tbaId;
    private String yearTime;
    private List<Integer> monthTime;



}
