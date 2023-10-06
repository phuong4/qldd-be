package com.evnit.ttpm.AuthApp.model.category.DeviceErrorLimit;

import com.evnit.ttpm.AuthApp.entity.category.SCongToErrorLimitMB;
import com.evnit.ttpm.AuthApp.entity.category.SCongToErrorLimitMT;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CongToErrorLimitMT {
    private Integer id;
    private Integer exactLevel;
    private String loadIn;
    private String phase;
    private String cosSinPhi;
    private Double wh;
    private Double varh;

    public SCongToErrorLimitMT convertDtoToEntity() {
        return new ModelMapper().map(this, SCongToErrorLimitMT.class);
    }

}
