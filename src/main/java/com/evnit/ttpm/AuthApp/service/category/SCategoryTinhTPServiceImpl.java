package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.category.SCategoryTinhTP;
import com.evnit.ttpm.AuthApp.entity.category.SDeliveryUnit;
import com.evnit.ttpm.AuthApp.entity.category.ViewTinhTP;
import com.evnit.ttpm.AuthApp.mapper.TinhTPMapper;
import com.evnit.ttpm.AuthApp.model.category.DeliveryUnit.DeliveryUnitListDto;
import com.evnit.ttpm.AuthApp.model.category.DeliveryUnit.SearchDeliveryUnit;
import com.evnit.ttpm.AuthApp.model.category.PairForwardingUnits.PairForwardingUnitsCreateDto;
import com.evnit.ttpm.AuthApp.model.category.PairForwardingUnits.PairForwardingUnitsListDto;
import com.evnit.ttpm.AuthApp.model.category.TinhTP.TinhTPCreateDto;
import com.evnit.ttpm.AuthApp.model.category.TinhTP.TinhTPListDto;
import com.evnit.ttpm.AuthApp.model.filter.SearchFilter;
import com.evnit.ttpm.AuthApp.model.filter.SearchQuery;
import com.evnit.ttpm.AuthApp.repository.category.TinhTPRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import com.evnit.ttpm.AuthApp.service.system.WonderHisService;
import com.evnit.ttpm.AuthApp.util.SpecificationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.JdbcTemplate;


@Service
public class SCategoryTinhTPServiceImpl implements SCategoryTinhTPService {
	@Autowired
	TinhTPRepository tinhTPRepository;
	   @Autowired
	    JdbcTemplate jdbcTemplate;
	   @Autowired
	   SCategoryTinhTPRepoService sCategoryTinhTPRepoService;
	@Autowired
	WonderHisService _wonderHisService;

	private final ModelMapper mapper;
	public SCategoryTinhTPServiceImpl(TinhTPMapper mapperConfig) {
		this.mapper = mapperConfig.getModelMapper();
	}

