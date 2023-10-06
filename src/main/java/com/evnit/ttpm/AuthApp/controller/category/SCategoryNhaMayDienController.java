package com.evnit.ttpm.AuthApp.controller.category;

import java.util.List;

import com.evnit.ttpm.AuthApp.annotation.CurrentUser;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.model.category.nhamaydien.SearchNMD;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.admin.ResponseDataReport;
import com.evnit.ttpm.AuthApp.entity.category.SElectricFactory;
import com.evnit.ttpm.AuthApp.entity.category.SEngineUnit;
import com.evnit.ttpm.AuthApp.entity.category.SLicenseOperate;
import com.evnit.ttpm.AuthApp.mapper.EngineUnitMapper;
import com.evnit.ttpm.AuthApp.mapper.LicenseOperateMapper;
import com.evnit.ttpm.AuthApp.model.category.EngineUnit.EngineUnitCreateDto;
import com.evnit.ttpm.AuthApp.model.category.LicenseOperate.LicenseOperateCreateDto;
import com.evnit.ttpm.AuthApp.model.category.nhamaydien.NhaMayDienCrudDto;
import com.evnit.ttpm.AuthApp.repository.category.EngineUnitRepository;
import com.evnit.ttpm.AuthApp.repository.category.LicenseOperateRepositry;
import com.evnit.ttpm.AuthApp.service.category.SEngineUnitService;
import com.evnit.ttpm.AuthApp.service.category.SLicenseOperateServcie;
import com.evnit.ttpm.AuthApp.service.category.SNhaMayDienService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api/category/nhamaydien")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true", maxAge = 3600)

public class SCategoryNhaMayDienController {
	
	private final SNhaMayDienService service;
	
	private final SEngineUnitService engineUnitservice;
	private final SLicenseOperateServcie licenseOperateservice;
	@Autowired
	EngineUnitRepository engineUnitRepository;
	
	@Autowired
	LicenseOperateRepositry licenseOperateRepository;
	
	private final ModelMapper mapper;
	private final ModelMapper mapper1;
	
	public SCategoryNhaMayDienController(SNhaMayDienService service, SEngineUnitService engineUnitservice,EngineUnitMapper mapperConfig,LicenseOperateMapper mapperConfig1,SLicenseOperateServcie licenseOperateservice) {
		this.service = service;
		this.engineUnitservice = engineUnitservice;
		this.mapper = mapperConfig.getModelMapper();
		this.mapper1 = mapperConfig1.getModelMapper();
		this.licenseOperateservice =licenseOperateservice;
	}

