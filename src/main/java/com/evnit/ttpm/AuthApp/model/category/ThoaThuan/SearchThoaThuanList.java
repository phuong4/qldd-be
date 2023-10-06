package com.evnit.ttpm.AuthApp.model.category.ThoaThuan;

import com.evnit.ttpm.AuthApp.model.payload.PagingParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchThoaThuanList extends PagingParam {
    private List<Integer> type;
    private List<String> tbaId;
    private List<Integer> tbaStatus;
    private List<String> typeNMD;
    private String numDoc;
    private String endDateDoc;
    private List<String> typeDeal;
    private Integer factor;




}
