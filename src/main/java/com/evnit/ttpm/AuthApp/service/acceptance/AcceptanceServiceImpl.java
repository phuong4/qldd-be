package com.evnit.ttpm.AuthApp.service.acceptance;

import com.evnit.ttpm.AuthApp.annotation.CurrentUser;
import com.evnit.ttpm.AuthApp.entity.acceptance.*;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.category.S_ATTRIBUTE_GROUP_ASSOB;
import com.evnit.ttpm.AuthApp.entity.device.cuon.CuonAssetTemp;
import com.evnit.ttpm.AuthApp.entity.device.cuon.ZAG_CUON;
import com.evnit.ttpm.AuthApp.entity.device.cuon.ZagCuonTemp;
import com.evnit.ttpm.AuthApp.entity.file.AF_OTHER;
import com.evnit.ttpm.AuthApp.mapper.AcceptanceMapper;
import com.evnit.ttpm.AuthApp.model.acceptance.*;
import com.evnit.ttpm.AuthApp.model.accreditation.FileData;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.model.filter.SearchFilter;
import com.evnit.ttpm.AuthApp.model.filter.SearchQuery;
import com.evnit.ttpm.AuthApp.repository.acceptance.*;
import com.evnit.ttpm.AuthApp.repository.category.A_ASSETRepository;
import com.evnit.ttpm.AuthApp.repository.category.S_ATTRIBUTE_GROUP_ASSOBJRepository;
import com.evnit.ttpm.AuthApp.repository.device.A_ASSETLINKRepository;
import com.evnit.ttpm.AuthApp.repository.device.cuon.CuonAssetTempRepo;
import com.evnit.ttpm.AuthApp.repository.device.cuon.ZAGCuonRepository;
import com.evnit.ttpm.AuthApp.repository.device.cuon.ZagCuonTempRepo;
import com.evnit.ttpm.AuthApp.repository.file.AFOtherRepository;
import com.evnit.ttpm.AuthApp.service.system.WonderHisService;
import com.evnit.ttpm.AuthApp.util.CustomPageImpl;
import com.evnit.ttpm.AuthApp.util.SpecificationUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
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
public class AcceptanceServiceImpl implements AcceptanceService {
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    WonderHisService _wonderHisService;

    private final
    AcceptanceRepository acceptanceRepository;
    private final ModelMapper mapper;
    private final AcceptanceDetailRepository acceptanceDetailRepository;
    private final AcceptanceChangeRepository acceptanceChangeRepository;
    private final ViewAcceptanceFinalRepository viewAcceptanceFinalRepository;
    private final ViewAcceptanceDetailRepository viewAcceptanceDetailRepository;
    private final ViewReplaceDetailRepo viewReplaceDetailRepo;
    private final AFOtherRepository afOtherRepository;
    private final ViewAcceptanceRepository viewAcceptanceRepository;
    private final EntityManager entityManager;
    private final A_ASSETRepository assetRepository;
    private final ZAGCuonRepository zagCuonRepository;
    private final CuonAssetTempRepo cuonAssetTempRepo;
    private final ZagCuonTempRepo zagCuonTempRepo;
    private final S_ATTRIBUTE_GROUP_ASSOBJRepository attrRepository;
    private final A_ASSETLINKRepository assetlinkRepository;
    @Value("${upload.dir}")
    private String uploadDir;

    public AcceptanceServiceImpl(AcceptanceRepository acceptanceRepository, AcceptanceDetailRepository acceptanceDetailRepository, AcceptanceMapper mapper,
                                 AcceptanceChangeRepository acceptanceChangeRepository, ViewAcceptanceFinalRepository viewAcceptanceFinalRepository,
                                 ViewAcceptanceDetailRepository viewAcceptanceDetailRepository, ViewReplaceDetailRepo viewReplaceDetailRepo,
                                 AFOtherRepository afOtherRepository, ViewAcceptanceRepository viewAcceptanceRepository, EntityManager entityManager, A_ASSETRepository assetRepository, ZAGCuonRepository zagCuonRepository, CuonAssetTempRepo cuonAssetTempRepo, ZagCuonTempRepo zagCuonTempRepo, S_ATTRIBUTE_GROUP_ASSOBJRepository attrRepository, A_ASSETLINKRepository assetlinkRepository) {
        this.mapper = mapper.getModelMapper();
        this.acceptanceRepository = acceptanceRepository;
        this.acceptanceDetailRepository = acceptanceDetailRepository;
        this.acceptanceChangeRepository = acceptanceChangeRepository;
        this.viewAcceptanceFinalRepository = viewAcceptanceFinalRepository;
        this.viewAcceptanceDetailRepository = viewAcceptanceDetailRepository;
        this.viewReplaceDetailRepo = viewReplaceDetailRepo;
        this.afOtherRepository = afOtherRepository;
        this.viewAcceptanceRepository = viewAcceptanceRepository;
        this.entityManager = entityManager;
        this.assetRepository = assetRepository;
        this.zagCuonRepository = zagCuonRepository;
        this.cuonAssetTempRepo = cuonAssetTempRepo;
        this.zagCuonTempRepo = zagCuonTempRepo;
        this.attrRepository = attrRepository;
        this.assetlinkRepository = assetlinkRepository;
    }

