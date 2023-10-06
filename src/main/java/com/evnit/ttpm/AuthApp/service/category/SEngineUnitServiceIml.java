package com.evnit.ttpm.AuthApp.service.category;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.category.SEngineUnit;
import com.evnit.ttpm.AuthApp.mapper.EngineUnitMapper;
import com.evnit.ttpm.AuthApp.model.category.EngineUnit.EngineUnitCreateDto;
import com.evnit.ttpm.AuthApp.model.category.EngineUnit.EngineUnitListDto;
import com.evnit.ttpm.AuthApp.repository.category.EngineUnitRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SEngineUnitServiceIml implements SEngineUnitService {
	@Autowired
	EngineUnitRepository engineUnitRepository;
	@Autowired
	JdbcTemplate jdbcTemplate;
	private final ModelMapper mapper;
	
	public SEngineUnitServiceIml(EngineUnitMapper mapperConfig) {
		this.mapper = mapperConfig.getModelMapper();
	}
	@Override
	public ResponseData getAll() {
		ResponseData response = new ResponseData();
		try {
			response.setState(ResponseData.STATE.OK.toString());
			response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
			var listTbaRgl = engineUnitRepository.findAll();
			var result = listTbaRgl.stream().map(tbaRgl -> mapper.map(tbaRgl, EngineUnitListDto.class));

           response.setData(result);
		}catch (Exception ex){
			response.setState(ResponseData.STATE.FAIL.toString());
			response.setMessage(ex.getMessage());
			response.setData(null);
		}
		return response;
	}
//	@Override
//	public ResponseData getNmdID() {
//		ResponseData response = new ResponseData();
//		try {
//			response.setState(ResponseData.STATE.OK.toString());
//			response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
//			
//			var listTbaRgl = engineUnitRepository.findByNmdIdIsNull(0);
//			var result = listTbaRgl.stream().map(tbaRgl -> mapper.map(tbaRgl, EngineUnitCreateDto.class));
//           response.setData(result);
//		}catch (Exception ex){
//			response.setState(ResponseData.STATE.FAIL.toString());
//			response.setMessage(ex.getMessage());
//			response.setData(null);
//		}
//		return response;
//	}

	@Override
	public ResponseData getById(int id) {
		return null;
	}

	@Override
	public ResponseData create(EngineUnitCreateDto createDto) {
		ResponseData response = new ResponseData();
		try {
			var entity = mapper.map(createDto, SEngineUnit.class);
			entity.setIs_Delete(false);
			entity.setNmdId(createDto.getNmdId());
			var result = engineUnitRepository.save(entity);
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
	public ResponseData update(int id, EngineUnitCreateDto updateDto) {
		ResponseData response = new ResponseData();
		try {
			var existEntity = engineUnitRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Nhà máy điện không tồn tại"));
			existEntity.setIs_Delete(false);
			updateDto.setIs_Delete(false);
			mapper.map(updateDto, existEntity);

			var result = engineUnitRepository.save(existEntity);
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
			var result = engineUnitRepository.findById(id).map(tbaRgl -> {
				tbaRgl.setIs_Delete(true);
				tbaRgl.setNmdId("0");
				return engineUnitRepository.save(tbaRgl);
			}).orElseThrow(() -> new IllegalArgumentException("Tổ máy không tồn tại"));
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
	        var entityOptional = engineUnitRepository.findById(id);
	        if (entityOptional.isPresent()) {
	            var entity = entityOptional.get();
	            engineUnitRepository.delete(entity);
	            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
	            response.setState(ResponseData.STATE.OK.toString());
	        } else {
	            throw new IllegalArgumentException("Trạm biến áp/ranh giới lẻ không tồn tại");
	        }
	    } catch (Exception ex) {
	        response.setState(ResponseData.STATE.FAIL.toString());
	        response.setMessage(ex.getMessage());
	        response.setData(null);
	    }
	    return response;
	}
}
