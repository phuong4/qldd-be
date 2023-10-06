package com.evnit.ttpm.AuthApp.model.Paramater;

import com.evnit.ttpm.AuthApp.model.payload.PagingParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchParamaterList extends PagingParam {
    private List<Integer> eventId;
    private List<String> tbaId;
    private List<Integer> diemDoId;
    private String dateAnalysis;



}