	@ApiOperation(value = "Hàm lấy danh sách nhà máy điện", response = ResponseDataReport.class)
	@ApiResponses({ @ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "") })
	@GetMapping()

	public ResponseEntity<?> getList(SearchNMD filter) {
		try {
			return ResponseEntity.ok(service.getList(filter));
		} catch (Exception ex) {
			return ResponseEntity.ok(new ResponseData(ResponseData.STATE.FAIL.toString(), ex.getMessage()));
		}
	}

	@ApiOperation(value = "Hàm thêm mới nhà máy điện", response = ResponseDataReport.class)
	@ApiResponses({ @ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "") })
	@PostMapping
	public ResponseEntity<?> create(@CurrentUser CustomUserDetails user, @RequestBody NhaMayDienCrudDto crudDto) {
		crudDto.setUserId(user.getUserid());
		EngineUnitCreateDto updateEngineUnit = new EngineUnitCreateDto();
		LicenseOperateCreateDto licenseOperateDto = new LicenseOperateCreateDto();
		ResponseData a = service.create(crudDto);
		
		//return ResponseEntity.ok(a);
		if (a.getState().equals(ResponseData.STATE.OK.toString()) && a.getData() != null) {
			String id;
	        if (a.getData() instanceof SElectricFactory) {
	            id = ((SElectricFactory) a.getData()).getId();
	            
	        } else {
	            // Xử lý trường hợp kiểu dữ liệu của data không khớp
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	        }
	        
	        // Cập nhật nmdId trên SEngineUnit
			List<SEngineUnit> engineUnitList1 = engineUnitRepository.findByNmdIdNull();
			for (SEngineUnit engineUnit : engineUnitList1) {
				mapper.map(engineUnit, updateEngineUnit);
				updateEngineUnit.setNmdId(id);
				engineUnitservice.update(engineUnit.getId(), updateEngineUnit); // Update the method signature accordingly
			}

	        /*List<SEngineUnit> engineUnitList = engineUnitRepository.findByNmdIdIsNull("0");
            
            for (SEngineUnit engineUnit : engineUnitList) {
            	mapper.map(engineUnit, updateEngineUnit);
            	updateEngineUnit.setNmdId(id);
                engineUnitservice.update(engineUnit.getId(), updateEngineUnit); // Update the method signature accordingly
            }*/
            
            List<SLicenseOperate> licenseOperateList = licenseOperateRepository.findByNmdIdNull();
            
            for (SLicenseOperate licenseOperate : licenseOperateList) {
            	mapper.map(licenseOperate, licenseOperateDto);
            	licenseOperateDto.setNmdId(id);
            	licenseOperateservice.update(licenseOperate.getId(), licenseOperateDto); // Update the method signature accordingly
            }
	        
	        
	        
	    } else {
	        // Xử lý trường hợp lỗi nếu cần
	       // return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    }
		return ResponseEntity.ok(a);
	}

	@ApiOperation(value = "Hàm cập nhật nhà máy điện", response = ResponseDataReport.class)
	@ApiResponses({ @ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "") })
	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable("id") String id, @RequestBody NhaMayDienCrudDto crudDto,@CurrentUser CustomUserDetails user) {
		crudDto.setUserId(user.getUserid());
		EngineUnitCreateDto updateEngineUnit = new EngineUnitCreateDto();
		LicenseOperateCreateDto licenseOperateDto = new LicenseOperateCreateDto();
		ResponseData a = service.update(id, crudDto);
		if (a.getState().equals(ResponseData.STATE.OK.toString()) && a.getData() != null){
			/*if (a.getData() instanceof SElectricFactory) {
				id = ((SElectricFactory) a.getData()).getId();

			} else {
				// Xử lý trường hợp kiểu dữ liệu của data không khớp
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
*/
			// Cập nhật nmdId trên SEngineUnit
			List<SEngineUnit> engineUnitList = engineUnitRepository.findByNmdIdNull();

			for (SEngineUnit engineUnit : engineUnitList) {
				mapper.map(engineUnit, updateEngineUnit);
				updateEngineUnit.setNmdId(id);
				engineUnitservice.update(engineUnit.getId(), updateEngineUnit); // Update the method signature accordingly
			}

			List<SLicenseOperate> licenseOperateList = licenseOperateRepository.findByNmdIdNull();

			for (SLicenseOperate licenseOperate : licenseOperateList) {
				mapper.map(licenseOperate, licenseOperateDto);
				licenseOperateDto.setNmdId(id);
				licenseOperateservice.update(licenseOperate.getId(), licenseOperateDto); // Update the method signature accordingly
			}



		} else {
			// Xử lý trường hợp lỗi nếu cần
			// return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		return ResponseEntity.ok(a);
	}
	@ApiOperation(value = "Hàm xóa nhà máy điện", response = ResponseDataReport.class)
	@ApiResponses({ @ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "") })
	@DeleteMapping("/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		ResponseData a = service.delete(id);
		if(a.getState()==ResponseData.STATE.OK.toString()){
			List<SEngineUnit> engineUnitList = engineUnitRepository.findByNmdIdIsNull(id);
			for (SEngineUnit engineUnit : engineUnitList) {
				engineUnitservice.delete(engineUnit.getId()); // Update the method signature accordingly
			}
			List<SLicenseOperate> licenseOperateList = licenseOperateRepository.findByNmdIdIsNull(id);

			for (SLicenseOperate licenseOperate : licenseOperateList) {
				licenseOperateservice.delete(licenseOperate.getId()); // Update the method signature accordingly
			}

		}
		return ResponseEntity.ok(a);
	}
	
	@ApiOperation(value = "Hàm xóa nhà máy điện", response = ResponseDataReport.class)
	@ApiResponses({ @ApiResponse(code = 404, message = "No user found"), @ApiResponse(code = 500, message = "") })
	@DeleteMapping("/delete")
	public ResponseEntity<?> delete1() {
		List<SEngineUnit> engineUnitList = engineUnitRepository.findByNmdIdIsNull("0");
		 for (SEngineUnit engineUnit : engineUnitList) {
             engineUnitservice.delete1(engineUnit.getId()); // Update the method signature accordingly
         }
		 List<SLicenseOperate> licenseOperateList = licenseOperateRepository.findByNmdIdIsNull("0");
         
         for (SLicenseOperate licenseOperate : licenseOperateList) {
             engineUnitservice.delete1(licenseOperate.getId()); // Update the method signature accordingly
         }
		 
		return ResponseEntity.ok(0);
	}
}