    @Override
    public ResponseData createAcceptance(AcceptanceCrudDto dto) {
        ResponseData response = new ResponseData();
        try {
            var uuid = UUID.randomUUID().toString();
            var entity = mapper.map(dto, Acceptance.class);
            entity.setAcceptId(uuid);
            var result = acceptanceRepository.save(entity);
            if (dto.getFileData() != null && dto.getFileData().size() > 0) {
                //Lưu filePath vào disk sau đó set lại filepath cho filedata trong các object
                for (FileData file : dto.getFileData()) {
                    if (file.getBase64().length() > 0) {
                        file.setFilePath(Base64ToFile(file, "ACCEPTANCE/" + uuid));
                    }
                }
                //Lưu filePath vào db
                for (FileData file : dto.getFileData()) {
                    AF_OTHER afOther = new AF_OTHER();
                    afOther.setAfFileId(UUID.randomUUID().toString());
                    afOther.setCrdTime(new Date());
                    afOther.setUserId(dto.getUserCrId());
                    afOther.setObjId(uuid);
                    afOther.setOrgId("0");
                    afOther.setObjTypeId(AF_OTHER.AF_TYPE.NT.toString());
                    afOther.setFileSizeB((int) file.getSize());
                    afOther.setFilePath(file.getFilePath());
                    afOther.setFileName(file.getFileName());
                    afOtherRepository.save(afOther);
                }
            }
            response.setData(result);


            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setState(ResponseData.STATE.OK.toString());
            //lưu his
            _wonderHisService.CreateHis(uuid, dto, dto.getUserCrId(), "M_ACCEPTANCE", "INS");
            //update htdd from temp data to data
            String sql = "EXEC [dbo].[sp_Update_Asset_Temp] @transId = " + dto.getTransId() + "";
            jdbcTemplate.execute(sql);
        } catch (Exception ignored) {
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setState(ResponseData.STATE.FAIL.toString());
        }
        return response;
    }

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

//    @Override
//    public ResponseData createOrUpdateAcceptDetail(AcceptanceDetailDto dto) {
//        ResponseData response = new ResponseData();
//        try {
//            var acceptDetailId = dto.getAcceptDetailId();
//            var isCreate = false;
//            if (acceptDetailId == null) {
//                isCreate = true;
//                dto.setAcceptDetailId(UUID.randomUUID().toString());
//            }
//            var acceptDetail = mapper.map(dto, AcceptanceDetail.class);
//            var result = acceptanceDetailRepository.save(acceptDetail);
//            var accept = acceptanceRepository.findById(dto.getAcceptId()).orElse(null);
//            String sql;
//            var htttSLAccept = dto.gethTTTSLAccept();
//            var statusAccept = dto.getStatusAccept();
//            assert accept != null;
//            var typeAccept = accept.getTypeAccept();
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            var acceptEndDate = dateFormat.format(accept.getActionEndDate());
//            if (htttSLAccept != null && htttSLAccept && accept != null) {
//                sql = "EXEC [dbo].[sp_UpdateDate_M_ACCEPTANCE_DETAIL_CHANGE] @acceptId = N'" + result.getAcceptId() + "', @Type = " + 1 + ",  @DateUpdate = N'" + acceptEndDate + "' ";
//                jdbcTemplate.execute(sql);
//            }
//            String sql1;
//            sql1 = "EXEC [dbo].[sp_UpdateDate_M_ACCEPTANCE_DETAIL_CHANGE] @acceptDetailId = N'" + result.getAcceptDetailId() + "', @acceptId = " + null + ",  @DateUpdate = N'" + acceptEndDate + "' ";
//            jdbcTemplate.execute(sql1);
//            if (typeAccept.equals("NTK")) {
//                sql1 = "EXEC [dbo].[sp_UpdateDate_M_ACCEPTANCE_DETAIL_CHANGE] @acceptId = N'" + result.getAcceptId() + "', @Type = " + 5 + ",  @DateUpdate = N'" + acceptEndDate + "',  @isCreate = " + isCreate + "";
//                jdbcTemplate.execute(sql1);
//            }
//            if (typeAccept.equals("TTTBDD") || typeAccept.equals("NTTLD")) {
//                sql1 = "EXEC [dbo].[sp_UpdateDate_M_ACCEPTANCE_DETAIL_CHANGE] @acceptId = N'" + result.getAcceptId() + "', @Type = " + 0 + ",  @DateUpdate = N'" + acceptEndDate + "',  @isCreate = " + isCreate + " ";
//                jdbcTemplate.execute(sql1);
//                sql1 = "EXEC [dbo].[sp_UpdateDate_M_ACCEPTANCE_DETAIL_CHANGE] @acceptId = N'" + result.getAcceptId() + "', @Type = " + 4 + ",  @DateUpdate = N'" + acceptEndDate + "',  @isCreate = " + isCreate + " ";
//                jdbcTemplate.execute(sql1);
//            }
//            if (typeAccept.equals("NTTLD")) {
//                sql1 = "EXEC [dbo].[sp_UpdateDate_M_ACCEPTANCE_DETAIL_CHANGE] @acceptId = N'" + result.getAcceptId() + "', @Type = " + 2 + ",  @DateUpdate = N'" + acceptEndDate + "' ";
//                jdbcTemplate.execute(sql1);
//            }
//            if (typeAccept.equals("NTMTLD")) {
//                sql1 = "EXEC [dbo].[sp_UpdateDate_M_ACCEPTANCE_DETAIL_CHANGE] @acceptId = N'" + result.getAcceptId() + "', @Type = " + 3 + ",  @DateUpdate = N'" + acceptEndDate + "' ";
//                jdbcTemplate.execute(sql1);
//            }
//            if (statusAccept != null && statusAccept && accept.getTypeAccept().equals("NTTLD")) {
//                sql1 = "UPDATE [dbo].[A_ASSET] set [USESTATUS_LAST_ID] = " + 2 + " where [ASSETID] = N'" + dto.getAssetId() + "'";
//                jdbcTemplate.execute(sql1);
//            }
//            var listAcceptDelete = dto.getListAcceptDelete();
//            if (listAcceptDelete != null && listAcceptDelete.size() > 0) {
//                for (String acceptId : dto.getListAcceptDelete()) {
//                    acceptanceDetailRepository.deleteById(acceptId);
//                }
//            }
//            var listDiemDoId = dto.getListDiemDoIdDelete();
//            if (listDiemDoId != null && listDiemDoId.size() > 0) {
//                for (String assetId : dto.getListDiemDoIdDelete()) {
//                    String sqlDiemDo = "EXEC [dbo].[sp_DELETE_HTDD] @assetId = N'" + assetId + "'";
//                    jdbcTemplate.execute(sqlDiemDo);
//                }
//            }
//            response.setData(result);
//            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
//            response.setState(ResponseData.STATE.OK.toString());
//        } catch (Exception ignored) {
//            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
//            response.setState(ResponseData.STATE.FAIL.toString());
//        }
//        return response;
//    }

//    @Override
//    public ResponseData createOrUpdateAcceptChange(AcceptanceChangeDto dto) {
//        ResponseData response = new ResponseData();
//        try {
//            var acceptChangeId = dto.getAccepChangeId();
//            if (acceptChangeId == null) {
//                dto.setAccepChangeId(UUID.randomUUID().toString());
//            }
//            var acceptChange = mapper.map(dto, AcceptanceChange.class);
//            var result = acceptanceChangeRepository.save(acceptChange);
//            var listAcceptDelete = dto.getListAcceptDelete();
//            var accept = acceptanceRepository.findById(result.getAcceptId()).orElse(new Acceptance());
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            var acceptEndDate = dateFormat.format(accept.getActionEndDate());
//            var typeAccept = accept.getTypeAccept();
//            String sql1;
//            sql1 = "EXEC [dbo].[sp_UpdateDate_M_ACCEPTANCE_DETAIL_CHANGE] @acceptId = N'" + result.getAcceptId() + "', @Type = " + 0 + ",  @DateUpdate = N'" + acceptEndDate + "' ";
//            jdbcTemplate.execute(sql1);
//            sql1 = "EXEC [dbo].[sp_UpdateDate_M_ACCEPTANCE_DETAIL_CHANGE] @acceptId = N'" + result.getAcceptId() + "', @Type = " + 4 + ",  @DateUpdate = N'" + acceptEndDate + "' ";
//            jdbcTemplate.execute(sql1);
//            if (listAcceptDelete != null && dto.getListAcceptDelete().size() > 0) {
//                for (String acceptId : dto.getListAcceptDelete()) {
//                    acceptanceDetailRepository.deleteById(acceptId);
//                }
//            }
//            String sql;
//            var statusAccept = dto.getStatusAccept();
//            if (statusAccept != null && statusAccept) {
//                sql = "UPDATE [dbo].[A_ASSET] set [USESTATUS_LAST_ID] = 2 where [ASSETID] = N'" + dto.getAssetId() + "'";
//                jdbcTemplate.execute(sql);
//            }
//            response.setData(result);
//            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
//            response.setState(ResponseData.STATE.OK.toString());
//        } catch (Exception ignored) {
//            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
//            response.setState(ResponseData.STATE.FAIL.toString());
//        }
//        return response;
//    }


