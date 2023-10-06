package com.evnit.ttpm.AuthApp.model.category.tbargl;

import com.evnit.ttpm.AuthApp.model.payload.PagingParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchTBAGRL extends PagingParam {
    private List<String> voltageLevel;
    private List<String> name;
    private List<Integer> type;
    private List<Integer> status;
    private String text;
}
