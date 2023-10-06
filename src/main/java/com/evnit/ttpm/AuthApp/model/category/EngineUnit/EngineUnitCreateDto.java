package com.evnit.ttpm.AuthApp.model.category.EngineUnit;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EngineUnitCreateDto {
	private Integer id;
    private String name;
    private Date dateCOD;
    private String nmdId;
    private Integer cong_suat_min;
    private Integer cong_suat_max;
    private Integer cong_suat_phan_khang;
    private String timeActive;
    private String speed_of_change_reduce;
    private String speed_of_change_increase;
    private boolean active;
    private String note;
    private Boolean is_Delete;
}
