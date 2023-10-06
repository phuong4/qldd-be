package com.evnit.ttpm.AuthApp.service.category;

import java.util.*;

import com.evnit.ttpm.AuthApp.entity.category.A_ASSET;
import com.evnit.ttpm.AuthApp.entity.common.ALstType;
import com.evnit.ttpm.AuthApp.model.category.nhamaydien.SearchNMD;
import com.evnit.ttpm.AuthApp.model.category.tbargl.TbaRglCrudDto;
import com.evnit.ttpm.AuthApp.model.filter.SearchFilter;
import com.evnit.ttpm.AuthApp.model.filter.SearchQuery;
import com.evnit.ttpm.AuthApp.repository.category.A_ASSETRepository;
import com.evnit.ttpm.AuthApp.service.system.WonderHisService;
import com.evnit.ttpm.AuthApp.util.SpecificationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.category.SElectricFactory;
import com.evnit.ttpm.AuthApp.entity.category.ViewElectricFactory;
import com.evnit.ttpm.AuthApp.mapper.NhaMayDienMapper;
import com.evnit.ttpm.AuthApp.model.category.nhamaydien.NhaMayDienCrudDto;
import com.evnit.ttpm.AuthApp.model.category.nhamaydien.NhaMayDienListDto;
import com.evnit.ttpm.AuthApp.repository.category.NhaMayDienRepository;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.stream.Collectors;


@Service
public class SNhaMayDienServicelmpl implements SNhaMayDienService {
    @Autowired
    NhaMayDienRepository nhaMayDienRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    SNhaMayDienRepoService sNhaMayDienRepoService;
    @Autowired
    A_ASSETRepository aAssetRepository;
    @Autowired
    WonderHisService _wonderHisService;

    private final ModelMapper mapper;

    public SNhaMayDienServicelmpl(NhaMayDienMapper mapperConfig) {
        this.mapper = mapperConfig.getModelMapper();

    }

