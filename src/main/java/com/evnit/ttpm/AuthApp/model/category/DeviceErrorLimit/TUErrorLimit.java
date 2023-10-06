package com.evnit.ttpm.AuthApp.model.category.DeviceErrorLimit;

import com.evnit.ttpm.AuthApp.entity.category.SCongToErrorLimitMB;
import com.evnit.ttpm.AuthApp.entity.category.STUErrorLimit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TUErrorLimit {
    private Integer id;
    private Double ccx;
    private Integer capacity;
    private BigDecimal f;
    private BigDecimal delta;

    public STUErrorLimit convertDtoToEntity() {
        return new ModelMapper().map(this, STUErrorLimit.class);
    }
}
