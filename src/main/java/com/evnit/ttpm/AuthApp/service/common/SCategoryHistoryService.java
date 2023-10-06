package com.evnit.ttpm.AuthApp.service.common;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.category.SCategoryHistory;
import com.evnit.ttpm.AuthApp.repository.category.SCategoryHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SCategoryHistoryService {
    @Autowired
    SCategoryHistoryRepository sCategoryRepo;
    public SCategoryHistoryService(){

    }
    public ResponseData getAll(String categoryType,Integer categoryId){
        ResponseData response = new ResponseData();
        try {
            List<SCategoryHistory> listHistory =  sCategoryRepo.findByCategoryTypeAndCategoryId(categoryType,categoryId);
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(listHistory);
        }catch (Exception ex){
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ex.getMessage());
            response.setData(null);
        }
        return response;
    }
    public ResponseData create(SCategoryHistory sCategoryHistory){
        ResponseData response = new ResponseData();
        try {
            sCategoryHistory.setDateModifier(new Date());
            SCategoryHistory entity = sCategoryRepo.save(sCategoryHistory);
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(entity);
        }catch (Exception ex){
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ex.getMessage());
            response.setData(null);
        }
        return response;
    }
}