    @Override
    public ResponseData getAll() {
        ResponseData response = new ResponseData();
        try {
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            var listTbaRgl = nhaMayDienRepository.findAll().stream().filter(x -> x.getIs_Delete() == false).collect(Collectors.toList());
            var result = listTbaRgl.stream().map(tbaRgl -> mapper.map(tbaRgl, NhaMayDienListDto.class)).collect(Collectors.toList());
            response.setData(result);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ex.getMessage());
            response.setData(null);
        }
        return response;
    }

    @Override
    public ResponseData getList(SearchNMD dto) {
        ResponseData response = new ResponseData();
        SearchQuery searchQuery = new SearchQuery();
        List<NhaMayDienListDto> listNMD = new ArrayList<NhaMayDienListDto>();
        SearchFilter searchFilter = new SearchFilter();
        List<SearchFilter> searchFilters = new ArrayList<>();
        searchFilter = new SearchFilter("is_Delete", "=", false);
        searchFilters.add(searchFilter);
        if (dto.getTypeNM() != null && !dto.getTypeNM().isEmpty()) {
            searchFilter = new SearchFilter("typeNM", "IN", dto.getTypeNM());
            searchFilters.add(searchFilter);
        }
        if (dto.getTenNM() != null && !dto.getTenNM().isEmpty()) {
            searchFilter = new SearchFilter("tenNM", "LIKE", dto.getTenNM());
            searchFilters.add(searchFilter);
        }
        if (dto.getRegions() != null && !dto.getRegions().isEmpty()) {
            searchFilter = new SearchFilter("regions", "IN", dto.getRegions());
            searchFilters.add(searchFilter);
        }
        if (isNumeric(dto.getMaNM()) && dto.getMaNM() != null && !dto.getMaNM().isEmpty()) {
            searchFilter = new SearchFilter("congSuatMW", "=", dto.getMaNM(), "OR");
            searchFilters.add(searchFilter);
        }
        if (isNumeric(dto.getMaNM()) && dto.getMaNM() != null && !dto.getMaNM().isEmpty()) {
            searchFilter = new SearchFilter("congSuatMwp", "=", dto.getMaNM(), "OR");
            searchFilters.add(searchFilter);
        }
        if (dto.getMaNM() != null && !dto.getMaNM().isEmpty()) {
            searchFilter = new SearchFilter("cityStr", "LIKE", dto.getMaNM(), "OR");
            searchFilters.add(searchFilter);
        }
        if (dto.getMaNM() != null && !dto.getMaNM().isEmpty()) {
            searchFilter = new SearchFilter("maNM", "LIKE", dto.getMaNM(), "OR");
            searchFilters.add(searchFilter);
        }

        if (isNumeric(dto.getMaNM()) && dto.getMaNM() != null && !dto.getMaNM().isEmpty()) {
            searchFilter = new SearchFilter("congSuatMaxMW", "=", dto.getMaNM(), "OR");
            searchFilters.add(searchFilter);
        }

        if (dto.getStatus() != null && !dto.getStatus().isEmpty()) {
            searchFilter = new SearchFilter("status", "IN", dto.getStatus());
            searchFilters.add(searchFilter);
        }

        Pageable pageable = PageRequest.of(dto.getPage(), dto.getLimit(), dto.getSortExpression());
        searchQuery.setSearchFilters(searchFilters);
        Specification<ViewElectricFactory> spec = SpecificationUtil.bySearchQuery(searchQuery, ViewElectricFactory.class);
        try {
            var pageResult = sNhaMayDienRepoService.findAll(spec, pageable);
            var dtoResult = pageResult.getContent().stream().map(item -> mapper.map(item, NhaMayDienListDto.class)).collect(Collectors.toList());
            Page<NhaMayDienListDto> result = new PageImpl<NhaMayDienListDto>(dtoResult, pageable, pageResult.getTotalElements());

            response.setData(result);
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setState(ResponseData.STATE.OK.toString());
        } catch (Exception ignored) {
            response.setData(null);
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setState(ResponseData.STATE.FAIL.toString());
        }
        return response;
    }

    @Override
    public ResponseData getById(String id) {
        return null;
    }

    @Override
    public ResponseData create(NhaMayDienCrudDto createDto) {
        ResponseData response = new ResponseData();
        try {
            String s;
            s = "select 1 from S_Electric_Factory  where (MA_NM=? or Ten_NM=?) and IS_DELETE=0";
            List lst = jdbcTemplate.queryForList(s, createDto.getMaNM(), createDto.getTenNM());
            if (lst != null && !lst.isEmpty())
                return new ResponseData(ResponseData.STATE.FAIL.toString(), "Mã/Tên đã tồn tại, không thể thêm mới");
            var guid = UUID.randomUUID().toString();
            createDto.setId(guid);
            var entity = mapper.map(createDto, SElectricFactory.class);
            entity.setIs_Delete(false);
            var result = nhaMayDienRepository.saveAndFlush(entity);
            var id = result.getId();
            A_ASSET asset = new A_ASSET();
            asset.setASSETID(result.getId());
            asset.setTYPEID("NM");
            asset.setCATEGORYID("NM");
            asset.setUSER_CR_ID(createDto.getUserId());
            asset.setORGID("0");
            asset.setUSER_CR_DTIME(new Date());
            asset.setASSETDESC(createDto.getTenNM());
            asset.setUSESTATUS_LAST_ID(createDto.getStatus().toString());
            asset.setISDELETE(false);
            //lưu his
            List<String> ignoredFields = new ArrayList<>(){{
                add("is_delete");
                add("userid");
                add("datein");
                add("datecod");
                add("ownershipunit");
                add("managementunit");
                add("status");
                add("typenm");
                add("regions");
                add("id");
                add("listcreatetmold");
                add("listcreategpold");
                add("listcreatetm");
                add("listcreategp");
            }};
            Map<String, String> variableNameMapping = new HashMap<>();
            variableNameMapping.put("manm", "Mã NMĐ");
            variableNameMapping.put("tennm", "Tên nhà máy");
            variableNameMapping.put( "diachi","Địa điểm");
            variableNameMapping.put( "city","Tỉnh/Thành phố");
            variableNameMapping.put("ownershipunitstr", "Đơn vị sở hữu");
            variableNameMapping.put("manageunitstr", "Đơn vị quản lý vận hành");
            variableNameMapping.put("regionstr","Vùng miền");
            variableNameMapping.put( "statusstr","Trạng thái");
            variableNameMapping.put("congsuatmw","Công suất MW");
            variableNameMapping.put("congsuatmwp","Công suất MWP");
            variableNameMapping.put("congsuatmaxmw","Công suất max MW");
            variableNameMapping.put("dienapkv","Điện áp đầu nối kV");
            variableNameMapping.put("sanluong","Sản lượng bình quân (triệu kWh)");
            variableNameMapping.put("datecodstr","Ngày COD nhà máy");
            variableNameMapping.put("dateinstr","Ngày tham gia TTĐ");
            variableNameMapping.put("typenmstr","Loại hình nhà máy điện");
            variableNameMapping.put("type","Kiểu");
            _wonderHisService.UpdateHis(guid, createDto,"",ignoredFields,variableNameMapping,createDto.getUserId(), "S_Electric_Factory", "INS");
            aAssetRepository.save(asset);
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
    public ResponseData update(String id, NhaMayDienCrudDto updateDto) {
        ResponseData response = new ResponseData();
        try {
            String s;
            s = "select 1 from S_Electric_Factory WHERE  (MA_NM=? or Ten_NM=?)  and IS_DELETE=? and Id!=?";
            List lst = jdbcTemplate.queryForList(s, updateDto.getMaNM(), updateDto.getTenNM(), 0, id);
            if (lst != null && !lst.isEmpty())
                return new ResponseData(ResponseData.STATE.FAIL.toString(), "Mã/Tên đã tồn tại, không thể sửa");

            var existEntity = nhaMayDienRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Nhà máy điện không tồn tại"));
            //lưu his
            NhaMayDienCrudDto dto = mapper.map(existEntity,NhaMayDienCrudDto.class);
            String sql = "Select * from View_Electric_Factory where Id = '"+id+"'";
            List<NhaMayDienCrudDto> lst2 = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(NhaMayDienCrudDto.class));
            dto.setRegionStr(lst2.get(0).getRegionStr());
            dto.setDateCODStr(lst2.get(0).getDateCODStr());
            dto.setDateInStr(lst2.get(0).getDateInStr());
            dto.setOwnershipUnitStr(lst2.get(0).getOwnershipUnitStr());
            dto.setManageUnitStr(lst2.get(0).getManageUnitStr());
            dto.setStatusStr(lst2.get(0).getStatusStr());
            dto.setTypeNMStr(lst2.get(0).getTypeNMStr());
            List<String> ignoredFields = new ArrayList<>(){{
                add("is_delete");
                add("userid");
                add("datein");
                add("datecod");
                add("ownershipunit");
                add("managementunit");
                add("status");
                add("typenm");
                add("regions");
            }};
            Map<String, String> variableNameMapping = new HashMap<>();
            variableNameMapping.put("manm", "Mã NMĐ");
            variableNameMapping.put("tennm", "Tên nhà máy");
            variableNameMapping.put( "diachi","Địa điểm");
            variableNameMapping.put( "city","Tỉnh/Thành phố");
            variableNameMapping.put("ownershipunitstr", "Đơn vị sở hữu");
            variableNameMapping.put("manageunitstr", "Đơn vị quản lý vận hành");
            variableNameMapping.put("regionstr","Vùng miền");
            variableNameMapping.put( "statusstr","Trạng thái");
            variableNameMapping.put("congsuatmw","Công suất MW");
            variableNameMapping.put("congsuatmwp","Công suất MWP");
            variableNameMapping.put("congsuatmaxmw","Công suất max MW");
            variableNameMapping.put("dienapkv","Điện áp đầu nối kV");
            variableNameMapping.put("sanluong","Sản lượng bình quân (triệu kWh)");
            variableNameMapping.put("datecodstr","Ngày COD nhà máy");
            variableNameMapping.put("dateinstr","Ngày tham gia TTĐ");
            variableNameMapping.put("typenmstr","Loại hình nhà máy điện");




            _wonderHisService.UpdateHis(id, updateDto,dto,ignoredFields,variableNameMapping, updateDto.getUserId(), "S_Electric_Factory", "UPDATE");

            mapper.map(updateDto, existEntity);
            existEntity.setIs_Delete(false);
            A_ASSET asset = aAssetRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Nhà máy điện không tồn tại"));
            asset.setUSER_MDF_DTIME(new Date());
            asset.setUSER_MDF_ID(updateDto.getUserId());
            asset.setASSETDESC(updateDto.getTenNM());
            asset.setUSESTATUS_LAST_ID(updateDto.getStatus().toString());
            asset.setISDELETE(false);
            aAssetRepository.save(asset);


            var result = mapper.map(nhaMayDienRepository.save(existEntity), NhaMayDienListDto.class);
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
            String s1;
            s1 = "select 1 from View_QLDD where tbaid=? and isdelete=?  ";
            List lst1 = jdbcTemplate.queryForList(s1, id, 0);
            if (lst1 != null && !lst1.isEmpty())
                return new ResponseData(ResponseData.STATE.FAIL.toString(), "Đã có dữ liệu ràng buộc, không thể xóa");

            String s2;
            s2 = "select 1 from View_Problem where Tbaid=?   ";
            List lst2 = jdbcTemplate.queryForList(s2, id);
            if (lst2 != null && !lst2.isEmpty())
                return new ResponseData(ResponseData.STATE.FAIL.toString(), "Đã có dữ liệu ràng buộc, không thể xóa");

            String s3;
            s3 = "select 1 from VIEW_KIEM_DINH_TABLE where ASSETID=?   ";
            List lst3 = jdbcTemplate.queryForList(s3, id);
            if (lst3 != null && !lst3.isEmpty())
                return new ResponseData(ResponseData.STATE.FAIL.toString(), "Đã có dữ liệu ràng buộc, không thể xóa");

            String s4;
            s4 = "select 1 from View_Deal where Tbaid=?   ";
            List lst4 = jdbcTemplate.queryForList(s4, id);
            if (lst4 != null && !lst4.isEmpty())
                return new ResponseData(ResponseData.STATE.FAIL.toString(), "Đã có dữ liệu ràng buộc, không thể xóa");

            String s5;
            s5 = "select 1 from VIEW_NGHIEM_THU_FINAL where ASSETID=?";
            List lst5 = jdbcTemplate.queryForList(s5, id);
            if (lst5 != null && !lst5.isEmpty())
                return new ResponseData(ResponseData.STATE.FAIL.toString(), "Đã có dữ liệu ràng buộc, không thể xóa");

            var result = nhaMayDienRepository.findById(id).map(tbaRgl -> {
                tbaRgl.setIs_Delete(true);
                return nhaMayDienRepository.save(tbaRgl);
            }).orElseThrow(() -> new IllegalArgumentException("Nhà máy không tồn tại"));
            response.setData(result);
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setState(ResponseData.STATE.OK.toString());
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ex.getMessage());
            response.setData(null);
        }
        return response;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
}


