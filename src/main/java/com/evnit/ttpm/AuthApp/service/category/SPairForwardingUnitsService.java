package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.model.category.PairForwardingUnits.PairForwardingUnitsCreateDto;
import com.evnit.ttpm.AuthApp.model.category.PairForwardingUnits.SearchUnit;

public interface SPairForwardingUnitsService {
	
	ResponseData getAll();

    ResponseData getList(SearchUnit dto);

    ResponseData getById(int id);
	ResponseData create(PairForwardingUnitsCreateDto createDto);
	ResponseData update(int id, PairForwardingUnitsCreateDto updateDto);
	ResponseData delete(int id);
}
