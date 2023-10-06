package com.evnit.ttpm.AuthApp.model.category.DonViSH;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DonViSHListDto {
	private Integer Id;
    private String name;
    private String name1;
    private String name2;
    private String name3;

    private String nameShort;
    private int type;
    private int parentId;
    private String parentStr;
    private int level;
    private String idHis;

    private String note;
    private boolean is_Delete;
    private DonViSHListDto parent;
	private List<DonViSHListDto> children;
	private List<DonViSHListDto> _children;
}
