package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.category.*;
import com.evnit.ttpm.AuthApp.entity.device.ViewDiemDo;
import com.evnit.ttpm.AuthApp.mapper.QuanLyDDMapper;
import com.evnit.ttpm.AuthApp.model.acceptance.AcceptanceListDto;
import com.evnit.ttpm.AuthApp.model.category.QuanLyDD.*;
import com.evnit.ttpm.AuthApp.model.category.ThoaThuan.SearchThoaThuanList;
import com.evnit.ttpm.AuthApp.model.category.ThoaThuan.ThoaThuanListDto;
import com.evnit.ttpm.AuthApp.model.category.nhamaydien.NhaMayDienCrudDto;
import com.evnit.ttpm.AuthApp.model.filter.SearchFilter;
import com.evnit.ttpm.AuthApp.model.filter.SearchQuery;
import com.evnit.ttpm.AuthApp.repository.category.A_ASSETRepository;
import com.evnit.ttpm.AuthApp.repository.category.QuanLyDDRepository;
import com.evnit.ttpm.AuthApp.repository.category.S_ATTRIBUTE_GROUP_ASSOBJRepository;
import com.evnit.ttpm.AuthApp.repository.category.ZAG_DIEMDORepository;
import com.evnit.ttpm.AuthApp.service.system.WonderHisService;
import com.evnit.ttpm.AuthApp.util.SpecificationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

