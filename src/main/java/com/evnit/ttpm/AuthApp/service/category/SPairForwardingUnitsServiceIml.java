package com.evnit.ttpm.AuthApp.service.category;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.evnit.ttpm.AuthApp.entity.category.DMCXNK;
import com.evnit.ttpm.AuthApp.entity.category.ViewSPairForwardingUnits;
import com.evnit.ttpm.AuthApp.model.category.PairForwardingUnits.SearchUnit;
import com.evnit.ttpm.AuthApp.model.category.QuanLyDD.QuanLyDDCreateDto;
import com.evnit.ttpm.AuthApp.model.filter.SearchFilter;
import com.evnit.ttpm.AuthApp.model.filter.SearchQuery;
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

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.category.SPairForwardingUnits;
import com.evnit.ttpm.AuthApp.exception.NotFoundException;
import com.evnit.ttpm.AuthApp.mapper.PairForwardingUnitsMapper;
import com.evnit.ttpm.AuthApp.model.category.PairForwardingUnits.PairForwardingUnitsCreateDto;
import com.evnit.ttpm.AuthApp.model.category.PairForwardingUnits.PairForwardingUnitsListDto;
import com.evnit.ttpm.AuthApp.repository.category.DeliveryUnitRepository;
import com.evnit.ttpm.AuthApp.repository.category.PairForwardingUnitsRepository;

@Service
public class SPairForwardingUnitsServiceIml implements SPairForwardingUnitsService {
	@Autowired
	PairForwardingUnitsRepository pairForwardingUnitsRepository;
	@Autowired
	DeliveryUnitRepository deliveryUnitRepository;
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	SPairForwardingUnitsRepoService sPairForwardingUnitsRepoService;
	@Autowired
	WonderHisService _wonderHisService;
	
	private final ModelMapper mapper;
	public SPairForwardingUnitsServiceIml(PairForwardingUnitsMapper mapperConfig) {
		this.mapper = mapperConfig.getModelMapper();
	}

