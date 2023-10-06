package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.model.category.DeliveryUnit.SearchDeliveryUnit;
import com.evnit.ttpm.AuthApp.model.category.TinhTP.TinhTPCreateDto;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

public interface SCategoryTinhTPService {

	ResponseData getAll();
	ResponseData getById(int id);
	ResponseData create(TinhTPCreateDto createDto);
	ResponseData update(int id,TinhTPCreateDto updateDto);
	ResponseData delete(int id);

    ResponseData getList(SearchDeliveryUnit dto);

    ResponseData fetchTinhTPDataAsPageWithFiltering(String nameFilter, int domain, int page, int size);
}
//