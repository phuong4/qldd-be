package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.annotation.CurrentUser;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.category.P_PROBLEM_ASSETS;
import com.evnit.ttpm.AuthApp.entity.category.SCategorySuCoBatThuong;
import com.evnit.ttpm.AuthApp.entity.file.AF_OTHER;
import com.evnit.ttpm.AuthApp.entity.category.View_Problem;

import com.evnit.ttpm.AuthApp.mapper.SuCoMapper;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.model.category.SuCo.*;
import com.evnit.ttpm.AuthApp.model.category.nhamaydien.NhaMayDienCrudDto;
import com.evnit.ttpm.AuthApp.model.filter.SearchFilter;
import com.evnit.ttpm.AuthApp.model.filter.SearchQuery;
import com.evnit.ttpm.AuthApp.repository.category.*;
import com.evnit.ttpm.AuthApp.repository.file.AFOtherRepository;
import com.evnit.ttpm.AuthApp.service.system.WonderHisService;
import com.evnit.ttpm.AuthApp.util.CustomPageImpl;
import com.evnit.ttpm.AuthApp.util.SpecificationUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
public class SCategorySuCoServiceImpl implements SCategorySuCoService {
    @Autowired
    SuCoRepository SuCoRepository;
    @Autowired
    P_PROBLEM_ASSETSRepository mapProblem;
    @Autowired
    AFOtherRepository afOtherRepository;
    @Autowired
    ViewProblemRepository ViewProblemRepository;
    @Autowired
    WonderHisService _wonderHisService;
    private UUID corrId = UUID.randomUUID();
    @Autowired
    JdbcTemplate jdbcTemplate;
    private final ModelMapper mapper;

    public SCategorySuCoServiceImpl(SuCoMapper mapperConfig) {
        this.mapper = mapperConfig.getModelMapper();
    }

