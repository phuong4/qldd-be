package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.annotation.CurrentUser;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.category.SCategoryThoaThuan;
import com.evnit.ttpm.AuthApp.entity.category.View_Deal;
import com.evnit.ttpm.AuthApp.entity.common.SListGroupAll;
import com.evnit.ttpm.AuthApp.entity.file.AF_OTHER;
import com.evnit.ttpm.AuthApp.mapper.ThoaThuanMapper;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.model.category.SuCo.FileData;
import com.evnit.ttpm.AuthApp.model.category.SuCo.SuCoCreateDto;
import com.evnit.ttpm.AuthApp.model.category.ThoaThuan.SearchThoaThuanList;
import com.evnit.ttpm.AuthApp.model.category.ThoaThuan.ThoaThuanCreateDto;
import com.evnit.ttpm.AuthApp.model.category.ThoaThuan.ThoaThuanListDto;
import com.evnit.ttpm.AuthApp.model.filter.SearchFilter;
import com.evnit.ttpm.AuthApp.model.filter.SearchQuery;
import com.evnit.ttpm.AuthApp.repository.category.ThoaThuanRepository;
import com.evnit.ttpm.AuthApp.repository.category.ViewDealRepository;

import com.evnit.ttpm.AuthApp.repository.file.AFOtherRepository;
import com.evnit.ttpm.AuthApp.service.system.WonderHisService;
import com.evnit.ttpm.AuthApp.util.SpecificationUtil;
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
public class SCategoryThoaThuanServiceImpl implements SCategoryThoaThuanService {
    @Autowired
    ThoaThuanRepository ThoaThuanRepository;
    @Autowired
    ViewDealRepository ViewDealRepository;
    @Autowired
    AFOtherRepository afOtherRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    WonderHisService _wonderHisService;
    private UUID corrId = UUID.randomUUID();

    private final ModelMapper mapper;

    public SCategoryThoaThuanServiceImpl(ThoaThuanMapper mapperConfig) {
        this.mapper = mapperConfig.getModelMapper();
    }

