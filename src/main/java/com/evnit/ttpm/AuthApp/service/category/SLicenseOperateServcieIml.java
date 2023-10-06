package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.model.category.ThoaThuan.ThoaThuanCreateDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.category.SLicenseOperate;
import com.evnit.ttpm.AuthApp.mapper.LicenseOperateMapper;
import com.evnit.ttpm.AuthApp.model.category.LicenseOperate.LicenseOperateCreateDto;
import com.evnit.ttpm.AuthApp.model.category.LicenseOperate.LicenseOperateListDto;
import com.evnit.ttpm.AuthApp.repository.category.LicenseOperateRepositry;

@Service
public class SLicenseOperateServcieIml implements SLicenseOperateServcie{
	@Autowired
	LicenseOperateRepositry licenseOperateRepository;
	private final ModelMapper mapper;
	public SLicenseOperateServcieIml(LicenseOperateMapper mapperConfig) {
		this.mapper = mapperConfig.getModelMapper();
	}

	@Override
	public ResponseData getAll() {
		ResponseData response = new ResponseData();
		try {
			response.setState(ResponseData.STATE.OK.toString());
			response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
			var listTbaRgl = licenseOperateRepository.findAll();
			var result = listTbaRgl.stream().map(tbaRgl -> mapper.map(tbaRgl, LicenseOperateListDto.class));
//
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
	public ResponseData create(LicenseOperateCreateDto createDto) {
		ResponseData response = new ResponseData();
		try {
			LicenseOperateCreateDto createDto1 = new LicenseOperateCreateDto();
			if(createDto.getEndDate() != null && createDto.getStartDate() != null){
				if (createDto.getStartDate().after(createDto.getEndDate())) {
					return new ResponseData(ResponseData.STATE.FAIL.toString(), "Vui lòng chọn ngày kết thúc hiệu lực >= Ngày bắt đầu hiệu lực");

				}}

			var entity = mapper.map(createDto, SLicenseOperate.class);
			entity.setIs_Delete(false);
			entity.setNmdId(createDto.getNmdId());
			var result = licenseOperateRepository.save(entity);
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
	public ResponseData update(int id, LicenseOperateCreateDto updateDto) {
		ResponseData response = new ResponseData();
		try {
			LicenseOperateCreateDto createDto1 = new LicenseOperateCreateDto();
			if(updateDto.getEndDate() != null && updateDto.getStartDate() != null){
				if (updateDto.getStartDate().after(updateDto.getEndDate())) {
					return new ResponseData(ResponseData.STATE.FAIL.toString(), "Vui lòng chọn ngày kết thúc hiệu lực >= Ngày bắt đầu hiệu lực");

				}}
			var existEntity = licenseOperateRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Nhà máy điện không tồn tại"));
			updateDto.setIs_Delete(false);
			/*if(updateDto.getIs_Delete()!=true){
				existEntity.setIs_Delete(true);
			}
			else {
				existEntity.setIs_Delete(false);
			}*/
			mapper.map(updateDto, existEntity);
			var result = licenseOperateRepository.save(existEntity);
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
			var result = licenseOperateRepository.findById(id).map(tbaRgl -> {
				tbaRgl.setIs_Delete(true);
				tbaRgl.setNmdId("0");
				return licenseOperateRepository.save(tbaRgl);
			}).orElseThrow(() -> new IllegalArgumentException("Giấy phép không tồn tại"));
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
	@Override
	public ResponseData delete1(int id) {
		ResponseData response = new ResponseData();
	    try {
	        var entityOptional = licenseOperateRepository.findById(id);
	        if (entityOptional.isPresent()) {
	            var entity = entityOptional.get();
	            licenseOperateRepository.delete(entity);
	            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
	            response.setState(ResponseData.STATE.OK.toString());
	        } else {
	            throw new IllegalArgumentException("Giấy phép không tồn tại");
	        }
	    } catch (Exception ex) {
	        response.setState(ResponseData.STATE.FAIL.toString());
	        response.setMessage(ex.getMessage());
	        response.setData(null);
	    }
	    return response;
	}
}
