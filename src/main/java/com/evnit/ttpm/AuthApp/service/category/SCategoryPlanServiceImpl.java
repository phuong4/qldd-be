package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.category.P_PROBLEM_ASSETS;
import com.evnit.ttpm.AuthApp.entity.category.PlanAccreditation;
import com.evnit.ttpm.AuthApp.entity.category.SCategorySuCoBatThuong;
import com.evnit.ttpm.AuthApp.entity.category.View_Problem;
import com.evnit.ttpm.AuthApp.mapper.PlanMapper;
import com.evnit.ttpm.AuthApp.util.CustomPageImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.evnit.ttpm.AuthApp.mapper.SuCoMapper;
import com.evnit.ttpm.AuthApp.model.category.Plan.PlanListDto;
import com.evnit.ttpm.AuthApp.model.category.Plan.SearchPlanList;
import com.evnit.ttpm.AuthApp.model.category.SuCo.*;
import com.evnit.ttpm.AuthApp.model.filter.SearchFilter;
import com.evnit.ttpm.AuthApp.model.filter.SearchQuery;
import com.evnit.ttpm.AuthApp.repository.category.P_PROBLEM_ASSETSRepository;
import com.evnit.ttpm.AuthApp.repository.category.SuCoRepository;
import com.evnit.ttpm.AuthApp.repository.category.ViewPlanRepository;
import com.evnit.ttpm.AuthApp.repository.category.ViewProblemRepository;
import com.evnit.ttpm.AuthApp.util.SpecificationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SCategoryPlanServiceImpl implements SCategoryPlanService {

    @Autowired
    P_PROBLEM_ASSETSRepository mapProblem;
    @Autowired
    ViewPlanRepository ViewPlanRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;

    private UUID corrId = UUID.randomUUID();

    private final ModelMapper mapper;

    public SCategoryPlanServiceImpl(PlanMapper mapperConfig) {
        this.mapper = mapperConfig.getModelMapper();
    }

    @Override
    public ResponseData getAll(SearchPlanList dto) {
        ResponseData response = new ResponseData();
        SearchQuery searchQuery = new SearchQuery();
        List<PlanListDto> listCongTo = new ArrayList<PlanListDto>();
//        List<PlanListDto> total = this.TotalDanhDanhSachKiem(dto);
//        List<PlanListDto> totalMonth = this.TotalMonthDanhDanhSachKiem(dto);

        SearchFilter searchFilter3 = new SearchFilter();
        List<SearchFilter> searchFilters = new ArrayList<>();
        if (dto.getType() != null && !dto.getType().isEmpty()) {
            searchFilter3 = new SearchFilter("type", "IN", dto.getType());
            searchFilters.add(searchFilter3);
        }
        if (dto.getTbaId() != null && !dto.getTbaId().isEmpty()) {
            searchFilter3 = new SearchFilter("tbaId", "IN", dto.getTbaId());
            searchFilters.add(searchFilter3);
        }
        if (dto.getYearTime() != null && !dto.getYearTime().isEmpty()) {
            searchFilter3 = new SearchFilter("yearTime", "=", dto.getYearTime());
            searchFilters.add(searchFilter3);
        }

        if (dto.getMonthTime() != null && !dto.getMonthTime().isEmpty()) {
            searchFilter3 = new SearchFilter("monthTime", "IN", dto.getMonthTime());
            searchFilters.add(searchFilter3);
        }
        Pageable pageable = PageRequest.of(dto.getPage(), dto.getLimit(), dto.getSortExpression());
        searchQuery.setSearchFilters(searchFilters);
        Specification<PlanAccreditation> spec = SpecificationUtil.bySearchQuery(searchQuery, PlanAccreditation.class);

        try {

            var pageResult = ViewPlanRepository.findAll(spec, pageable);
            var dtoResult = pageResult.getContent().stream().map(item -> mapper.map(item, PlanListDto.class)).collect(Collectors.toList());



            Page<PlanListDto> result = new PageImpl<PlanListDto>(dtoResult, pageable, pageResult.getTotalElements());

            /*//Tháng kiểm định vòng for
            String monthTime = "";
            //Index hiện tại cần add
            Integer indexMonth = 0;

            for (PlanListDto item: dtoResult) {
                if(item.getMonthTime()!=null) {
                    if (!item.getMonthTime().equals(monthTime)) {
                        monthTime = item.getMonthTime();
                        listCongTo.add(totalMonth.get(indexMonth));
                        indexMonth++;
                    }
                    listCongTo.add(item);
                }
            }


            Page<PlanListDto> resultFinal = new PageImpl<PlanListDto>(listCongTo, pageable, pageResult.getTotalElements());

            CustomPageImpl cusResult = new CustomPageImpl();
            cusResult.setContent(listCongTo);
            cusResult.setNumber(result.getNumber());
            cusResult.setSize(result.getSize());
            cusResult.setTotalElements(pageResult.getTotalElements());
            cusResult.setLast(result.isLast());
            cusResult.setTotalPages(result.getTotalPages());
            cusResult.setFirst(result.isFirst());
            cusResult.setNumberOfElements(result.getNumberOfElements());

            ObjectMapper mapperJson = new ObjectMapper();
            cusResult.setPageable(mapperJson.convertValue(result.getPageable(), JsonNode.class));
            cusResult.setSort(mapperJson.convertValue(result.getSort(), JsonNode.class));*/



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
    //Dòng tổng của danh sách
    private List<PlanListDto> TotalMonthDanhDanhSachKiem(SearchPlanList dto) {

        String sql;

        List lst;
        sql = "SELECT NEWID() as Id,\n" +
                "      '' as TbaId,\n" +
                "      COUNT(TbaId) as TbaName,\n" +
                "      0 as Type,\n" +
                "      SUM(CountCT) as CountCT,\n" +
                "      SUM(CountTU) as CountTU,\n" +
                "      SUM(CountTI) as countTI,\n" +
                "      '' as DetailCT,\n" +
                "      '' as DetailTU,\n" +
                "      '' as DetailTI,\n" +
                "      YearTime ,\n" +
                "     8 as MonthTime, \n" +
                "         MonthTimeShow " +
                "  FROM [QLDD].[dbo].[PlanAccreditation] where 1=1 ";


        if (dto.getTbaId() != null && !dto.getTbaId().isEmpty()) {
            sql += " AND Tbaid IN ('" + String.join("','", dto.getTbaId()) + "')";
        }
        if (dto.getYearTime() != null ) {
            sql += " AND YearTime = '" + dto.getYearTime().toString() + "'";
        }
        if (dto.getMonthTime() != null ) {
            sql += " AND MonthTime = '" + dto.getMonthTime().toString() + "'";
        }


        sql += " GROUP BY MonthTime , YearTime,MonthTimeShow ORDER BY MonthTime, YearTime,MonthTimeShow DESC ";

        lst = jdbcTemplate.query(sql,new BeanPropertyRowMapper<PlanListDto>(PlanListDto.class));

        return  lst;
    }

    //Dòng tổng của danh sách kiểm định
    private List<PlanListDto> TotalDanhDanhSachKiem(SearchPlanList dto) {

        String sql;

        List lst;
        sql = "SELECT NEWID() as Id,\n" +
                "      '' as TbaId,\n" +
                "      COUNT(TbaId) as TbaName,\n" +
                "      0 as Type,\n" +
                "      SUM(CountCT) as CountCT,\n" +
                "      SUM(CountTU) as CountTU,\n" +
                "      SUM(CountTI) as countTI,\n" +
                "      '' as DetailCT,\n" +
                "      '' as DetailTU,\n" +
                "      '' as DetailTI,\n" +
                "      '' as YearTime,\n" +
                "      '' as MonthTime,\n" +
                "       1 as TypeShow " +
                "  FROM [QLDD].[dbo].[PlanAccreditation] where 1=1";
        if (dto.getTbaId() != null && !dto.getTbaId().isEmpty()) {
            sql += " AND Tbaid IN ('" + String.join("','", dto.getTbaId()) + "')";
        }
        if (dto.getYearTime() != null ) {
            sql += " AND YearTime = '" + dto.getYearTime().toString() + "'";
        }
        if (dto.getMonthTime() != null ) {
            sql += " AND MonthTime = '" + dto.getMonthTime().toString() + "'";
        }

        lst = jdbcTemplate.query(sql,new BeanPropertyRowMapper<PlanListDto>(PlanListDto.class));

        return  lst;
    }


}

