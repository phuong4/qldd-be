package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.category.PARAMATER;
import com.evnit.ttpm.AuthApp.entity.category.Q_PQOBJ_USER;
import com.evnit.ttpm.AuthApp.mapper.ParamaterMapper;
import com.evnit.ttpm.AuthApp.model.PQOBJ.PQOBJCreateDto;
import com.evnit.ttpm.AuthApp.model.Paramater.ParamaterCreateDto;
import com.evnit.ttpm.AuthApp.repository.category.PARAMATERRepository;
import com.evnit.ttpm.AuthApp.repository.category.PQOBJRepository;
import com.evnit.ttpm.AuthApp.repository.category.P_PROBLEM_ASSETSRepository;
import com.evnit.ttpm.AuthApp.repository.category.ViewProblemRepository;
import com.evnit.ttpm.AuthApp.repository.file.AFOtherRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class SCategoryPQOBJServiceImpl implements SCategoryPQOBJService {
    @Autowired
    PARAMATERRepository ParamaterRepository;
    @Autowired
    PQOBJRepository mapProblem;
    @Autowired
    AFOtherRepository afOtherRepository;
    @Autowired
    ViewProblemRepository ViewProblemRepository;
    private UUID corrId = UUID.randomUUID();
    @Autowired
    JdbcTemplate jdbcTemplate;
    private final ModelMapper mapper;

    public SCategoryPQOBJServiceImpl(ParamaterMapper mapperConfig) {
        this.mapper = mapperConfig.getModelMapper();
    }




    @Override
    public ResponseData create(PQOBJCreateDto createDto) {
        ResponseData response = new ResponseData();
        try {

            PQOBJCreateDto createDto1 = new PQOBJCreateDto();

            createDto1.setASSETID(createDto.getId());
            createDto1.setUSERID(createDto.getUseridtype());
            createDto1.setISLD(createDto.getIsldtype());
            createDto1.setISQL(createDto.getIsqltype());

            var entity = mapper.map(createDto1, Q_PQOBJ_USER.class);
            var result = mapProblem.save(entity);



            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(result);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ex.getMessage());
            response.setData(null);
        }
        return response;
    }


    @Override
    public ResponseData update(int id, PQOBJCreateDto updateDto) {
        ResponseData response = new ResponseData();
        try {
            var existEntity = ParamaterRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Thỏa thuận không tồn tại"));
            PQOBJCreateDto updateDto1 = new PQOBJCreateDto();


           // updateDto1.setISLD(updateDto.getISLD());
            //updateDto1.setISQL(updateDto.getISQL());



            mapper.map(updateDto1, existEntity);
            var result = ParamaterRepository.save(existEntity);
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(result);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ex.getMessage());
            response.setData(null);
        }
        return response;
    }


   
}

