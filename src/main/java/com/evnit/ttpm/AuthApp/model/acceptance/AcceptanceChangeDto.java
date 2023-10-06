package com.evnit.ttpm.AuthApp.model.acceptance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcceptanceChangeDto {
    private String accepChangeId;
    private String acceptId;
    private String assetIdChange;
    private String assetId;
    private String causeChange;
    private String accredId;
    private String idDiemDo;
    private String idDiemDoChange;
    private Boolean statusAccept;
    private String idCuon;
    private List<String> listAcceptDelete;
}
