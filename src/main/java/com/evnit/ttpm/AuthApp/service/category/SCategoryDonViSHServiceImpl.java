package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.category.SCategoryDonViSH;
import com.evnit.ttpm.AuthApp.entity.category.v_DONVISH_PHANCAP;
import com.evnit.ttpm.AuthApp.mapper.DonViSHMapper;
import com.evnit.ttpm.AuthApp.model.category.DonViSH.DonViSHCreateDto;
import com.evnit.ttpm.AuthApp.model.category.DonViSH.DonViSHListDto;
import com.evnit.ttpm.AuthApp.model.category.DonViSH.SearchDonViSH;
import com.evnit.ttpm.AuthApp.model.category.TinhTP.TinhTPListDto;
import com.evnit.ttpm.AuthApp.model.filter.SearchFilter;
import com.evnit.ttpm.AuthApp.model.filter.SearchQuery;
import com.evnit.ttpm.AuthApp.repository.category.DonViSHPhanCapRepository;
import com.evnit.ttpm.AuthApp.repository.category.DonViSHRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SCategoryDonViSHServiceImpl implements SCategoryDonViSHService {
	@Autowired
	DonViSHRepository donViSHRepository;

	private final ModelMapper mapper;

	@Autowired
	SCategoryDonViSHRepoService donViSHRepoService;
	@Autowired
	ViewDonViSHPhanCapRepoService viewDonViSHPhanCapRepoService;
	@Autowired
	DonViSHPhanCapRepository donViSHPhanCapRepository;
	@Autowired
	JdbcTemplate jdbcTemplate;
	@Autowired
	WonderHisService _wonderHisService;

	public SCategoryDonViSHServiceImpl(DonViSHMapper mapperConfig) {
		this.mapper = mapperConfig.getModelMapper();
	}

	@Override
	public ResponseData getAll() {
		ResponseData response = new ResponseData();
		try {
			response.setState(ResponseData.STATE.OK.toString());
			response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
			var listDonViSH = donViSHRepository.findAll();
//			var result = listDonViSH.stream().map(tinhtp -> mapper.map(tinhtp, DonViSHListDto.class)).collect(Collectors.toList());
//			  result.forEach(x -> {
//				if(x.getType() == 1) {
//					x.setName1(x.getName());
//				}
//				if(x.getType() == 2) {
//					x.setName2(x.getName());
//				}
//				if(x.getType() == 3) {
//					x.setName3(x.getName());
//				}
//			});



//			  
			  
			  String sql;
	             List lst; //List<SListGroupAll> lst;
	        	sql = "Select Id,Name as name ,IIF(Type = 1,name,'') as name1,IIF(Type = 2,name,'') as name2,IIF(Type = 3,name,'') as name3,Type as type,ParentId as parentId,NameShort as nameShort,Is_Delete,Note as note from S_DONVISH  where Is_Delete =0 order by id";
	              lst=jdbcTemplate.queryForList(sql);
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

	@Override
	public ResponseData getListDonViSH(SearchDonViSH dto) {
		ResponseData response = new ResponseData();
		SearchQuery searchQuery = new SearchQuery();
		List<DonViSHListDto> listDonViSH = new ArrayList<DonViSHListDto>();
		SearchFilter searchFilter = new SearchFilter();
		List<SearchFilter> searchFilters = new ArrayList<>();
		if (dto.getName() != null && !dto.getName().isEmpty()) {
			searchFilter = new SearchFilter("name", "LIKE", dto.getName(),"OR");
			searchFilters.add(searchFilter);
			searchFilter = new SearchFilter("note", "LIKE", dto.getName(),"OR");
			searchFilters.add(searchFilter);
		}

		Pageable pageable = PageRequest.of(dto.getPage(), dto.getLimit(), dto.getSortExpression());
		searchQuery.setSearchFilters(searchFilters);
		Specification<v_DONVISH_PHANCAP> spec = SpecificationUtil.bySearchQuery(searchQuery, v_DONVISH_PHANCAP.class);
		try {
			var pageResult = viewDonViSHPhanCapRepoService.findAllPaging(spec, pageable);
			var dtoResult = pageResult.getContent().stream().map(item -> mapper.map(item, DonViSHListDto.class)).collect(Collectors.toList());
			Page<DonViSHListDto> result = new PageImpl<DonViSHListDto>(dtoResult, pageable, pageResult.getTotalElements());
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
	public ResponseData getById(int id) {
		return null;
	}

	@Override
	public ResponseData create(DonViSHCreateDto createDto) {
		ResponseData response = new ResponseData();
		try {
			 String sName;
		        sName="select 1 from S_DONVISH  where Name like  ('%' + ? + '%')  and Is_Delete=? ";
		        List lstName=jdbcTemplate.queryForList(sName,createDto.getName().trim(),0);
		        if (lstName!=null && !lstName.isEmpty())
		            return new ResponseData(ResponseData.STATE.FAIL.toString(), "Tên đã tồn tại, không thể thêm mới");

		        if(!createDto.getNameShort().isEmpty()) {
					String s;
					s = "select 1 from S_DONVISH  where NameShort like  ('%' + ? + '%') and Is_Delete=? ";
					List lst = jdbcTemplate.queryForList(s, createDto.getNameShort().trim(), false);
					if (lst != null && !lst.isEmpty())
						return new ResponseData(ResponseData.STATE.FAIL.toString(), "Tên viết tắt đã tồn tại, không thể thêm mới");
				}
		        var idHis = UUID.randomUUID().toString();
				createDto.setIdHis(idHis);
			var entity = mapper.map(createDto, SCategoryDonViSH.class);
			entity.setIs_Delete(false);
			var result = donViSHRepository.save(entity);
			//lưu his
			List<String> ignoredFields = new ArrayList<>(){{
				add("userid");
				add("level");
				add("parentid");
				add("is_delete");
				add("id");
				add("idhis");
			}};
			Map<String, String> variableNameMapping = Map.of(
					"name", "Tên",
					"nameshort", "Tên tắt",
					"parent", "Đơn vị cha",
					"note","Mô tả",
					"type","Đơn vị cấp"
			);
			_wonderHisService.UpdateHis(idHis, createDto,"",ignoredFields,variableNameMapping, createDto.getUserId(), "S_DONVISH", "INS");

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
	public ResponseData update(int id, DonViSHCreateDto updateDto) {
		ResponseData response = new ResponseData();
		try {
			 String sName;
		        sName="select 1 from S_DONVISH  where Name like  ('%' + ? + '%')  and Is_Delete=? and Id!=? ";

		        List lstName=jdbcTemplate.queryForList(sName,updateDto.getName().trim(),false,id);
		        if (lstName!=null && !lstName.isEmpty())
		            return new ResponseData(ResponseData.STATE.FAIL.toString(), "Tên đã tồn tại, không thể cập nhật");
		        
			 String s;
		        s="select 1 from S_DONVISH  where NameShort like  ('%' + ? + '%') and Is_Delete=? and Id!=? ";
				String shortName = "";
				if(updateDto.getNameShort()!= null) {
					shortName = updateDto.getNameShort().trim();
				}
				if(!shortName.isEmpty())
				{
					List lst=jdbcTemplate.queryForList(s,shortName,0,id);
					if (lst!=null && !lst.isEmpty())
						return new ResponseData(ResponseData.STATE.FAIL.toString(), "Tên viết tắt đã tồn tại, không thể cập nhật");
				}



			var existEntity = donViSHRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Đơn vị sở hữu không tồn tại"));
			//lưu his
			String sql = "select * from v_DONVISH_PHANCAP where id = '"+id+"'";
			List<DonViSHCreateDto> lst2 = jdbcTemplate.query(sql, new BeanPropertyRowMapper<DonViSHCreateDto>(DonViSHCreateDto.class));
			lst2.get(0).setType(lst2.get(0).getLevel());
			//existEntity.setParent(lst2.get(0).getParent());
			List<String> ignoredFields = new ArrayList<>(){{
				add("userid");
				add("level");
				add("parentid");
			}};
			Map<String, String> variableNameMapping = Map.of(
					"name", "Tên",
					"nameshort", "Tên tắt",
					"parent", "Đơn vị cha",
					"note","Mô tả",
					"type","Đơn vị cấp"
			);
			_wonderHisService.UpdateHis(updateDto.getIdHis(), updateDto,lst2.get(0),ignoredFields,variableNameMapping, updateDto.getUserId(), "S_DONVISH", "UPDATE");

			mapper.map(updateDto, existEntity);
			existEntity.setId(id);
			var result = donViSHRepository.save(existEntity);
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
	        s="select 1 from S_CATEGORY_TBA_RGL  where OWNERSHIP_UNIT=? and IS_DELETE=?  ";
	        List lst=jdbcTemplate.queryForList(s,id,0);
	        if (lst!=null && !lst.isEmpty())
	            return new ResponseData(ResponseData.STATE.FAIL.toString(), "Đã có dữ liệu ràng buộc, không thể xóa");
			 String s1;

	        s1="select 1 from S_Electric_Factory  where Ownership_Unit=? and IS_DELETE=?  ";
	        List lst1=jdbcTemplate.queryForList(s1,id,0);
	        if (lst1!=null && !lst1.isEmpty())
	            return new ResponseData(ResponseData.STATE.FAIL.toString(), "Đã có dữ liệu ràng buộc, không thể xóa");
	        
		try {
			var result = donViSHRepository.findById(id).map(tinhthanhpho -> {
				tinhthanhpho.setIs_Delete(true);
				return donViSHRepository.save(tinhthanhpho);
			}).orElseThrow(() -> new IllegalArgumentException("Đơn vị sở hữu không tồn tại"));
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
