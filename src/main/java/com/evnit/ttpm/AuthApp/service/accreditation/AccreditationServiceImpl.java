package com.evnit.ttpm.AuthApp.service.accreditation;

import com.evnit.ttpm.AuthApp.annotation.CurrentUser;
import com.evnit.ttpm.AuthApp.entity.accreditation.*;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.file.AF_OTHER;
import com.evnit.ttpm.AuthApp.mapper.AccreditationMapper;
import com.evnit.ttpm.AuthApp.model.accreditation.*;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.model.category.SuCo.SuCoCreateDto;
import com.evnit.ttpm.AuthApp.model.category.ThoaThuan.ThoaThuanCreateDto;
import com.evnit.ttpm.AuthApp.model.filter.SearchFilter;
import com.evnit.ttpm.AuthApp.model.filter.SearchQuery;
import com.evnit.ttpm.AuthApp.repository.accreditation.*;
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

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.text.SimpleDateFormat;

import javax.transaction.Transactional;

import static java.lang.Math.abs;

@Service
public class AccreditationServiceImpl implements AccreditationService {
    private final ModelMapper mapper;

    @Autowired
    AccreditationRepoService _accreditationRepoService;

    @Autowired
    AccreditationRepository _accreditationRepository;
    @Autowired
    AccreditationDetailRepository _accreditationDetailRepository;
    @Autowired
    AccreditationResultMeterRepository _accreditationResultMeterRepository;
    @Autowired
    AccreditationResultTURepository _accreditationResultTURepository;
    @Autowired
    AccreditationResultTIRepository _accreditationResultTIRepository;
    @Autowired
    WonderHisService _wonderHisService;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    AFOtherRepository afOtherRepository;
    @Value("${upload.dir}")
    private String uploadDir;

    public AccreditationServiceImpl(AccreditationMapper mapperConfig) {
        this.mapper = mapperConfig.getModelMapper();
    }

