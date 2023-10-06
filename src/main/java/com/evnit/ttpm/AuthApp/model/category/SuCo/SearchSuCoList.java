package com.evnit.ttpm.AuthApp.model.category.SuCo;

import com.evnit.ttpm.AuthApp.model.payload.PagingParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchSuCoList extends PagingParam {
    private List<Integer> type;
    private List<String> tbaId;
    private List<Integer> tbaStatus;
    private String fromDate;
    private String endDate;
    private List<String> categoryId;
    private String noiDung;
    private String ketQua;
    private String bienPhap;
    private String nguyenNhan;
    private String hauQua;
    private String chiTiet;




}
