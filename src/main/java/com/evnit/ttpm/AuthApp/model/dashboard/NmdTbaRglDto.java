package com.evnit.ttpm.AuthApp.model.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NmdTbaRglDto {
    private Integer soLuongKD;
    private Integer soLuongNT;
    private Integer soLuongXLSC;
    private Date thang;
}
