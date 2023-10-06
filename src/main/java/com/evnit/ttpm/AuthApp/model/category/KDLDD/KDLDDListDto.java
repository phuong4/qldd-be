package com.evnit.ttpm.AuthApp.model.category.KDLDD;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KDLDDListDto {
    private Integer Id;
    private String NameUnit;
    private String NameDD;
    private String CodeDD	;
    private String SeriCT	;
    private Date DateClosing	;
    private Double Expression1;
    private Double Expression2;
    private Double Expression3;
    private Double InVain;
    private Double ExpressionSum;
    private Double ExpressionReceive1;
    private Double ExpressionReceive2;
    private Double ExpressionReceive3;
    private Double InVainReceive;
    private Double ExpressionSumReceive;
    private Double Multiplier;
    private String LessOperation;
    private String ReadingStatus;
    private String UnitCCSL;
    private Integer UnitType;
    private Integer UnitId;
    private Integer IdDD;
    private Date SynDate;
}
