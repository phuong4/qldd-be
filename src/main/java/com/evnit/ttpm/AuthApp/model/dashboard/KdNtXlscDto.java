package com.evnit.ttpm.AuthApp.model.dashboard;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class KdNtXlscDto {
    private Integer soLuongNM;
    private Integer soLuongTBA;
    private Integer soLuongRGL;
    private Date thang;
}