import javax.persistence.Transient;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SCategoryQuanLyDDServiceImpl implements SCategoryQuanLyDDService {
	@Autowired
	QuanLyDDRepository QuanLyDDRepository;
	 @Autowired
	    JdbcTemplate jdbcTemplate;
	@Autowired
	A_ASSETRepository A_ASSETRepository;
	@Autowired
	S_ATTRIBUTE_GROUP_ASSOBJRepository S_ATTRIBUTE_GROUP_ASSOBJRepository;
	@Autowired
	ZAG_DIEMDORepository ZAG_DIEMDORepository;
	@Autowired
	WonderHisService _wonderHisService;

	@Transient
	private UUID corrId = UUID.randomUUID();
	private final ModelMapper mapper;
	public SCategoryQuanLyDDServiceImpl(QuanLyDDMapper mapperConfig) {
		this.mapper = mapperConfig.getModelMapper();
	}

	@Override
	public ResponseData getAll(SearchQLDDList dto) {
		ResponseData response = new ResponseData();




		SearchQuery searchQuery = new SearchQuery();
		List<QuanLyDDListDto> qldd = new ArrayList<QuanLyDDListDto>();
		SearchFilter searchFilter3 = new SearchFilter();
		List<SearchFilter> searchFilters = new ArrayList<>();
		if (dto.getType() != null && !dto.getType().isEmpty()) {
			searchFilter3 = new SearchFilter("TYPE", "IN", dto.getType());
			searchFilters.add(searchFilter3);
		}
		if (dto.getTbaId() != null && !dto.getTbaId().isEmpty()) {
			searchFilter3 = new SearchFilter("TBAID", "IN", dto.getTbaId());
			searchFilters.add(searchFilter3);
		}
		if (dto.getTbaStatus() != null && !dto.getTbaStatus().isEmpty()){
			searchFilter3 = new SearchFilter("TBASTATUS", "IN", dto.getTbaStatus());
			searchFilters.add(searchFilter3);
		}
		if (dto.getStatus() != null && !dto.getStatus().isEmpty()){
			searchFilter3 = new SearchFilter("STATUS", "IN", dto.getStatus());
			searchFilters.add(searchFilter3);
		}
		if(dto.getNameDD() !=null && !dto.getNameDD().isEmpty()){
			searchFilter3 = new SearchFilter("NAME","LIKE",dto.getNameDD());
			searchFilters.add(searchFilter3);
		}
		if(dto.getCodeDD() !=null && !dto.getCodeDD().isEmpty()){
			searchFilter3 = new SearchFilter("CODE","LIKE",dto.getCodeDD());
			searchFilters.add(searchFilter3);
		}
		if (dto.getDonVi1() != null && !dto.getDonVi1().isEmpty()) {
			searchFilter3 = new SearchFilter("PTGN1DVGN", "IN", dto.getDonVi1());
			searchFilters.add(searchFilter3);
		}
		if (dto.getDonVi2() != null && !dto.getDonVi2().isEmpty()) {
			searchFilter3 = new SearchFilter("PTGN2DVGN", "IN", dto.getDonVi2());
			searchFilters.add(searchFilter3);
		}if (dto.getTinhChat1() != null && !dto.getTinhChat1().isEmpty()) {
			searchFilter3 = new SearchFilter("PTGN1TC", "IN", dto.getTinhChat1());
			searchFilters.add(searchFilter3);
		}
		if (dto.getTinhChat2() != null && !dto.getTinhChat2().isEmpty()) {
			searchFilter3 = new SearchFilter("PTGN2TC", "IN", dto.getTinhChat2());
			searchFilters.add(searchFilter3);
		}
		if(dto.getDongDien() !=null && !dto.getDongDien().isEmpty()){
			searchFilter3 = new SearchFilter("MNTTI","LIKE",dto.getDongDien(),"OR");
			searchFilters.add(searchFilter3);
		}
		if(dto.getDongDien() !=null && !dto.getDongDien().isEmpty()){
			searchFilter3 = new SearchFilter("DLTI","LIKE",dto.getDongDien(),"OR");
			searchFilters.add(searchFilter3);
		}
		if(dto.getDongDien() !=null && !dto.getDongDien().isEmpty()){
			searchFilter3 = new SearchFilter("CCXTI","LIKE",dto.getDongDien(),"OR");
			searchFilters.add(searchFilter3);
		}
		if(dto.getDongDien() !=null && !dto.getDongDien().isEmpty()){
			searchFilter3 = new SearchFilter("TSBTI","LIKE",dto.getDongDien(),"OR");
			searchFilters.add(searchFilter3);
		}
		if(dto.getDongDien() !=null && !dto.getDongDien().isEmpty()){
			searchFilter3 = new SearchFilter("VLTDTI","LIKE",dto.getDongDien(),"OR");
			searchFilters.add(searchFilter3);
		}
		if(dto.getBienAp() !=null && !dto.getBienAp().isEmpty()){
			searchFilter3 = new SearchFilter("VLTDTU","LIKE",dto.getBienAp(),"OR");
			searchFilters.add(searchFilter3);
		}
		if(dto.getBienAp() !=null && !dto.getBienAp().isEmpty()){
			searchFilter3 = new SearchFilter("TSBTU","LIKE",dto.getBienAp(),"OR");
			searchFilters.add(searchFilter3);
		}if(dto.getBienAp() !=null && !dto.getBienAp().isEmpty()){
			searchFilter3 = new SearchFilter("CCXTU","LIKE",dto.getBienAp(),"OR");
			searchFilters.add(searchFilter3);
		}if(dto.getBienAp() !=null && !dto.getBienAp().isEmpty()){
			searchFilter3 = new SearchFilter("DLTU","LIKE",dto.getBienAp(),"OR");
			searchFilters.add(searchFilter3);
		}
		if(dto.getBienAp() !=null && !dto.getBienAp().isEmpty()){
			searchFilter3 = new SearchFilter("MNTTU","LIKE",dto.getBienAp(),"OR");
			searchFilters.add(searchFilter3);
		}




		Pageable pageable = PageRequest.of(dto.getPage(), dto.getLimit(), dto.getSortExpression());
		searchQuery.setSearchFilters(searchFilters);
		Specification<DMCXNK> spec = SpecificationUtil.bySearchQuery(searchQuery, DMCXNK.class);

		try {
			var pageResult = QuanLyDDRepository.findAll(spec,pageable);
			var dtoResult = pageResult.getContent().stream().map(item -> mapper.map(item, QuanLyDDListDto.class)).collect(Collectors.toList());
			Page<QuanLyDDListDto> result = new PageImpl<>(dtoResult, pageable, pageResult.getTotalElements());
			response.setData(result);
			response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
			response.setState(ResponseData.STATE.OK.toString());
			response.setData(result);
		}catch (Exception ex){
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
	public ResponseData create(QuanLyDDCreateDto createDto) {
		ResponseData response = new ResponseData();

		String sql1;
		sql1 = "select  1 from Q_PQOBJ_USER where ASSETID =? and USERID=? and ISQL=?";
		List lst = jdbcTemplate.queryForList(sql1, createDto.getTbaid(), createDto.getUserId(), true);
		if (lst == null || lst.isEmpty()){
			return new ResponseData(ResponseData.STATE.FAIL.toString(), "Không có quyền thêm mới điểm đo");
		}
		
		try {
			
			AssetCreateDto ass = new AssetCreateDto();
			var guid =UUID.randomUUID().toString();
			var guid1 =UUID.randomUUID().toString();


			ass.setASSETID(guid);
			ass.setASSETID_LINK(createDto.getCode());

			ass.setASSETDESC(createDto.getName());
			if(createDto.getStatus() != null){
				ass.setUSESTATUS_LAST_ID(createDto.getStatus().toString());
			}
			if(createDto.getType() == 1){
				ass.setTYPEID("NM");

			}else if (createDto.getType() ==2){
				ass.setTYPEID("TBA");

			}
			else if(createDto.getType() == 3){
				ass.setTYPEID("RGL");

			}
			ass.setCATEGORYID("DIEMDO");
            if(createDto.getUnit() == null){
                ass.setORGID("");

            }
            else{
                ass.setORGID(createDto.getUnit());

            }
			ass.setASSETNOTE(createDto.getNote());
			ass.setP_INSTALLDATE(createDto.getDateincome());
			ass.setASSETLINK(createDto.getTbaid());
			ass.setASSETID_PARENT(createDto.getTbaid());

			ass.setISDELETE(false);

			var entity = mapper.map(ass, A_ASSET.class);
			var result = A_ASSETRepository.save(entity);
			
			
			ZangDiemDoCreateDto zang = new ZangDiemDoCreateDto();

			zang.setATTRDATAID(guid1);
			zang.setOBJTYPEID("A");
			zang.setOBJID(guid);
			zang.setNTHU_TTSL(createDto.getDateincome());
			zang.setTINH_CHAT1(createDto.getPtgn1TC());
			zang.setTINH_CHAT2(createDto.getPtgn2TC());
			zang.setDVI_GNHAN1(createDto.getPtgn1DVGN());
			zang.setDVI_GNHAN2(createDto.getPtgn2DVGN());
			zang.setXNK(createDto.getXnk());
			zang.setDTAC_XNK(createDto.getDtxnk());

			var entityDD = mapper.map(zang, ZAG_DIEMDO.class);
			var resultDD = ZAG_DIEMDORepository.save(entityDD);
			
			
			SagaCreateDto saga = new SagaCreateDto();
			saga.setOBJID(guid);
			saga.setOBJTYPEID("A");
			saga.setATTRGROUPID("DIEMDO");
			saga.setATTRDATAID(guid1);

			var entityTG = mapper.map(saga, S_ATTRIBUTE_GROUP_ASSOB.class);
			var resultTG = S_ATTRIBUTE_GROUP_ASSOBJRepository.save(entityTG);

			//lưu his
			List<String> ignoredFields = new ArrayList<>(){{
				add("is_delete");
				add("userid");
				add("unit");
				add("type");
				add("tbaid");
				add("status");
				add("ptgn1dvgn");
				add("ptgn2dvgn");
				add("dateincome");
				add("dtxnk");
				add("dateincome");
				add("id");

			}};
			Map<String, String> variableNameMapping = new HashMap<>();
			variableNameMapping.put("ptgn1dvgnname","PTGN1- ĐV giao nhận");
			variableNameMapping.put("ptgn2dvgnname","PTGN2- ĐV giao nhận");
			variableNameMapping.put("ptgn1tc","PTGN1- Tính chất");
			variableNameMapping.put("ptgn2tc","PTGN2- Tính chất");
			variableNameMapping.put("code","Mã điểm đo");
			variableNameMapping.put("name","Tên điểm đo");
			variableNameMapping.put("unitname","Đơn vị sở hữu");
			variableNameMapping.put("statusstr","Trạng thái");
			variableNameMapping.put("xnkname","XNK");
			variableNameMapping.put("dtxnkname","Đối tác XNK");
			variableNameMapping.put("dateincomestr","Ngày nghiệm thu hệ thống thu thập số liệu");
			variableNameMapping.put("typename","Kiểu");
			variableNameMapping.put("tbaname","Tên NMĐ/TBA/RGL");
			variableNameMapping.put("note","Mô tả");
			_wonderHisService.UpdateHis(guid, createDto,"",ignoredFields,variableNameMapping, createDto.getUserId(), "DEVICE_DD", "INS");



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
	public ResponseData update(String id, QuanLyDDCreateDto updateDto) {
		ResponseData response = new ResponseData();
		String sql1;
		sql1 = "select  1 from Q_PQOBJ_USER where ASSETID =? and USERID=? and ISQL=?";
		List lst = jdbcTemplate.queryForList(sql1, updateDto.getTbaid(), updateDto.getUserId(), true);
		if (lst == null || lst.isEmpty()){
			return new ResponseData(ResponseData.STATE.FAIL.toString(), "Không có quyền cập nhật điểm đo");
		}
		try {
			String sql2;
			sql2 = "select  * from VIEW_QLDD where KeyId = '" + id + "'";

			List<DMCXNK> lst2 = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<DMCXNK>(DMCXNK.class));
//			if(lst2.get(0).getSTATUS() == 2 && updateDto.getDatestatic() !=null && updateDto.getStatus() !="2"){
//				return new ResponseData(ResponseData.STATE.FAIL.toString(), "Điểm đo đã nghiệm thu tĩnh lần đầu vui lòng chọn lại trạng thái");
//			}


	var existEntity = A_ASSETRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Quản lý điểm đo không tồn tại"));

			AssetCreateDto ass = new AssetCreateDto();
			existEntity.setASSETID(id);
			existEntity.setASSETID_LINK(updateDto.getCode());

			existEntity.setASSETDESC(updateDto.getName());
			if(updateDto.getType() == 1){
				ass.setTYPEID("NM");

			}else if (updateDto.getType() ==2){
				ass.setTYPEID("TBA");

			}
			else if(updateDto.getType() == 3){
				ass.setTYPEID("RGL");

			}
			ass.setCATEGORYID("DIEMDO");
            if(updateDto.getUnit() == null){
                existEntity.setORGID("");

            }
            else{
                existEntity.setORGID(updateDto.getUnit());

            }
			existEntity.setASSETNOTE(updateDto.getNote());
			existEntity.setP_INSTALLDATE(updateDto.getDateincome());
			existEntity.setASSETLINK(updateDto.getTbaid());
			existEntity.setASSETID_PARENT(updateDto.getTbaid());

			existEntity.setISDELETE(false);
			if(updateDto.getStatus() != null){
				existEntity.setUSESTATUS_LAST_ID(updateDto.getStatus().toString());
			}
			mapper.map(updateDto, existEntity);
			A_ASSETRepository.save(existEntity);




			SAttrGroupAssObjId objId = new SAttrGroupAssObjId();
			objId.setOBJID(id);
			objId.setATTRGROUPID("DIEMDO");
			objId.setOBJTYPEID("A");
			var existEntity1 = S_ATTRIBUTE_GROUP_ASSOBJRepository.findById(objId).orElseThrow(() -> new IllegalArgumentException("Quản lý điểm đo không tồn tại"));

			SagaCreateDto saga = new SagaCreateDto();
			existEntity1.setOBJID(id);
			existEntity1.setOBJTYPEID("A");
			existEntity1.setATTRGROUPID("DIEMDO");
			//saga.setATTRDATAID(guid);
//
			mapper.map(updateDto, existEntity1);
			 S_ATTRIBUTE_GROUP_ASSOBJRepository.save(existEntity1);
			
			 
				var existEntity2 = ZAG_DIEMDORepository.findById(existEntity1.getATTRDATAID()).orElseThrow(() -> new IllegalArgumentException("Quản lý điểm đo không tồn tại"));
				ZangDiemDoCreateDto zang = new ZangDiemDoCreateDto();



				existEntity2.setOBJID(id);
				existEntity2.setNTHU_TTSL(updateDto.getDateincome());
				existEntity2.setTINH_CHAT1(updateDto.getPtgn1TC());
				existEntity2.setTINH_CHAT2(updateDto.getPtgn2TC());
				existEntity2.setDVI_GNHAN1(updateDto.getPtgn1DVGN());
				existEntity2.setDVI_GNHAN2(updateDto.getPtgn2DVGN());
				existEntity2.setXNK(updateDto.getXnk());
				existEntity2.setDTAC_XNK(updateDto.getDtxnk());
				mapper.map(updateDto, existEntity2);
				 var result = ZAG_DIEMDORepository.save(existEntity2);


			//lưu his

			QuanLyDDCreateDto dto = mapper.map(existEntity2,QuanLyDDCreateDto.class);
			dto.setPtgn1TC(lst2.get(0).getPTGN1TC());
			dto.setPtgn2TC(lst2.get(0).getPTGN2TC());
			dto.setPtgn1DVGNNAME(lst2.get(0).getPTGN1DVGNNAME());
			dto.setPtgn2DVGNNAME(lst2.get(0).getPTGN2DVGNNAME());
			dto.setXnkName(lst2.get(0).getXNKNAME());
			dto.setDtxnkName(lst2.get(0).getDTXNKNAME());
			dto.setName(lst2.get(0).getNAME());
			dto.setCode(lst2.get(0).getCODE());
			dto.setUnitname(lst2.get(0).getUNITNAME());
			dto.setDateincomeStr(lst2.get(0).getDATEINCOMESTR());
			dto.setDateload((Date) lst2.get(0).getDATELOAD());
			dto.setDatestatic((Date) lst2.get(0).getDatestatic());
			dto.setTbaName(lst2.get(0).getTBANAME());
			dto.setNote(lst2.get(0).getNOTE());
			dto.setStatusname(lst2.get(0).getSTATUSNAME());
			dto.setTypeName(lst2.get(0).getTYPENAME());
			dto.setId(lst2.get(0).getID());



			List<String> ignoredFields = new ArrayList<>(){{
				add("is_delete");
				add("userid");
				add("unit");
				add("type");
				add("tbaid");
				add("status");
				add("ptgn1dvgn");
				add("ptgn2dvgn");
				add("dateincome");
				add("dtxnk");
				add("dateincome");


			}};
			Map<String, String> variableNameMapping = new HashMap<>();
			variableNameMapping.put("ptgn1dvgnname","PTGN1- ĐV giao nhận");
			variableNameMapping.put("ptgn2dvgnname","PTGN2- ĐV giao nhận");
			variableNameMapping.put("ptgn1tc","PTGN1- Tính chất");
			variableNameMapping.put("ptgn2tc","PTGN2- Tính chất");
			variableNameMapping.put("code","Mã điểm đo");
			variableNameMapping.put("name","Tên điểm đo");
			variableNameMapping.put("unitname","Đơn vị sở hữu");
			variableNameMapping.put("xnkname","XNK");
			variableNameMapping.put("dtxnkname","Đối tác XNK");
			variableNameMapping.put("dateincomestr","Ngày nghiệm thu hệ thống thu thập số liệu");
			variableNameMapping.put("typename","Kiểu");
			variableNameMapping.put("tbaname","Tên NMĐ/TBA/RGL");
			variableNameMapping.put("note","Mô tả");




			_wonderHisService.UpdateHis(id, updateDto,dto,ignoredFields,variableNameMapping, updateDto.getUserId(), "DEVICE_DD", "UPDATE");

			//var existEntity1 = ZAG_DIEMDORepository.findOne(getBy).orElseThrow(() -> new IllegalArgumentException("Quản lý điểm đo không tồn tại"));

//			ZangDiemDoCreateDto zang = new ZangDiemDoCreateDto();
//            var guid =UUID.randomUUID().toString();
//
//			//zang.setATTRDATAID(guid);
//			zang.setOBJTYPEID("A");
//			zang.setOBJID(updateDto.getCode());
//			zang.setNTHU_TTSL(updateDto.getDateincome());
//			zang.setTINH_CHAT1(updateDto.getPtgn1tc());
//			zang.setTINH_CHAT2(updateDto.getPtgn2tc());
//			zang.setDVI_GNHAN1(updateDto.getPtgn1dvgn());
//			zang.setDVI_GNHAN2(updateDto.getPtgn2dvgn());
//			zang.setXNK(updateDto.getXnk());
//			zang.setDTAC_XNK(updateDto.getDtxnk());
//			mapper.map(zang, existEntity1);
//
//			var entityDD = mapper.map(zang, ZAG_DIEMDO.class);
//			var resultDD = ZAG_DIEMDORepository.save(entityDD);
//			var existEntity2 = A_ASSETRepository.fin(updateDto.getCode()).orElseThrow(() -> new IllegalArgumentException("Quản lý điểm đo không tồn tại"));
//
//			
		
	
//			existEntity.setID(id);
//			var result = QuanLyDDRepository.save(existEntity);
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
	public ResponseData delete(String code) {
		ResponseData response = new ResponseData();
		try {
			var result = A_ASSETRepository.findById(code).map(tbaRgl -> {
				tbaRgl.setISDELETE(true);
				return A_ASSETRepository.save(tbaRgl);
			}).orElseThrow(() -> new IllegalArgumentException("Trạm biến áp/ranh giới lẻ không tồn tại"));
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

