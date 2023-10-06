package com.evnit.ttpm.AuthApp.model.system;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MWorderHisDto {
    private String id;
    private String worderId;
    private String wContent;
    private String wContentEdit;
    private String wManipulation;
    private Date dateManipulation;
    private Date userMdfId;
    private String typeWorder;
}
