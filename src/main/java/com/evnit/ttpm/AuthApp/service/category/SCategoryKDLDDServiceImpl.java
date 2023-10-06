package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.category.KDLDD;
import com.evnit.ttpm.AuthApp.entity.category.WarningDevice;
import com.evnit.ttpm.AuthApp.mapper.KDLDDMapper;
import com.evnit.ttpm.AuthApp.mapper.WarningDeviceMapper;
import com.evnit.ttpm.AuthApp.model.category.KDLDD.KDLDDListDto;
import com.evnit.ttpm.AuthApp.model.category.KDLDD.SearchKDLDDList;
import com.evnit.ttpm.AuthApp.model.category.WarningDevice.SearchWarningDeviceList;
import com.evnit.ttpm.AuthApp.model.category.WarningDevice.WarningDeviceListDto;
import com.evnit.ttpm.AuthApp.model.filter.SearchFilter;
import com.evnit.ttpm.AuthApp.model.filter.SearchQuery;
import com.evnit.ttpm.AuthApp.repository.category.KDLDDRepository;
import com.evnit.ttpm.AuthApp.repository.category.P_PROBLEM_ASSETSRepository;
import com.evnit.ttpm.AuthApp.repository.category.ViewWarningDeviceRepository;
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
public class SCategoryKDLDDServiceImpl implements SCategoryKDLDDService {

    @Autowired
    P_PROBLEM_ASSETSRepository mapProblem;
    @Autowired
    KDLDDRepository kDLDDRepository;
    private UUID corrId = UUID.randomUUID();

    private final ModelMapper mapper;

    public SCategoryKDLDDServiceImpl(KDLDDMapper mapperConfig) {
        this.mapper = mapperConfig.getModelMapper();
    }

    @Override
    public ResponseData getAll(SearchKDLDDList dto) {
        ResponseData response = new ResponseData();


        SearchQuery searchQuery = new SearchQuery();
        List<KDLDDListDto> listCongTo = new ArrayList<KDLDDListDto>();
        SearchFilter searchFilter3 = new SearchFilter();
        List<SearchFilter> searchFilters = new ArrayList<>();

        Pageable pageable = PageRequest.of(dto.getPage(), dto.getLimit(), dto.getSortExpression());
        searchQuery.setSearchFilters(searchFilters);
        Specification<KDLDD> spec = SpecificationUtil.bySearchQuery(searchQuery, KDLDD.class);

        try {
            var pageResult = kDLDDRepository.findAll(spec, pageable);
            var dtoResult = pageResult.getContent().stream().map(item -> mapper.map(item, KDLDDListDto.class)).collect(Collectors.toList());
            Page<KDLDDListDto> result = new PageImpl<>(dtoResult, pageable, pageResult.getTotalElements());
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

