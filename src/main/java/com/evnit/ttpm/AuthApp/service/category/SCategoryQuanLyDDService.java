package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.model.category.QuanLyDD.QuanLyDDCreateDto;
import com.evnit.ttpm.AuthApp.model.category.QuanLyDD.SearchQLDDList;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

public interface SCategoryQuanLyDDService {

	ResponseData getAll(SearchQLDDList searchQLDDList);
	ResponseData getById(int id);
	ResponseData create(QuanLyDDCreateDto createDto);
	ResponseData update(String id,QuanLyDDCreateDto updateDto);
	ResponseData delete(String id);

}
//