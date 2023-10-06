package com.evnit.ttpm.AuthApp.model.category.QuanLyDD;

import com.evnit.ttpm.AuthApp.model.payload.PagingParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchQLDDList extends PagingParam {
    private List<Integer> type;
    private List<String> tbaId;
    private List<Integer> tbaStatus;
    private List<Integer> status;
    private String nameDD;
    private String codeDD;
    private List<String> tinhChat1;
    private List<String> tinhChat2;
    private List<String> donVi1;
    private List<String> donVi2;
    private String dongDien;
    private String bienAp;


}