    @Override
    public ResponseData getAll(SearchSuCoList dto) {
        ResponseData response = new ResponseData();


        SearchQuery searchQuery = new SearchQuery();
        List<SuCoListDto> listCongTo = new ArrayList<SuCoListDto>();
        List<SuCoListDto> total = this.TotalDanhDanhSachKiem(dto);
        SearchFilter searchFilter3 = new SearchFilter();
        List<SearchFilter> searchFilters = new ArrayList<>();
        if (dto.getType() != null && !dto.getType().isEmpty()) {
            searchFilter3 = new SearchFilter("Type", "IN", dto.getType());
            searchFilters.add(searchFilter3);
        }
        if (dto.getTbaId() != null && !dto.getTbaId().isEmpty()) {
            searchFilter3 = new SearchFilter("TbaId", "IN", dto.getTbaId());
            searchFilters.add(searchFilter3);
        }
        if (dto.getTbaStatus() != null && !dto.getTbaStatus().isEmpty()) {
            searchFilter3 = new SearchFilter("TbaStatus", "IN", dto.getTbaStatus());
            searchFilters.add(searchFilter3);
        }
        if (dto.getCategoryId() != null && !dto.getCategoryId().isEmpty()) {
            searchFilter3 = new SearchFilter("CategoryId", "IN", dto.getCategoryId());
            searchFilters.add(searchFilter3);
        }
        if (dto.getChiTiet() != null && !dto.getChiTiet().isEmpty()) {
            searchFilter3 = new SearchFilter("Detail", "LIKE", dto.getChiTiet());
            searchFilters.add(searchFilter3);
        }
        if (dto.getBienPhap() != null && !dto.getBienPhap().isEmpty()) {
            searchFilter3 = new SearchFilter("Remedies", "LIKE", dto.getBienPhap());
            searchFilters.add(searchFilter3);
        }
        if (dto.getHauQua() != null && !dto.getHauQua().isEmpty()) {
            searchFilter3 = new SearchFilter("Consequence", "LIKE", dto.getHauQua());
            searchFilters.add(searchFilter3);
        }
        if (dto.getKetQua() != null && !dto.getKetQua().isEmpty()) {
            searchFilter3 = new SearchFilter("Result", "LIKE", dto.getKetQua());
            searchFilters.add(searchFilter3);
        }
        if (dto.getNguyenNhan() != null && !dto.getNguyenNhan().isEmpty()) {
            searchFilter3 = new SearchFilter("Cause", "LIKE", dto.getNguyenNhan());
            searchFilters.add(searchFilter3);
        }
        if (dto.getNoiDung() != null && !dto.getNoiDung().isEmpty()) {
            searchFilter3 = new SearchFilter("Content", "LIKE", dto.getNoiDung());
            searchFilters.add(searchFilter3);
        }
        if (dto.getFromDate() != null && !dto.getFromDate().equals("Invalid date")) {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date fromDate = null;
            try {
                fromDate = formatter.parse(dto.getFromDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            searchFilter3 = new SearchFilter("EndDate", ">=", fromDate);
            searchFilters.add(searchFilter3);
        }
        if (!dto.getEndDate().equals("Invalid date") && dto.getEndDate() != null) {
            Date endDate = null;
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

            try {
                endDate = formatter.parse(dto.getEndDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            searchFilter3 = new SearchFilter("EndDate", "<=", endDate);
            searchFilters.add(searchFilter3);
        }

        Pageable pageable = PageRequest.of(dto.getPage(), dto.getLimit(), dto.getSortExpression());
        searchQuery.setSearchFilters(searchFilters);
        Specification<View_Problem> spec = SpecificationUtil.bySearchQuery(searchQuery, View_Problem.class);

        try {
            var pageResult = ViewProblemRepository.findAll(spec, pageable);
            var dtoResult = pageResult.getContent().stream().map(item -> mapper.map(item, SuCoListDto.class)).collect(Collectors.toList());

            for (SuCoListDto item: dtoResult)
            {
                listCongTo.add(item);
            }
            listCongTo.add(0, total.get(0));
            Page<SuCoListDto> result = new PageImpl<>(dtoResult, pageable, pageResult.getTotalElements());
            CustomPageImpl cusResult = new CustomPageImpl();
            cusResult.setContent(listCongTo);
            cusResult.setNumber(result.getNumber());
            cusResult.setSize(result.getSize());
            cusResult.setTotalElements(pageResult.getTotalElements());
            cusResult.setLast(result.isLast());
            cusResult.setTotalPages(result.getTotalPages());
            cusResult.setFirst(result.isFirst());
            cusResult.setNumberOfElements(result.getNumberOfElements());

            ObjectMapper mapperJson = new ObjectMapper();
            cusResult.setPageable(mapperJson.convertValue(result.getPageable(), JsonNode.class));
            cusResult.setSort(mapperJson.convertValue(result.getSort(), JsonNode.class));



            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setState(ResponseData.STATE.OK.toString());
            response.setData(cusResult);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ex.getMessage());
            response.setData(null);
        }
        return response;
    }
    private List<SuCoListDto> TotalDanhDanhSachKiem(SearchSuCoList dto) {

        String sql;

        List lst;
        sql = "SELECT '' as TypeObjId\n" +
                "      , 0 as Type\n" +
                "      , 0 as CheckLD\n" +
                "      , 0 as CheckNQL\n" +
                "      , CAST(NULL as date) as EndDate\n" +
                "      , CAST(NULL as date) as FromDate\n" +
                "      , 0 as Id\n" +
                "      , '' as TbaId\n" +
                "      , '' as TbaName\n" +
                "      , 0 as TbaStatus\n" +
                "      , '' as IdType\n" +
                "      , '' as TbaStatusName\n" +
                "      , '' as TypeName\n" +
                "      , '' as Cause\n" +
                "      , '' as Content\n" +
                "      , '' as Detail\n" +
                "      , 0 as Status\n" +
                "      , '' as StatusName\n" +
                "      , '' as Result\n" +
                "      , '' as Consequence\n" +
                "      , '' as Remedies\n" +
                "      , NEWID() as KeyId\n" +
                "      , sum(Count) as Count\n" +
                "      , '' as FileData\n" +
                "  FROM [QLDD].[dbo].[View_Problem] where 1=1 ";

        if (dto.getType() != null  && !dto.getType().isEmpty()) {
            sql += " AND Type IN (" + dto.getType().stream().map(String::valueOf).collect(Collectors.joining(",")) + ")";
        }
        if (dto.getTbaStatus() != null && !dto.getTbaStatus().isEmpty()) {
            sql += " AND TbaStatus IN (" + dto.getTbaStatus().stream().map(String::valueOf).collect(Collectors.joining(",")) + ")";
        }
        if (dto.getTbaId() != null && !dto.getTbaId().isEmpty()) {
            sql += " AND TbaId IN ('" + String.join("','", dto.getTbaId()) + "')";
        }
        if (dto.getCategoryId() != null && !dto.getCategoryId().isEmpty()) {
            sql += " AND CategoryId IN ('" + String.join("','", dto.getCategoryId()) + "')";
        }
        if (dto.getChiTiet() != null ) {
            sql += "AND Detail LIKE '%" + dto.getChiTiet() + "%'";
        }
        if (dto.getNoiDung() != null ) {
            sql += "AND Content LIKE '%" + dto.getNoiDung() + "%'";
        }
        if (dto.getKetQua() != null ) {
            sql += "AND Result LIKE '%" + dto.getKetQua() + "%'";
        }
        if (dto.getHauQua() != null ) {
            sql += "AND Consequence LIKE '%" + dto.getHauQua() + "%'";
        }
        if (dto.getBienPhap() != null ) {
            sql += "AND Remedies LIKE '%" + dto.getBienPhap() + "%'";
        }
        if (dto.getNguyenNhan() != null ) {
            sql += "AND Cause LIKE '%" + dto.getNguyenNhan() + "%'";
        }


        if (dto.getFromDate() != null ) {
            sql += " AND EndDate >= '" + dto.getFromDate().toString() + "'";
        }

        if (dto.getEndDate() != null ) {
            sql += " AND EndDate <= '" + dto.getEndDate().substring(0,10).toString() + " 23:59:59'";
        }

        lst = jdbcTemplate.query(sql,new BeanPropertyRowMapper<SuCoListDto>(SuCoListDto.class));

        return  lst;
    }

    @Override
    public ResponseData getById(int id) {
        return null;
    }

    @Override
    public ResponseData create(SuCoCreateDto createDto) {
        ResponseData response = new ResponseData();
        try {

            SuCoCreateDto createDto1 = new SuCoCreateDto();
            if (createDto.getFromDate() != null && createDto.getEndDate() != null) {
                if (createDto.getFromDate().after(createDto.getEndDate())) {
                    return new ResponseData(ResponseData.STATE.FAIL.toString(), "Vui lòng chọn Thời gian thực hiện- kết thúc >= Thời gian thực hiện-bắt đầu");
                }
            }
            String sql;
            sql = " select 1 from P_PROBLEM where ASSETID = ? and STARTDTIME =? and ENDDTIME =?";
            List lst= jdbcTemplate.query(sql, new Object[]{createDto.getTbaId(),createDto.getFromDate(),createDto.getEndDate()},
                    new BeanPropertyRowMapper(SuCoListDto.class));
            if (lst.size() > 0 )
                return new ResponseData(ResponseData.STATE.FAIL.toString(), "Đợt công tác bị trùng");

            var guid = UUID.randomUUID().toString();

            createDto1.setPROBLEMID(guid);
            createDto1.setSTARTDTIME(createDto.getFromDate());
            createDto1.setENDDTIME(createDto.getEndDate());
            createDto1.setPCONTENT(createDto.getContent());
            createDto1.setPCONSEQUENCE(createDto.getConsequence());
            createDto1.setPCAUSE(createDto.getCause());
            createDto1.setPREMEDIES(createDto.getRemedies());
            createDto1.setPRESULT_PROCESS(createDto.getResult());
            createDto1.setASSETID(createDto.getTbaId());
            createDto1.setISDELETE(false);
            createDto1.setTYPE(createDto.getTYPE());
            createDto1.setFILEDATA(createDto.getFileDataCreate());
            createDto1.setUSER_CR_ID(createDto.getUSER_CR_ID());

            createDto1.setISDELETE(false);

//
//            if (createDto.getCHECK() == null || createDto.getCHECK()== false ) {
//                createDto1.setCheckTick(false);
//
//            } else {
//                createDto1.setCheckTick(createDto.getCHECK());
//                createDto1.setFACTOR(createDto.getFACTOR());
//                createDto1.setSTARTDATE_DOC(createDto.getFromdateslcreate());
//                createDto1.setVALID_BASE(createDto.getValidbasecreate());
//                createDto1.setENDDATE_DOC(createDto.getEnddateslcreate());
//            }
//            createDto1.setFORM_SYN_POWER(createDto.getFormsynpowercreate());

//            createDto1.setTYPE(createDto.getTypecreate());


            var entity = mapper.map(createDto1, SCategorySuCoBatThuong.class);
            var result = SuCoRepository.save(entity);


            if (createDto.getListDiemDo() != null) {
                createDto.getListDiemDo().forEach(e -> {

                    MapSuCoDiemDoCreateDto createDto2 = new MapSuCoDiemDoCreateDto();
                    createDto2.setPROBLEMID(guid);
                    createDto2.setASSETID(e);
                    createDto2.setTYPE(2);
                    createDto2.setISDELETE(false);

                    var entity1 = mapper.map(createDto2, P_PROBLEM_ASSETS.class);
                    var result1 = mapProblem.save(entity1);

                });
            }
            if (createDto.getListCongTo() != null) {
                createDto.getListCongTo().forEach(e -> {

                    MapSuCoDiemDoCreateDto createDto2 = new MapSuCoDiemDoCreateDto();
                    createDto2.setPROBLEMID(guid);
                    createDto2.setASSETID(e);
                    createDto2.setTYPE(1);
                    createDto2.setISDELETE(false);


                    var entity1 = mapper.map(createDto2, P_PROBLEM_ASSETS.class);
                    var result1 = mapProblem.save(entity1);

                });
            }
            if (createDto.getListTI() != null) {

                createDto.getListTI().forEach(e -> {
                    MapSuCoDiemDoCreateDto createDto2 = new MapSuCoDiemDoCreateDto();
                    createDto2.setPROBLEMID(guid);
                    createDto2.setASSETID(e);
                    createDto2.setTYPE(3);
                    createDto2.setISDELETE(false);
                    var entity1 = mapper.map(createDto2, P_PROBLEM_ASSETS.class);
                    var result1 = mapProblem.save(entity1);

                });
            }
            if (createDto.getListTU() != null) {

                createDto.getListTU().forEach(e -> {

                    MapSuCoDiemDoCreateDto createDto2 = new MapSuCoDiemDoCreateDto();
                    createDto2.setPROBLEMID(guid);
                    createDto2.setASSETID(e);
                    createDto2.setTYPE(4);
                    createDto2.setISDELETE(false);


                    var entity1 = mapper.map(createDto2, P_PROBLEM_ASSETS.class);
                    var result1 = mapProblem.save(entity1);

                });
            }
            if (createDto.getFileDataCreate() != null && createDto.getFileDataCreate().size() > 0) {
                //Lưu filePath vào disk sau đó set lại filepath cho filedata trong các object
                for (FileData file : createDto.getFileDataCreate()) {
                    if (file.getBase64().length() > 0) {
                        file.setFilePath(Base64ToFile(file, "ACCREDITATION/" + guid));
                    }
                }
                //Lưu filePath vào db
                for (FileData file : createDto.getFileDataCreate()) {
                    AF_OTHER entity1 = new AF_OTHER();
                    entity1.setAfFileId(UUID.randomUUID().toString());
                    entity1.setCrdTime(new Date());
                    entity1.setUserId(createDto.getUSER_CR_ID());
                    entity1.setObjId(guid);
                    entity1.setOrgId("0");
                    entity1.setObjTypeId(AF_OTHER.AF_TYPE.SC.toString());
                    entity1.setFileSizeB((int) file.getSize());
                    entity1.setFilePath(file.getFilePath());
                    entity1.setFileName(file.getFileName());
                    afOtherRepository.save(entity1);
                }
            }
            //lưu his
            SuCoCreateDto dto = mapper.map("",SuCoCreateDto.class);
            List<String> ignoredFields = new ArrayList<>(){{
                add("isdelete");
                add("user_cr_id");
                add("typeobjid");
                add("listtiold");
                add("listtuold");
                add("listcongtoold");
                add("listdiemdoold");
                add("lstfiledelete");
                add("listti");
                add("listtu");
                add("listcongto");
                add("listdiemdo");
                add("filedatacreate");
                add("count");
                add("fromdate");
                add("enddate");
                add("checknql");
                add("checkld");
                add("tbastatus");
                add("type");
                add("tbaid");

            }};
            Map<String, String> variableNameMapping = new HashMap<>();
            variableNameMapping.put("cause", "Nguyên nhân");
            variableNameMapping.put("tbaname", "Tên NMĐ/TBA/RGL");
            variableNameMapping.put( "content","Nội dung");
            variableNameMapping.put( "typename","Kiểu");
            variableNameMapping.put("result", "Kết quả");
            variableNameMapping.put("consequence", "Hậu quả");
            variableNameMapping.put("remedies","Biện pháp");
            variableNameMapping.put( "fromdatestr","Thời gian thực hiện-Bắt đầu");
            variableNameMapping.put( "enddatestr","Thời gian thực hiện-Kết thúc");
            variableNameMapping.put( "detaildd","Điểm đo");
            variableNameMapping.put( "detailct","Công tơ");
            variableNameMapping.put( "detailtu","TU");
            variableNameMapping.put( "detailti","TI");

            _wonderHisService.UpdateHis(guid, createDto,dto,ignoredFields,variableNameMapping, createDto.getUSER_CR_ID(), "P_Problem", "INS");

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
    public ResponseData update(String id, SuCoCreateDto updateDto) {
        ResponseData response = new ResponseData();
        try {
            var existEntity = SuCoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Thỏa thuận không tồn tại"));
            SuCoCreateDto updateDto1 = new SuCoCreateDto();

            if (updateDto.getFromDate() != null && updateDto.getEndDate() != null) {
                if (updateDto.getFromDate().after(updateDto.getEndDate())) {
                    return new ResponseData(ResponseData.STATE.FAIL.toString(), "Vui lòng chọn Thời gian thực hiện- kết thúc >= Thời gian thực hiện-bắt đầu");
                }
            }
            String sql;
            sql = " select 1 from P_PROBLEM where ASSETID = ? and STARTDTIME =? and ENDDTIME =? and PROBLEMID != ? ";
            List lst= jdbcTemplate.query(sql, new Object[]{updateDto.getTbaId(),updateDto.getFromDate(),updateDto.getEndDate(),id},
                    new BeanPropertyRowMapper(SuCoListDto.class));
            if (lst.size() > 0 )
                return new ResponseData(ResponseData.STATE.FAIL.toString(), "Đợt công tác bị trùng");

            String sqlhis = "Select * from View_Problem where Keyid = '"+id+"'";
            List<SuCoCreateDto> lst2 = jdbcTemplate.query(sqlhis, new BeanPropertyRowMapper<>(SuCoCreateDto.class));
            SuCoCreateDto dto = mapper.map(lst2.get(0),SuCoCreateDto.class);

            updateDto1.setPROBLEMID(id);
            updateDto1.setSTARTDTIME(updateDto.getFromDate());
            updateDto1.setENDDTIME(updateDto.getEndDate());
            updateDto1.setPCONTENT(updateDto.getContent());
            updateDto1.setPCONSEQUENCE(updateDto.getConsequence());
            updateDto1.setPCAUSE(updateDto.getCause());
            updateDto1.setPREMEDIES(updateDto.getRemedies());
            updateDto1.setPRESULT_PROCESS(updateDto.getResult());
            updateDto1.setASSETID(updateDto.getTbaId());
            updateDto1.setISDELETE(false);
            updateDto1.setTYPE(updateDto.getTYPE());
            //updateDto1.setFILEDATA(updateDto.getFileDataCreate());


            mapper.map(updateDto1, existEntity);
//            existEntity.setDealId(DEALID);
            var result = SuCoRepository.save(existEntity);
            String sql1;
            sql1 = "DELETE FROM P_PROBLEM_ASSETS WHERE PROBLEMID='"+id+"'";
            jdbcTemplate.execute(sql1);
            if (updateDto.getListDiemDo() != null) {
                updateDto.getListDiemDo().forEach(e -> {

                    MapSuCoDiemDoCreateDto createDto2 = new MapSuCoDiemDoCreateDto();
                    createDto2.setPROBLEMID(id.trim());
                    createDto2.setASSETID(e.trim());
                    createDto2.setTYPE(2);
                    createDto2.setISDELETE(false);

                    var entity1 = mapper.map(createDto2, P_PROBLEM_ASSETS.class);
                    var result1 = mapProblem.save(entity1);

                });
            }else{
                updateDto.getListDiemDoOld().forEach(e -> {

                    MapSuCoDiemDoCreateDto createDto2 = new MapSuCoDiemDoCreateDto();
                    createDto2.setPROBLEMID(id.trim());
                    createDto2.setASSETID(e.trim());
                    createDto2.setTYPE(2);
                    createDto2.setISDELETE(false);

                    var entity1 = mapper.map(createDto2, P_PROBLEM_ASSETS.class);
                    var result1 = mapProblem.save(entity1);

                });
            }
            if (updateDto.getListCongTo() != null) {
                updateDto.getListCongTo().forEach(e -> {

                    MapSuCoDiemDoCreateDto createDto2 = new MapSuCoDiemDoCreateDto();
                    createDto2.setPROBLEMID(id.trim());
                    createDto2.setASSETID(e.trim());
                    createDto2.setTYPE(1);
                    createDto2.setISDELETE(false);


                    var entity1 = mapper.map(createDto2, P_PROBLEM_ASSETS.class);
                    var result1 = mapProblem.save(entity1);

                });
            }else{
                updateDto.getListCongToOld().forEach(e -> {

                    MapSuCoDiemDoCreateDto createDto2 = new MapSuCoDiemDoCreateDto();
                    createDto2.setPROBLEMID(id.trim());
                    createDto2.setASSETID(e.trim());
                    createDto2.setTYPE(1);
                    createDto2.setISDELETE(false);


                    var entity1 = mapper.map(createDto2, P_PROBLEM_ASSETS.class);
                    var result1 = mapProblem.save(entity1);

                });
            }
            if (updateDto.getListTI() != null) {

                updateDto.getListTI().forEach(e -> {
                    MapSuCoDiemDoCreateDto createDto2 = new MapSuCoDiemDoCreateDto();
                    createDto2.setPROBLEMID(id.trim());
                    createDto2.setASSETID(e.trim());
                    createDto2.setTYPE(3);
                    createDto2.setISDELETE(false);
                    var entity1 = mapper.map(createDto2, P_PROBLEM_ASSETS.class);
                    var result1 = mapProblem.save(entity1);

                });
            }{
                updateDto.getListTIOld().forEach(e -> {
                    MapSuCoDiemDoCreateDto createDto2 = new MapSuCoDiemDoCreateDto();
                    createDto2.setPROBLEMID(id.trim());
                    createDto2.setASSETID(e.trim());
                    createDto2.setTYPE(3);
                    createDto2.setISDELETE(false);
                    var entity1 = mapper.map(createDto2, P_PROBLEM_ASSETS.class);
                    var result1 = mapProblem.save(entity1);

                });
            }
            if (updateDto.getListTU() != null) {

                updateDto.getListTU().forEach(e -> {

                    MapSuCoDiemDoCreateDto createDto2 = new MapSuCoDiemDoCreateDto();
                    createDto2.setPROBLEMID(id.trim());
                    createDto2.setASSETID(e.trim());
                    createDto2.setTYPE(4);
                    createDto2.setISDELETE(false);


                    var entity1 = mapper.map(createDto2, P_PROBLEM_ASSETS.class);
                    var result1 = mapProblem.save(entity1);

                });
            }{
                updateDto.getListTUOld().forEach(e -> {

                    MapSuCoDiemDoCreateDto createDto2 = new MapSuCoDiemDoCreateDto();
                    createDto2.setPROBLEMID(id.trim());
                    createDto2.setASSETID(e.trim());
                    createDto2.setTYPE(4);
                    createDto2.setISDELETE(false);


                    var entity1 = mapper.map(createDto2, P_PROBLEM_ASSETS.class);
                    var result1 = mapProblem.save(entity1);

                });
            }

            List<FileData> newFileList = new ArrayList<FileData>();
            if (updateDto.getFileDataCreate() != null && updateDto.getFileDataCreate().size() > 0) {
                //Lưu filePath vào disk sau đó set lại filepath cho filedata trong các object
                for (FileData file : updateDto.getFileDataCreate()) {
                    if (file.getBase64() != null && file.getBase64().length() > 0) {
                        file.setFilePath(Base64ToFile(file, "ACCREDITATION/" + updateDto.getPROBLEMID()));
                        newFileList.add(file);
                    }
                }
                //Lưu file duoc them vào dbfileName = "Planet9_Wallpaper_5000x2813.jpg"
                for (FileData file : updateDto.getFileDataCreate()) {
                    if (file.getBase64() != null && file.getBase64().length() > 0) {
                        AF_OTHER entity = new AF_OTHER();
                        entity.setAfFileId(UUID.randomUUID().toString());
                        entity.setCrdTime(new Date());
                        entity.setUserId(updateDto.getUSER_CR_ID());
                        entity.setObjId(id);
                        entity.setOrgId("0");
                        entity.setObjTypeId(AF_OTHER.AF_TYPE.SC.toString());
                        entity.setFileSizeB((int) file.getSize());
                        entity.setFilePath(file.getFilePath());
                        entity.setFileName(file.getFileName());
                        afOtherRepository.save(entity);
                    }
                }
            }
            //Del file on db only for Accreditation
            if (updateDto.getLstFileDelete() != null && updateDto.getLstFileDelete().size() > 0) {
                for (var file : updateDto.getLstFileDelete()) {
                    String cmd;
                    cmd = "DELETE FROM [dbo].AF_OTHER WHERE AFFILEID = '" + file.getAfFileId() + "';";
                    jdbcTemplate.execute(cmd);
                }
            }

            //Del file on disk when all db updated
            if (updateDto.getLstFileDelete() != null && updateDto.getLstFileDelete().size() > 0) {
                DelAllFile(updateDto.getLstFileDelete());
            }
           /* if (updateDto.getListTI() != null) {


                updateDto.getListTIOld().forEach(e -> {
                    MapSuCoDiemDoKey i = new MapSuCoDiemDoKey();
                    i.setPROBLEMID(id);
                    i.setASSETID(e.trim());


                    var existEntity2 = mapProblem.findById(i).orElseThrow(() -> new IllegalArgumentException("Quản lý điểm đo không tồn tại"));
                    MapSuCoDiemDoCreateDto b = new MapSuCoDiemDoCreateDto();
                    b.setISDELETE(true);
                    b.setPROBLEMID(id);
                    b.setASSETID(e.trim());
                    mapper.map(b, existEntity2);
                    var result1 = mapProblem.save(existEntity2);


                });

                updateDto.getListTI().forEach(e -> {

                    MapSuCoDiemDoCreateDto createDto2 = new MapSuCoDiemDoCreateDto();
                    createDto2.setPROBLEMID(id);
                    createDto2.setASSETID(e);
                    createDto2.setTYPE(3);
                    createDto2.setISDELETE(false);

                    var entity1 = mapper.map(createDto2, P_PROBLEM_ASSETS.class);
                    var result3 = mapProblem.save(entity1);

                });
            }
            if (updateDto.getListTU() != null) {


                updateDto.getListTUOld().forEach(e -> {
                    MapSuCoDiemDoKey i = new MapSuCoDiemDoKey();
                    i.setPROBLEMID(id);
                    i.setASSETID(e.trim());

                    var existEntity2 = mapProblem.findById(i).orElseThrow(() -> new IllegalArgumentException("Quản lý điểm đo không tồn tại"));
                    MapSuCoDiemDoCreateDto b = new MapSuCoDiemDoCreateDto();
                    b.setISDELETE(true);
                    b.setPROBLEMID(id);
                    b.setASSETID(e.trim());
                    mapper.map(b, existEntity2);
                    var result1 = mapProblem.save(existEntity2);


                });
                updateDto.getListTU().forEach(e -> {

                    MapSuCoDiemDoCreateDto createDto2 = new MapSuCoDiemDoCreateDto();
                    createDto2.setPROBLEMID(id);
                    createDto2.setASSETID(e);
                    createDto2.setTYPE(4);

                    var entity1 = mapper.map(createDto2, P_PROBLEM_ASSETS.class);
                    var result3 = mapProblem.save(entity1);

                });

            }
            if (updateDto.getListCongTo() != null) {
                updateDto.getListCongToOld().forEach(e -> {
                    MapSuCoDiemDoKey i = new MapSuCoDiemDoKey();
                    i.setPROBLEMID(id);
                    i.setASSETID(e.trim());

                    var existEntity2 = mapProblem.findById(i).orElseThrow(() -> new IllegalArgumentException("Quản lý điểm đo không tồn tại"));
                    MapSuCoDiemDoCreateDto b = new MapSuCoDiemDoCreateDto();
                    b.setISDELETE(true);
                    b.setPROBLEMID(id);
                    b.setASSETID(e.trim());
                    mapper.map(b, existEntity2);
                    var result1 = mapProblem.save(existEntity2);


                });

                updateDto.getListCongTo().forEach(e -> {

                    MapSuCoDiemDoCreateDto createDto2 = new MapSuCoDiemDoCreateDto();
                    createDto2.setPROBLEMID(id);
                    createDto2.setASSETID(e);
                    createDto2.setTYPE(1);

                    var entity1 = mapper.map(createDto2, P_PROBLEM_ASSETS.class);
                    var result3 = mapProblem.save(entity1);

                });
            }
            if (updateDto.getListDiemDo() != null) {
                updateDto.getListDiemDoOld().forEach(e -> {
                    MapSuCoDiemDoKey i = new MapSuCoDiemDoKey();
                    i.setPROBLEMID(id);
                    i.setASSETID(e.trim());

                    var existEntity2 = mapProblem.findById(i).orElseThrow(() -> new IllegalArgumentException("Quản lý điểm đo không tồn tại"));
                    MapSuCoDiemDoCreateDto b = new MapSuCoDiemDoCreateDto();
                    b.setISDELETE(true);
                    b.setPROBLEMID(id);
                    b.setASSETID(e.trim());
                    mapper.map(b, existEntity2);
                    var result1 = mapProblem.save(existEntity2);


                });


                updateDto.getListDiemDo().forEach(e -> {

                    MapSuCoDiemDoCreateDto createDto2 = new MapSuCoDiemDoCreateDto();
                    createDto2.setPROBLEMID(id);
                    createDto2.setASSETID(e);
                    createDto2.setTYPE(2);
                    var entity1 = mapper.map(createDto2, P_PROBLEM_ASSETS.class);
                    var result3 = mapProblem.save(entity1);

                });
            }
*/
            //lưu his
            List<String> ignoredFields = new ArrayList<>(){{
                add("is_delete");
                add("user_cr_id");
                add("typeobjid");
                add("checknql");
                add("checkld");
                add("tbastatus");
                add("filedatacreate");
                add("lstfiledelete");
                add("listdiemdoold");
                add("listcongtoold");
                add("listtuold");
                add("listtiold");
                add("count");add("fromdate");
                add("enddate");
            }};
            Map<String, String> variableNameMapping = new HashMap<>();
            variableNameMapping.put("cause", "Nguyên nhân");
            variableNameMapping.put("tbaname", "Tên NMĐ/TBA/RGL");
            variableNameMapping.put( "content","Nội dung");
            variableNameMapping.put( "typename","Kiểu");
            variableNameMapping.put("result", "Kết quả");
            variableNameMapping.put("consequence", "Hậu quả");
            variableNameMapping.put("remedies","Biện pháp");
            variableNameMapping.put( "fromdatestr","Thời gian thực hiện-Bắt đầu");
            variableNameMapping.put( "enddatestr","Thời gian thực hiện-Kết thúc");
            variableNameMapping.put( "detaildd","Điểm đo");
            variableNameMapping.put( "detailct","Công tơ");
            variableNameMapping.put( "detailtu","TU");
            variableNameMapping.put( "detailti","TI");

            _wonderHisService.UpdateHis(id, updateDto,dto,ignoredFields,variableNameMapping,updateDto.getUSER_CR_ID(), "P_Problem", "UPDATE");


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
    public ResponseData delete(String id) {
        ResponseData response = new ResponseData();
        try {
            var result = SuCoRepository.findById(id).map(SuCo -> {
                SuCo.setISDELETE(true);
                return SuCoRepository.save(SuCo);
            }).orElseThrow(() -> new IllegalArgumentException("Thỏa thuận không tồn tại"));
            response.setData(id);
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setState(ResponseData.STATE.OK.toString());
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ex.getMessage());
            response.setData(null);
        }
        return response;
    }
    @Value("${upload.dir}")
    private String uploadDir;
    private String Base64ToFile(FileData filedata, String subDirectory) throws Exception {
        try {
            Path currentDirectory = Paths.get("");
            Path fileDirectory = Paths.get(currentDirectory.toString(), uploadDir + "/" + subDirectory);
            if (!Files.exists(fileDirectory)) {
                File f = new File(fileDirectory.toUri());
                f.mkdirs();
            }
            String file_name = UUID.randomUUID().toString();
            //String file_Extension = filedata.getBase64().split(";")[0].split("/")[1];
            String file_Extension = "";
            if (filedata.getFileName().contains(".")) {
                var name = filedata.getFileName();
                var filenames = name.split("\\.");
                file_Extension = filenames[filenames.length - 1];
            }
            String file_DataStr = filedata.getBase64().split(",")[1];
            byte[] file_DataByte = Base64.getDecoder().decode(file_DataStr);
            OutputStream out = new FileOutputStream(new File(fileDirectory + "\\" + file_name + "." + file_Extension));
            out.write(file_DataByte);
            out.close();
            return fileDirectory + "\\" + file_name + "." + file_Extension;
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
    public ResponseData getFileKiemDinh(String KeyId) {
        ResponseData response = new ResponseData();

        try {
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());

            String sql;
            List lst;
            //Lấy từ bảng chi tiết
            sql = "SELECT afFileId, [FILENAME] as 'fileName', '' as 'base64', filePath, FILESIZEB as 'size' FROM [QLDD].[dbo].[AF_OTHER] where OBJTYPEID ='SC' and [OBJID] = '" + KeyId + "'";
            lst = jdbcTemplate.queryForList(sql);

            response.setData(lst);


        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ex.getMessage());
            response.setData(null);
        }


        return response;
    }
    @Override
    public ResponseData updateNQL(@CurrentUser CustomUserDetails user, String PROBLEMID, String ASSETID, Boolean stt) {
        String sql1;
        var listId = ASSETID.split(",");
        for (String idAset : listId){
            sql1 = "select  1 from Q_PQOBJ_USER where ASSETID=? and USERID=? and ISQL=?";
            List lst=jdbcTemplate.queryForList(sql1,idAset,user.getUserid(),true);
            if (lst==null || lst.isEmpty() )
                return new ResponseData(ResponseData.STATE.FAIL.toString(), "Không có quyền cập nhật nhà quản lý");

        }

        ResponseData response = new ResponseData();
        String strId = PROBLEMID.replace(",", "','");

        var strStt = 0;
        if (stt) strStt = 1;
        else strStt = 0;
        try {
            String sql;
            sql = "UPDATE [dbo].[P_PROBLEM] SET [NQL_XNHAN] = " + strStt + " WHERE [PROBLEMID] in ('" + strId + "')";
            jdbcTemplate.execute(sql);

            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setState(ResponseData.STATE.OK.toString());
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ex.getMessage());
            response.setData(null);
        }
        return response;
    }

    @Override
    public ResponseData updateLDP(@CurrentUser CustomUserDetails user,String id,String ASSETID, Boolean stt) {
        String sql1;

        var strASSETID = ASSETID.split(",");
        for (String assetId:strASSETID){
            sql1 = "select  1 from Q_PQOBJ_USER where ASSETID =? and USERID=? and ISLD=?";
            List lst=jdbcTemplate.queryForList(sql1,assetId,user.getUserid(),true);
            if (lst==null || lst.isEmpty() )
                return new ResponseData(ResponseData.STATE.FAIL.toString(), "Không có quyền cập nhật lãnh đạo phòng");
        }
        ResponseData response = new ResponseData();
        String strId = id.replace(",", "','");

        var strStt = 0;
        if (stt) strStt = 1;
        else strStt = 0;
        try {
            String sql;
            sql = "UPDATE [dbo].[P_PROBLEM] SET [LD_XNHAN] = " + strStt + " WHERE [PROBLEMID] in ('" + strId + "')";
            jdbcTemplate.execute(sql);

            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setState(ResponseData.STATE.OK.toString());
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ex.getMessage());
            response.setData(null);
        }
        return response;
    }
    private void DelAllFile(List<FileData> files) {
        try {
            for (FileData filedata : files) {
                String path = filedata.getFilePath();
                if (path.length() <= 0) continue;
                Path currentDirectory = Paths.get("");
                Path fileDirectory = Paths.get(currentDirectory.toString(), uploadDir, path);
                if (Files.exists(fileDirectory)) {
                    File file = new File(fileDirectory.toUri());
                    file.delete();
                }
            }
        } catch (Exception ex) {
        }
    }
}

