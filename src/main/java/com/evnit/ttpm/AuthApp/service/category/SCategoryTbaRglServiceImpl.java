package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.category.A_ASSET;
import com.evnit.ttpm.AuthApp.entity.category.SCategoryTbaRgl;
import com.evnit.ttpm.AuthApp.entity.category.ViewTbaRgl;
import com.evnit.ttpm.AuthApp.entity.category.v_DONVISH_PHANCAP;
import com.evnit.ttpm.AuthApp.entity.common.ALstType;
import com.evnit.ttpm.AuthApp.exception.NotFoundException;
import com.evnit.ttpm.AuthApp.mapper.TbaRglMapper;
import com.evnit.ttpm.AuthApp.model.category.DonViSH.DonViSHListDto;
import com.evnit.ttpm.AuthApp.model.category.DonViSH.SearchDonViSH;
import com.evnit.ttpm.AuthApp.model.category.TinhTP.TinhTPListDto;
import com.evnit.ttpm.AuthApp.model.category.tbargl.SearchTBAGRL;
import com.evnit.ttpm.AuthApp.model.category.tbargl.TbaRglCrudDto;
import com.evnit.ttpm.AuthApp.model.category.tbargl.TbaRglListDto;
import com.evnit.ttpm.AuthApp.model.filter.SearchFilter;
import com.evnit.ttpm.AuthApp.model.filter.SearchQuery;
import com.evnit.ttpm.AuthApp.repository.acceptance.AcceptanceRepository;
import com.evnit.ttpm.AuthApp.repository.accreditation.AccreditationRepository;
import com.evnit.ttpm.AuthApp.repository.category.*;
import com.evnit.ttpm.AuthApp.service.accreditation.AccreditationRepoService;
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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SCategoryTbaRglServiceImpl implements SCategoryTbaRglService {
    @Autowired
    TbaRglRepository tbaRglRepository;
    @Autowired
    TinhTPRepository tinhTPRepository;
    @Autowired
    DonViSHRepository donViSHRepository;
    @Autowired
    A_ASSETRepository aAssetRepository;
    @Autowired
    ViewTbaRglRepository viewTbaRglRepository;
    @Autowired
    SCategoryTbaRglRepoService tbaRglRepoService;
    @Autowired
    ThoaThuanRepository thoaThuanRepository;
    @Autowired
    AcceptanceRepository acceptanceRepository;
    @Autowired
    AccreditationRepository accreditationRepository;
    @Autowired
    SuCoRepository suCoRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    WonderHisService _wonderHisService;
    private final ModelMapper mapper;

    public SCategoryTbaRglServiceImpl(TbaRglMapper mapperConfig) {
        this.mapper = mapperConfig.getModelMapper();
    }

    @Override
    public ResponseData getAll() {
        ResponseData response = new ResponseData();
        try {
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            var listTbaRgl = tbaRglRepository.findAll().stream().filter(x -> !x.getIsDelete()).collect(Collectors.toList());
            var result = listTbaRgl.stream().map(tbaRgl -> mapper.map(tbaRgl, TbaRglListDto.class)).collect(Collectors.toList());
            response.setData(result);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ex.getMessage());
            response.setData(null);
        }
        return response;
    }

    @Override
    public ResponseData getListTBA(SearchTBAGRL dto) {
        ResponseData response = new ResponseData();
        SearchQuery searchQuery = new SearchQuery();
        List<TbaRglListDto> listTBA = new ArrayList<TbaRglListDto>();
        SearchFilter searchFilter = new SearchFilter();
        List<SearchFilter> searchFilters = new ArrayList<>();
        searchFilter = new SearchFilter("isDelete", "=", false);
        searchFilters.add(searchFilter);
        if (dto.getName() != null && !dto.getName().isEmpty()) {
            searchFilter = new SearchFilter("id", "IN", dto.getName());
            searchFilters.add(searchFilter);
        }

        if (dto.getType() != null && !dto.getType().isEmpty()) {
            searchFilter = new SearchFilter("type", "IN", dto.getType());
            searchFilters.add(searchFilter);
        }

        if (dto.getVoltageLevel() != null && !dto.getVoltageLevel().isEmpty()) {
            searchFilter = new SearchFilter("voltageLevel", "IN", dto.getVoltageLevel());
            searchFilters.add(searchFilter);
        }

        if (dto.getText() != null && !dto.getText().isEmpty()) {
            var textSearch = dto.getText().trim();
            searchFilter = new SearchFilter("tbaRglCode", "LIKE", textSearch, "OR");
            searchFilters.add(searchFilter);
            searchFilter = new SearchFilter("cityName", "LIKE", textSearch, "OR");
            searchFilters.add(searchFilter);
        }

        if (dto.getStatus() != null && !dto.getStatus().isEmpty()) {
            searchFilter = new SearchFilter("tbaRglStatus", "IN", dto.getStatus());
            searchFilters.add(searchFilter);
        }

        Pageable pageable = PageRequest.of(dto.getPage(), dto.getLimit(), dto.getSortExpression());
        searchQuery.setSearchFilters(searchFilters);
        Specification<ViewTbaRgl> spec = SpecificationUtil.bySearchQuery(searchQuery, ViewTbaRgl.class);
        try {
            var pageResult = viewTbaRglRepository.findAll(spec, pageable);
            var dtoResult = pageResult.getContent().stream().map(item -> mapper.map(item, TbaRglListDto.class)).collect(Collectors.toList());
            Page<TbaRglListDto> result = new PageImpl<TbaRglListDto>(dtoResult, pageable, pageResult.getTotalElements());
            //var rs = donViSHPhanCapRepository.findAll();
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
    public ResponseData create(TbaRglCrudDto createDto) {
        ResponseData response = new ResponseData();

        var checkExist = tbaRglRepository.existsByCodeOrName(createDto.getCode(), createDto.getName(), createDto.getType());
        if (checkExist) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage("Tba/Rgl mã/tên đã tồn tại");
            response.setData(null);
        } else {
            try {
                A_ASSET asset = new A_ASSET();
                var guid = UUID.randomUUID().toString();
                createDto.setId(guid);
                var entity = mapper.map(createDto, SCategoryTbaRgl.class);
                if (entity.getTbaRglCode() != null && entity.getTbaRglCode().isEmpty()) entity.setTbaRglCode(null);
                entity.setIsDelete(false);
                var result = tbaRglRepository.saveAndFlush(entity);
                asset.setASSETID(result.getId());
                if (createDto.getType() == 0) {
                    asset.setTYPEID(ALstType.TYPENAME.TBA.toString());
                    asset.setCATEGORYID(ALstType.TYPENAME.TBA.toString());
                } else {
                    asset.setTYPEID(ALstType.TYPENAME.RGL.toString());
                    asset.setCATEGORYID(ALstType.TYPENAME.RGL.toString());
                }
                asset.setASSETDESC(createDto.getName());
                asset.setUSER_CR_ID(createDto.getUserId());
                asset.setORGID("0");
                asset.setUSER_CR_DTIME(new Date());
                asset.setUSESTATUS_LAST_ID(createDto.getStatus().toString());
                asset.setISDELETE(false);

                //lưu his
                List<String> ignoredFields = new ArrayList<>(){{
                    add("userid");
                    add("city");
                    add("type");
                    add("ownershipunit");
                    add("manageunit");
                    add("status");
                    add("id");
                    add("");
                    add("status");
                }};
                Map<String, String> variableNameMapping = Map.of(
                        "name", "Tên TBA/RGL",
                        "code", "Mã TBA/RGL",
                        "typestr", "Kiểu",
                        "voltagelevel", "Cấp điện áp",
                        "cityname", "Tỉnh/Thành phố",
                        "ownershipunitstr", "Đơn vị sở hữu",
                        "manageunitstr", "Đơn vị quản lý vận hành",
                        "statusstr", "Trạng thái",
                        "description", "Mô tả",
                        "xnk", "XNK"

                );
                _wonderHisService.UpdateHis(guid, createDto,"",ignoredFields,variableNameMapping, createDto.getUserId(), "S_TBA_RGL", "INS");
                aAssetRepository.save(asset);
                response.setState(ResponseData.STATE.OK.toString());
                response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
                response.setData(result);
            } catch (Exception ex) {
                response.setState(ResponseData.STATE.FAIL.toString());
                response.setMessage(ex.getMessage());
                response.setData(null);
            }
        }
        return response;
    }

    @Override
    public ResponseData update(String id, TbaRglCrudDto updateDto) {
        var existEntityByCode = tbaRglRepository.findByTbaRglCode(updateDto.getCode(), updateDto.getName(), id, updateDto.getType());
        ResponseData response = new ResponseData();
        if (existEntityByCode.size() > 0 && !existEntityByCode.get(0).getId().equals(updateDto.getId())) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR.toString() + "Tba/Rgl mã/tên đã tồn tại");
            response.setData(null);
        } else {
            try {
                var existEntity = tbaRglRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Trạm biến áp/ranh giới lẻ không tồn tại"));
                updateDto.setId(existEntity.getId());
                String sql = "select * from VIEW_TBA_RGL where id = '"+id+"'";
                List<TbaRglCrudDto> lst2 = jdbcTemplate.query(sql, new BeanPropertyRowMapper<TbaRglCrudDto>(TbaRglCrudDto.class));
                TbaRglCrudDto dto = mapper.map(existEntity,TbaRglCrudDto.class);
                dto.setCityName(lst2.get(0).getCityName());
                dto.setManageUnitStr(lst2.get(0).getManageUnitStr());
                dto.setOwnershipUnitStr(lst2.get(0).getOwnershipUnitStr());
                dto.setStatusStr(lst2.get(0).getStatusStr());
                dto.setTypeStr(lst2.get(0).getTypeStr());

                //lưu his
                List<String> ignoredFields = new ArrayList<>(){{
                    add("userid");
                    add("city");
                    add("type");
                    add("ownershipunit");
                    add("manageunit");
                    add("status");
                }};
                Map<String, String> variableNameMapping = Map.of(
                        "name", "Tên TBA/RGL",
                        "code", "Mã TBA/RGL",
                        "typestr", "Kiểu",
                        "voltagelevel", "Cấp điện áp",
                        "cityname", "Tỉnh/Thành phố",
                        "ownershipunitstr", "Đơn vị sở hữu",
                        "manageunitstr", "Đơn vị quản lý vận hành",
                        "statusstr", "Trạng thái",
                        "description", "Mô tả",
                        "xnk", "XNK"

                );
                _wonderHisService.UpdateHis(id, updateDto,dto,ignoredFields,variableNameMapping, updateDto.getUserId(), "S_TBA_RGL", "UPDATE");

                mapper.map(updateDto, existEntity);
                if (existEntity.getTbaRglCode() != null && existEntity.getTbaRglCode().isEmpty())
                    existEntity.setTbaRglCode(null);
                //var tinhTPEntity = tinhTPRepository.findById(existEntity.getTbaRglCity()).orElseThrow(() -> new NotFoundException("khong tim thay"));
//                var ownerUnit = donViSHRepository.findById(existEntity.getOwnerShipUnit()).orElseThrow(() -> new NotFoundException("not found"));
//                var manageUnit = donViSHRepository.findById(existEntity.getManageUnit()).orElseThrow(() -> new NotFoundException("not found"));
                existEntity.setIsDelete(false);
                // existEntity.setSCategoryTinhTP(tinhTPEntity);
//                existEntity.setSCategoryDonViSHByManage(manageUnit);
//                existEntity.setSCategoryDonViSHByOwnership(ownerUnit);
                var result = tbaRglRepository.save(existEntity);
                //save to  asset entity
                var assetResult = aAssetRepository.findById(existEntity.getId()).orElse(new A_ASSET());
                assetResult.setASSETID(existEntity.getId());
                if (updateDto.getType() == 0) {
                    assetResult.setTYPEID(ALstType.TYPENAME.TBA.toString());
                    assetResult.setCATEGORYID(ALstType.TYPENAME.TBA.toString());
                } else {
                    assetResult.setTYPEID(ALstType.TYPENAME.RGL.toString());
                    assetResult.setCATEGORYID(ALstType.TYPENAME.RGL.toString());
                }
                assetResult.setUSER_MDF_ID(updateDto.getUserId());
                assetResult.setASSETDESC(updateDto.getName());
                assetResult.setUSESTATUS_LAST_ID(updateDto.getStatus().toString());
                assetResult.setORGID("0");
                assetResult.setUSER_MDF_DTIME(new Date());
                assetResult.setISDELETE(false);
                 aAssetRepository.save(assetResult);
                response.setState(ResponseData.STATE.OK.toString());
                response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
                response.setData(mapper.map(result, TbaRglCrudDto.class));
            } catch (Exception ex) {
                response.setState(ResponseData.STATE.FAIL.toString());
                response.setMessage(ex.getMessage());
                response.setData(null);
            }
        }

        return response;
    }

    @Override
    public ResponseData delete(String id) {
        ResponseData response = new ResponseData();
        try {
            var result = tbaRglRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Trạm biến áp/ranh giới lẻ không tồn tại"));
            var checkAsset = aAssetRepository.existByAssetIdParent(id);
            String sql1 = "select  1 from Q_PQOBJ_USER where ASSETID=? and (ISQL = 1 or ISLD = 1)  ";
            List lstPQ = jdbcTemplate.queryForList(sql1, id);
            var checkThoaThuan = thoaThuanRepository.existsByASSETIDAndISDELETEIsFalse(id);
            var checkAcceptance = acceptanceRepository.existsByAssetId(id);
            var checkAccreditation = accreditationRepository.existsByASSETID(id);
            var checkSuCo = suCoRepository.existsByASSETIDAndISDELETEIsFalse(id);
            if (checkAsset || checkAcceptance || checkAccreditation || checkSuCo || checkThoaThuan || lstPQ.size() > 0) {
                response.setMessage("Đã có dữ liệu ràng buộc không thể xoá!");
                response.setState(ResponseData.STATE.FAIL.toString());
                return response;
            }
            result.setIsDelete(true);
            tbaRglRepository.save(result);
            response.setData(mapper.map(result, TbaRglCrudDto.class));
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
