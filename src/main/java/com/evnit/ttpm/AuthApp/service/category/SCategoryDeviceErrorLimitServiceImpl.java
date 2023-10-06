package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.category.*;
import com.evnit.ttpm.AuthApp.model.category.DeviceErrorLimit.*;
import com.evnit.ttpm.AuthApp.repository.category.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SCategoryDeviceErrorLimitServiceImpl implements SCategoryDeviceErrorLimitService {
    @Autowired
    SCongToMBErrorLimitRepository congToMBRepository;
    @Autowired
    SCongToMTErrorLimitRepository congToMTRepository;
    @Autowired
    SCongToMNErrorLimitRepository congToMNRepository;
    @Autowired
    SCongToErrorLimitRepository congToRepository;
    @Autowired
    STUErrorLimitRepository tuErrorLimitRepository;
    @Autowired
    STIErrorLimitRepository tiErrorLimitRepository;

    @Override
    public ResponseData getCongToMB(Integer exactLevel) {
        ResponseData response = new ResponseData();
        try {
            var result = congToMBRepository.findByExactLevel(exactLevel);
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(result);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR + " " + ex.getMessage());
            response.setData(null);
        }
        return response;
    }

    @Override
    public ResponseData getCongToMT(Integer exactLevel) {
        ResponseData response = new ResponseData();
        try {
            var result = congToMTRepository.findByExactLevel(exactLevel);
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(result);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR + " " + ex.getMessage());
            response.setData(null);
        }
        return response;
    }

    @Override
    public ResponseData getCongToMN(Integer exactLevel) {
        ResponseData response = new ResponseData();
        try {
            var result = congToMNRepository.findByExactLevel(exactLevel);
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(result);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR + " " + ex.getMessage());
            response.setData(null);
        }
        return response;
    }

    @Override
    public ResponseData getCongToErrorLimit() {
        ResponseData response = new ResponseData();
        try {
            var result = congToRepository.findAll();
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(result);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR + " " + ex.getMessage());
            response.setData(null);
        }
        return response;
    }

    @Override
    public ResponseData updateCongToErrorLimit(List<CongToErrorLimit> dataList) {
        ResponseData response = new ResponseData();
        try {
            List<SCongToErrorLimit> listEntity = dataList.stream().map(CongToErrorLimit::convertDtoToEntity).collect(Collectors.toList());
            var listResult = new ArrayList<SCongToErrorLimit>();
            int index = 1;
            for (SCongToErrorLimit congTo : listEntity) {
                var existError = congToRepository.findById(congTo.getId());
                if (existError.isPresent()) {
                    existError.get().setIdx(index++);
                    existError.get().setPhase(congTo.getPhase());
                    existError.get().setLoadLN(congTo.getLoadLN());
                    existError.get().setPf(congTo.getPf());
                    existError.get().setQuantityLimit1(congTo.getQuantityLimit1());
                    existError.get().setQuantityLimit2(congTo.getQuantityLimit2());
                    existError.get().setQuantityLimit02(congTo.getQuantityLimit02());
                    existError.get().setQuantityLimit05(congTo.getQuantityLimit05());
                    existError.get().setQuantityLimit3(congTo.getQuantityLimit3());
                    var congToEntity = congToRepository.save(existError.get());
                    listResult.add(congToEntity);
                }
            }
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(listResult);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR + " " + ex.getMessage());
            response.setData(null);
        }
        return response;
    }

    @Override
    public ResponseData getDeviceTU() {
        ResponseData response = new ResponseData();
        try {
            var result = tuErrorLimitRepository.findAll();
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(result);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR + " " + ex.getMessage());
            response.setData(null);
        }
        return response;
    }

    @Override
    public ResponseData getDeviceTI() {
        ResponseData response = new ResponseData();
        try {
            var result = tiErrorLimitRepository.findAll();
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(result);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR + " " + ex.getMessage());
            response.setData(null);
        }
        return response;
    }

    @Override
    public ResponseData updateCongToMB(Integer exactLevel, List<CongToErrorLimitMB> dataList) {
        ResponseData response = new ResponseData();
        try {
            List<SCongToErrorLimitMB> listEntity = dataList.stream().map(CongToErrorLimitMB::convertDtoToEntity).collect(Collectors.toList());
            var listResult = new ArrayList<SCongToErrorLimitMB>();
            int index = 0;
            for (SCongToErrorLimitMB congTo : listEntity) {
                var existError = congToMBRepository.findById(congTo.getId());
                if (existError.isPresent()) {
                    existError.get().setIdx(index++);
                    existError.get().setWh(congTo.getWh());
                    ;
                    existError.get().setPhase(congTo.getPhase());
                    existError.get().setVarh(congTo.getVarh());
                    existError.get().setCosSinPhi(congTo.getCosSinPhi());
                    existError.get().setLoadIn(congTo.getLoadIn());
                    var congToEntity = congToMBRepository.save(existError.get());
                    listResult.add(congToEntity);
                }
            }
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(listResult);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR + " " + ex.getMessage());
            response.setData(null);
        }
        return response;
    }

    @Override
    public ResponseData updateCongToMT(Integer exactLevel, List<CongToErrorLimitMT> dataList) {
        ResponseData response = new ResponseData();
        try {
            List<SCongToErrorLimitMT> listEntity = dataList.stream().map(CongToErrorLimitMT::convertDtoToEntity).collect(Collectors.toList());
            var listResult = new ArrayList<SCongToErrorLimitMT>();
            int index = 0;
            for (SCongToErrorLimitMT congTo : listEntity) {
                var existError = congToMTRepository.findById(congTo.getId());
                if (existError.isPresent()) {
                    existError.get().setIdx(index++);
                    existError.get().setWh(congTo.getWh());
                    ;
                    existError.get().setPhase(congTo.getPhase());
                    existError.get().setVarh(congTo.getVarh());
                    existError.get().setCosSinPhi(congTo.getCosSinPhi());
                    existError.get().setLoadIn(congTo.getLoadIn());
                    var congToEntity = congToMTRepository.save(existError.get());
                    listResult.add(congToEntity);
                }
            }
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(listResult);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR + " " + ex.getMessage());
            response.setData(null);
        }
        return response;
    }

    @Override
    public ResponseData updateCongToMN(Integer exactLevel, List<CongToErrorLimitMN> dataList) {
        ResponseData response = new ResponseData();
        try {
            List<SCongToErrorLimitMN> listEntity = dataList.stream().map(CongToErrorLimitMN::convertDtoToEntity).collect(Collectors.toList());
            var listResult = new ArrayList<SCongToErrorLimitMN>();
            int index = 0;
            for (SCongToErrorLimitMN congTo : listEntity) {
                var existError = congToMNRepository.findById(congTo.getId());
                if (existError.isPresent()) {
                    existError.get().setIdx(index++);
                    existError.get().setWh(congTo.getWh());
                    ;
                    existError.get().setPhase(congTo.getPhase());
                    existError.get().setVarh(congTo.getVarh());
                    existError.get().setCosSinPhi(congTo.getCosSinPhi());
                    existError.get().setLoadIn(congTo.getLoadIn());
                    var congToEntity = congToMNRepository.save(existError.get());
                    listResult.add(congToEntity);
                }
            }
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(listResult);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR + " " + ex.getMessage());
            response.setData(null);
        }
        return response;
    }

    @Override
    public ResponseData updateTU(List<TUErrorLimit> dataList) {
        ResponseData response = new ResponseData();
        try {
            List<STUErrorLimit> listEntity = dataList.stream().map(TUErrorLimit::convertDtoToEntity).collect(Collectors.toList());
            ArrayList<STUErrorLimit> listResult = new ArrayList<>();
            int index = 0;
            for (STUErrorLimit congTo : listEntity) {
                var existError = tuErrorLimitRepository.findById(congTo.getId());
                if (existError.isPresent()) {
                    existError.get().setIdx(index++);
                    existError.get().setCapacity(congTo.getCapacity());
                    ;
                    existError.get().setCcx(congTo.getCcx());
                    existError.get().setDelta(congTo.getDelta());
                    existError.get().setF(congTo.getF());
                }
                var congToEntity = tuErrorLimitRepository.save(existError.get());
                listResult.add(congToEntity);
            }
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(listResult);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR + " " + ex.getMessage());
            response.setData(null);
        }
        return response;
    }

    @Override
    public ResponseData updateTI(List<TIErrorLimit> dataList) {
        ResponseData response = new ResponseData();
        try {
            List<STIErrorLimit> listEntity = dataList.stream().map(TIErrorLimit::convertDtoToEntity).collect(Collectors.toList());
            var listResult = new ArrayList<STIErrorLimit>();
            for (int i = 0; i < listEntity.size(); i++) {
                var congTo = listEntity.get(i);
                var existError = tiErrorLimitRepository.findById(congTo.getId());
                if (existError.isPresent()) {
                    existError.get().setIdx(i);
                    existError.get().setCapacity(congTo.getCapacity());
                    ;
                    existError.get().setCcx(congTo.getCcx());
                    existError.get().setF1(congTo.getF1());
                    existError.get().setDelta1(congTo.getDelta1());
                    existError.get().setF5(congTo.getF5());
                    existError.get().setDelta5(congTo.getDelta5());
                    existError.get().setF20(congTo.getF20());
                    existError.get().setDelta20(congTo.getDelta20());
                    existError.get().setDelta100(congTo.getDelta100());
                    existError.get().setF100(congTo.getF100());
                    existError.get().setDelta120(congTo.getDelta120());
                    existError.get().setF120(congTo.getF120());
                    var congToEntity = tiErrorLimitRepository.save(existError.get());
                    listResult.add(congToEntity);
                }
            }
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(listResult);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR + " " + ex.getMessage());
            response.setData(null);
        }
        return response;
    }
}
