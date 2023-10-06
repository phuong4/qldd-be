package com.evnit.ttpm.AuthApp.service.common;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.common.ALstType;
import com.evnit.ttpm.AuthApp.repository.common.ALstTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ALstTypeService {
    final
    ALstTypeRepository aLstTypeRepository;

    public ALstTypeService(ALstTypeRepository aLstTypeRepository) {
        this.aLstTypeRepository = aLstTypeRepository;
    }
    public ResponseData GetLstTypeNotVisible(){
//        ResponseData response = new ResponseData();
//        try {
//           List<ALstType> listRs = aLstTypeRepository.findByIsVisibleIsFalseAndTypeIdParent();
//           response.setData(listRs);
//            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
//            response.setState(ResponseData.STATE.OK.toString());
//        }catch (Exception ex){
//            response.setData(null);
//            response.setMessage(ex.getMessage());
//            response.setState(ResponseData.STATE.OK.toString());
//        }
//        return response;
        return null;
    }
}
