package com.evnit.ttpm.AuthApp.model.device.cuon;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ZAGCuon {
    private String attrDataId;
    private String objTypeId;
    private String objId;
    private String ccx;
    private String dungLuong;
    private String cuonDNoi;
    private List<String> machNThu;
    private String tinhChat;
    private String tsb;

    public String getCuonDNoi() {
        return cuonDNoi;
    }

    public void setCuonDNoi(String cuonDNoi) {
        this.cuonDNoi = cuonDNoi;
    }

    public String getTinhChat() {
        return tinhChat;
    }

    public void setTinhChat(String tinhChat) {
        this.tinhChat = tinhChat;
    }
}
