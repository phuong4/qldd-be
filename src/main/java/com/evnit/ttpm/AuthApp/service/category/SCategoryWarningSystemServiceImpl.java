package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.category.WarningSystem;
import com.evnit.ttpm.AuthApp.mapper.WarningSystemMapper;
import com.evnit.ttpm.AuthApp.model.category.WarningSystem.SearchWarningSystemList;
import com.evnit.ttpm.AuthApp.model.category.WarningSystem.WarningSystemListDto;
import com.evnit.ttpm.AuthApp.model.filter.SearchFilter;
import com.evnit.ttpm.AuthApp.model.filter.SearchQuery;
import com.evnit.ttpm.AuthApp.repository.category.P_PROBLEM_ASSETSRepository;
import com.evnit.ttpm.AuthApp.repository.category.ViewWarningSystemRepository;
import com.evnit.ttpm.AuthApp.util.SpecificationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SCategoryWarningSystemServiceImpl implements SCategoryWarningSystemService {

    @Autowired
    P_PROBLEM_ASSETSRepository mapProblem;
    @Autowired
    ViewWarningSystemRepository viewWarningSystemRepository;
    private UUID corrId = UUID.randomUUID();

    private final ModelMapper mapper;

    public SCategoryWarningSystemServiceImpl(WarningSystemMapper mapperConfig) {
        this.mapper = mapperConfig.getModelMapper();
    }

    @Override
    public ResponseData getAll(SearchWarningSystemList dto) {
        ResponseData response = new ResponseData();


        SearchQuery searchQuery = new SearchQuery();
        List<WarningSystemListDto> listCongTo = new ArrayList<WarningSystemListDto>();
        SearchFilter searchFilter3 = new SearchFilter();
        List<SearchFilter> searchFilters = new ArrayList<>();
        if (dto.getType() != null && !dto.getType().isEmpty()) {
            searchFilter3 = new SearchFilter("Type", "IN", dto.getType());
            searchFilters.add(searchFilter3);
        }
        Pageable pageable = PageRequest.of(dto.getPage(), dto.getLimit(), dto.getSortExpression());
        searchQuery.setSearchFilters(searchFilters);
        Specification<WarningSystem> spec = SpecificationUtil.bySearchQuery(searchQuery, WarningSystem.class);

        try {
            var pageResult = viewWarningSystemRepository.findAll(spec, pageable);
            var dtoResult = pageResult.getContent().stream().map(item -> mapper.map(item, WarningSystemListDto.class)).collect(Collectors.toList());
            Page<WarningSystemListDto> result = new PageImpl<>(dtoResult, pageable, pageResult.getTotalElements());
            response.setData(result);
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setState(ResponseData.STATE.OK.toString());
            response.setData(result);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ex.getMessage());
            response.setData(null);
        }
            return response;
    }


}