	@Override
	public ResponseData getAll() {
		ResponseData response = new ResponseData();
		try {
			
			
			 String sql;
             List lst; //List<SListGroupAll> lst;
        	sql = "Select a.ID as id , a.DOMAIN as domain ,a.GUID as guid,a.NAME  as name , Case when a.DOMAIN = 0 then N'Miền bắc'when a.DOMAIN = 1 then N'Miền trung'  when a.DOMAIN =2 then N'Miền nam'end as domainStr from S_PROVINCES a  where a.ISDELETE = 0 order by a.DOMAIN,a.NAME";
              lst=jdbcTemplate.queryForList(sql);
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(lst);
			response.setState(ResponseData.STATE.OK.toString());
			response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
//			var listTinhTp = tinhTPRepository.findAll();
//			var result = listTinhTp.stream().map(tinhtp -> mapper.map(tinhtp, TinhTPListDto.class));
//			
//			
//           response.setData(result);
		}catch (Exception ex){
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
		List<TinhTPListDto> listUnit = new ArrayList<TinhTPListDto>();
		SearchFilter searchFilter = new SearchFilter();
		List<SearchFilter> searchFilters = new ArrayList<>();
		searchFilter = new SearchFilter("isdelete", "=", false);
		searchFilters.add(searchFilter);
		if (dto.getName() != null && !dto.getName().isEmpty()) {
			searchFilter = new SearchFilter("name", "LIKE", dto.getName(),"OR");
			searchFilters.add(searchFilter);
			searchFilter = new SearchFilter("mien", "LIKE", dto.getName(),"OR");
			searchFilters.add(searchFilter);
		}

		if (dto.getDescription() != null && !dto.getDescription().isEmpty()) {
			searchFilter = new SearchFilter("description", "LIKE", dto.getDescription());
			searchFilters.add(searchFilter);
		}

		Pageable pageable = PageRequest.of(dto.getPage(), dto.getLimit(), dto.getSortExpression());
		searchQuery.setSearchFilters(searchFilters);
		Specification<ViewTinhTP> spec = SpecificationUtil.bySearchQuery(searchQuery, ViewTinhTP.class);
		try {
			var pageResult = sCategoryTinhTPRepoService.findAll(spec, pageable);
			var dtoResult = pageResult.getContent().stream().map(item -> mapper.map(item, TinhTPListDto.class)).collect(Collectors.toList());
			Page<TinhTPListDto> result = new PageImpl<TinhTPListDto>(dtoResult, pageable, pageResult.getTotalElements());

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
	public ResponseData fetchTinhTPDataAsPageWithFiltering(String nameFilter, int domain, int page, int size) {
		ResponseData response = new ResponseData();
		try{
			// create Pageable object using the page and size
			Pageable pageable = PageRequest.of(page, size);
			// fetch the page object by additionally passing pageable with the filters
			response.setState(ResponseData.STATE.OK.toString());
			response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());

			var result = tinhTPRepository.findByNameLike(nameFilter, domain, pageable);
			response.setData(result);
		}
		catch (Exception ex)
		{
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
	public ResponseData create(TinhTPCreateDto createDto) {
		ResponseData response = new ResponseData();
		try {
			var guid = UUID.randomUUID().toString();
			 String s;
		        s="select 1 from S_PROVINCES  where NAME=? and ISDELETE=? ";
		        List lst=jdbcTemplate.queryForList(s,createDto.getName().trim(),0);
		        if (lst!=null && !lst.isEmpty())
		            return new ResponseData(ResponseData.STATE.FAIL.toString(), "Tên tỉnh thành phố đã tồn tại, không thể thêm mới");
			createDto.setGuid(guid);
			var entity = mapper.map(createDto, SCategoryTinhTP.class);
			var result = tinhTPRepository.save(entity);
			//lưu his
			List<String> ignoredFields = new ArrayList<>(){{
				add("userid");
				add("domain");
				add("id");
				add("guid");
				add("isdelete");
			}};
			Map<String, String> variableNameMapping = Map.of(
					"name", "Tên",
					"mien", "Tên miền",
					"xnk", "Xuất nhập khẩu"
			);
			_wonderHisService.UpdateHis(guid, createDto,"",ignoredFields,variableNameMapping, createDto.getUserId(), "S_PROVINCES", "INS");

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
	public ResponseData update(int id, TinhTPCreateDto updateDto) {
		ResponseData response = new ResponseData();
		try {
			
			 String s;
		        s="select 1 from S_PROVINCES  where NAME=? and ISDELETE=? and ID!=? ";
		        List lst=jdbcTemplate.queryForList(s,updateDto.getName().trim(),0,updateDto.getId());
		        if (lst!=null && !lst.isEmpty())
		            return new ResponseData(ResponseData.STATE.FAIL.toString(), "Tên tỉnh thành phố đã tồn tại, không thể thêm mới");

		        
			var existEntity = tinhTPRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Tỉnh thành phố không tồn tại"));
			//lưu his
			String sql = "select * from View_TinhTP where id = '"+id+"'";
			List<TinhTPListDto> lst2 = jdbcTemplate.query(sql, new BeanPropertyRowMapper<TinhTPListDto>(TinhTPListDto.class));
			TinhTPCreateDto dto = mapper.map(existEntity,TinhTPCreateDto.class);
			dto.setMien(lst2.get(0).getMien());
			List<String> ignoredFields = new ArrayList<>(){{
				add("userid");
				add("domain");
			}};
			Map<String, String> variableNameMapping = Map.of(
					"name", "Tên",
					"mien", "Tên miền",
					"xnk", "Xuất nhập khẩu"
			);
			_wonderHisService.UpdateHis(updateDto.getGuid(), updateDto,dto,ignoredFields,variableNameMapping, updateDto.getUserId(), "S_PROVINCES", "UPDATE");

			mapper.map(updateDto, existEntity);
			existEntity.setId(id);
			var result = tinhTPRepository.save(existEntity);

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
		
		 String s;
	        s="select 1 from S_CATEGORY_TBA_RGL  where TBA_RGL_CITY=? and IS_DELETE=?  ";
	        List lst=jdbcTemplate.queryForList(s,id,0);
	        if (lst!=null && !lst.isEmpty())
	            return new ResponseData(ResponseData.STATE.FAIL.toString(), "Đã có dữ liệu ràng buộc, không thể xóa");
			 String s1;

	        s1="select 1 from S_Electric_Factory  where City=? and IS_DELETE=?  ";
	        List lst1=jdbcTemplate.queryForList(s1,id,0);
	        if (lst1!=null && !lst1.isEmpty())
	            return new ResponseData(ResponseData.STATE.FAIL.toString(), "Đã có dữ liệu ràng buộc, không thể xóa");
		try {
			var result = tinhTPRepository.findById(id).map(tinhthanhpho -> {
				
				
			        
				tinhthanhpho.setIsdelete(true);
				return tinhTPRepository.save(tinhthanhpho);
			}).orElseThrow(() -> new IllegalArgumentException("Tỉnh thành phố không tồn tại"));
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
