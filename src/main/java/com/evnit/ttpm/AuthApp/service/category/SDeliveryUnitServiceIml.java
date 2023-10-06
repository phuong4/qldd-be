package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.entity.category.ViewSPairForwardingUnits;
import com.evnit.ttpm.AuthApp.model.category.DeliveryUnit.DeliveryUnitListDto;
import com.evnit.ttpm.AuthApp.model.category.DeliveryUnit.SearchDeliveryUnit;
import com.evnit.ttpm.AuthApp.model.category.PairForwardingUnits.PairForwardingUnitsListDto;
import com.evnit.ttpm.AuthApp.model.filter.SearchFilter;
import com.evnit.ttpm.AuthApp.model.filter.SearchQuery;
import com.evnit.ttpm.AuthApp.repository.category.PairForwardingUnitsRepository;
import com.evnit.ttpm.AuthApp.repository.category.QuanLyDDRepository;
import com.evnit.ttpm.AuthApp.service.system.WonderHisService;
import com.evnit.ttpm.AuthApp.util.SpecificationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.category.SDeliveryUnit;
import com.evnit.ttpm.AuthApp.mapper.DeliveryUnitMapper;
import com.evnit.ttpm.AuthApp.model.category.DeliveryUnit.DeliveryUnitCreateDto;
import com.evnit.ttpm.AuthApp.repository.category.DeliveryUnitRepository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SDeliveryUnitServiceIml implements SDeliveryUnitService {
    @Autowired
    DeliveryUnitRepository deliveryUnitRepository;
    @Autowired
    PairForwardingUnitsRepository pairForwardingUnitsRepository;
    @Autowired
    QuanLyDDRepository quanLyDDRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    SDeliveryUnitRepoService sDeliveryUnitRepoService;
    @Autowired
    WonderHisService _wonderHisService;

    private final ModelMapper mapper;

    public SDeliveryUnitServiceIml(DeliveryUnitMapper mapperConfig) {
        this.mapper = mapperConfig.getModelMapper();
    }

    @Override
    public ResponseData getAll() {
        ResponseData response = new ResponseData();
        try {
            String sql;
            List lst;
            sql = "Select a.ID as id , a.Name as name, a.DESCRIPTION as description, a.XNK as xnk from S_Delivery_Unit a where a.IS_DELETE = 0 order by a.NAME";
            lst = jdbcTemplate.queryForList(sql);
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
			/*var listTinhTp = deliveryUnitRepository.findAll().stream().filter(x-> x.getIs_Delete()==false).collect(Collectors.toList());
			var result = listTinhTp.stream().map(tinhtp -> mapper.map(tinhtp, DeliveryUnitListDto.class));
           response.setData(result);*/
            response.setData(lst);
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ex.getMessage());
            response.setData(null);
        }
        return response;
    }

    @Override
    public ResponseData getList(SearchDeliveryUnit dto) {
        ResponseData response = new ResponseData();
        SearchQuery searchQuery = new SearchQuery();
        List<DeliveryUnitListDto> listUnit = new ArrayList<DeliveryUnitListDto>();
        SearchFilter searchFilter = new SearchFilter();
        List<SearchFilter> searchFilters = new ArrayList<>();
        searchFilter = new SearchFilter("is_Delete", "=", false);
        searchFilters.add(searchFilter);
        if (dto.getName() != null && !dto.getName().isEmpty()) {
            searchFilter = new SearchFilter("name", "LIKE", dto.getName(), "OR");
            searchFilters.add(searchFilter);
            searchFilter = new SearchFilter("description", "LIKE", dto.getName(), "OR");
            searchFilters.add(searchFilter);
        }

        if (dto.getDescription() != null && !dto.getDescription().isEmpty()) {
            searchFilter = new SearchFilter("description", "LIKE", dto.getDescription());
            searchFilters.add(searchFilter);
        }

        Pageable pageable = PageRequest.of(dto.getPage(), dto.getLimit(), dto.getSortExpression());
        searchQuery.setSearchFilters(searchFilters);
        Specification<SDeliveryUnit> spec = SpecificationUtil.bySearchQuery(searchQuery, SDeliveryUnit.class);
        try {
            var pageResult = sDeliveryUnitRepoService.findAll(spec, pageable);
            var dtoResult = pageResult.getContent().stream().map(item -> mapper.map(item, DeliveryUnitListDto.class)).collect(Collectors.toList());
            Page<DeliveryUnitListDto> result = new PageImpl<DeliveryUnitListDto>(dtoResult, pageable, pageResult.getTotalElements());

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
    public ResponseData getById(int id) {
        return null;
    }

    @Override
    public ResponseData create(DeliveryUnitCreateDto createDto) {
        ResponseData response = new ResponseData();
        try {
            String s;
            s = "select 1 from S_Delivery_Unit  where NAME=? and IS_DELETE=? ";
            List lst = jdbcTemplate.queryForList(s, createDto.getName().trim(), 0);
            if (lst != null && !lst.isEmpty())
                return new ResponseData(ResponseData.STATE.FAIL.toString(), "Tên đơn vị đã tồn tại, không thể thêm mới");
            var guid = UUID.randomUUID().toString();
            createDto.setGuid(guid);
             var entity = mapper.map(createDto, SDeliveryUnit.class);

            List<String> ignoredFields = new ArrayList<>(){{
                add("userid");
                add("id");
                add("is_delete");
                add("guid");
            }};
            //lưu his
            Map<String, String> variableNameMapping = Map.of(
                    "name", "Tên",
                    "description", "Mô tả",
                    "xnk", "Xuất nhập khẩu"
            );
            _wonderHisService.UpdateHis(guid, createDto,"",ignoredFields,variableNameMapping, createDto.getUserId(), "S_Delivery_Unit", "INS");

            var result = deliveryUnitRepository.save(entity);
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
    public ResponseData update(int id, DeliveryUnitCreateDto updateDto) {
        ResponseData response = new ResponseData();
        try {
            String s;
            s = "select 1 from S_Delivery_Unit  where NAME=? and IS_DELETE=? and ID!=? ";
            List lst = jdbcTemplate.queryForList(s, updateDto.getName().trim(), 0, updateDto.getId());
            if (lst != null && !lst.isEmpty())
                return new ResponseData(ResponseData.STATE.FAIL.toString(), "Tên đơn vị đã tồn tại, không thể sửa tên");

            String sql;
            sql = "select * from S_Delivery_Unit  where IS_DELETE=? and ID=? ";
            List lst1 = jdbcTemplate.queryForList(sql,  0, updateDto.getId());

            var entity = deliveryUnitRepository.findById(id);
            var existEntity = deliveryUnitRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Đơn vị tham gia giao nhận không tồn tại"));
            List<String> ignoredFields = new ArrayList<>(){{
                add("userid");
            }};
            //lưu his
            Map<String, String> variableNameMapping = Map.of(
                    "name", "Tên",
                    "description", "Mô tả",
                    "xnk", "Xuất nhập khẩu"
            );
            _wonderHisService.UpdateHis(updateDto.getGuid(), updateDto,existEntity,ignoredFields,variableNameMapping, updateDto.getUserId(), "S_Delivery_Unit", "UPDATE");

            mapper.map(updateDto, existEntity);
            existEntity.setId(id);

            var result = deliveryUnitRepository.save(existEntity);
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
    public ResponseData delete(int id) {
        ResponseData response = new ResponseData();
        try {
            var check = pairForwardingUnitsRepository.existsByUnitId(id);
            String idStr = String.valueOf(id);
            var check1 = quanLyDDRepository.existsByUnitId(idStr);
            if (check == true || check1 == true) {
                response.setState(ResponseData.STATE.FAIL.toString());
                response.setMessage("Đã có ràng buộc không thể xóa");
                response.setData(null);
            } else {
                //deliveryUnitRepository.deleteById(id);
                var result = deliveryUnitRepository.findById(id).map(DonViGiaoNhan -> {
                    DonViGiaoNhan.setIs_Delete(true);
                    return deliveryUnitRepository.save(DonViGiaoNhan);
                }).orElseThrow(() -> new IllegalArgumentException("Đơn vị tham gia giao nhận không tồn tại"));
                response.setData(id);
                response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
                response.setState(ResponseData.STATE.OK.toString());
            }
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ex.getMessage());
            response.setData(null);
        }
        return response;
    }
}
