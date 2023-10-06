package com.evnit.ttpm.AuthApp.model.accreditation;

import com.evnit.ttpm.AuthApp.model.payload.PagingParam;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchKiemDinh extends PagingParam {
    private List<String> type;
    private List<String> assetId;
    private List<Integer> trangThaiNMD;
    private String startDate;
    private String endDate;
    private List<String> hinhThuc;
    private List<String> categoryId;
    private String chiTiet;
}
