package com.evnit.ttpm.AuthApp.model.category.DeviceErrorLimit;

import com.evnit.ttpm.AuthApp.entity.category.SCongToErrorLimit;
import com.evnit.ttpm.AuthApp.entity.category.SCongToErrorLimitMB;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CongToErrorLimit {
    private Integer id;
    private String phase;
    private String loadLN;
    private String pf;
    private Double quantityLimit02;
    private Double quantityLimit05;
    private Double quantityLimit1;
    private Double quantityLimit2;
    private Double quantityLimit3;
    public SCongToErrorLimit convertDtoToEntity() {
        return new ModelMapper().map(this, SCongToErrorLimit.class);
    }
}
