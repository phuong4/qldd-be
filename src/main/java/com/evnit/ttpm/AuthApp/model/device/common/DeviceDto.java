package com.evnit.ttpm.AuthApp.model.device.common;

import com.evnit.ttpm.AuthApp.model.device.common.DeviceCrudDto;
import com.evnit.ttpm.AuthApp.model.device.ti.SetTICreateDto;
import com.evnit.ttpm.AuthApp.model.device.tu.SetTUCreateDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDto {
    private DeviceCrudDto diemDo;
    private DeviceCrudDto congTo;
    private List<DeviceCrudDto> listTU;
    private List<DeviceCrudDto> listTI;
    private List<SetTUCreateDto> listSetTU;
    private List<SetTICreateDto> listSetTI;
}
