package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.category.*;
import com.evnit.ttpm.AuthApp.model.category.DeviceErrorLimit.*;

import java.util.List;

public interface SCategoryDeviceErrorLimitService {
    ResponseData getCongToMB(Integer exactLevel);

    ResponseData getCongToMT(Integer exactLevel);

    ResponseData getCongToMN(Integer exactLevel);

    ResponseData getCongToErrorLimit();

    ResponseData updateCongToErrorLimit(List<CongToErrorLimit> dataList);

    ResponseData getDeviceTU();

    ResponseData getDeviceTI();

    ResponseData updateCongToMB(Integer exactLevel, List<CongToErrorLimitMB> data);

    ResponseData updateCongToMT(Integer exactLevel, List<CongToErrorLimitMT> data);

    ResponseData updateCongToMN(Integer exactLevel, List<CongToErrorLimitMN> data);

    ResponseData updateTU(List<TUErrorLimit> data);

    ResponseData updateTI(List<TIErrorLimit> data);

}