    @Override
    public ResponseData getAll(SearchThoaThuanList dto) {
        ResponseData response = new ResponseData();


        SearchQuery searchQuery = new SearchQuery();
        List<ThoaThuanListDto> listCongTo = new ArrayList<ThoaThuanListDto>();
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
        if (dto.getTypeNMD() != null && !dto.getTypeNMD().isEmpty()) {
            searchFilter3 = new SearchFilter("TypeNMDid", "IN", dto.getTypeNMD());
            searchFilters.add(searchFilter3);
        }
        if (dto.getNumDoc() != null && !dto.getNumDoc().isEmpty()) {
            searchFilter3 = new SearchFilter("NumDoc", "LIKE", dto.getNumDoc());
            searchFilters.add(searchFilter3);
        }
        if (dto.getEndDateDoc() != null && !dto.getEndDateDoc().equals("Invalid date")  ) {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date fromDate = null;
            try {
                fromDate = formatter.parse(dto.getEndDateDoc());
            } catch (ParseException e) {
                e.printStackTrace();
            }
            searchFilter3 = new SearchFilter("EndDateDoc", "=", fromDate);
            searchFilters.add(searchFilter3);
        }
        if (dto.getTypeDeal() != null && !dto.getTypeDeal().isEmpty()) {
            searchFilter3 = new SearchFilter("TypeDealCode", "IN", dto.getTypeDeal());
            searchFilters.add(searchFilter3);
        }
        if (dto.getFactor() != null) {
            searchFilter3 = new SearchFilter("Factor", "=", dto.getFactor());
            searchFilters.add(searchFilter3);
        }


        Pageable pageable = PageRequest.of(dto.getPage(), dto.getLimit(), dto.getSortExpression());
        searchQuery.setSearchFilters(searchFilters);
        Specification<View_Deal> spec = SpecificationUtil.bySearchQuery(searchQuery, View_Deal.class);

        try {
            var pageResult = ViewDealRepository.findAll(spec, pageable);
            var dtoResult = pageResult.getContent().stream().map(item -> mapper.map(item, ThoaThuanListDto.class)).collect(Collectors.toList());
            Page<ThoaThuanListDto> result = new PageImpl<>(dtoResult, pageable, pageResult.getTotalElements());
            response.setData(result);
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setState(ResponseData.STATE.OK.toString());
            response.setData(result);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ex.getMessage());
            response.setData(null);
        }
        return response;
    }

    @Override
    public ResponseData getById(int id) {
        return null;
    }

    @Override
    public ResponseData create(ThoaThuanCreateDto createDto) {
        ResponseData response = new ResponseData();
        try {

            ThoaThuanCreateDto createDto1 = new ThoaThuanCreateDto();
            if(createDto.getFromdatecreate() != null && createDto.getEnddatecreate() != null){
            if (createDto.getFromdatecreate().after(createDto.getEnddatecreate())) {
                return new ResponseData(ResponseData.STATE.FAIL.toString(), "Vui lòng chọn ngày kết thúc hiệu lực >= Ngày bắt đầu hiệu lực");

            }}
            if(createDto.getFromdateslcreate() != null && createDto.getEnddateslcreate() != null && createDto.getCHECK()==true){
                if (createDto.getFromdateslcreate().after(createDto.getEnddateslcreate())) {
                    return new ResponseData(ResponseData.STATE.FAIL.toString(), "Vui lòng chọn ngày kết thúc hiệu lực >= Ngày bắt đầu hiệu lực");

                }
            }

            var guid = UUID.randomUUID().toString();

            createDto1.setDEALID(guid);
            createDto1.setDEALCONTENT(createDto.getDealcontentcreate());
            createDto1.setDEALDESC(createDto.getNote());
            createDto1.setASSETID(createDto.getTbaidcreate());
            createDto1.setNUMDOC(createDto.getNumdoccreate());
            createDto1.setUSER_CR_ID(createDto.getUSER_CR_ID());
            createDto1.setDATEDOC(createDto.getEnddatedoccreate());
            createDto1.setSTARTDATE_EFFECT(createDto.getFromdatecreate());
            createDto1.setENDDATE_EFFECT(createDto.getEnddatecreate());
            createDto1.setTYPE_DEAL(createDto.getTypedealcreate());

            if (createDto.getCHECK() == null || createDto.getCHECK()== false ) {
                createDto1.setCheckTick(false);

            } else {
                createDto1.setCheckTick(createDto.getCHECK());
                createDto1.setFACTOR(createDto.getFACTOR());
                createDto1.setSTARTDATE_DOC(createDto.getFromdateslcreate());
                createDto1.setVALID_BASE(createDto.getValidbasecreate());
                createDto1.setENDDATE_DOC(createDto.getEnddateslcreate());
            }
            createDto1.setFORM_SYN_POWER(createDto.getFormsynpowercreate());

            createDto1.setISDELETE(false);
            createDto1.setFileData(createDto.getFileData());
            createDto1.setTYPE(createDto.getTypecreate());
            if (createDto.getFileData() != null && createDto.getFileData().size() > 0) {
                //Lưu filePath vào disk sau đó set lại filepath cho filedata trong các object
                for (FileData file : createDto.getFileData()) {
                    if (file.getBase64().length() > 0) {
                        file.setFilePath(Base64ToFile(file, "ACCREDITATION/" + guid));
                    }
                }
                //Lưu filePath vào db
                for (FileData file : createDto1.getFileData()) {
                    AF_OTHER entity1 = new AF_OTHER();
                    entity1.setAfFileId(UUID.randomUUID().toString());
                    entity1.setCrdTime(new Date());
                    entity1.setUserId(createDto1.getUSER_CR_ID());
                    entity1.setObjId(guid);
                    entity1.setOrgId("0");
                    entity1.setObjTypeId(AF_OTHER.AF_TYPE.TT.toString());
                    entity1.setFileSizeB((int) file.getSize());
                    entity1.setFilePath(file.getFilePath());
                    entity1.setFileName(file.getFileName());
                    afOtherRepository.save(entity1);
                }
            }
            ThoaThuanCreateDto dto = mapper.map("",ThoaThuanCreateDto.class);
            //lưu his
            List<String> ignoredFields = new ArrayList<>(){{
                add("is_delete");
                add("id");
                add("tbaidcreate");
                add("typecreate");
                add("enddatedoccreate");
                add("fromdatecreate");
                add("enddatecreate");
                add("typedealcreate");
                add("formsynpowercreate");
                add("factorcreate");
                add("validbasecreate");
                add("fromdateslcreate");
                add("enddateslcreate");
                add("filedata");
                add("lstfiledelete");
                add("userid");
                add("formsynpower");add("dealid");
                add("dealcontent");add("numdoc");
                add("checktick");
                add("type");
                add("user_cr_id");

            }};
            Map<String, String> variableNameMapping = new HashMap<>();
            variableNameMapping.put("enddatestr", "Ngày bắt đầu hiệu lực");
            variableNameMapping.put("fromdatestr", "Ngày kết thúc hiệu lực");
            variableNameMapping.put( "enddatedocstr","Ngày văn bản thỏa thuận");
            variableNameMapping.put( "fromdateslstr","Ngày bắt đầu hiệu lực (sản lượng)");
            variableNameMapping.put( "enddateslstr","Ngày kết thúc hiệu lực (sản lượng)");
            variableNameMapping.put( "numdoccreate","Số văn bản thỏa thuận");
            variableNameMapping.put( "dealcontentcreate","Nội dung thỏa thuận");
            variableNameMapping.put( "note","Ghi chú");
            variableNameMapping.put( "validbase","Căn cứ hiệu lực");
            variableNameMapping.put( "typedeal","Loại thỏa thuận");
            variableNameMapping.put( "factor","Hệ số quy đổi SL");
            variableNameMapping.put( "tbaname","Tên NMĐ/TBA/RGL");
            variableNameMapping.put( "typename","Kiểu");
            variableNameMapping.put( "check","Hệ số quy đổi sản lượng");

            _wonderHisService.UpdateHis(guid, createDto,dto,ignoredFields,variableNameMapping, createDto.getUserId(), "M_Deal", "INS");



            var entity = mapper.map(createDto1, SCategoryThoaThuan.class);
            var result = ThoaThuanRepository.save(entity);
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
    public ResponseData update(String DEALID, ThoaThuanCreateDto updateDto) {
        ResponseData response = new ResponseData();
        try {
            var existEntity = ThoaThuanRepository.findById(DEALID).orElseThrow(() -> new IllegalArgumentException("Thỏa thuận không tồn tại"));
            ThoaThuanCreateDto updateDto1 = new ThoaThuanCreateDto();
            if(updateDto.getFromdatecreate() != null && updateDto.getEnddatecreate() != null){
                if (updateDto.getFromdatecreate().after(updateDto.getEnddatecreate())) {
                    return new ResponseData(ResponseData.STATE.FAIL.toString(), "Vui lòng chọn ngày kết thúc hiệu lực >= Ngày bắt đầu hiệu lực");

                }}
            if(updateDto.getFromdateslcreate() != null && updateDto.getEnddateslcreate() != null && updateDto.getCHECK()==true){
                if (updateDto.getFromdateslcreate().after(updateDto.getEnddateslcreate())) {
                    return new ResponseData(ResponseData.STATE.FAIL.toString(), "Vui lòng chọn ngày kết thúc hiệu lực >= Ngày bắt đầu hiệu lực");

                }
            }
            String sqlhis = "Select * from View_Deal where DEALID = '"+DEALID+"'";
            List<ThoaThuanCreateDto> lst2 = jdbcTemplate.query(sqlhis, new BeanPropertyRowMapper<>(ThoaThuanCreateDto.class));
            ThoaThuanCreateDto dto = mapper.map(lst2.get(0),ThoaThuanCreateDto.class);
            dto.setNumdoccreate(lst2.get(0).getNUMDOC());
            dto.setDealcontentcreate(lst2.get(0).getDEALCONTENT());
            assert lst2.get(0).getCheckTick() != null;
            var checkDto = lst2.get(0).getCheckTick();
            dto.setCHECK(checkDto);
//            dto.setCHECK(lst2.get(0).getCheckTick());

            updateDto1.setDEALCONTENT(updateDto.getDealcontentcreate());
            updateDto1.setDEALDESC(updateDto.getNote());
            updateDto1.setASSETID(updateDto.getTbaidcreate());
            updateDto1.setNUMDOC(updateDto.getNumdoccreate());
            updateDto1.setDATEDOC(updateDto.getEnddatedoccreate());
            updateDto1.setSTARTDATE_EFFECT(updateDto.getFromdatecreate());
            updateDto1.setENDDATE_EFFECT(updateDto.getEnddatecreate());
            updateDto1.setTYPE_DEAL(updateDto.getTypedealcreate());
            updateDto1.setFORM_SYN_POWER(updateDto.getFormsynpowercreate());
            if (updateDto.getCHECK() == null || updateDto.getCHECK()== false ) {
                updateDto1.setCheckTick(false);

            } else {
                updateDto1.setCheckTick(updateDto.getCHECK());
                updateDto1.setFACTOR(updateDto.getFACTOR());
                updateDto1.setVALID_BASE(updateDto.getValidbasecreate());

                updateDto1.setSTARTDATE_DOC(updateDto.getFromdateslcreate());
                updateDto1.setENDDATE_DOC(updateDto.getEnddateslcreate());
            }
            updateDto1.setFORM_SYN_POWER(updateDto.getFormsynpowercreate());
            updateDto1.setISDELETE(false);
            if(updateDto.getFileDataCreate() != null){
                updateDto1.setFileData(updateDto.getFileDataCreate());

            }else{
                //updateDto1.setFileData(existEntity.getFileData());

            }
            updateDto1.setTYPE(updateDto.getTypecreate());
            List<FileData> newFileList = new ArrayList<FileData>();
            if (updateDto.getFileData() != null && updateDto.getFileData().size() > 0) {
                //Lưu filePath vào disk sau đó set lại filepath cho filedata trong các object
                for (FileData file : updateDto.getFileData()) {
                    if (file.getBase64() != null && file.getBase64().length() > 0) {
                        file.setFilePath(Base64ToFile(file, "ACCREDITATION/" + updateDto.getDEALID()));
                        newFileList.add(file);
                    }
                }
                //Lưu file duoc them vào dbfileName = "Planet9_Wallpaper_5000x2813.jpg"
                for (FileData file : updateDto.getFileData()) {
                    if (file.getBase64() != null && file.getBase64().length() > 0) {
                        AF_OTHER entity = new AF_OTHER();
                        entity.setAfFileId(UUID.randomUUID().toString());
                        entity.setCrdTime(new Date());
                        entity.setUserId(updateDto.getUSER_CR_ID());
                        entity.setObjId(DEALID);
                        entity.setOrgId("0");
                        entity.setObjTypeId(AF_OTHER.AF_TYPE.TT.toString());
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
            //lưu his
            List<String> ignoredFields = new ArrayList<>(){{
                add("is_delete");
                add("id");
                add("tbaidcreate");
                add("typecreate");
                add("enddatedoccreate");
                add("fromdatecreate");
                add("enddatecreate");
                add("typedealcreate");
                add("formsynpowercreate");
                add("factorcreate");
                add("validbasecreate");
                add("fromdateslcreate");
                add("enddateslcreate");
                add("filedata");
                add("lstfiledelete");
                add("userid");
                add("formsynpower");add("dealid");
                add("dealcontent");add("numdoc");
                add("checktick");
                add("type");





            }};
            Map<String, String> variableNameMapping = new HashMap<>();
            variableNameMapping.put("enddatestr", "Ngày bắt đầu hiệu lực");
            variableNameMapping.put("fromdatestr", "Ngày kết thúc hiệu lực");
            variableNameMapping.put( "enddatedocstr","Ngày văn bản thỏa thuận");
            variableNameMapping.put( "fromdateslstr","Ngày bắt đầu hiệu lực (sản lượng)");
            variableNameMapping.put( "enddateslstr","Ngày kết thúc hiệu lực (sản lượng)");
            variableNameMapping.put( "numdoccreate","Số văn bản thỏa thuận");
            variableNameMapping.put( "dealcontentcreate","Nội dung thỏa thuận");
            variableNameMapping.put( "note","Ghi chú");
            variableNameMapping.put( "validbase","Căn cứ hiệu lực");
            variableNameMapping.put( "typedeal","Loại thỏa thuận");
            variableNameMapping.put( "factor","Hệ số quy đổi SL");
            variableNameMapping.put( "tbaname","Tên NMĐ/TBA/RGL");
            variableNameMapping.put( "typename","Kiểu");
            variableNameMapping.put( "check","Hệ số quy đổi sản lượng");


            _wonderHisService.UpdateHis(DEALID, updateDto,dto,ignoredFields,variableNameMapping,updateDto.getUserId(), "M_Deal", "UPDATE");


            mapper.map(updateDto1, existEntity);
            existEntity.setDealId(DEALID);
            var result = ThoaThuanRepository.save(existEntity);
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

    @Override
    public ResponseData delete(String DEALID) {
        ResponseData response = new ResponseData();
        try {
            var result = ThoaThuanRepository.findById(DEALID).map(thoathuan -> {
                thoathuan.setISDELETE(true);
                return ThoaThuanRepository.save(thoathuan);
            }).orElseThrow(() -> new IllegalArgumentException("Thỏa thuận không tồn tại"));
            response.setData(DEALID);
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
    public ResponseData getFileKiemDinh(String DEALID) {
        ResponseData response = new ResponseData();

        try {
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());

            String sql;
            List lst;
            //Lấy từ bảng chi tiết
            sql = "SELECT afFileId, [FILENAME] as 'fileName', '' as 'base64', filePath, FILESIZEB as 'size' FROM [QLDD].[dbo].[AF_OTHER] where OBJTYPEID ='TT' and [OBJID] = '" + DEALID + "'";
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
    public ResponseData updateNQL(@CurrentUser CustomUserDetails user, String DEALID,String ASSETID, Boolean stt) {
        ResponseData response = new ResponseData();

        String sql1;

        var strASSETID = ASSETID.split(",");
        for (String assetId:strASSETID){
            sql1 = "select  1 from Q_PQOBJ_USER where ASSETID =? and USERID=? and ISQL=?";
            List lst=jdbcTemplate.queryForList(sql1,assetId,user.getUserid(),true);
            if (lst==null || lst.isEmpty() )
                return new ResponseData(ResponseData.STATE.FAIL.toString(), "Không có quyền cập nhật nhà quản lý");
        }


        String strId = DEALID.replace(",", "','");

        var strStt = 0;
        if (stt) strStt = 1;
        else strStt = 0;
        try {
            String sql;
            sql = "UPDATE [dbo].[M_DEAL] SET [NQL_XNHAN] = " + strStt + " WHERE [DEALID] in ('" + strId + "')";
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
            sql1 = "select  1 from Q_PQOBJ_USER where ASSETID=? and USERID=? and ISLD=?";
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
            sql = "UPDATE [dbo].[M_DEAL] SET [LD_XNHAN] = " + strStt + " WHERE [DEALID] in ('" + strId + "')";
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
}

