package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.model.category.EngineUnit.EngineUnitCreateDto;

public interface SEngineUnitService {
	ResponseData getAll();
	ResponseData getById(int id);
	ResponseData create(EngineUnitCreateDto createDto);
	ResponseData update(int id, EngineUnitCreateDto updateDto);
	ResponseData delete(int id);
	ResponseData delete1(int id);
}
