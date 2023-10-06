package com.evnit.ttpm.AuthApp.model.category.DeviceErrorLimit;

import com.evnit.ttpm.AuthApp.entity.category.STIErrorLimit;
import com.evnit.ttpm.AuthApp.entity.category.STUErrorLimit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TIErrorLimit {
    private Integer id;
    private Double ccx;
    private Integer capacity;
    private BigDecimal f1;
    private BigDecimal delta1;
    private BigDecimal f5;
    private BigDecimal delta5;
    private BigDecimal f20;
    private BigDecimal delta20;
    private BigDecimal f100;
    private BigDecimal delta100;
    private BigDecimal f120;
    private BigDecimal delta120;

    public STIErrorLimit convertDtoToEntity() {
        return new ModelMapper().map(this, STIErrorLimit.class);
    }
}
