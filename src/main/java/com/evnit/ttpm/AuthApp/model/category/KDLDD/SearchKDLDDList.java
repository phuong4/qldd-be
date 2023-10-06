package com.evnit.ttpm.AuthApp.model.category.KDLDD;

import com.evnit.ttpm.AuthApp.model.payload.PagingParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchKDLDDList extends PagingParam {
    private Integer UnitType;
    private Integer UnitId;
    private Integer IdDD;
    private Date SynDate;


}