	@Override
	public ResponseData getAll() {
		ResponseData response = new ResponseData();
		try {
			String sql;
			List lst;
			sql = "Select a.ID as id ,a.UNIT1 as unit1,a.UNIT2 as unit2, a.Unit1Name as unit1Str,a.UNIT2Name as unit2Str, a.DESCRIPTION as description from View_SPairForWardingUnit a where a.IS_DELETE = 0 order by a.Unit1Name";
			lst= jdbcTemplate.queryForList(sql);

			response.setState(ResponseData.STATE.OK.toString());
			response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
			/*var listTbaRgl = pairForwardingUnitsRepository.findAll().stream().filter(x -> x.getIs_Delete() == false).collect(Collectors.toList());
			var result = listTbaRgl.stream().map(tbaRgl -> mapper.map(tbaRgl, PairForwardingUnitsListDto.class));*/
//            listTbaRgl.stream().map(this::)
//            for (SCategoryTbaRgl sCategoryTbaRgl : listTbaRgl) {
//                sCategoryTbaRgl.getTbaRglStatus();
//            }
           response.setData(lst);
			response.setState(ResponseData.STATE.OK.toString());
			response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
		}catch (Exception ex){
			response.setState(ResponseData.STATE.FAIL.toString());
			response.setMessage(ex.getMessage());
			response.setData(null);
		}
		return response;
	}
	@Override
	public ResponseData getList(SearchUnit dto) {
		ResponseData response = new ResponseData();
		SearchQuery searchQuery = new SearchQuery();
		List<PairForwardingUnitsListDto> listUnit = new ArrayList<PairForwardingUnitsListDto>();
		SearchFilter searchFilter = new SearchFilter();
		List<SearchFilter> searchFilters = new ArrayList<>();
		searchFilter = new SearchFilter("IS_DELETE", "=", false);
		searchFilters.add(searchFilter);

		if (dto.getDescription() != null && !dto.getDescription().isEmpty()) {
			searchFilter = new SearchFilter("description", "LIKE", dto.getDescription(),"OR");
			searchFilters.add(searchFilter);
			searchFilter = new SearchFilter("unit1Name", "LIKE", dto.getDescription(),"OR");
			searchFilters.add(searchFilter);
			searchFilter = new SearchFilter("unit2Name", "LIKE", dto.getDescription(),"OR");
			searchFilters.add(searchFilter);
		}

		Pageable pageable = PageRequest.of(dto.getPage(), dto.getLimit(), dto.getSortExpression());
		searchQuery.setSearchFilters(searchFilters);
		//Specification<SPairForwardingUnits> spec = SpecificationUtil.bySearchQuery(searchQuery, SPairForwardingUnits.class);
		Specification<ViewSPairForwardingUnits> spec = SpecificationUtil.bySearchQuery(searchQuery, ViewSPairForwardingUnits.class);
		try {
			//var pageResult = sPairForwardingUnitsRepoService.findAllPaging(spec, pageable);
			var pageResult = sPairForwardingUnitsRepoService.findAll(spec, pageable);
			var dtoResult = pageResult.getContent().stream().map(item -> mapper.map(item, PairForwardingUnitsListDto.class)).collect(Collectors.toList());
			Page<PairForwardingUnitsListDto> result = new PageImpl<PairForwardingUnitsListDto>(dtoResult, pageable, pageResult.getTotalElements());

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
	public ResponseData create(PairForwardingUnitsCreateDto createDto) {
		ResponseData response = new ResponseData();
		try {
			String s;
			s="select 1 from View_SPairForWardingUnit  where ((UNIT1=? and UNIT2=?) or (UNIT2=? and UNIT1=?)) and IS_DELETE=0";
			List lst= jdbcTemplate.queryForList(s,createDto.getUnit1(),createDto.getUnit2(),createDto.getUnit1(),createDto.getUnit2());
			if (lst!=null  && !lst.isEmpty())
				return new ResponseData(ResponseData.STATE.FAIL.toString(), "Cặp đơn vị đã tồn tại, không thể thêm mới");
			var id = createDto.getId();
			var guid = UUID.randomUUID().toString();
			createDto.setGuid(guid);
			var entity = mapper.map(createDto, SPairForwardingUnits.class);
			entity.setIs_Delete(false);
			var result = pairForwardingUnitsRepository.save(entity);
			List<String> ignoredFields = new ArrayList<>(){{
				add("unit1");
				add("unit2");
				add("userid");
				add("is_delete");
				add("_delete");
				add("guid");
				add("id");
			}};
			Map<String, String> variableNameMapping = Map.of(
					"unit1str", "Đơn vị 1",
					"unit2str", "Đơn vị 2",
					"description", "Mô tả"
			);//lưu his
			_wonderHisService.UpdateHis(guid, createDto,"",ignoredFields,variableNameMapping, createDto.getUserId(), "S_Pair_Forwarding_Units", "INS");

			response.setState(ResponseData.STATE.OK.toString());
			response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
			response.setData(result);
		}catch (Exception ex){
			response.setState(ResponseData.STATE.FAIL.toString());
			response.setMessage(ex.getMessage());
			response.setData(null);
		}
		return response;
	}

	@Override
	public ResponseData update(int id, PairForwardingUnitsCreateDto updateDto) {
		ResponseData response = new ResponseData();
		try {
			String s;
			s="select 1 from View_SPairForWardingUnit  where ((UNIT1=? and UNIT2=?) or (UNIT2=? and UNIT1=?)) and IS_DELETE=? and Id!=?";
			List lst= jdbcTemplate.queryForList(s,updateDto.getUnit1(),updateDto.getUnit2(),updateDto.getUnit1(),updateDto.getUnit2(),0,id);
			if (lst!=null  && !lst.isEmpty())
				return new ResponseData(ResponseData.STATE.FAIL.toString(), "Cặp đơn vị đã tồn tại, không thể sửa tên");
			var existEntity = pairForwardingUnitsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cặp đơn vị không tồn tại"));
			//lưu his
			String sql = "select * from View_SPairForWardingUnit where id = '"+id+"'";
			List<PairForwardingUnitsListDto> lst2 = jdbcTemplate.query(sql, new BeanPropertyRowMapper<PairForwardingUnitsListDto>(PairForwardingUnitsListDto.class));
			PairForwardingUnitsCreateDto dto = mapper.map(existEntity,PairForwardingUnitsCreateDto.class);
			dto.setUnit1Str(lst2.get(0).getUnit1Name());
			dto.setUnit2Str(lst2.get(0).getUnit2Name());
			List<String> ignoredFields = new ArrayList<>(){{
				add("unit1");
				add("unit2");
				add("userid");
				add("is_delete");
				add("_delete");
			}};
			Map<String, String> variableNameMapping = Map.of(
					"unit1str", "Đơn vị 1",
					"unit2str", "Đơn vị 2",
					"description", "Mô tả"
			);
			_wonderHisService.UpdateHis(updateDto.getGuid(), updateDto,dto,ignoredFields,variableNameMapping, updateDto.getUserId(), "S_Pair_Forwarding_Units", "UPDATE");

			mapper.map(updateDto, existEntity);
			updateDto.setId(existEntity.getId());
			var deliveryUnit = deliveryUnitRepository.findById(existEntity.getUnit1()).orElseThrow(() -> new NotFoundException("khong tim thay"));
			var deliveryUnit1 = deliveryUnitRepository.findById(existEntity.getUnit2()).orElseThrow(() -> new NotFoundException("not found"));
			existEntity.setIs_Delete(false);
			existEntity.setSDeliveryUnit(deliveryUnit);
			existEntity.setSDeliveryUnit1(deliveryUnit1);
			existEntity.setId(id);


			var result = pairForwardingUnitsRepository.save(existEntity);
			response.setState(ResponseData.STATE.OK.toString());
			response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
			response.setData(result);
		}catch (Exception ex){
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
			var result = pairForwardingUnitsRepository.findById(id).map(tbaRgl -> {
				tbaRgl.setIs_Delete(true);
				return pairForwardingUnitsRepository.save(tbaRgl);
			}).orElseThrow(() -> new IllegalArgumentException("Cặp đơn vị giao nhận không tồn tại"));
			response.setData(result);
			response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
			response.setState(ResponseData.STATE.OK.toString());
		}catch (Exception ex){
			response.setState(ResponseData.STATE.FAIL.toString());
			response.setMessage(ex.getMessage());
			response.setData(null);
		}
		return response;
	}
}
