package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.category.PARAMATER;
import com.evnit.ttpm.AuthApp.entity.category.P_PROBLEM_ASSETS;
import com.evnit.ttpm.AuthApp.entity.category.View_Problem;
import com.evnit.ttpm.AuthApp.entity.file.AF_OTHER;
import com.evnit.ttpm.AuthApp.mapper.ParamaterMapper;
import com.evnit.ttpm.AuthApp.model.Paramater.ParamaterCreateDto;
import com.evnit.ttpm.AuthApp.model.Paramater.ParamaterListDto;
import com.evnit.ttpm.AuthApp.model.Paramater.SearchParamaterList;
import com.evnit.ttpm.AuthApp.model.filter.SearchFilter;
import com.evnit.ttpm.AuthApp.model.filter.SearchQuery;
import com.evnit.ttpm.AuthApp.repository.category.PARAMATERRepository;
import com.evnit.ttpm.AuthApp.repository.category.P_PROBLEM_ASSETSRepository;
import com.evnit.ttpm.AuthApp.repository.category.ViewProblemRepository;
import com.evnit.ttpm.AuthApp.repository.file.AFOtherRepository;
import com.evnit.ttpm.AuthApp.util.CustomPageImpl;
import com.evnit.ttpm.AuthApp.util.SpecificationUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class SCategoryParamaterServiceImpl implements SCategoryParamaterService {
    @Autowired
    PARAMATERRepository ParamaterRepository;
    @Autowired
    P_PROBLEM_ASSETSRepository mapProblem;
    @Autowired
    AFOtherRepository afOtherRepository;
    @Autowired
    ViewProblemRepository ViewProblemRepository;
    private UUID corrId = UUID.randomUUID();
    @Autowired
    JdbcTemplate jdbcTemplate;
    private final ModelMapper mapper;

    public SCategoryParamaterServiceImpl(ParamaterMapper mapperConfig) {
        this.mapper = mapperConfig.getModelMapper();
    }




    @Override
    public ResponseData create(ParamaterCreateDto createDto) {
        ResponseData response = new ResponseData();
        try {

            ParamaterCreateDto createDto1 = new ParamaterCreateDto();

            createDto1.setId(UUID.randomUUID().toString());
            createDto1.setIa(createDto.getIa());
            createDto1.setIb(createDto.getIb());
            createDto1.setIc(createDto.getIc());
            createDto1.setUa(createDto.getUa());
            createDto1.setUb(createDto.getUb());
            createDto1.setUc(createDto.getUc());
            createDto1.setPhia(createDto.getPhia());
            createDto1.setPhib(createDto.getPhib());
            createDto1.setPhic(createDto.getPhic());
            createDto1.setTbaId(createDto.getTbaId());
            createDto1.setDiemDoId(createDto.getDiemDoId());
            createDto1.setEventId(createDto.getEventId());
            createDto1.setDateEvent(createDto.getDateEvent());


            var entity = mapper.map(createDto1, PARAMATER.class);
            var result = ParamaterRepository.save(entity);



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
    public ResponseData update(int id, ParamaterCreateDto updateDto) {
        ResponseData response = new ResponseData();
        try {
            var existEntity = ParamaterRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Thỏa thuận không tồn tại"));
            ParamaterCreateDto updateDto1 = new ParamaterCreateDto();

            updateDto1.setIa(updateDto.getIa());
            updateDto1.setIb(updateDto.getIb());
            updateDto1.setIc(updateDto.getIc());
            updateDto1.setUa(updateDto.getUa());
            updateDto1.setUb(updateDto.getUb());
            updateDto1.setUc(updateDto.getUc());
            updateDto1.setPhia(updateDto.getPhia());
            updateDto1.setPhib(updateDto.getPhib());
            updateDto1.setPhic(updateDto.getPhic());
            updateDto1.setTbaId(updateDto.getTbaId());
            updateDto1.setDiemDoId(updateDto.getDiemDoId());
            updateDto1.setEventId(updateDto.getEventId());
            updateDto1.setDateEvent(updateDto.getDateEvent());


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

