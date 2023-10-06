package com.evnit.ttpm.AuthApp.model.acceptance;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AcceptanceDetailDto {
    private String acceptDetailId;
    private String acceptId;
    private String assetId;
    private Boolean statusAccept;
    private String note;
    private Boolean hTTTSLAccept;
    private String idCT;
    private String idsTU;
    private String idsTI;
    private List<String> listAcceptDelete;
    private List<String> listDiemDoIdDelete;

    public Boolean gethTTTSLAccept() {
        return hTTTSLAccept;
    }

    public void sethTTTSLAccept(Boolean hTTTSLAccept) {
        this.hTTTSLAccept = hTTTSLAccept;
    }
}