    @Override
    public ResponseData getAcceptDetail(String acceptId) {
        ResponseData response = new ResponseData();
        try {
            List<ViewAcceptDetail> acceptanceDetail = viewAcceptanceDetailRepository.findByAcceptId(acceptId);
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(acceptanceDetail);
            return response;
        } catch (Exception ignored) {
            response.setMessage(ResponseData.MESSAGE.ERROR.toString() + " " + ignored.getMessage());
            response.setMessage(ResponseData.STATE.FAIL.toString());
        }
        return response;
    }

    @Override
    public ResponseData getAcceptChange(String acceptId) {
        ResponseData response = new ResponseData();
        try {
            List<ViewReplaceDetail> acceptanceDetail = viewReplaceDetailRepo.findByAcceptId(acceptId);
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(acceptanceDetail);
            return response;
        } catch (Exception ignored) {
            response.setMessage(ResponseData.MESSAGE.ERROR.toString() + " " + ignored.getMessage());
            response.setMessage(ResponseData.STATE.FAIL.toString());
        }
        return response;
    }

    @Override
    public ResponseData getListAcceptanceToTable(SearchAcceptanceList dto) {
        ResponseData response = new ResponseData();
        SearchQuery searchQuery = new SearchQuery();
        List<AcceptanceListDto> listAcceptance = new ArrayList<AcceptanceListDto>();

        SearchFilter searchFilter = new SearchFilter();
        SearchFilter searchFilterCondition = new SearchFilter();

        List<AcceptanceListDto> total = this.TotalAcceptanceList(dto);
        List<AcceptanceListDto> totalMonth = this.TotalMonthAcceptanceList(dto);

        List<SearchFilter> searchFilters = new ArrayList<>();

        if (dto.getType() != null && !dto.getType().isEmpty()) {
            searchFilterCondition = new SearchFilter("powerSystemTyped", "IN", dto.getType());
            searchFilters.add(searchFilterCondition);
        }

        if (dto.getAssetId() != null && !dto.getAssetId().isEmpty()) {
            searchFilterCondition = new SearchFilter("assetId", "IN", dto.getAssetId());
            searchFilters.add(searchFilterCondition);
        }

        if (dto.getTrangThaiNMD() != null && !dto.getTrangThaiNMD().isEmpty()) {
            searchFilterCondition = new SearchFilter("powerSystemStatus", "IN", dto.getTrangThaiNMD());
            searchFilters.add(searchFilterCondition);
        }
        if (dto.getTypeNT() != null && !dto.getTypeNT().isEmpty()) {
            searchFilterCondition = new SearchFilter("typeAccept", "IN", dto.getTypeNT());
            searchFilters.add(searchFilterCondition);
        }
        if (dto.getTypeObj() != null && !dto.getTypeObj().isEmpty()) {
            searchFilterCondition = new SearchFilter("categoryId", "IN", dto.getTypeObj());
            searchFilters.add(searchFilterCondition);
        }
        if (dto.getExpression() != null && !dto.getExpression().isEmpty()) {
            searchFilterCondition = new SearchFilter("acceptForm", "IN", dto.getExpression());
            searchFilters.add(searchFilterCondition);
        }
        if (dto.getDetailObj() != null && !dto.getDetailObj().isEmpty()) {
            searchFilter = new SearchFilter("assetDescDetail", "LIKE", dto.getDetailObj());
            searchFilters.add(searchFilter);
        }

        if (dto.getStartDate() != null) {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date fromDate = null;
            try {
                fromDate = formatter.parse(dto.getStartDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            searchFilterCondition = new SearchFilter("actionEndDate", ">=", fromDate);
            searchFilters.add(searchFilterCondition);
        }

        if (dto.getEndDate() != null) {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date endDate = null;
            try {
                endDate = formatter.parse(dto.getEndDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            searchFilterCondition = new SearchFilter("actionEndDate", "<=", endDate);
            searchFilters.add(searchFilterCondition);
        }

        Pageable pageable = PageRequest.of(dto.getPage(), dto.getLimit(), dto.getSortExpression());
        searchQuery.setSearchFilters(searchFilters);
        Specification<ViewAcceptanceFinal> spec = SpecificationUtil.bySearchQuery(searchQuery, ViewAcceptanceFinal.class);
        try {
            var pageResult = viewAcceptanceFinalRepository.findAll(spec, pageable);
            var dtoResult = pageResult.getContent().stream().map(item -> mapper.map(item, AcceptanceListDto.class)).collect(Collectors.toList());

            //Tháng nt vòng for
            Date thangNT = new Date();
            //Index hiện tại cần add
            Integer indexMonth = 0;

            for (AcceptanceListDto item : dtoResult) {
                if (!item.getThangNT().equals(thangNT)) {
                    thangNT = item.getThangNT();
                    listAcceptance.add(totalMonth.get(indexMonth));
                    indexMonth++;
                }
                listAcceptance.add(item);
            }

            listAcceptance.add(0, total.get(0));
            Page<AcceptanceListDto> result = new PageImpl<AcceptanceListDto>(listAcceptance, pageable, pageResult.getTotalElements());

            CustomPageImpl cusResult = new CustomPageImpl();
            cusResult.setContent(listAcceptance);
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

            response.setData(cusResult);
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setState(ResponseData.STATE.OK.toString());
        } catch (Exception ignored) {
            response.setData(null);
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setState(ResponseData.STATE.FAIL.toString());
        }
        return response;
    }

    private List<AcceptanceListDto> TotalAcceptanceList(SearchAcceptanceList dto) {

        String sql;

        List lst;
        sql = "select NEWID() as ID,'' as [POWERSYSTEMTYPE],'' as[POWERSYSTEMTYPED],'' as [POWERSYSTEMNAME], '' as [ACCEPT_FORM],'' as [ACCEPT_FORMSTR],CAST(NULL as Date) as [ACTIONSTARTDATE], CAST(NULL as Date)  as [ACTIONENDDATE],'' as [TYPE_ACCEPT],'' as [DEVICETYPE], '' as [ASSETDESCDETAIL], sum([AMOUNT]) as [AMOUNT],sum([ACCEPTANCED]) as [ACCEPTANCED],SUM([UNTESTED]) as [UNTESTED], SUM([SOLUOT]) as [SOLUOT], '' as [RESULT_ACCEPT],CAST(NULL as date) as 'THANGNT', '' as [ASSETID],'' as [ACCEPTID], CAST(NULL as int) as [POWERSYSTEMSTATUS] from  [dbo].[VIEW_NGHIEM_THU_FINAL] where 1 = 1";

        if (dto.getType() != null && !dto.getType().isEmpty()) {
            sql += " AND POWERSYSTEMTYPED IN ('" + String.join("','", dto.getType()) + "')";
        }

        if (dto.getAssetId() != null && !dto.getAssetId().isEmpty()) {
            sql += " AND ASSETID IN ('" + String.join("','", dto.getAssetId()) + "')";
        }

        if (dto.getTrangThaiNMD() != null && !dto.getTrangThaiNMD().isEmpty()) {
            sql += " AND POWERSYSTEMSTATUS IN (" + dto.getTrangThaiNMD().stream().map(String::valueOf).collect(Collectors.joining(",")) + ")";
        }
        if (dto.getExpression() != null && !dto.getExpression().isEmpty()) {
            sql += " AND ACCEPT_FORM IN ('" + String.join("','", dto.getExpression()) + "')";
        }
        if (dto.getTypeObj() != null && !dto.getTypeObj().isEmpty()) {
            sql += " AND CATEGORYID IN ('" + String.join("','", dto.getTypeObj()) + "')";
        }
        if (dto.getTypeNT() != null && !dto.getTypeNT().isEmpty()) {
            sql += " AND TYPE_ACCEPT IN ('" + String.join("','", dto.getTypeNT()) + "')";
        }
        if (dto.getDetailObj() != null) {
            sql += "AND ASSETDESCDETAIL LIKE '%" + dto.getDetailObj() + "%'";
        }


        if (dto.getStartDate() != null) {
            sql += " AND ACTIONENDDATE >= '" + dto.getStartDate().toString() + "'";
        }

        if (dto.getEndDate() != null) {
            sql += " AND ACTIONENDDATE <= '" + dto.getEndDate().substring(0, 10).toString() + " 23:59:59'";
        }

        lst = jdbcTemplate.query(sql, new BeanPropertyRowMapper<AcceptanceListDto>(AcceptanceListDto.class));

        return lst;
    }

    //Dòng tổng từng tháng của danh sách kiểm định
    private List<AcceptanceListDto> TotalMonthAcceptanceList(SearchAcceptanceList dto) {

        String sql;

        List lst;
        sql = "select NEWID() as ID,'' as [POWERSYSTEMTYPE],''as [POWERSYSTEMTYPED], '' as [POWERSYSTEMNAME], '' as [ACCEPT_FORM], '' as[ACCEPT_FORMSTR]," +
                "CAST(NULL as Date) as [ACTIONSTARTDATE], CAST(NULL as Date)  as [ACTIONENDDATE], '' as [TYPE_ACCEPT],NULL  as [DEVICETYPE], '' as [ASSETDESCDETAIL], sum([AMOUNT]) as [AMOUNT],sum([ACCEPTANCED]) as [ACCEPTANCED],SUM([UNTESTED]) as [UNTESTED], SUM([SOLUOT]) as [SOLUOT], '' as [RESULT_ACCEPT], [THANGNT], '' as [ASSETID],'' as [ACCEPTID], CAST(NULL as int) as [POWERSYSTEMSTATUS] from  [dbo].[VIEW_NGHIEM_THU_FINAL] where 1 = 1";
        if (dto.getType() != null && !dto.getType().isEmpty()) {
            sql += " AND POWERSYSTEMTYPED IN ('" + String.join("','", dto.getType()) + "')";
        }

        if (dto.getAssetId() != null && !dto.getAssetId().isEmpty()) {
            sql += " AND ASSETID IN ('" + String.join("','", dto.getAssetId()) + "')";
        }

        if (dto.getTrangThaiNMD() != null && !dto.getTrangThaiNMD().isEmpty()) {
            sql += " AND POWERSYSTEMSTATUS IN (" + dto.getTrangThaiNMD().stream().map(String::valueOf).collect(Collectors.joining(",")) + ")";
        }
        if (dto.getExpression() != null && !dto.getExpression().isEmpty()) {
            sql += " AND ACCEPT_FORM IN ('" + String.join("','", dto.getExpression()) + "')";
        }
        if (dto.getTypeObj() != null && !dto.getTypeObj().isEmpty()) {
            sql += " AND CATEGORYID IN ('" + String.join("','", dto.getTypeObj()) + "')";
        }
        if (dto.getTypeNT() != null && !dto.getTypeNT().isEmpty()) {
            sql += " AND TYPE_ACCEPT IN ('" + String.join("','", dto.getTypeNT()) + "')";
        }
        if (dto.getDetailObj() != null) {
            sql += "AND ASSETDESCDETAIL LIKE '%" + dto.getDetailObj() + "%'";
        }

        if (dto.getStartDate() != null) {
            sql += " AND ACTIONENDDATE >= '" + dto.getStartDate().toString() + "'";
        }

        if (dto.getEndDate() != null) {
            sql += " AND ACTIONENDDATE <= '" + dto.getEndDate().substring(0, 10).toString() + " 23:59:59'";
        }

        sql += " GROUP BY THANGNT ORDER BY THANGNT DESC";

        lst = jdbcTemplate.query(sql, new BeanPropertyRowMapper<AcceptanceListDto>(AcceptanceListDto.class));

        return lst;
    }

    @Override
    public ResponseData updateNQL(@CurrentUser CustomUserDetails user, String id, String ASSETID, Boolean stt) {
        String sql1;

        var strASSETID = ASSETID.split(",");
        for (String assetId : strASSETID) {
            sql1 = "select  1 from Q_PQOBJ_USER where ASSETID =? and USERID=? and ISQL=?";
            List lst = jdbcTemplate.queryForList(sql1, assetId, user.getUserid(), true);
            if (lst == null || lst.isEmpty())
                return new ResponseData(ResponseData.STATE.FAIL.toString(), "Không có quyền cập nhật nhà quản lý");
        }
        ResponseData response = new ResponseData();
        String strId = id.replace(",", "','");

        var strStt = 0;
        if (stt) strStt = 1;
        else strStt = 0;
        try {
            String sql;
            sql = "UPDATE [dbo].[M_ACCEPTANCE] SET [NQL_XNHAN] = " + strStt + " WHERE [ACCEPTID] in ('" + strId + "')";
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
    public ResponseData updateLDP(@CurrentUser CustomUserDetails user, String id, String ASSETID, Boolean stt) {
        String sql1;
        var strASSETID = ASSETID.split(",");
        for (String assetId : strASSETID) {
            sql1 = "select  1 from Q_PQOBJ_USER where ASSETID=? and USERID=? and ISLD=?";
            List lst = jdbcTemplate.queryForList(sql1, assetId, user.getUserid(), true);
            if (lst == null || lst.isEmpty())
                return new ResponseData(ResponseData.STATE.FAIL.toString(), "Không có quyền cập nhật lãnh đạo phòng");

        }

        ResponseData response = new ResponseData();
        String strId = id.replace(",", "','");

        var strStt = 0;
        if (stt) strStt = 1;
        else strStt = 0;
        try {
            String sql;
            sql = "UPDATE [dbo].[M_ACCEPTANCE] SET [LD_XNHAN] = " + strStt + " WHERE [ACCEPTID] in ('" + strId + "')";
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
    public ResponseData getFileNghiemThu(String id) {
        ResponseData response = new ResponseData();
        try {
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());

            String sql;
            List lst;
            //Lấy từ bảng chi tiết
            sql = "SELECT afFileId, [FILENAME] as 'fileName', '' as 'base64', filePath, FILESIZEB as 'size' FROM [dbo].[AF_OTHER] where OBJTYPEID ='NT' and [OBJID] = '" + id + "'";
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
    public ResponseData deleteAcceptance(String id) {
        ResponseData response = new ResponseData();
        try {
            String sql;
            sql = "EXEC [dbo].[sp_Delete_M_ACCEPTANCE] @acceptId = N'" + id + "'";
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
    public boolean checkAcceptance(String accredId, String assetId, Date startDate, Date endDate, String acceptForm, String typeAccept) {
        boolean rs = false;
        String strStartDate = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
        String strEndDate = new SimpleDateFormat("yyyy-MM-dd").format(endDate);

        String sql;
        List lst; //List<SListGroupAll> lst;
        if (accredId != null)
            sql = "SELECT *  FROM [dbo].[M_ACCEPTANCE] where  ACCEPTID != '" + accredId + "' AND ASSETID = '" + assetId + "' AND ACTIONSTARTDATE = '" + strStartDate + "' AND ACTIONENDDATE = '" + strEndDate + "' AND ACCEPT_FORM = '" + acceptForm + "' AND TYPE_ACCEPT = '" + typeAccept + "'";
        else
            sql = "SELECT *  FROM [dbo].[M_ACCEPTANCE] where  ASSETID = '" + assetId + "' AND ACTIONSTARTDATE = '" + strStartDate + "' AND ACTIONENDDATE = '" + strEndDate + "'AND ACCEPT_FORM = '" + acceptForm + "'AND TYPE_ACCEPT = '" + typeAccept + "'";

        lst = jdbcTemplate.queryForList(sql);

        //Nếu chưa có bản ghi nào thì cập nhật trạng thái là true
        if (lst.size() == 0) {
            rs = true;
        }

        return rs;
    }

    @Override
    public ResponseData updateAcceptance(String id, AcceptanceCrudDto dto) throws Exception {
        ResponseData response = new ResponseData();
        List<FileData> newFileList = new ArrayList<FileData>();
        try {
            var existEntity = acceptanceRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Nghiem thu không tồn tại"));
            var createDate = new Date();
            existEntity.setAcceptDesc(dto.getAcceptDesc());
            existEntity.setAssetId(dto.getAssetId());
            existEntity.setActionEndDate(dto.getActionEndDate());
            existEntity.setActionStartDate(dto.getActionStartDate());
            existEntity.setTypeAccept(dto.getTypeAccept());
            existEntity.setAcceptForm(dto.getAcceptForm());
            existEntity.setResultAccept(dto.getResultAccept());
            existEntity.setStatusAccept(dto.getStatusAccept());
            existEntity.setUserMdfDTime(createDate);
            existEntity.setUserMdfId(dto.getUserMdfId());
            var result = acceptanceRepository.save(existEntity);
            //Add file when update Acceptance
            if (dto.getFileData() != null && dto.getFileData().size() > 0) {
                //Lưu filePath vào disk sau đó set lại filepath cho filedata trong các object
                for (FileData file : dto.getFileData()) {
                    if (file.getBase64() != null && file.getBase64().length() > 0) {
                        file.setFilePath(Base64ToFile(file, "ACCEPTANCE/" + dto.getAcceptId()));
                        newFileList.add(file);
                    }
                }
                //Lưu file duoc them vào db
                for (FileData file : dto.getFileData()) {
                    if (file.getBase64() != null && file.getBase64().length() > 0) {
                        AF_OTHER entity = new AF_OTHER();
                        entity.setAfFileId(UUID.randomUUID().toString());
                        entity.setCrdTime(new Date());
                        entity.setUserId(dto.getUserCrId());
                        entity.setObjId(dto.getAcceptId());
                        entity.setOrgId("0");
                        entity.setObjTypeId(AF_OTHER.AF_TYPE.NT.toString());
                        entity.setFileSizeB((int) file.getSize());
                        entity.setFilePath(file.getFilePath());
                        entity.setFileName(file.getFileName());
                        afOtherRepository.save(entity);
                    }
                }
            }
            //Del file on db only for Accreditation
            if (dto.getLstFileDelete() != null && dto.getLstFileDelete().size() > 0) {
                for (var file : dto.getLstFileDelete()) {
                    String cmd;
                    cmd = "DELETE FROM [dbo].AF_OTHER WHERE AFFILEID = '" + file.getAfFileId() + "';";
                    jdbcTemplate.execute(cmd);
                }
            }
            var typeAccept = result.getTypeAccept();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            var acceptEndDate = dateFormat.format(result.getActionEndDate());
            String sql;
            if (result.getTypeAccept().equals("TTTBDD") || typeAccept.equals("NTTLD") || typeAccept.equals("NTK")) {
                sql = "EXEC [dbo].[sp_UpdateDate_M_ACCEPTANCE_DETAIL_CHANGE] @acceptId = N'" + result.getAcceptId() + "', @Type =" + 0 + ",  @DateUpdate = N'" + acceptEndDate + "' ";
                jdbcTemplate.execute(sql);
            }
            if (result.getTypeAccept().equals("TTTBDD") || typeAccept.equals("NTTLD")) {
                sql = "EXEC [dbo].[sp_UpdateDate_M_ACCEPTANCE_DETAIL_CHANGE] @acceptId = N'" + result.getAcceptId() + "', @Type = " + 4 + ",  @DateUpdate = N'" + acceptEndDate + "' ";
                jdbcTemplate.execute(sql);
            }
            if (result.getTypeAccept().equals("NTTLD")) {
                sql = "EXEC [dbo].[sp_UpdateDate_M_ACCEPTANCE_DETAIL_CHANGE] @acceptId = N'" + result.getAcceptId() + "', @Type = " + 2 + ",  @DateUpdate = N'" + acceptEndDate + "' ";
                jdbcTemplate.execute(sql);
            }
            if (result.getTypeAccept().equals("NTMTLD")) {
                sql = "EXEC [dbo].[sp_UpdateDate_M_ACCEPTANCE_DETAIL_CHANGE] @acceptId = N'" + result.getAcceptId() + "', @Type = " + 3 + ",  @DateUpdate = N'" + acceptEndDate + "' ";
                jdbcTemplate.execute(sql);
            }
            //Del file on disk when all db updated
            if (dto.getLstFileDelete() != null && dto.getLstFileDelete().size() > 0) {
                DelAllFile(dto.getLstFileDelete());
            }

            _wonderHisService.CreateHis(id, dto, dto.getUserCrId(), "M_ACCEPTANCE", "UPDATE");
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(result);
        } catch (Exception ex) {
            if (newFileList != null && newFileList.size() > 0) {
                DelAllFile(newFileList);
            }
            throw ex;
        }
        return response;
    }

    @Override
    public ResponseData createOrUpdateAcceptDetail(AcceptanceDetailDto dto) {
        ResponseData response = new ResponseData();
        try {
            var acceptDetailId = dto.getAcceptDetailId();
            var flag = false;
            if (acceptDetailId == null) {
                dto.setAcceptDetailId(UUID.randomUUID().toString());
                //check create or update
                flag = true;
            }
            var acceptDetail = mapper.map(dto, AcceptanceDetail.class);
            var result = acceptanceDetailRepository.save(acceptDetail);
            var accept = acceptanceRepository.findById(dto.getAcceptId()).orElse(null);
            String sql;
            var htttSLAccept = dto.gethTTTSLAccept();
            var statusAccept = dto.getStatusAccept();
            assert accept != null;
            var typeAccept = accept.getTypeAccept();
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            var acceptEndDate = dateFormat.format(accept.getActionEndDate());

            String sql1;
            //update id cong to/tu/ti cho accept detail
            sql1 = "EXEC [dbo].[sp_UpdateDate_M_ACCEPTANCE_DETAIL_CHANGE] @acceptDetailId = N'" + dto.getAcceptDetailId() + "', @acceptId = " + null + ",  @DateUpdate = N'" + acceptEndDate + "' ";
            jdbcTemplate.execute(sql1);
            sql1 = "SELECT * from [dbo].[M_ACCEPTANCE_DETAILS] where ACCEPTDETAILID = '" + dto.getAcceptDetailId() + "'";
            var rows = jdbcTemplate.queryForList(sql1);
            AcceptanceDetail acceptDetailFinal = new AcceptanceDetail();
            for (Map row : rows) {
                acceptDetailFinal.setAcceptDetailId((String) row.get("ACCEPTDETAILID"));
                acceptDetailFinal.setAcceptId((String) row.get("ACCEPTID"));
                acceptDetailFinal.setAssetId((String) row.get("ASSETID"));
                acceptDetailFinal.setIdCongTo((String) row.get("ID_CT"));
                acceptDetailFinal.setIdDeviceTU((String) row.get("IDS_TU"));
                acceptDetailFinal.setIdDeviceTI((String) row.get("IDS_TI"));
                acceptDetailFinal.setStatusAccept((Boolean) row.get("STATUS_ACCEPT"));
            }
            assert acceptDetailFinal != null;
            var idsTU = acceptDetailFinal.getIdDeviceTU();
            var idsTI = acceptDetailFinal.getIdDeviceTI();
            var idCT = acceptDetailFinal.getIdCongTo();
            List<String> idTuList = Arrays.asList(idsTU.split(","));
            List<String> idTiList = Arrays.asList(idsTI.split(","));
            //update nthtttsl
            List<ViewAcceptDetail> listAcceptanceDetail = viewAcceptanceDetailRepository.findByAssetId(acceptDetail.getAssetId());
            if (htttSLAccept != null && htttSLAccept) {
                if (listAcceptanceDetail.size() > 0) {
                    var acceptance = listAcceptanceDetail.get(0);
                    sql = "EXEC [dbo].[sp_UpdateDate_M_ACCEPTANCE_DETAIL_CHANGE] @acceptId = N'" + result.getAcceptId() + "', @Type = " + 1 + ",  @DateUpdate = N'" + acceptance.getActionEndDate() + "',@isReplace = " + 0 + "";
                    jdbcTemplate.execute(sql);
                }
            }
            if (htttSLAccept != null && !htttSLAccept && !flag) {
                if (listAcceptanceDetail.size() > 0) {
                    var acceptance = listAcceptanceDetail.get(0);
                    sql = "EXEC [dbo].[sp_UpdateDate_M_ACCEPTANCE_DETAIL_CHANGE] @acceptId = N'" + result.getAcceptId() + "', @Type = " + 1 + ",  @DateUpdate = N'" + acceptance.getActionEndDate() + "',@isReplace = " + 0 + "";
                    jdbcTemplate.execute(sql);
                }
            }
            //update p_installDate tu/ti create in nt khac
            if (typeAccept.equals("NTK")) {
                sql1 = "EXEC [dbo].[sp_UpdateDate_M_ACCEPTANCE_DETAIL_CHANGE] @acceptId = N'" + result.getAcceptId() + "', @Type = " + 5 + ",  @DateUpdate = N'" + acceptEndDate + "',  @isReplace = " + 0 + "";
                jdbcTemplate.execute(sql1);
            }
            //update p_installDate of tu-ti-congto
            if (typeAccept.equals("NTTLD")) {
                //update tu
                for (String idTu : idTuList) {
                    List<ViewAcceptance> listAcceptance = viewAcceptanceRepository.findByIdDevice(idTu);
                    if (listAcceptance.size() > 0) {
                        var acceptance = listAcceptance.get(0);
                        var date = acceptance.getActionEndDate();
                        var dateStr = dateFormat.format(date);
                        sql1 = "EXEC [dbo].[sp_UpdateDate_M_ACCEPTANCE_DETAIL_CHANGE] @acceptId = N'" + result.getAcceptId() + "', @Type = " + 0 + ",  @DateUpdate = N'" + dateStr + "',  @isReplace = " + 0 + " ";
                        jdbcTemplate.execute(sql1);
                    }
                }
                //update ti
                for (String idTi : idTiList) {
                    List<ViewAcceptance> listAcceptance = viewAcceptanceRepository.findByIdDevice(idTi);
                    if (listAcceptance.size() > 0) {
                        var acceptance = listAcceptance.get(0);
                        var date = acceptance.getActionEndDate();
                        var dateStr = dateFormat.format(date);
                        sql1 = "EXEC [dbo].[sp_UpdateDate_M_ACCEPTANCE_DETAIL_CHANGE] @acceptId = N'" + result.getAcceptId() + "', @Type = " + 0 + ",  @DateUpdate = N'" + dateStr + "',  @isReplace = " + 0 + " ";
                        jdbcTemplate.execute(sql1);
                    }
                }
                //update congto
                List<ViewAcceptance> listAcceptance = viewAcceptanceRepository.findByIdDevice(idCT);
                if (listAcceptance.size() > 0) {
                    var acceptance = listAcceptance.get(0);
                    var date = acceptance.getActionEndDate();
                    var dateStr = dateFormat.format(date);
                    sql1 = "EXEC [dbo].[sp_UpdateDate_M_ACCEPTANCE_DETAIL_CHANGE] @acceptId = N'" + result.getAcceptId() + "', @Type = " + 4 + ",  @DateUpdate = N'" + dateStr + "',  @isReplace = " + 0 + " ";
                    jdbcTemplate.execute(sql1);
                }
            }
            //update ngay nt tinh của điểm đo
            if (typeAccept.equals("NTTLD")) {
                sql1 = "EXEC [dbo].[sp_UpdateDate_M_ACCEPTANCE_DETAIL_CHANGE] @acceptId = N'" + result.getAcceptId() + "', @Type = " + 2 + ",  @DateUpdate = N'" + acceptEndDate + "', @isReplace = " + 0 + "";
                jdbcTemplate.execute(sql1);
            }
            //update ngày nt mang tải của điểm đo
            if (typeAccept.equals("NTMTLD")) {
                sql1 = "EXEC [dbo].[sp_UpdateDate_M_ACCEPTANCE_DETAIL_CHANGE] @acceptId = N'" + result.getAcceptId() + "', @Type = " + 3 + ",  @DateUpdate = N'" + acceptEndDate + "', @isReplace = " + 0 + "";
                jdbcTemplate.execute(sql1);
            }
            //update trạng thái điểm đo
            if (statusAccept != null && statusAccept && accept.getTypeAccept().equals("NTTLD")) {
                sql1 = "UPDATE [dbo].[A_ASSET] set [USESTATUS_LAST_ID] = " + 2 + " where [ASSETID] = N'" + dto.getAssetId() + "'";
                jdbcTemplate.execute(sql1);
            }
            var listAcceptDelete = dto.getListAcceptDelete();
            if (listAcceptDelete != null && listAcceptDelete.size() > 0) {
                for (String acceptId : dto.getListAcceptDelete()) {
                    acceptanceDetailRepository.deleteById(acceptId);
                }
            }
            var listDiemDoId = dto.getListDiemDoIdDelete();
            if (listDiemDoId != null && listDiemDoId.size() > 0) {
                for (String assetId : dto.getListDiemDoIdDelete()) {
                    String sqlDiemDo = "EXEC [dbo].[sp_DELETE_HTDD] @assetId = N'" + assetId + "'";
                    jdbcTemplate.execute(sqlDiemDo);
                }
            }
            response.setData(result);
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setState(ResponseData.STATE.OK.toString());
        } catch (Exception ignored) {
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setState(ResponseData.STATE.FAIL.toString());
        }
        return response;
    }


    @Override
    public ResponseData createOrUpdateAcceptChange(AcceptanceChangeDto dto) {
        ResponseData response = new ResponseData();
        try {
            var acceptChangeId = dto.getAccepChangeId();
            if (acceptChangeId == null) {
                dto.setAccepChangeId(UUID.randomUUID().toString());
            }
            var acceptChange = mapper.map(dto, AcceptanceChange.class);
            var result = acceptanceChangeRepository.save(acceptChange);
            var listAcceptDelete = dto.getListAcceptDelete();
            var accept = acceptanceRepository.findById(result.getAcceptId()).orElse(new Acceptance());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            var acceptEndDate = dateFormat.format(accept.getActionEndDate());
            var typeAccept = accept.getTypeAccept();

            List<ViewAcceptance> listAcceptDetail = viewAcceptanceRepository.findByIdDevice(result.getAssetId());
            String dateUpdate = "";
            if (listAcceptDetail.size() > 0) {
                var acceptAcceptChange = listAcceptDetail.get(0);
                dateUpdate = dateFormat.format(acceptAcceptChange.getActionEndDate());
            }
            String sql1;
            //update p_installDate tu/ti
            sql1 = "EXEC [dbo].[sp_UpdateDate_M_ACCEPTANCE_DETAIL_CHANGE] @acceptId = N'" + result.getAcceptId() + "', @isReplace = " + 1 + ",  @DateUpdate = N'" + dateUpdate + "' ";
            jdbcTemplate.execute(sql1);
//            //update p_installDate cong-to
//            sql1 = "EXEC [dbo].[sp_UpdateDate_M_ACCEPTANCE_DETAIL_CHANGE] @acceptId = N'" + result.getAcceptId() + "', @Type = " + 4 + ",  @DateUpdate = N'" + dateUpdate + "' ";
//            jdbcTemplate.execute(sql1);
            if (listAcceptDelete != null && dto.getListAcceptDelete().size() > 0) {
                for (String acceptId : dto.getListAcceptDelete()) {
                    acceptanceChangeRepository.deleteById(acceptId);
                }
            }
            String sql;
            var statusAccept = dto.getStatusAccept();
            if (statusAccept != null && statusAccept) {
                sql = "UPDATE [dbo].[A_ASSET] set [USESTATUS_LAST_ID] = 2 where [ASSETID] = N'" + dto.getAssetId() + "'";
                jdbcTemplate.execute(sql);
            }
            response.setData(result);
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setState(ResponseData.STATE.OK.toString());
        } catch (Exception ignored) {
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setState(ResponseData.STATE.FAIL.toString());
        }
        return response;
    }
}
