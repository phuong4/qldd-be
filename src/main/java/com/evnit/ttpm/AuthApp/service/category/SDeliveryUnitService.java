package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.model.category.DeliveryUnit.DeliveryUnitCreateDto;
import com.evnit.ttpm.AuthApp.model.category.DeliveryUnit.SearchDeliveryUnit;
import com.evnit.ttpm.AuthApp.model.category.PairForwardingUnits.SearchUnit;

public interface SDeliveryUnitService {
	ResponseData getAll();
	ResponseData getList(SearchDeliveryUnit dto);

	ResponseData getById(int id);
	ResponseData create(DeliveryUnitCreateDto createDto);
	ResponseData update(int id,DeliveryUnitCreateDto updateDto);
	ResponseData delete(int id);
}