    @Override
    public ResponseData createAccreditation(AccreditationDetailDto dto) {
        ResponseData response = new ResponseData();
        try {
            var uuid = UUID.randomUUID().toString();
            M_ACCREDITATION_DETAILS asset = new M_ACCREDITATION_DETAILS();
            asset.setACCREDDETAILID(uuid);
            asset.setACCREDID(dto.getAccredId());
            asset.setASSETID(dto.getAssetId());
            asset.setACCRED_RESULT(dto.getAccred_Result());
//            asset.setTRANSID(dto.getTransId());
            asset.setPHA(dto.getPha());
            var assetEntity = _accreditationRepoService.createAccreditationDetail(asset);
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(uuid);
        } catch (Exception ignored) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setData(null);
        }
        return response;
    }

    @Override
    @Transactional
    public ResponseData UpdateAccreditation(String id, AccreditationDto dto) throws Exception {


        ResponseData response = new ResponseData();
        List<FileData> newFileList = new ArrayList<FileData>();

        try {
//            for(var i = 0;i<dto.getLstCongto().size();i++){
//                dto.setChitietCT(String.join(",",dto.getLstCongto().get(i).getAssetDesc()));
//            }
            dto.getLstCongto().sort(Comparator.comparing(AccreditationDetailDto::getAssetDesc));
            dto.getLstTU().sort(Comparator.comparing(AccreditationDetailDto::getAssetDesc));
            dto.getLstTI().sort(Comparator.comparing(AccreditationDetailDto::getAssetDesc));

            dto.setChitietCT(dto.getLstCongto().stream().map(x -> x.getAssetDesc()).collect(Collectors.joining(", ")));
            dto.setChitietTU(dto.getLstTU().stream().map(x -> x.getAssetDesc()+ ((x.getPha() != null && !x.getPha().isEmpty()) ? " pha "+x.getPha() : "")).collect(Collectors.joining(", ")));
            dto.setChitietTI(dto.getLstTI().stream().map(x -> x.getAssetDesc()+ ((x.getPha() != null && !x.getPha().isEmpty()) ? " pha "+x.getPha() : "")).collect(Collectors.joining(", ")));

            String sqlhis = "Select * from [VIEW_KIEM_DINH_TABLE] where ACCREDID = '"+id+"'";
            List<AccreditationDto> lst2 = jdbcTemplate.query(sqlhis, new BeanPropertyRowMapper<>(AccreditationDto.class));
            AccreditationDto dtohis = mapper.map(lst2.get(0),AccreditationDto.class);
            if(lst2.size()==3){
                dtohis.setChitietCT(lst2.get(0).getChitiet());
                dtohis.setChitietTI(lst2.get(1).getChitiet());
                dtohis.setChitietTU(lst2.get(2).getChitiet());
            }
            else if (lst2.size()==2){
                if(lst2.get(0).getCategoryId() == "CONGTO" && lst2.get(1).getCategoryId()=="TU"){
                    dtohis.setChitietCT(lst2.get(0).getChitiet());
                    dtohis.setChitietTU(lst2.get(1).getChitiet());
                }
                else if(lst2.get(0).getCategoryId() == "CONGTO" && lst2.get(1).getCategoryId()=="TI"){
                    dtohis.setChitietCT(lst2.get(0).getChitiet());
                    dtohis.setChitietTI(lst2.get(1).getChitiet());
                }
                else{
                    dtohis.setChitietTI(lst2.get(0).getChitiet());
                    dtohis.setChitietTU(lst2.get(1).getChitiet());
                }
            }
            else{
                if(lst2.get(0).getCategoryId() == "CONGTO"){
                    dtohis.setChitietCT(lst2.get(0).getChitiet());
                }
                if(lst2.get(0).getCategoryId() == "TI"){
                    dtohis.setChitietTI(lst2.get(0).getChitiet());
                }
                if(lst2.get(0).getCategoryId() == "TU"){
                    dtohis.setChitietTU(lst2.get(0).getChitiet());
                }
            }

            //String sqlhis1 = "Select * from [VIEW_KIEM_DINH_TABLE] where ACCREDID = '"+id+"'";
            //dtohis.setLstCongto();
            var existEntity = _accreditationRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Kiểm định không tồn tại"));

            var createDate = new Date();
            existEntity.setACCREDDESC(dto.getAccredDesc());
            existEntity.setASSETID(dto.getAssetId());
            existEntity.setACCREDITATION_STARTDATE(dto.getAccredtationStartDate());
            existEntity.setACCREDITATION_ENDDATE(dto.getAccredtationEndDate());
            existEntity.setACCREDITATION_TYPE(dto.getAccredtationType());
            existEntity.setACCREDITATION_RESULT(dto.getAccredtationResult());
            existEntity.setNOTE(dto.getNote());
            existEntity.setUSER_MDF_ID(dto.getUser_mdf_id());
            existEntity.setUSER__MDF_DTIME(createDate);

            var result = _accreditationRepository.save(existEntity);


            //update Detail
            updateOrAddListAccreditationDetail(dto.getLstCongto(), existEntity.getACCREDID(), AF_OTHER.AF_TYPE.KDCT.toString());
            updateOrAddListAccreditationDetail(dto.getLstTI(), existEntity.getACCREDID(), AF_OTHER.AF_TYPE.KDTI.toString());
            updateOrAddListAccreditationDetail(dto.getLstTU(), existEntity.getACCREDID(), AF_OTHER.AF_TYPE.KDTU.toString());

            //deldata in db
            deleteAccreditationDetail(dto.getLstCongtoDelete(), AF_OTHER.AF_TYPE.KDCT.toString(), id);
            deleteAccreditationDetail(dto.getLstTIDelete(), AF_OTHER.AF_TYPE.KDTI.toString(), id);
            deleteAccreditationDetail(dto.getLstTUDelete(), AF_OTHER.AF_TYPE.KDTU.toString(), id);

            //Add file when update Accreditation
            if (dto.getFileData() != null && dto.getFileData().size() > 0) {
                //Lưu filePath vào disk sau đó set lại filepath cho filedata trong các object
                for (FileData file : dto.getFileData()) {
                    if (file.getBase64() != null && file.getBase64().length() > 0) {
                        file.setFilePath(Base64ToFile(file, "ACCREDITATION/" + dto.getAccredId()));
                        newFileList.add(file);
                    }
                }
                //Lưu file duoc them vào db
                for (FileData file : dto.getFileData()) {
                    if (file.getBase64() != null && file.getBase64().length() > 0) {
                        AF_OTHER entity = new AF_OTHER();
                        entity.setAfFileId(UUID.randomUUID().toString());
                        entity.setCrdTime(new Date());
                        entity.setUserId(dto.getUser_cr_id());
                        entity.setObjId(dto.getAccredId());
                        entity.setOrgId("0");
                        entity.setObjTypeId(AF_OTHER.AF_TYPE.KD.toString());
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

            //Del file on disk when all db updated
            if (dto.getLstFileDelete() != null && dto.getLstFileDelete().size() > 0) {
                DelAllFile(dto.getLstFileDelete());
            }
            if (dto.getLstCongtoDelete() != null && dto.getLstCongtoDelete().size() > 0) {
                for (var item : dto.getLstCongtoDelete()) {
                    if (item.getLstFileDelete() != null && item.getLstFileDelete().size() > 0) {
                        DelAllFile(item.getLstFileDelete());
                    }
                }
            }
            if (dto.getLstTIDelete() != null && dto.getLstTIDelete().size() > 0) {
                for (var item : dto.getLstTIDelete()) {
                    if (item.getLstFileDelete() != null && item.getLstFileDelete().size() > 0) {
                        DelAllFile(item.getLstFileDelete());
                    }
                }
            }
            if (dto.getLstTUDelete() != null && dto.getLstTUDelete().size() > 0) {
                for (var item : dto.getLstTUDelete()) {
                    if (item.getLstFileDelete() != null && item.getLstFileDelete().size() > 0) {
                        DelAllFile(item.getLstFileDelete());
                    }
                }
            }
            //Lưu ngày kiểm định gần nhất của thiết bị
            String sql;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String dateStr = dateFormat.format(dto.getAccredtationEndDate());
            sql = "UPDATE [dbo].[A_ASSET] SET DATEACCREDITATION = '" + dateStr + "' WHERE ASSETID IN  (SELECT ASSETID FROM [dbo].[M_ACCREDITATION_DETAILS] WHERE ACCREDID ='" + dto.getAccredId() + "')";
            jdbcTemplate.execute(sql);
            //lưu his



            List<String> ignoredFields = new ArrayList<>(){{
                add("is_delete");
                add("id");
                add("accreddesc");
                add("accredtationstartdate");
                add("accredtationenddate");
                add("accredtationtype");
                add("accredtationresult");
                add("note");
                add("user_mdf_id");
                add("nql_xnhan");
                add("ld_xnhan");add("filedata");
                add("lstfiledelete");
                add("categoryid");
                add("chitiet");
                add("lstcongto");add("lstcongtodelete");
                add("lsttu");add("lsttudelete");
                add("lstti");add("lsttidelete");
                add("assetid");
            }};
            Map<String, String> variableNameMapping = new HashMap<>();
            variableNameMapping.put("batdaustr", "Thời gian thực hiện-Bắt đầu");
            variableNameMapping.put("ketthucstr", "Thời gian thực hiện-Kết thúc");
            variableNameMapping.put( "hinhthuc","Hình thức");
            variableNameMapping.put( "ten","Tên NMĐ/TBA/RGL");
            variableNameMapping.put( "kieu","Kiểu");
            variableNameMapping.put( "chitietketquathuchien","Chi tiết");
            variableNameMapping.put( "chitietct","Đối tượng kiểm định công tơ");
            variableNameMapping.put( "chitiettu","Đối tượng kiểm định TU");
            variableNameMapping.put( "chitietti","Đối tượng kiểm định TI");



            _wonderHisService.UpdateHis(id, dto,dtohis,ignoredFields,variableNameMapping, dto.getUser_mdf_id(), "M_ACCREDITATION", "UPDATE");


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
    public ResponseData getAccreditationDetails(String transId, String type, String accredId) {
        ResponseData response = new ResponseData();
        try {
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            String sql;
            List<AccreditationDetailDto> lst; //List<SListGroupAll> lst;
            sql = "SELECT case when acc.ACCEPCHANGEID is not null then 1 else 0 end as accChange, [ACCREDDETAILID] as 'accredDetailId' , pha, dtt.[ACCREDID] as 'accredId' ,dtt.[ASSETID] as 'assetId' ,dtt.ACCRED_RESULT as 'accred_Result' ,ass.ASSETDESC as 'assetDesc', ass.ASSETID_PARENT as 'assetId_Parent', ass.ISDELETE as 'isDelete' ,0 as 'selected' ,ass.SERIALNUM as 'serialNum', NULL as lstFile FROM [dbo].[M_ACCREDITATION_DETAILS] dtt left join [dbo].[A_ASSET] ass  on dtt.[ASSETID] = ass.[ASSETID]  left join [dbo].[M_ACCEPTANCE_CHANGE] acc on dtt.[ASSETID] = acc.ASSETID_CHANGE  and dtt.ACCREDID = acc.ACCREDID where ass.CATEGORYID = '" + type + "' and ass.ISDELETE = 0 and dtt.[ACCREDID] = '" + accredId + "' order by assetDesc asc, PHA asc";

            lst = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(AccreditationDetailDto.class));

            String objType = "";
            switch (type) {
                case "CONGTO":
                    objType = "KDCT";
                    break;
                case "TU":
                    objType = "KDTU";
                    break;
                case "TI":
                    objType = "KDTI";
                    break;
            }
            for (AccreditationDetailDto item : lst) {
                List<FileData> lstFile;
                sql = "SELECT afFileId, [FILENAME] as 'fileName', '' as 'base64', filePath, FILESIZEB as 'size' FROM [dbo].[AF_OTHER] where OBJTYPEID ='" + objType + "' and [OBJID] = '" + item.getAccredDetailId() + "'";
                lstFile = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(FileData.class));
                item.setLstFile(lstFile);
            }

            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(lst);

        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ex.getMessage());
            response.setData(null);
        }
        return response;
    }

    public ResponseData getThietBiKD(String transId, String categoryId, String strThietBi, String nhaMayDienId) {
        ResponseData response = new ResponseData();

        try {
            strThietBi = strThietBi.replace(",", "','");
            strThietBi = "('" + strThietBi + "')";

            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            String tableName = "";
            String sql = "";
            if (categoryId.equals("TU")) {
                sql = "SELECT DISTINCT [IDTU] as 'assetId', [PHA] as 'pha', [IDBOTU] as 'assetId_Parent', [BOTUNAME] as 'assetDesc', [TUSERIALNUM] as 'serialNum', [ISDELETE] as 'isDelete', '' as 'transId', CAST(1 as bit) as 'accred_Result', 0 as 'selected' FROM [dbo].[VIEWTU_DEVICE] where TRANGTHAIDD not like '%0%' and ISDELETE = 0 and POWERSYSTEMID = '" + nhaMayDienId + "' and IDTU not in " + strThietBi + " order by BOTUNAME, PHA";
            } else if (categoryId.equals("TI")) {
                sql = "SELECT DISTINCT [IDTI] as 'assetId', [PHA] as 'pha', [IDBOTI] as 'assetId_Parent', [BOTINAME] as 'assetDesc', [TISERIALNUM] as 'serialNum', [ISDELETE] as 'isDelete', '' as 'transId', CAST(1 as bit) as 'accred_Result', 0 as 'selected' FROM [dbo].[VIEWTI_DEVICE] where TRANGTHAIDD not like '%0%' and ISDELETE = 0  and POWERSYSTEMID = '" + nhaMayDienId + "' and IDTI not in " + strThietBi + " order by BOTINAME, PHA";
            } else if (categoryId.equals("CONGTO")) {
                sql = "SELECT DISTINCT ASSETID as 'assetId', '' as 'pha', IDDIEMDO as 'assetId_Parent', ASSETDESC as 'assetDesc', SERIALNUM as 'serialNum', [ISDELETE] as 'isDelete', '' as 'transId', CAST(1 as bit) as 'accred_Result', 0 as 'selected' FROM [dbo].[VIEWCONGTO_DEVICE] where TRANGTHAIDD != 0 and ISDELETE = 0 and IDNHAMAY = '" + nhaMayDienId + "' and ASSETID not in " + strThietBi + " order by ASSETDESC";
            }

            List lst; //List<SListGroupAll> lst;

            lst = jdbcTemplate.queryForList(sql);
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(lst);
            //response.setData(result);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ex.getMessage());
            response.setData(null);
        }

        return response;
    }

    //Lấy thông tin chi tiết kiểm định TU
    public ResponseData GetThongTinKiemDinhTUTI(String accredId, String strThietBi) {
        ResponseData response = new ResponseData();
        strThietBi = strThietBi.replace(",", "','");

        try {
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());

            String sql;
            List lst;
            //Lấy từ bảng chi tiết
            sql = "select case when rs.[CCX] is not null then rs.[CCX] else '' end + case when rs.PHA is not null then rs.PHA else '' end + CUON_DNOI as 'mergCol', dt.ASSETID as assetId, [CCX] as 'ccx', rs.pha, SERIALNUM as 'soCheTao', CUON_DNOI as 'cuonDauNoi', [TISO] as 'tsb', [DUNGLUONG] as dungLuong, [UF80] as 'uf80', [USSG80] as 'ussg80', [UF100] as 'uf100', [USSG100] as 'ussg100', [UF120] as 'uf120', [USSG120] as 'ussg120' from [M_ACCREDITATION_RESULT_TU] rs left join [dbo].[M_ACCREDITATION_DETAILS] dt on rs.ACCREDDETAILID = dt.ACCREDDETAILID where rs.[ACCREDDETAILID] in (Select ACCREDDETAILID from [dbo].[M_ACCREDITATION_DETAILS] where ACCREDID = '" + accredId + "')  and dt.ASSETID in ('" + strThietBi + "') order by rs.PHA, ccx, CUON_DNOI, DUNGLUONG asc";
            lst = jdbcTemplate.queryForList(sql);

            if (lst != null && lst.size() > 0) {
                response.setState(ResponseData.STATE.OK.toString());
                response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
                response.setData(lst);
            } else {
                sql = "select c.CCX + case when tu.PHA is not null then tu.PHA else '' end  + c.CUON_DNOI as 'mergCol', assTU.CATEGORYID, sla.LISTDESC as 'ccx', c.CUON_DNOI as 'cuonDauNoi', tu.PHA as 'pha', assTU.SERIALNUM as 'soCheTao', assTU.ASSETDESC, assTU.assetId, '' as 'tsb', '' as dungLuong,'' as 'uf80', '' as 'ussg80', '' as 'uf100', '' as 'ussg100', '' as 'uf120', '' as 'ussg120' from A_ASSET assCuon left join S_ATTRIBUTE_GROUP_ASSOBJ att on assCuon.ASSETID = att.OBJID left join ZAG_CUON c on att.ATTRDATAID = c.ATTRDATAID left join A_ASSET assTU on assCuon.ASSETID_PARENT = assTU.ASSETID left join S_ATTRIBUTE_GROUP_ASSOBJ attTu on assTU.ASSETID = attTu.OBJID left join ZAG_TU tu on attTu.ATTRDATAID = tu.ATTRDATAID inner join HLP_TYPE hlp on assTU.CATEGORYID = hlp.[TYPE] left join [dbo].[S_LIST_ALL] sla on c.CCX = sla.LISTID and sla.GROUPLISTID = 'CCX' where assCuon.ISDELETE = 0 and assTU.ASSETID in ('" + strThietBi + "') order by PHA, ccx, c.CUON_DNOI";

                lst = jdbcTemplate.queryForList(sql);

                response.setState(ResponseData.STATE.OK.toString());
                response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
                response.setData(lst);
            }


        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ex.getMessage());
            response.setData(null);
        }

        return response;
    }

    //lấy danh sách file kiểm định
    public ResponseData getFileKiemDinh(String accredId) {
        ResponseData response = new ResponseData();

        try {
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());

            String sql;
            List lst;
            //Lấy từ bảng chi tiết
            sql = "SELECT afFileId, [FILENAME] as 'fileName', '' as 'base64', filePath, FILESIZEB as 'size' FROM [dbo].[AF_OTHER] where OBJTYPEID ='KD' and [OBJID] = '" + accredId + "'";
            lst = jdbcTemplate.queryForList(sql);

            response.setData(lst);


        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ex.getMessage());
            response.setData(null);
        }

        return response;
    }

    //Lấy thông tin chi tiết kiểm định TI
    public ResponseData GetThongTinKiemDinhTI(String accredId, String strThietBi) {
        ResponseData response = new ResponseData();
        strThietBi = strThietBi.replace(",", "','");

        try {
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());

            String sql;
            List lst;
            //Lấy từ bảng chi tiết
            sql = "select case when rs.[CCX] is not null then rs.[CCX] else '' end + case when rs.PHA is not null then rs.PHA else '' end + CUON_DNOI as 'mergCol', dt.ASSETID as assetId, [CCX] as 'ccx', rs.pha, SERIALNUM as 'soCheTao', CUON_DNOI as 'cuonDauNoi', [TISO] as 'tsb', [TAI] as 'dungLuong', [UF1] as 'uf1', [USSG1] as 'ussg1', [UF5] as 'uf5', [USSG5] as 'ussg5', [UF20] as 'uf20', [USSG20] as 'ussg20', [UF100] as 'uf100', [USSG100] as 'ussg100', [UF120] as 'uf120', [USSG120] as 'ussg120' from [M_ACCREDITATION_RESULT_TI] rs left join [dbo].[M_ACCREDITATION_DETAILS] dt on rs.ACCREDDETAILID = dt.ACCREDDETAILID where rs.[ACCREDDETAILID] in (Select ACCREDDETAILID from [dbo].[M_ACCREDITATION_DETAILS] where ACCREDID = '" + accredId + "') and dt.ASSETID in ('" + strThietBi + "') order by rs.pha, ccx, CUON_DNOI, TAI asc";
            lst = jdbcTemplate.queryForList(sql);

            if (lst != null && lst.size() > 0) {
                response.setState(ResponseData.STATE.OK.toString());
                response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
                response.setData(lst);
            } else {
                sql = "select c.CCX + case when ti.PHA is not null then ti.PHA else '' end  + c.CUON_DNOI as 'mergCol', assTI.CATEGORYID, sla.LISTDESC as 'ccx', c.CUON_DNOI as 'cuonDauNoi', ti.PHA as 'pha', assTI.SERIALNUM as 'soCheTao', assTI.ASSETDESC, assTI.assetId, '' as 'tsb', '' as dungLuong, '' as 'uf1', '' as 'ussg1', '' as 'uf5', '' as 'ussg5', '' as 'uf20', '' as 'ussg20', '' as 'uf100', '' as 'ussg100', '' as 'uf120', '' as 'ussg120' from A_ASSET assCuon left join S_ATTRIBUTE_GROUP_ASSOBJ att on assCuon.ASSETID = att.OBJID left join ZAG_CUON c on att.ATTRDATAID = c.ATTRDATAID left join A_ASSET assTI on assCuon.ASSETID_PARENT = assTI.ASSETID left join S_ATTRIBUTE_GROUP_ASSOBJ attTi on assTI.ASSETID = attTi.OBJID left join ZAG_TI ti on attTi.ATTRDATAID = ti.ATTRDATAID inner join HLP_TYPE hlp on assTI.CATEGORYID = hlp.[TYPE] left join [dbo].[S_LIST_ALL] sla on c.CCX = sla.LISTID and sla.GROUPLISTID = 'CCX' where assCuon.ISDELETE = 0 and assTI.ASSETID in ('" + strThietBi + "') order by PHA, ccx, c.CUON_DNOI";

                lst = jdbcTemplate.queryForList(sql);

                response.setState(ResponseData.STATE.OK.toString());
                response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
                response.setData(lst);
            }


        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ex.getMessage());
            response.setData(null);
        }

        return response;
    }

    @Override
    public boolean checkAccreditation(String accredId, String assetId, Date startDate, Date endDate, String accredtationType) {
        boolean rs = false;
        String strStartDate = new SimpleDateFormat("yyyy-MM-dd").format(startDate);
        String strEndDate = new SimpleDateFormat("yyyy-MM-dd").format(endDate);

        String sql;
        List lst; //List<SListGroupAll> lst;
        if (accredId != null)
            sql = "SELECT *  FROM [dbo].[M_ACCREDITATION] where  ACCREDID != '" + accredId + "' AND ASSETID = '" + assetId + "' AND ACCREDITATION_STARTDATE = '" + strStartDate + "' AND ACCREDITATION_ENDDATE = '" + strEndDate + "' AND ACCREDITATION_TYPE = '" + accredtationType + "'";
        else
            sql = "SELECT *  FROM [dbo].[M_ACCREDITATION] where  ASSETID = '" + assetId + "' AND ACCREDITATION_STARTDATE = '" + strStartDate + "' AND ACCREDITATION_ENDDATE = '" + strEndDate + "'AND ACCREDITATION_TYPE = '" + accredtationType + "'";

        lst = jdbcTemplate.queryForList(sql);

        //Nếu chưa có bản ghi nào thì cập nhật trạng thái là true
        if (lst.size() == 0) {
            rs = true;
        }

        return rs;
    }

    @Override
    public ResponseData getResultMeter(String accredDetailId, String assetId) {
        ResponseData response = new ResponseData();
        try {
            String sql;
            List lst; //List<SListGroupAll> lst;
            sql = "SELECT CCX as 'ccx', DVI_TND as 'dviTND', [ROW_INDEX] as 'index', DIENAP as 'dienAp', TAI_IN as 'taiIn', pha, cosPhi, WH_GIAO as 'whGiao', WH_NHAN as 'whNhan', VARH_GIAO as 'varhGiao', VARH_NHAN as 'varhNhan' FROM [dbo].[M_ACCREDITATION_RESULT_METER] where ACCREDDETAILID = '" + accredDetailId + "'order by [ROW_INDEX]";
            lst = jdbcTemplate.queryForList(sql);
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(lst);

        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ex.getMessage());
            response.setData(null);
        }
        return response;
    }

    private KiemDinhListDto findMonth(List<KiemDinhListDto> totalMonth, Date thangKiemDinh) {
        if (totalMonth != null && totalMonth.size() > 0) {
            for (var item : totalMonth) {
                if (item.getThangKd().equals(thangKiemDinh)) {
                    return item;
                }
            }
        }
        return null;
    }

    @Override
    public ResponseData getDanhSachThongTinKiemDinh(SearchKiemDinh dto) {
        ResponseData response = new ResponseData();
        SearchQuery searchQuery = new SearchQuery();
        List<KiemDinhListDto> listKiemDinh = new ArrayList<KiemDinhListDto>();

        SearchFilter searchFilter = new SearchFilter();
        SearchFilter searchFilterCondition = new SearchFilter();

        List<KiemDinhListDto> total = this.TotalDanhDanhSachKiem(dto);
        List<KiemDinhListDto> totalMonth = this.TotalMonthDanhDanhSachKiem(dto);

        List<SearchFilter> searchFilters = new ArrayList<>();

        if (dto.getType() != null && !dto.getType().isEmpty()) {
            searchFilterCondition = new SearchFilter("kieu", "IN", dto.getType());
            searchFilters.add(searchFilterCondition);
        }

        if (dto.getAssetId() != null && !dto.getAssetId().isEmpty()) {
            searchFilterCondition = new SearchFilter("assetId", "IN", dto.getAssetId());
            searchFilters.add(searchFilterCondition);
        }

        if (dto.getTrangThaiNMD() != null && !dto.getTrangThaiNMD().isEmpty()) {
            searchFilterCondition = new SearchFilter("trangThaiNMD", "IN", dto.getTrangThaiNMD());
            searchFilters.add(searchFilterCondition);
        }
        if (dto.getHinhThuc() != null && !dto.getHinhThuc().get(0).equals("undefined") && !dto.getHinhThuc().isEmpty()) {
            searchFilterCondition = new SearchFilter("hinhThucId", "IN", dto.getHinhThuc());
            searchFilters.add(searchFilterCondition);
        }
        if (dto.getChiTiet() != null && !dto.getChiTiet().equals("undefined") && !dto.getChiTiet().isEmpty()) {
            searchFilterCondition = new SearchFilter("chiTiet", "LIKE", dto.getChiTiet());
            searchFilters.add(searchFilterCondition);
        }
        if (dto.getCategoryId() != null && !dto.getCategoryId().get(0).equals("undefined") && !dto.getCategoryId().isEmpty()) {
            searchFilterCondition = new SearchFilter("categoryId", "IN", dto.getCategoryId());
            searchFilters.add(searchFilterCondition);
        }

        if (dto.getStartDate() != null) {

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            Date fromDate = null;
            try {
                fromDate = formatter.parse(dto.getStartDate());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            searchFilterCondition = new SearchFilter("ketThuc", ">=", fromDate);
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

            searchFilterCondition = new SearchFilter("ketThuc", "<=", endDate);
            searchFilters.add(searchFilterCondition);
        }

        Pageable pageable = PageRequest.of(dto.getPage(), dto.getLimit(), dto.getSortExpression());
        searchQuery.setSearchFilters(searchFilters);
        Specification<VIEW_KIEM_DINH_FINAL> spec = SpecificationUtil.bySearchQuery(searchQuery, VIEW_KIEM_DINH_FINAL.class);
        try {
            var pageResult = _accreditationRepoService.findAllPaging(spec, pageable);
            var dtoResult = pageResult.getContent().stream().map(item -> mapper.map(item, KiemDinhListDto.class)).collect(Collectors.toList());

            //Tháng kiểm định vòng for
            //Date thangKD = new Date();
            Date check_CurrentRow_Month = null;
            Date check_NextRow_Month = null;

            ////Index hiện tại cần add
//            Integer indexMonth = 0;

//            for (KiemDinhListDto item : dtoResult) {
//                if (!item.getThangKd().equals(thangKD)) {
//                    thangKD = item.getThangKd();
//                    listKiemDinh.add(totalMonth.get(indexMonth));
//                    indexMonth++;
//                }
//                listKiemDinh.add(item);
//            }
            for (int idx_dto = 0; idx_dto < dtoResult.size(); idx_dto++) {
                // only one row
                if (dtoResult.size() == 1) {
                    check_CurrentRow_Month = dtoResult.get(idx_dto).getThangKd();
                    var addTotalMontItem = findMonth(totalMonth, check_CurrentRow_Month);
                    if (addTotalMontItem != null) {
                        listKiemDinh.add(addTotalMontItem);
                    }
                    listKiemDinh.add(dtoResult.get(idx_dto));
                    continue;
                }
                // lastRow and not is first row
                else if (idx_dto == (dtoResult.size() - 1)) {
                    var prevMonth = check_CurrentRow_Month;
                    var thisMonth = check_NextRow_Month;
                    if (!prevMonth.equals(thisMonth)) {
                        var addTotalMontItem = findMonth(totalMonth, thisMonth);
                        if (addTotalMontItem != null) {
                            listKiemDinh.add(addTotalMontItem);
                        }
                    }
                    listKiemDinh.add(dtoResult.get(idx_dto));
                }
                // first row and not is end row
                else if (idx_dto == 0) {
                    check_CurrentRow_Month = dtoResult.get(idx_dto).getThangKd();
                    check_NextRow_Month = dtoResult.get(idx_dto + 1).getThangKd();
                    var addTotalMontItem = findMonth(totalMonth, check_CurrentRow_Month);
                    if (addTotalMontItem != null) {
                        listKiemDinh.add(addTotalMontItem);
                    }
                    listKiemDinh.add(dtoResult.get(idx_dto));
                    continue;
                }
                //between row
                else {
                    var prevMonth = check_CurrentRow_Month;
                    var thisMonth = check_NextRow_Month;
                    if (!prevMonth.equals(thisMonth)) {
                        var addTotalMontItem = findMonth(totalMonth, thisMonth);
                        if (addTotalMontItem != null) {
                            listKiemDinh.add(addTotalMontItem);
                        }
                    }
                    listKiemDinh.add(dtoResult.get(idx_dto));
                    check_CurrentRow_Month = dtoResult.get(idx_dto).getThangKd();
                    check_NextRow_Month = dtoResult.get(idx_dto + 1).getThangKd();
                }

            }


            listKiemDinh.add(0, total.get(0));
            Page<KiemDinhListDto> result = new PageImpl<KiemDinhListDto>(listKiemDinh, pageable, pageResult.getTotalElements());

            CustomPageImpl cusResult = new CustomPageImpl();
            cusResult.setContent(listKiemDinh);
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

    //Dòng tổng của danh sách kiểm định
    private List<KiemDinhListDto> TotalDanhDanhSachKiem(SearchKiemDinh dto) {

        String sql;

        List lst;
        sql = "select  NEWID() as ID, '' as 'KIEU', '' as 'TEN','' 'HINHTHUC',CAST(NULL as date) as 'BATDAU',CAST(NULL as date) as 'KETTHUC','' as 'LOAI',sum(SOLUONG)as 'SOLUONG',sum(DAT) as 'DAT',sum(KHONGDAT) as 'KHONGDAT',sum(THAYTHE) as 'THAYTHE', sum(SOLUOT) as 'SOLUOT','' as 'CHITIETKETQUATHUCHIEN',CAST(NULL as date) as 'THANGKD','' as 'ASSETID','' as 'ACCREDID',CAST(NULL as int) as 'TRANGTHAINMD'\tfrom [dbo].[VIEW_KIEM_DINH_FINAL] where 1 = 1";

        if (dto.getType() != null && !dto.getType().isEmpty()) {
            sql += " AND KIEU IN ('" + String.join("','", dto.getType()) + "')";
        }

        if (dto.getAssetId() != null && !dto.getAssetId().isEmpty()) {
            sql += " AND ASSETID IN ('" + String.join("','", dto.getAssetId()) + "')";
        }
        if (dto.getChiTiet() != null && !dto.getChiTiet().equals("undefined")) {
            sql += "AND CHITIET LIKE '%" + dto.getChiTiet() + "%'";
        }
        if (dto.getCategoryId() != null && !dto.getCategoryId().get(0).equals("undefined") && !dto.getCategoryId().isEmpty()) {
            sql += " AND CATEGORYID IN ('" + String.join("','", dto.getCategoryId()) + "')";
        }
        if (dto.getHinhThuc() != null && !dto.getHinhThuc().get(0).equals("undefined") && !dto.getHinhThuc().isEmpty()) {
            sql += " AND HINHTHUCID IN ('" + String.join("','", dto.getHinhThuc()) + "')";
        }

        if (dto.getTrangThaiNMD() != null && !dto.getTrangThaiNMD().isEmpty()) {
            sql += " AND TRANGTHAINMD IN (" + dto.getTrangThaiNMD().stream().map(String::valueOf).collect(Collectors.joining(",")) + ")";
        }


        if (dto.getStartDate() != null) {
            sql += " AND KETTHUC >= '" + dto.getStartDate().toString() + "'";
        }

        if (dto.getEndDate() != null) {
            sql += " AND KETTHUC <= '" + dto.getEndDate().substring(0, 10).toString() + " 23:59:59'";
        }

        lst = jdbcTemplate.query(sql, new BeanPropertyRowMapper<KiemDinhListDto>(KiemDinhListDto.class));

        return lst;
    }

    //Dòng tổng từng tháng của danh sách kiểm định
    private List<KiemDinhListDto> TotalMonthDanhDanhSachKiem(SearchKiemDinh dto) {

        String sql;

        List lst;
        sql = "select  NEWID() as ID, '' as 'KIEU', '' as 'TEN','' 'HINHTHUC',CAST(NULL as date) as 'BATDAU',CAST(NULL as date) as 'KETTHUC',NULL as 'LOAI',sum(SOLUONG)as 'SOLUONG',sum(DAT) as 'DAT',sum(KHONGDAT) as 'KHONGDAT', sum(THAYTHE) as 'THAYTHE', sum(SOLUOT) as 'SOLUOT','' as 'CHITIETKETQUATHUCHIEN',THANGKD,'' as 'ASSETID','' as 'ACCREDID',CAST(NULL as int) as 'TRANGTHAINMD' from [dbo].[VIEW_KIEM_DINH_FINAL] where 1 = 1";

        if (dto.getType() != null && !dto.getType().isEmpty()) {
            sql += " AND KIEU IN ('" + String.join("','", dto.getType()) + "')";
        }

        if (dto.getAssetId() != null && !dto.getAssetId().isEmpty()) {
            sql += " AND ASSETID IN ('" + String.join("','", dto.getAssetId()) + "')";
        }

        if (dto.getTrangThaiNMD() != null && !dto.getTrangThaiNMD().isEmpty()) {
            sql += " AND TRANGTHAINMD IN (" + dto.getTrangThaiNMD().stream().map(String::valueOf).collect(Collectors.joining(",")) + ")";
        }
        if (dto.getChiTiet() != null && !dto.getChiTiet().equals("undefined")) {
            sql += "AND CHITIET LIKE '%" + dto.getChiTiet() + "%'";
        }
        if (dto.getCategoryId() != null && !dto.getCategoryId().get(0).equals("undefined") && !dto.getCategoryId().isEmpty()) {
            sql += " AND CATEGORYID IN ('" + String.join("','", dto.getCategoryId()) + "')";
        }
        if (dto.getHinhThuc() != null && !dto.getHinhThuc().get(0).equals("undefined") && !dto.getHinhThuc().isEmpty()) {
            sql += " AND HINHTHUCID IN ('" + String.join("','", dto.getHinhThuc()) + "')";
        }

        if (dto.getStartDate() != null) {
            sql += " AND KETTHUC >= '" + dto.getStartDate().toString() + "'";
        }

        if (dto.getEndDate() != null) {
            sql += " AND KETTHUC <= '" + dto.getEndDate().substring(0, 10).toString() + " 23:59:59'";
        }

        sql += " GROUP BY THANGKD ORDER BY THANGKD DESC";

        lst = jdbcTemplate.query(sql, new BeanPropertyRowMapper<KiemDinhListDto>(KiemDinhListDto.class));

        return lst;
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

    @Override
    @Transactional
    public ResponseData CreateAccreditation(AccreditationDto dto) throws Exception {
        ResponseData response = new ResponseData();
        try {

            var createDate = new Date();
            var uuid = UUID.randomUUID().toString();
            M_ACCREDITATION asset = new M_ACCREDITATION();
            asset.setACCREDID(uuid);
            asset.setACCREDDESC(dto.getAccredDesc());
            asset.setASSETID(dto.getAssetId());
            asset.setACCREDITATION_STARTDATE(dto.getAccredtationStartDate());
            asset.setACCREDITATION_ENDDATE(dto.getAccredtationEndDate());
            asset.setACCREDITATION_TYPE(dto.getAccredtationType());
            asset.setACCREDITATION_RESULT(dto.getAccredtationResult());
            asset.setNOTE(dto.getNote());
            asset.setUSER_CR_ID(dto.getUser_cr_id());
            asset.setUSER_CR_DTIME(createDate);
            asset.setUSER_MDF_ID(dto.getUser_cr_id());
            asset.setUSER__MDF_DTIME(createDate);
            asset.setLD_XNHAN(false);
            asset.setNQL_XNHAN(false);
            var assetEntity = _accreditationRepoService.createAccreditation(asset);

            //Lưu thông tin Công tơ
            if (dto.getLstCongto() != null && dto.getLstCongto().size() > 0) {
                for (AccreditationDetailDto detail : dto.getLstCongto()) {
                    detail.setAccredId(uuid);
                    detail.setUser_cr_id(dto.getUser_cr_id());
                    this.CreateAccreditationDetail(detail, AF_OTHER.AF_TYPE.KDCT.toString());
                }
            }

            //Lưu thông tin TI
            if (dto.getLstTI() != null && dto.getLstTI().size() > 0) {
                //List kết quả kiểm định TI
                var listKQTI = new ArrayList<AccreditationResultTIDto>();

                for (AccreditationDetailDto detail : dto.getLstTI()) {
                    detail.setAccredId(uuid);
                    detail.setUser_cr_id(dto.getUser_cr_id());

                    if (detail.getLstKetQuaKiemDinhTI() != null)
                        listKQTI.addAll(detail.getLstKetQuaKiemDinhTI().getLstAccreditationResultTITempDto());

                    //Lọc kết quả kiểm định theo thiết bị
                    Predicate<AccreditationResultTIDto> byAssetId = TUResult -> TUResult.getAssetId().equals(detail.getAssetId());
                    if (listKQTI.size() > 0) {
                        var result = listKQTI.stream().filter(byAssetId).collect(Collectors.toList());
                        detail.setLstKetQuaKiemDinhTI(new AccreditationResultTIDataDto());
                        detail.getLstKetQuaKiemDinhTI().setLstAccreditationResultTITempDto(result);
                    }

                    this.CreateAccreditationDetail(detail, AF_OTHER.AF_TYPE.KDTI.toString());
                }
            }

            //Lưu thông tin TU
            if (dto.getLstTU() != null && dto.getLstTU().size() > 0) {
                //List kết quả kiểm định TU

                List<AccreditationResultTUDto> listKQTU = new ArrayList<AccreditationResultTUDto>();

                for (AccreditationDetailDto detail : dto.getLstTU()) {
                    detail.setAccredId(uuid);
                    detail.setUser_cr_id(dto.getUser_cr_id());

                    if (detail.getLstKetQuaKiemDinhTU() != null)
                        listKQTU.addAll(detail.getLstKetQuaKiemDinhTU().getLstAccreditationResultTUTempDto());

                    //Lọc kết quả kiểm định theo thiết bị
                    Predicate<AccreditationResultTUDto> byAssetId = TUResult -> TUResult.getAssetId().equals(detail.getAssetId());
                    if (listKQTU.size() > 0) {
                        var result = listKQTU.stream().filter(byAssetId).collect(Collectors.toList());
                        detail.setLstKetQuaKiemDinhTU(new AccreditationResultTUDataDto());
                        detail.getLstKetQuaKiemDinhTU().setLstAccreditationResultTUTempDto(result);
                    }

                    this.CreateAccreditationDetail(detail, AF_OTHER.AF_TYPE.KDTU.toString());
                }
            }

            if (dto.getFileData() != null && dto.getFileData().size() > 0) {
                //Lưu filePath vào disk sau đó set lại filepath cho filedata trong các object
                for (FileData file : dto.getFileData()) {
                    if (file.getBase64().length() > 0) {
                        file.setFilePath(Base64ToFile(file, "ACCREDITATION/" + uuid));
                    }
                }
                //Lưu filePath vào db
                for (FileData file : dto.getFileData()) {
                    AF_OTHER entity = new AF_OTHER();
                    entity.setAfFileId(UUID.randomUUID().toString());
                    entity.setCrdTime(new Date());
                    entity.setUserId(dto.getUser_cr_id());
                    entity.setObjId(uuid);
                    entity.setOrgId("0");
                    entity.setObjTypeId(AF_OTHER.AF_TYPE.KD.toString());
                    entity.setFileSizeB((int) file.getSize());
                    entity.setFilePath(file.getFilePath());
                    entity.setFileName(file.getFileName());
                    afOtherRepository.save(entity);
                }
            }
            String sql;
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String dateStr = dateFormat.format(dto.getAccredtationEndDate());
            sql = "UPDATE [dbo].[A_ASSET] SET DATEACCREDITATION = '" + dateStr + "' WHERE ASSETID IN  (SELECT ASSETID FROM [dbo].[M_ACCREDITATION_DETAILS] WHERE ACCREDID ='" + uuid + "')";
            jdbcTemplate.execute(sql);

            //lưu his
            dto.getLstCongto().sort(Comparator.comparing(AccreditationDetailDto::getAssetDesc));
            dto.getLstTU().sort(Comparator.comparing(AccreditationDetailDto::getAssetDesc));
            dto.getLstTI().sort(Comparator.comparing(AccreditationDetailDto::getAssetDesc));

            dto.setChitietCT(dto.getLstCongto().stream().map(x -> x.getAssetDesc()).collect(Collectors.joining(", ")));
            dto.setChitietTU(dto.getLstTU().stream().map(x -> x.getAssetDesc()+ (x.getPha() != null ? " pha "+x.getPha() : "")).collect(Collectors.joining(", ")));
            dto.setChitietTI(dto.getLstTI().stream().map(x -> x.getAssetDesc()+ (x.getPha() != null ? " pha "+x.getPha() : "")).collect(Collectors.joining(", ")));

            AccreditationDto dtohis = mapper.map("",AccreditationDto.class);
            List<String> ignoredFields = new ArrayList<>(){{
                add("is_delete");
                add("id");
                add("accreddesc");
                add("accredtationstartdate");
                add("accredtationenddate");
                add("accredtationtype");
                add("accredtationresult");
                add("note");
                add("user_mdf_id");
                add("nql_xnhan");
                add("ld_xnhan");add("filedata");
                add("lstfiledelete");
                add("categoryid");
                add("chitiet");
                add("lstcongto");add("lstcongtodelete");
                add("lsttu");add("lsttudelete");
                add("lstti");add("lsttidelete");
                add("assetid");add("user_cr_id");
            }};
            Map<String, String> variableNameMapping = new HashMap<>();
            variableNameMapping.put("batdaustr", "Thời gian thực hiện-Bắt đầu");
            variableNameMapping.put("ketthucstr", "Thời gian thực hiện-Kết thúc");
            variableNameMapping.put( "hinhthuc","Hình thức");
            variableNameMapping.put( "ten","Tên NMĐ/TBA/RGL");
            variableNameMapping.put( "kieu","Kiểu");
            variableNameMapping.put( "chitietketquathuchien","Chi tiết");
            variableNameMapping.put( "chitietct","Đối tượng kiểm định công tơ");
            variableNameMapping.put( "chitiettu","Đối tượng kiểm định TU");
            variableNameMapping.put( "chitietti","Đối tượng kiểm định TI");
            _wonderHisService.UpdateHis(uuid, dto,dtohis,ignoredFields,variableNameMapping, dto.getUser_cr_id(), "M_ACCREDITATION", "INS");

            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
        } catch (Exception ignored) {
            if (dto.getFileData() != null && dto.getFileData().size() > 0) {
                DelAllFile(dto.getFileData());
            }
            throw ignored;
        }
        return response;
    }

    @Override
    public ResponseData CreateAccreditationDetail(AccreditationDetailDto dto, String type) throws Exception {
        ResponseData response = new ResponseData();
        try {

            var createDate = new Date();
            var uuid = UUID.randomUUID().toString();
            M_ACCREDITATION_DETAILS asset = new M_ACCREDITATION_DETAILS();
            asset.setACCREDDETAILID(uuid);
            asset.setACCREDID(dto.getAccredId());
            asset.setASSETID(dto.getAssetId());
            asset.setACCRED_RESULT(dto.getAccred_Result());
            asset.setPHA(dto.getPha());
            var assetEntity = _accreditationRepoService.createAccreditationDetail(asset);

            if (dto.getLstKetQuaKiemDinhCT() != null && dto.getLstKetQuaKiemDinhCT().getLstAccreditationResultMeterTempDto() != null && dto.getLstKetQuaKiemDinhCT().getLstAccreditationResultMeterTempDto().size() > 0) {
                dto.getLstKetQuaKiemDinhCT().setAccredDetailId(uuid);
                createAccreditationResultMeter(dto.getLstKetQuaKiemDinhCT());
            }
            if (dto.getLstKetQuaKiemDinhTI() != null && dto.getLstKetQuaKiemDinhTI().getLstAccreditationResultTITempDto() != null && dto.getLstKetQuaKiemDinhTI().getLstAccreditationResultTITempDto().size() > 0) {
                dto.getLstKetQuaKiemDinhTI().setAccredDetailId(uuid);
                createAccreditationResultTI(dto.getLstKetQuaKiemDinhTI());
            }
            if (dto.getLstKetQuaKiemDinhTU() != null && dto.getLstKetQuaKiemDinhTU().getLstAccreditationResultTUTempDto() != null && dto.getLstKetQuaKiemDinhTU().getLstAccreditationResultTUTempDto().size() > 0) {
                dto.getLstKetQuaKiemDinhTU().setAccredDetailId(uuid);
                createAccreditationResultTU(dto.getLstKetQuaKiemDinhTU());


            }


            //Lưu filePath vào disk sau đó set lại filepath cho filedata trong các object
            if (dto.getLstFile() != null && dto.getLstFile().size() > 0) {
                for (FileData file : dto.getLstFile()) {
                    if (file.getBase64().length() > 0) {
                        file.setFilePath(Base64ToFile(file, "ACCREDITATION/" + dto.getAccredId() + "/ACCREDITATION_DETAIL/" + uuid));
                    }
                }
                //Lưu filePath vào db
                for (FileData file : dto.getLstFile()) {
                    AF_OTHER entity = new AF_OTHER();
                    entity.setAfFileId(UUID.randomUUID().toString());
                    entity.setCrdTime(new Date());
                    entity.setUserId(dto.getUser_cr_id());
                    entity.setObjId(uuid);
                    entity.setOrgId("0");
                    entity.setObjTypeId(type);
                    entity.setFileSizeB((int) file.getSize());
                    entity.setFilePath(file.getFilePath());
                    entity.setFileName(file.getFileName());
                    afOtherRepository.save(entity);
                }
            }
        } catch (Exception ignored) {
            if (dto.getLstFile() != null && dto.getLstFile().size() > 0) {
                DelAllFile(dto.getLstFile());
            }
            throw ignored;
        }
        return response;
    }

//    @Override
//    public ResponseData deleteAccreditationDetailMeter(String accDetailId) {
//        ResponseData response = new ResponseData();
//
//        try {
//
//            //Xóa data ở các bảng tạm
//            String sql;
//            sql = "DELETE FROM [dbo].M_ACCREDITATION_RESULT_METER WHERE ACCREDDETAILID = '" + accDetailId + "';";
//            sql += "DELETE FROM [dbo].M_ACCREDITATION_DETAILS WHERE ACCREDDETAILID = '" + accDetailId + "';";
//
//            jdbcTemplate.execute(sql);
//
//            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
//            response.setState(ResponseData.STATE.OK.toString());
//        } catch (Exception ex) {
//            response.setState(ResponseData.STATE.FAIL.toString());
//            response.setMessage(ex.getMessage());
//            response.setData(null);
//        }
//        return response;
//    }
//
//    @Override
//    public ResponseData deleteAccreditationDetailTU(String accDetailId) {
//        ResponseData response = new ResponseData();
//
//        try {
//
//            //Xóa data ở các bảng tạm
//            String sql;
//            sql = "DELETE FROM [dbo].M_ACCREDITATION_RESULT_TU WHERE ACCREDDETAILID = '" + accDetailId + "';";
//            sql += "DELETE FROM [dbo].M_ACCREDITATION_DETAILS WHERE ACCREDDETAILID = '" + accDetailId + "';";
//
//            jdbcTemplate.execute(sql);
//
//            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
//            response.setState(ResponseData.STATE.OK.toString());
//        } catch (Exception ex) {
//            response.setState(ResponseData.STATE.FAIL.toString());
//            response.setMessage(ex.getMessage());
//            response.setData(null);
//        }
//        return response;
//    }
//
//    @Override
//    public ResponseData deleteAccreditationDetailTI(String accDetailId) {
//        ResponseData response = new ResponseData();
//        try {
//
//            //Xóa data ở các bảng tạm
//            String sql;
//            sql = "DELETE FROM [dbo].M_ACCREDITATION_RESULT_TI WHERE ACCREDDETAILID = '" + accDetailId + "';";
//            sql += "DELETE FROM [dbo].M_ACCREDITATION_DETAILS WHERE ACCREDDETAILID = '" + accDetailId + "';";
//
//            jdbcTemplate.execute(sql);
//
//            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
//            response.setState(ResponseData.STATE.OK.toString());
//        } catch (Exception ex) {
//            response.setState(ResponseData.STATE.FAIL.toString());
//            response.setMessage(ex.getMessage());
//            response.setData(null);
//        }
//        return response;
//    }

    @Override
    public ResponseData createAccreditationResultTI(AccreditationResultTIDataDto dto) {
        ResponseData response = new ResponseData();
        try {
//            //Xóa data cũ trước khi thêm mới
//            String sql;
//            sql = "DELETE FROM [dbo].[M_ACCREDITATION_RESULT_TI_TEMP] WHERE [TRANSID] = '" + dto.getTransId() + "' and [ACCREDDETAILID] = '" + dto.getAccredDetailId() + "'";
//            jdbcTemplate.execute(sql);

            var ngayCN = new Date();

            for (AccreditationResultTIDto item : dto.getLstAccreditationResultTITempDto()) {
                var uuid = UUID.randomUUID().toString();
                M_ACCREDITATION_RESULT_TI asset = new M_ACCREDITATION_RESULT_TI();
                asset.setMACCREDTIID(uuid);
                asset.setACCREDDETAILID(dto.getAccredDetailId());
                asset.setCCX(item.getCcx());
                asset.setDVI_TND(dto.getDvi_tnd());
                asset.setTISO(item.getTsb());
                asset.setTAI(item.getDungLuong());
                asset.setUF1(item.getUf1());
                asset.setUSSG1(item.getUssg1());
                asset.setUF5(item.getUf5());
                asset.setUSSG5(item.getUssg5());
                asset.setUF20(item.getUf20());
                asset.setUSSG20(item.getUssg20());
                asset.setUF100(item.getUf100());
                asset.setUSSG100(item.getUssg100());
                asset.setUF120(item.getUf120());
                asset.setUSSG120(item.getUssg120());
                asset.setNGUOI_CNHAT(dto.getNguoi_cnhat());
                asset.setNGAY_CNHAT(ngayCN);
                asset.setPHA(item.getPha());
                asset.setSERIALNUM(item.getSoCheTao());
                asset.setCUON_DNOI(item.getCuonDauNoi());


                List<Double> lst = new ArrayList<>();
                String sql1;
                sql1 = "SELECT TOP 1 F1 FROM [dbo].[ViewLimitTi] where  CCX= ? and Capacity =? ";
                lst = jdbcTemplate.queryForList(sql1, Double.class, 0.2, item.getDungLuong());


                if (!lst.isEmpty()) {
                    Double result = lst.get(0);

                    if (item.getUf1() != null) {
                        if (( Math.round(abs(abs(item.getUf1()) - abs(result))*100)/100  ) <= Math.round((result * 10 / 100)*100)/100)
                        {
                            asset.setDeltaTi1((abs(abs(item.getUf1()) - abs(result))));
                            asset.setDeltaMauF1(result);

                        }
                    }


                }
                String sql2;
                sql2 = "SELECT TOP 1 F5 FROM [dbo].[ViewLimitTi] where  CCX= ? and Capacity =? ";
                lst = jdbcTemplate.queryForList(sql2, Double.class, item.getCcx(), item.getDungLuong());


                if (!lst.isEmpty()) {
                    Double result = lst.get(0);
                    if (item.getUf5() != null) {
                        if (( Math.round(abs(abs(item.getUf5()) - abs(result))*100)/100  ) <= Math.round((result * 10 / 100)*100)/100) {
                            asset.setDeltaTi2((abs(abs(item.getUf5()) - abs(result))));
                            asset.setDeltaMauF5(result);

                        }
                    }

                }
                String sql3;
                sql3 = "SELECT TOP 1 F20 FROM [dbo].[ViewLimitTi] where  CCX= ? and Capacity =? ";
                lst = jdbcTemplate.queryForList(sql3, Double.class, item.getCcx(), item.getDungLuong());


                if (!lst.isEmpty()) {
                    Double result = lst.get(0);

                    if (item.getUf20() != null) {
                        if (( Math.round((abs(abs(item.getUf20()) - abs(result)))*100)/100  ) <= Math.round((result * 10 / 100)*100)/100)
                        {
                            asset.setDeltaTi3((abs(abs(item.getUf20()) - abs(result))));
                            asset.setDeltaMauF20(result);

                        }
                    }

                }
                String sql4;
                sql4 = "SELECT TOP 1 F100 FROM [dbo].[ViewLimitTi] where  CCX= ? and Capacity =? ";
                lst = jdbcTemplate.queryForList(sql4, Double.class, item.getCcx(), item.getDungLuong());


                if (!lst.isEmpty()) {
                    Double result = lst.get(0);

                    if (item.getUf100() != null) {
                        if ((Math.round( (abs(abs(item.getUf100()) - abs(result)))  *100)/100  ) <= Math.round( (result * 10 / 100) *100)/100)
                         {
                            asset.setDeltaTi4((abs(abs(item.getUf100()) - abs(result))));
                            asset.setDeltaMauF100(result);

                        }
                    }

                }
                String sql5;
                sql5 = "SELECT TOP 1 F120 FROM [dbo].[ViewLimitTi] where  CCX= ? and Capacity =? ";
                lst = jdbcTemplate.queryForList(sql5, Double.class, item.getCcx(), item.getDungLuong());


                if (!lst.isEmpty()) {
                    Double result = lst.get(0);

                    if (item.getUf120() != null) {
                        if ((Math.round( (abs(abs(item.getUf120()) - abs(result)))  *100)/100  ) <= Math.round( (result * 10 / 100) *100)/100)
                         {
                            asset.setDeltaTi5((abs(abs(item.getUf120()) - abs(result))));
                            asset.setDeltaMauF120(result);

                        }
                    }

                }
                String sql6;
                sql6 = "SELECT TOP 1 Delta1 FROM [dbo].[ViewLimitTi] where  CCX= ? and Capacity =? ";
                lst = jdbcTemplate.queryForList(sql6, Double.class, item.getCcx(), item.getDungLuong());


                if (!lst.isEmpty()) {
                    Double result = lst.get(0);

                    if (item.getUssg1() != null) {
                        if ((Math.round( (abs(abs(item.getUssg1()) - abs(result)))  *100)/100  ) <= Math.round( (result * 10 / 100) *100)/100)
                        {
                            asset.setDeltaTi6((abs(abs(item.getUssg1()) - abs(result))));
                            asset.setDeltaMauSG1(result);

                        }
                    }

                }
                String sql7;
                sql7 = "SELECT TOP 1 Delta5 FROM [dbo].[ViewLimitTi] where  CCX= ? and Capacity =? ";
                lst = jdbcTemplate.queryForList(sql7, Double.class, item.getCcx(), item.getDungLuong());


                if (!lst.isEmpty()) {
                    Double result = lst.get(0);

                    if (item.getUssg5() != null) {
                        if ((Math.round( (abs(abs(item.getUssg5()) - abs(result)))  *100)/100  ) <= Math.round( (result * 10 / 100) *100)/100)
                        {
                            asset.setDeltaTi7((abs(abs(item.getUssg5()) - abs(result))));
                            asset.setDeltaMauSG5(result);

                        }
                    }

                }
                String sql8;
                sql8 = "SELECT TOP 1 Delta20 FROM [dbo].[ViewLimitTi] where  CCX= ? and Capacity =? ";
                lst = jdbcTemplate.queryForList(sql8, Double.class, item.getCcx(), item.getDungLuong());


                if (!lst.isEmpty()) {
                    Double result = lst.get(0);

                    if (item.getUssg20() != null) {
                        if ((Math.round( (abs(abs(item.getUssg20()) - abs(result))) *100)/100  ) <= Math.round( (result * 10 / 100) *100)/100)
                        {
                            asset.setDeltaTi8((abs(abs(item.getUssg20()) - abs(result))));
                            asset.setDeltaMauSG20(result);

                        }
                    }

                }
                String sql9;
                sql9 = "SELECT TOP 1 Delta100 FROM [dbo].[ViewLimitTi] where  CCX= ? and Capacity =? ";
                lst = jdbcTemplate.queryForList(sql9, Double.class, item.getCcx(), item.getDungLuong());


                if (!lst.isEmpty()) {
                    Double result = lst.get(0);

                    if (item.getUssg100() != null) {
                        if ((Math.round( (abs(abs(item.getUssg100()) - abs(result))) *100)/100  ) <= Math.round( (result * 10 / 100) *100)/100)
                         {
                            asset.setDeltaTi9((abs(abs(item.getUssg100()) - abs(result))));
                            asset.setDeltaMauSG100(result);

                        }
                    }

                }
                String sql10;
                sql10 = "SELECT TOP 1 Delta120 FROM [dbo].[ViewLimitTi] where  CCX= ? and Capacity =? ";
                lst = jdbcTemplate.queryForList(sql10, Double.class, item.getCcx(), item.getDungLuong());


                if (!lst.isEmpty()) {
                    Double result = lst.get(0);

                    if (item.getUssg120() != null) {
                        if ((Math.round( (abs(abs(item.getUssg120()) - abs(result))) *100)/100  ) <= Math.round( (result * 10 / 100) *100)/100)
                        {
                            asset.setDeltaTi10((abs(abs(item.getUssg120()) - abs(result))));
                            asset.setDeltaMauSG120(result);

                        }
                    }

                }
                var assetEntity = _accreditationResultTIRepository.saveAndFlush(asset);
            }

            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
        } catch (Exception ignored) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setData(null);
        }
        return response;
    }

    @Override
    public ResponseData createAccreditationResultTU(AccreditationResultTUDataDto dto) {
        ResponseData response = new ResponseData();
        try {
//            //Xóa data cũ trước khi thêm mới
//            String sql;
//            sql = "DELETE FROM [dbo].[M_ACCREDITATION_RESULT_TU_TEMP] WHERE [TRANSID] = '" + dto.getTransId() + "' and [ACCREDDETAILID] = '" + dto.getAccredDetailId() + "'";
//
//            jdbcTemplate.execute(sql);

            var ngayCN = new Date();

            for (AccreditationResultTUDto item : dto.getLstAccreditationResultTUTempDto()) {
                var uuid = UUID.randomUUID().toString();
                M_ACCREDITATION_RESULT_TU asset = new M_ACCREDITATION_RESULT_TU();
                asset.setMACCREDTUID(uuid);
                asset.setACCREDDETAILID(dto.getAccredDetailId());
                asset.setCCX(item.getCcx());
                asset.setDVI_TND(dto.getDvi_tnd());
                asset.setTISO(item.getTsb());
                asset.setDUNGLUONG(item.getDungLuong());
                asset.setUF80(item.getUf80());
                asset.setUSSG80(item.getUssg80());
                asset.setUF100(item.getUf100());
                asset.setUSSG100(item.getUssg100());
                asset.setUF120(item.getUf120());
                asset.setUSSG120(item.getUssg120());
                asset.setNGUOI_CNHAT(dto.getNguoi_cnhat());
                asset.setNGAY_CNHAT(ngayCN);
                asset.setPHA(item.getPha());
                asset.setSERIALNUM(item.getSoCheTao());
                asset.setCUON_DNOI(item.getCuonDauNoi());
                List<Double> lst = new ArrayList<>();
                String sql1;
                sql1 = "SELECT TOP 1 F FROM [dbo].[ViewLimitTu] where  CCX= ? and Capacity =? ";
                lst = jdbcTemplate.queryForList(sql1, Double.class, item.getCcx(), item.getDungLuong());


                if (!lst.isEmpty()) {
                    Double result = lst.get(0);

                    if (item.getUf80() != null) {
                        if ((Math.round( (abs(abs(item.getUf80()) - abs(result))) *100)/100  ) <= Math.round( (result * 10 / 100) *100)/100)
                        {
                            asset.setDeltaTu1((abs(abs(item.getUf80()) - abs(result))));
                            asset.setDeltaMau(result);

                        }
                    }


                }
                String sql2;
                sql2 = "SELECT TOP 1 F FROM [dbo].[ViewLimitTu] where  CCX= ? and Capacity =? ";
                lst = jdbcTemplate.queryForList(sql2, Double.class, item.getCcx(), item.getDungLuong());


                if (!lst.isEmpty()) {
                    Double result = lst.get(0);

                    if (item.getUf100() != null) {
                        if ((Math.round( (abs(abs(item.getUf100()) - abs(result))) *100)/100  ) <= Math.round( (result * 10 / 100) *100)/100)
                         {
                            asset.setDeltaTu2((abs(abs(item.getUf100()) - abs(result))));
                            asset.setDeltaMau(result);

                        }
                    }

                }
                String sql3;
                sql3 = "SELECT TOP 1 F FROM [dbo].[ViewLimitTu] where  CCX= ? and Capacity =? ";
                lst = jdbcTemplate.queryForList(sql3, Double.class, item.getCcx(), item.getDungLuong());


                if (!lst.isEmpty()) {
                    Double result = lst.get(0);

                    if (item.getUf120() != null) {
                        if ((Math.round( (abs(abs(item.getUf120()) - abs(result))) *100)/100  ) <= Math.round( (result * 10 / 100) *100)/100)
                         {
                            asset.setDeltaTu3((abs(abs(item.getUf120()) - abs(result))));
                            asset.setDeltaMau(result);

                        }
                    }

                }
                String sql4;
                sql4 = "SELECT TOP 1 Delta FROM [dbo].[ViewLimitTu] where  CCX= ? and Capacity =? ";
                lst = jdbcTemplate.queryForList(sql4, Double.class, item.getCcx(), item.getDungLuong());


                if (!lst.isEmpty()) {
                    Double result = lst.get(0);

                    if (item.getUssg80() != null) {
                        if ((Math.round( (abs(abs(item.getUssg80()) - abs(result))) *100)/100  ) <= Math.round( (result * 10 / 100) *100)/100)
                        {
                            asset.setDeltaTu4((abs(abs(item.getUssg80()) - abs(result))));
                            asset.setDeltaMauM(result);

                        }
                    }

                }
                String sql5;
                sql5 = "SELECT TOP 1 Delta FROM [dbo].[ViewLimitTu] where  CCX= ? and Capacity =? ";
                lst = jdbcTemplate.queryForList(sql5, Double.class, item.getCcx(), item.getDungLuong());


                if (!lst.isEmpty()) {
                    Double result = lst.get(0);

                    if (item.getUssg100() != null) {
                        if ((Math.round( (abs(abs(item.getUssg100()) - abs(result))) *100)/100  ) <= Math.round( (result * 10 / 100) *100)/100)
                         {
                            asset.setDeltaTu5((abs(abs(item.getUssg100()) - abs(result))));
                            asset.setDeltaMauM(result);

                        }
                    }

                }
                String sql6;
                sql6 = "SELECT TOP 1 Delta FROM [dbo].[ViewLimitTu] where  CCX= ? and Capacity =? ";
                lst = jdbcTemplate.queryForList(sql6, Double.class, item.getCcx(), item.getDungLuong());


                if (!lst.isEmpty()) {
                    Double result = lst.get(0);

                    if (item.getUssg120() != null) {
                        if ((Math.round( (abs(abs(item.getUssg120()) - abs(result))) *100)/100  ) <= Math.round( (result * 10 / 100) *100)/100)
                         {
                            asset.setDeltaTu6((abs(abs(item.getUssg120()) - abs(result))));
                            asset.setDeltaMauM(result);

                        }
                    }

                }
                var assetEntity = _accreditationResultTURepository.saveAndFlush(asset);
            }

            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
        } catch (Exception ignored) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setData(null);
        }
        return response;
    }

    @Override
    public ResponseData createAccreditationResultMeter(AccreditationResultMeterDataDto dto) {
        ResponseData response = new ResponseData();
        try {
//            //Xóa data cũ trước khi thêm mới
//            String sql;
//            sql = "DELETE FROM [dbo].[M_ACCREDITATION_RESULT_METER_TEMP] WHERE [TRANSID] = '" + dto.getTransId() + "' and [ACCREDDETAILID] = '" + dto.getAccredDetailId() + "'";
//
//            jdbcTemplate.execute(sql);

            var ngayCN = new Date();

            for (AccreditationResultMeterDto item : dto.getLstAccreditationResultMeterTempDto()) {
                var uuid = UUID.randomUUID().toString();
                M_ACCREDITATION_RESULT_METER asset = new M_ACCREDITATION_RESULT_METER();
                asset.setMACCREDMETERTID(uuid);
                asset.setACCREDDETAILID(dto.getAccredDetailId());
                asset.setCCX(dto.getCcx());
                asset.setDVI_TND(dto.getDvi_tnd());
                asset.setDIENAP(item.getDienAp());
                asset.setTAI_IN(item.getTaiIn());
                asset.setPHA(item.getPha());
                asset.setCOSPHI(item.getCosPhi());
                asset.setWH_GIAO(item.getWhGiao());
                asset.setWH_NHAN(item.getWhNhan());
                asset.setVARH_GIAO(item.getVarhGiao());
                asset.setVARH_NHAN(item.getVarhNhan());
                asset.setNGUOI_CNHAT(item.getNguoi_cnhat());
                asset.setNGAY_CNHAT(ngayCN);
                asset.setROW_INDEX(item.getIndex());
                List<Double> lst = new ArrayList<>();

                if (dto.getCcx().equals("1")) {
                    String sql1;
                    sql1 = "SELECT TOP 1 QUANTITYLIMIT02 FROM [dbo].[S_CONG_TO_ERROR_LIMIT]  where IDX =?";
                    lst = jdbcTemplate.queryForList(sql1, Double.class, item.getIndex());
                }
                if (dto.getCcx().equals("2")) {
                    String sql1;
                    sql1 = "SELECT TOP 1 QUANTITYLIMIT05 FROM [dbo].[S_CONG_TO_ERROR_LIMIT] where IDX =?";
                    lst = jdbcTemplate.queryForList(sql1, Double.class, item.getIndex());
                }
                if (dto.getCcx().equals("3")) {
                    String sql1;
                    sql1 = "SELECT TOP 1 QUANTITYLIMIT02 FROM [dbo].[S_CONG_TO_ERROR_LIMIT] where IDX =?";
                    lst = jdbcTemplate.queryForList(sql1, Double.class, item.getIndex());
                }
                if (dto.getCcx().equals("4")) {
                    String sql1;
                    sql1 = "SELECT TOP 1 QUANTITYLIMIT05 FROM [dbo].[S_CONG_TO_ERROR_LIMIT]  where IDX =?";
                    lst = jdbcTemplate.queryForList(sql1, Double.class, item.getIndex());
                }
                if (dto.getCcx().equals("5")) {
                    String sql1;
                    sql1 = "SELECT TOP 1 QUANTITYLIMIT1 FROM [dbo].[S_CONG_TO_ERROR_LIMIT]   where IDX =?";
                    lst = jdbcTemplate.queryForList(sql1, Double.class, item.getIndex());
                }

                if (!lst.isEmpty()) {
                    Double result = lst.get(0);

                    if (item.getWhGiao() != null) {
                        if ((Math.round( (abs(abs(item.getWhGiao()) - abs(result))) *100)/100  ) <= Math.round( (result * 10 / 100) *100)/100)
                        {
                            asset.setDeltaCt1((abs(abs(item.getWhGiao()) - abs(result))));
                            asset.setDeltaMau(result);

                        }
                    }

                }
                List<Double> lst2 = new ArrayList<>();

                if (dto.getCcx().equals("1")) {
                    String sql2;
                    sql2 = "SELECT TOP 1 QUANTITYLIMIT02 FROM [dbo].[S_CONG_TO_ERROR_LIMIT]  where IDX =?";
                    lst2 = jdbcTemplate.queryForList(sql2, Double.class, item.getIndex());
                }
                if (dto.getCcx().equals("2")) {
                    String sql2;
                    sql2 = "SELECT TOP 1 QUANTITYLIMIT05 FROM [dbo].[S_CONG_TO_ERROR_LIMIT]  where IDX =?";
                    lst2 = jdbcTemplate.queryForList(sql2, Double.class, item.getIndex());
                }
                if (dto.getCcx().equals("3")) {
                    String sql2;
                    sql2 = "SELECT TOP 1 QUANTITYLIMIT02 FROM [dbo].[S_CONG_TO_ERROR_LIMIT] where IDX =?";
                    lst2 = jdbcTemplate.queryForList(sql2, Double.class, item.getIndex());
                }
                if (dto.getCcx().equals("4")) {
                    String sql2;
                    sql2 = "SELECT TOP 1 QUANTITYLIMIT05 FROM [dbo].[S_CONG_TO_ERROR_LIMIT] where IDX =?";
                    lst2 = jdbcTemplate.queryForList(sql2, Double.class, item.getIndex());
                }
                if (dto.getCcx().equals("5")) {
                    String sql2;
                    sql2 = "SELECT TOP 1 QUANTITYLIMIT1 FROM [dbo].[S_CONG_TO_ERROR_LIMIT]   where IDX =?";
                    lst2 = jdbcTemplate.queryForList(sql2, Double.class, item.getIndex());
                }

                if (!lst2.isEmpty()) {
                    Double result = lst2.get(0);
                    if (item.getWhNhan() != null) {
                        if ((Math.round( (abs(abs(item.getWhNhan()) - abs(result))) *100)/100  ) <= Math.round( (result * 10 / 100) *100)/100)
                         {
                            asset.setDeltaCt2((abs(abs(item.getWhNhan()) - abs(result))));
                            asset.setDeltaMau(result);

                        }
                    }
                }
                List<Double> lst3 = new ArrayList<>();


                if (dto.getCcx().equals("1")) {
                    String sql3;
                    sql3 = "SELECT TOP 1 QUANTITYLIMIT02 FROM [dbo].[S_CONG_TO_ERROR_LIMIT]  where IDX =?";
                    lst3 = jdbcTemplate.queryForList(sql3, Double.class, item.getIndex());
                }
                if (dto.getCcx().equals("2")) {
                    String sql3;
                    sql3 = "SELECT TOP 1 QUANTITYLIMIT05 FROM [dbo].[S_CONG_TO_ERROR_LIMIT]  where IDX =?";
                    lst3 = jdbcTemplate.queryForList(sql3, Double.class, item.getIndex());
                }
                if (dto.getCcx().equals("3")) {
                    String sql3;
                    sql3 = "SELECT TOP 1 QUANTITYLIMIT02 FROM [dbo].[S_CONG_TO_ERROR_LIMIT]  where IDX =?";
                    lst3 = jdbcTemplate.queryForList(sql3, Double.class, item.getIndex());
                }
                if (dto.getCcx().equals("4")) {
                    String sql3;
                    sql3 = "SELECT TOP 1 QUANTITYLIMIT05 FROM [dbo].[S_CONG_TO_ERROR_LIMIT]  where IDX =?";
                    lst3 = jdbcTemplate.queryForList(sql3, Double.class, item.getIndex());
                }
                if (dto.getCcx().equals("5")) {
                    String sql3;
                    sql3 = "SELECT TOP 1 QUANTITYLIMIT1 FROM [dbo].[S_CONG_TO_ERROR_LIMIT]   where IDX =?";
                    lst3 = jdbcTemplate.queryForList(sql3, Double.class, item.getIndex());
                }


                if (!lst3.isEmpty()) {
                    Double result = lst3.get(0);
                    if (item.getVarhGiao() != null) {
                        if ((Math.round( (abs(abs(item.getVarhGiao()) - abs(result))) *100)/100  ) <= Math.round( (result * 10 / 100) *100)/100)
                         {
                            asset.setDeltaCt3((abs(abs(item.getVarhGiao()) - abs(result))));
                            asset.setDeltaMau(result);

                        }
                    }


                }

                List<Double> lst4 = new ArrayList<>();

                if (dto.getCcx().equals("1")) {
                    String sql4;
                    sql4 = "SELECT TOP 1 QUANTITYLIMIT02 FROM [dbo].[S_CONG_TO_ERROR_LIMIT]  where IDX =?";
                    lst4 = jdbcTemplate.queryForList(sql4, Double.class, item.getIndex());
                }
                if (dto.getCcx().equals("2")) {
                    String sql4;
                    sql4 = "SELECT TOP 1 QUANTITYLIMIT05 FROM [dbo].[S_CONG_TO_ERROR_LIMIT]  where IDX =?";
                    lst4 = jdbcTemplate.queryForList(sql4, Double.class, item.getIndex());
                }
                if (dto.getCcx().equals("3")) {
                    String sql4;
                    sql4 = "SELECT TOP 1 QUANTITYLIMIT02 FROM [dbo].[S_CONG_TO_ERROR_LIMIT] where IDX =?";
                    lst4 = jdbcTemplate.queryForList(sql4, Double.class, item.getIndex());
                }
                if (dto.getCcx().equals("4")) {
                    String sql4;
                    sql4 = "SELECT TOP 1 QUANTITYLIMIT05 FROM [dbo].[S_CONG_TO_ERROR_LIMIT]  where IDX =?";
                    lst4 = jdbcTemplate.queryForList(sql4, Double.class, item.getIndex());
                }
                if (dto.getCcx().equals("5")) {
                    String sql4;
                    sql4 = "SELECT TOP 1 QUANTITYLIMIT1 FROM [dbo].[S_CONG_TO_ERROR_LIMIT]   where IDX =?";
                    lst4 = jdbcTemplate.queryForList(sql4, Double.class, item.getIndex());
                }
                if (!lst4.isEmpty()) {
                    Double result = lst4.get(0);

                    if (item.getVarhNhan() != null) {
                        if ((Math.round( (abs(abs(item.getVarhNhan()) - abs(result))) *100)/100  ) <= Math.round( (result * 10 / 100) *100)/100)
                        {
                            asset.setDeltaCt4((abs(abs(item.getVarhNhan()) - abs(result))));
                            asset.setDeltaMau(result);

                        }
                    }
                }
                var assetEntity = _accreditationResultMeterRepository.saveAndFlush(asset);
            }

            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
        } catch (Exception ignored) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setData(null);
        }
        return response;
    }

    @Override
    public ResponseData deleteAccreditation(String id) {
        ResponseData response = new ResponseData();

        try {

            String sql;
            sql = "EXEC [dbo].[sp_Delete_M_ACCREDITATION] @accredId = N'" + id + "'";
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
            sql = "UPDATE [dbo].[M_ACCREDITATION] SET [NQL_XNHAN] = " + strStt + " WHERE [ACCREDID] in ('" + strId + "')";
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
            sql = "UPDATE [dbo].[M_ACCREDITATION] SET [LD_XNHAN] = " + strStt + " WHERE [ACCREDID] in ('" + strId + "')";
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
    public ResponseData UpdateAccreditationDetail(AccreditationDetailDto dto, String type) throws Exception {
        ResponseData response = new ResponseData();
        response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
        response.setState(ResponseData.STATE.OK.toString());
        List<FileData> newFileList = new ArrayList<FileData>();
        try {

            var existEntity = _accreditationDetailRepository.findById(dto.getAccredDetailId()).orElseThrow(() -> new IllegalArgumentException("Kiểm định không tồn tại"));

            var updateDate = new Date();
            existEntity.setPHA(dto.getPha());
            existEntity.setASSETID(dto.getAssetId());
            existEntity.setACCRED_RESULT(dto.getAccred_Result());
            var result = _accreditationDetailRepository.save(existEntity);
            //delete all ACCREDITATION_RESULT before
            var createDate = new Date();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String dateStr = dateFormat.format(createDate);
            if (dto.getLstKetQuaKiemDinhCT() != null && dto.getLstKetQuaKiemDinhCT().getLstAccreditationResultMeterTempDto() != null && dto.getLstKetQuaKiemDinhCT().getLstAccreditationResultMeterTempDto().size() > 0) {
                String sql = "DELETE FROM [dbo].M_ACCREDITATION_RESULT_METER WHERE ACCREDDETAILID = '" + dto.getAccredDetailId() + "';";
                jdbcTemplate.execute(sql);
                dto.getLstKetQuaKiemDinhCT().setAccredDetailId(dto.getAccredDetailId());
                createAccreditationResultMeter(dto.getLstKetQuaKiemDinhCT());
            }
            if (dto.getLstKetQuaKiemDinhTI() != null && dto.getLstKetQuaKiemDinhTI().getLstAccreditationResultTITempDto() != null && dto.getLstKetQuaKiemDinhTI().getLstAccreditationResultTITempDto().size() > 0) {
                String sql = "DELETE FROM [dbo].M_ACCREDITATION_RESULT_TI WHERE ACCREDDETAILID = '" + dto.getAccredDetailId() + "';";
                jdbcTemplate.execute(sql);
                dto.getLstKetQuaKiemDinhTI().setAccredDetailId(dto.getAccredDetailId());
                createAccreditationResultTI(dto.getLstKetQuaKiemDinhTI());
            }
            if (dto.getLstKetQuaKiemDinhTU() != null && dto.getLstKetQuaKiemDinhTU().getLstAccreditationResultTUTempDto() != null && dto.getLstKetQuaKiemDinhTU().getLstAccreditationResultTUTempDto().size() > 0) {
                String sql = "DELETE FROM [dbo].M_ACCREDITATION_RESULT_TU WHERE ACCREDDETAILID = '" + dto.getAccredDetailId() + "';";
                jdbcTemplate.execute(sql);
                dto.getLstKetQuaKiemDinhTU().setAccredDetailId(dto.getAccredDetailId());
                createAccreditationResultTU(dto.getLstKetQuaKiemDinhTU());
            }
            String sql = "UPDATE [dbo].[A_ASSET] SET DATEACCREDITATION = '" + dateStr + "' WHERE ASSETID  =   '" + dto.getAccredDetailId() + "'";
            jdbcTemplate.execute(sql);
            //File delete on db only for AccreditationDetail, it's file on disk will remove after all db update success in UpdateAccreditation
            if (dto.getLstFileDelete() != null && dto.getLstFileDelete().size() > 0) {
                for (var file : dto.getLstFileDelete()) {
                    String cmd;
                    cmd = "DELETE FROM [dbo].AF_OTHER WHERE AFFILEID = '" + file.getAfFileId() + "';";
                    jdbcTemplate.execute(cmd);
                }
            }
            //File add
            if (dto.getLstFile() != null && dto.getLstFile().size() > 0) {
                //Lưu filePath vào disk sau đó set lại filepath cho filedata trong các object
                for (FileData file : dto.getLstFile()) {
                    if (file.getBase64() != null && file.getBase64().length() > 0) {
                        file.setFilePath(Base64ToFile(file, "ACCREDITATION/" + dto.getAccredId() + "/ACCREDITATION_DETAIL/" + dto.getAccredDetailId()));
                        newFileList.add(file);
                    }
                }
                //Lưu file duoc them vào db
                for (FileData file : dto.getLstFile()) {
                    if (file.getBase64() != null && file.getBase64().length() > 0) {
                        AF_OTHER entity = new AF_OTHER();
                        entity.setAfFileId(UUID.randomUUID().toString());
                        entity.setCrdTime(new Date());
                        entity.setUserId(dto.getUser_cr_id());
                        entity.setObjId(dto.getAccredDetailId());
                        entity.setOrgId("0");
                        entity.setObjTypeId(type);
                        entity.setFileSizeB((int) file.getSize());
                        entity.setFilePath(file.getFilePath());
                        entity.setFileName(file.getFileName());
                        afOtherRepository.save(entity);
                    }
                }
            }
            return response;
        } catch (Exception ex) {

            if (newFileList != null && newFileList.size() > 0) {
                DelAllFile(newFileList);
            }
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ex.getMessage());
            response.setData(null);
            throw ex;
        }
    }

    @Override
    public ResponseData updateOrAddListAccreditationDetail(List<AccreditationDetailDto> dto, String AccreditationID, String type) throws Exception {

        ResponseData response = new ResponseData();
        response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
        response.setState(ResponseData.STATE.OK.toString());

        try {
            if (dto == null || dto.size() <= 0) return response;

            var listKQTI = new ArrayList<AccreditationResultTIDto>();
            var listKQTU = new ArrayList<AccreditationResultTUDto>();

            for (var item : dto) {

                if (item.getLstKetQuaKiemDinhTI() != null)
                    listKQTI.addAll(item.getLstKetQuaKiemDinhTI().getLstAccreditationResultTITempDto());

                if (item.getLstKetQuaKiemDinhTU() != null)
                    listKQTU.addAll(item.getLstKetQuaKiemDinhTU().getLstAccreditationResultTUTempDto());

                //Lọc kết quả kiểm định theo TI
                Predicate<AccreditationResultTIDto> byTIAssetId = TIResult -> TIResult.getAssetId().equals(item.getAssetId());
                if (listKQTI.size() > 0) {
                    var result = listKQTI.stream().filter(byTIAssetId).collect(Collectors.toList());
                    item.setLstKetQuaKiemDinhTI(new AccreditationResultTIDataDto());
                    item.getLstKetQuaKiemDinhTI().setLstAccreditationResultTITempDto(result);
                }

                //Lọc kết quả kiểm định theo TU
                Predicate<AccreditationResultTUDto> byTUAssetId = TUResult -> TUResult.getAssetId().equals(item.getAssetId());
                if (listKQTU.size() > 0) {
                    var result = listKQTU.stream().filter(byTUAssetId).collect(Collectors.toList());
                    item.setLstKetQuaKiemDinhTU(new AccreditationResultTUDataDto());
                    item.getLstKetQuaKiemDinhTU().setLstAccreditationResultTUTempDto(result);
                }

                //add
                item.setAccredId(AccreditationID);
                if (item.getAccredDetailId() == null || item.getAccredDetailId() == "")
                    this.CreateAccreditationDetail(item, type);
                    //update
                else {
                    this.UpdateAccreditationDetail(item, type);
                }
            }

            return response;
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ex.getMessage());
            response.setData(null);
            throw ex;
        }
    }

    @Override
    public ResponseData deleteAccreditationDetail(List<AccreditationDetailDto> dto, String type, String id) throws Exception {

        ResponseData response = new ResponseData();
        response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
        response.setState(ResponseData.STATE.OK.toString());

        try {
            if (dto == null || dto.size() <= 0) return response;
            String sql;

//            sql = "DELETE FROM [dbo].M_ACCREDITATION_RESULT_TI WHERE ACCREDDETAILID in (Select ACCREDDETAILID from [dbo].[M_ACCREDITATION_DETAILS] where ACCREDID = '" + id + "'); ";
//            sql += "DELETE FROM [dbo].M_ACCREDITATION_RESULT_TU WHERE ACCREDDETAILID in (Select ACCREDDETAILID from [dbo].[M_ACCREDITATION_DETAILS] where ACCREDID = '" + id + "'); ";
            for (var item : dto) {

                sql = "DELETE FROM [dbo].M_ACCREDITATION_RESULT_METER WHERE ACCREDDETAILID = '" + item.getAccredDetailId() + "';";
                sql += "DELETE FROM [dbo].M_ACCREDITATION_RESULT_TI WHERE ACCREDDETAILID = '" + item.getAccredDetailId() + "';";
                sql += "DELETE FROM [dbo].M_ACCREDITATION_RESULT_TU WHERE ACCREDDETAILID = '" + item.getAccredDetailId() + "';";
                sql += "DELETE FROM [dbo].AF_OTHER WHERE OBJID = '" + item.getAccredDetailId() + "';";
                sql += "DELETE FROM [dbo].M_ACCREDITATION_DETAILS WHERE ACCREDDETAILID = '" + item.getAccredDetailId() + "';";
                jdbcTemplate.execute(sql);

                // cập nhật thời gian kiểm định lần cuối của thiết bị
                String update_sql =
                        "UPDATE asset\n" +
                                "SET DATEACCREDITATION =  lastKD.END_DATE\n" +
                                "FROM (SELECT * FROM dbo.A_ASSET WHERE ASSETID = '" + item.getAssetId() + "' ) asset\n" +
                                "LEFT JOIN \n" +
                                "\t(SELECT\n" +
                                "\t\tM_ACCREDITATION_DETAILS.ASSETID\n" +
                                "\t\t,MAX(M_ACCREDITATION.ACCREDITATION_ENDDATE ) END_DATE\n" +
                                "\tFROM M_ACCREDITATION \n" +
                                "\tLEFT JOIN M_ACCREDITATION_DETAILS \n" +
                                "\tON M_ACCREDITATION.ACCREDID = M_ACCREDITATION_DETAILS.ACCREDID\n" +
                                "\tWHERE M_ACCREDITATION_DETAILS.ASSETID = '" + item.getAssetId() + "'\n" +
                                "\tGROUP BY M_ACCREDITATION_DETAILS.ASSETID\n" +
                                "\t) lastKD\n" +
                                "ON lastKD.ASSETID = asset.ASSETID\n;";
                jdbcTemplate.execute(update_sql);
            }

            return response;
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ex.getMessage());
            response.setData(null);
            throw ex;
        }
    }


}
