package com.evnit.ttpm.AuthApp.service.category;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.category.WarningDevice;
import com.evnit.ttpm.AuthApp.mapper.WarningDeviceMapper;
import com.evnit.ttpm.AuthApp.model.category.WarningDevice.SearchWarningDeviceList;
import com.evnit.ttpm.AuthApp.model.category.WarningDevice.WarningDeviceListDto;
import com.evnit.ttpm.AuthApp.model.filter.SearchFilter;
import com.evnit.ttpm.AuthApp.model.filter.SearchQuery;
import com.evnit.ttpm.AuthApp.repository.category.P_PROBLEM_ASSETSRepository;
import com.evnit.ttpm.AuthApp.repository.category.ViewWarningDeviceRepository;
import com.evnit.ttpm.AuthApp.util.CustomPageImpl;
import com.evnit.ttpm.AuthApp.util.SpecificationUtil;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SCategoryWarningDeviceServiceImpl implements SCategoryWarningDeviceService {

    @Autowired
    P_PROBLEM_ASSETSRepository mapProblem;
    @Autowired
    ViewWarningDeviceRepository viewWarningDeviceRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;
    private UUID corrId = UUID.randomUUID();

    private final ModelMapper mapper;

    public SCategoryWarningDeviceServiceImpl(WarningDeviceMapper mapperConfig) {
        this.mapper = mapperConfig.getModelMapper();
    }

    @Override
    public ResponseData getAll(SearchWarningDeviceList dto) {
        ResponseData response = new ResponseData();
        SearchQuery searchQuery = new SearchQuery();
        List<WarningDeviceListDto> listCongTo = new ArrayList<WarningDeviceListDto>();


        //List<WarningDeviceListDto> totalMonth = this.TotalMonthDanhDanhSachKiem(dto);
        SearchFilter searchFilter3 = new SearchFilter();
        List<SearchFilter> searchFilters = new ArrayList<>();
        if (dto.getType() != null  && !dto.getType().isEmpty()) {
            searchFilter3 = new SearchFilter("type", "IN", dto.getType());
            searchFilters.add(searchFilter3);
        }
        if (dto.getMonth() != null && !dto.getMonth().isEmpty() ) {
            searchFilter3 = new SearchFilter("month", "IN", dto.getMonth());
            searchFilters.add(searchFilter3);
        }
        Pageable pageable = PageRequest.of(dto.getPage(), dto.getLimit(), dto.getSortExpression());
        searchQuery.setSearchFilters(searchFilters);
        Specification<WarningDevice> spec = SpecificationUtil.bySearchQuery(searchQuery, WarningDevice.class);

        try {
            var pageResult = viewWarningDeviceRepository.findAll(spec, pageable);
            var dtoResult = pageResult.getContent().stream().map(item -> mapper.map(item, WarningDeviceListDto.class)).collect(Collectors.toList());

            Page<WarningDeviceListDto> result = new PageImpl<WarningDeviceListDto>(dtoResult, pageable, pageResult.getTotalElements());


            /*//Tháng kiểm định vòng for
            String month = "";
            //Index hiện tại cần add
            Integer indexMonth = 0;

            for (WarningDeviceListDto item: dtoResult) {
                if(item.getMonth()!=null) {
                    if (!item.getMonth().equals(month)) {
                        month = item.getMonth();
                        listCongTo.add(totalMonth.get(indexMonth));
                        indexMonth++;
                    }
                    listCongTo.add(item);
                }
            }


            Page<WarningDeviceListDto> resultFinal = new PageImpl<WarningDeviceListDto>(listCongTo, pageable, pageResult.getTotalElements());

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
    public ResponseData GetThongTinKiemDinhTUTI(String accredId, String strThietBi) {
        ResponseData response = new ResponseData();
        strThietBi = strThietBi.replace(",", "','");
        accredId = accredId.replace(",", "','");

        try {
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());

            String sql;
            List lst;
            //Lấy từ bảng chi tiết
            sql = "select case when rs.[CCX] is not null then rs.[CCX] else '' end + case when rs.PHA is not null then rs.PHA else '' end + CUON_DNOI as 'mergCol', dt.ASSETID as assetId, [CCX] as 'ccx', rs.pha, SERIALNUM as 'soCheTao', CUON_DNOI as 'cuonDauNoi', [TISO] as 'tsb', [DUNGLUONG] as dungLuong, [UF80] as 'uf80', [USSG80] as 'ussg80', [UF100] as 'uf100', [USSG100] as 'ussg100', [UF120] as 'uf120', [USSG120] as 'ussg120' from [M_ACCREDITATION_RESULT_TU] rs left join [dbo].[M_ACCREDITATION_DETAILS] dt on rs.ACCREDDETAILID = dt.ACCREDDETAILID where rs.[ACCREDDETAILID] in ('"+accredId+"') order by rs.PHA, ccx, CUON_DNOI, DUNGLUONG asc";
            lst = jdbcTemplate.queryForList(sql);

            if (lst != null && lst.size() > 0) {
                response.setState(ResponseData.STATE.OK.toString());
                response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
                response.setData(lst);
            } else {
                sql = "select c.CCX + case when tu.PHA is not null then tu.PHA else '' end  + c.CUON_DNOI as 'mergCol', assTU.CATEGORYID, sla.LISTDESC as 'ccx', c.CUON_DNOI as 'cuonDauNoi', tu.PHA as 'pha', assTU.SERIALNUM as 'soCheTao', assTU.ASSETDESC, assTU.assetId, '' as 'tsb', '' as dungLuong,'' as 'uf80', '' as 'ussg80', '' as 'uf100', '' as 'ussg100', '' as 'uf120', '' as 'ussg120' from A_ASSET assCuon left join S_ATTRIBUTE_GROUP_ASSOBJ att on assCuon.ASSETID = att.OBJID left join ZAG_CUON c on att.ATTRDATAID = c.ATTRDATAID left join A_ASSET assTU on assCuon.ASSETID_PARENT = assTU.ASSETID left join S_ATTRIBUTE_GROUP_ASSOBJ attTu on assTU.ASSETID = attTu.OBJID left join ZAG_TU tu on attTu.ATTRDATAID = tu.ATTRDATAID inner join HLP_TYPE hlp on assTU.CATEGORYID = hlp.[TYPE] left join [dbo].[S_LIST_ALL] sla on c.CCX = sla.LISTID and sla.GROUPLISTID = 'CCX' where assCuon.ISDELETE = 0 and assTU.ASSETID in ('" + strThietBi + "') order by PHA, ccx, c.CUON_DNOI";

                lst = jdbcTemplate.queryForList(sql);

                response.setState(ResponseData.STATE.OK.toString());
                response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
                response.setData(lst);
            }


        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ex.getMessage());
            response.setData(null);
        }

        return response;
    }
    public ResponseData GetThongTinKiemDinhTI(String accredId, String strThietBi) {
        ResponseData response = new ResponseData();
        strThietBi = strThietBi.replace(",", "','");
        accredId =accredId.replace(",", "','");

        try {
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());

            String sql;
            List lst;
            //Lấy từ bảng chi tiết
            sql = "select case when rs.[CCX] is not null then rs.[CCX] else '' end + case when rs.PHA is not null then rs.PHA else '' end + CUON_DNOI as 'mergCol', dt.ASSETID as assetId, [CCX] as 'ccx', rs.pha, SERIALNUM as 'soCheTao', CUON_DNOI as 'cuonDauNoi', [TISO] as 'tsb', [TAI] as 'dungLuong', [UF1] as 'uf1', [USSG1] as 'ussg1', [UF5] as 'uf5', [USSG5] as 'ussg5', [UF20] as 'uf20', [USSG20] as 'ussg20', [UF100] as 'uf100', [USSG100] as 'ussg100', [UF120] as 'uf120', [USSG120] as 'ussg120' from [M_ACCREDITATION_RESULT_TI] rs left join [dbo].[M_ACCREDITATION_DETAILS] dt on rs.ACCREDDETAILID = dt.ACCREDDETAILID where rs.[ACCREDDETAILID] in ('"+accredId+"')  order by rs.pha, ccx, CUON_DNOI, TAI asc";
            lst = jdbcTemplate.queryForList(sql);

            if (lst != null && lst.size() > 0) {
                response.setState(ResponseData.STATE.OK.toString());
                response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
                response.setData(lst);
            } else {
                sql = "select c.CCX + case when ti.PHA is not null then ti.PHA else '' end  + c.CUON_DNOI as 'mergCol', assTI.CATEGORYID, sla.LISTDESC as 'ccx', c.CUON_DNOI as 'cuonDauNoi', ti.PHA as 'pha', assTI.SERIALNUM as 'soCheTao', assTI.ASSETDESC, assTI.assetId, '' as 'tsb', '' as dungLuong, '' as 'uf1', '' as 'ussg1', '' as 'uf5', '' as 'ussg5', '' as 'uf20', '' as 'ussg20', '' as 'uf100', '' as 'ussg100', '' as 'uf120', '' as 'ussg120' from A_ASSET assCuon left join S_ATTRIBUTE_GROUP_ASSOBJ att on assCuon.ASSETID = att.OBJID left join ZAG_CUON c on att.ATTRDATAID = c.ATTRDATAID left join A_ASSET assTI on assCuon.ASSETID_PARENT = assTI.ASSETID left join S_ATTRIBUTE_GROUP_ASSOBJ attTi on assTI.ASSETID = attTi.OBJID left join ZAG_TI ti on attTi.ATTRDATAID = ti.ATTRDATAID inner join HLP_TYPE hlp on assTI.CATEGORYID = hlp.[TYPE] left join [dbo].[S_LIST_ALL] sla on c.CCX = sla.LISTID and sla.GROUPLISTID = 'CCX' where assCuon.ISDELETE = 0 and assTI.ASSETID in ('" + strThietBi + "') order by PHA, ccx, c.CUON_DNOI";

                lst = jdbcTemplate.queryForList(sql);

                response.setState(ResponseData.STATE.OK.toString());
                response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
                response.setData(lst);
            }


        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ex.getMessage());
            response.setData(null);
        }

        return response;
    }

    private List<WarningDeviceListDto> TotalMonthDanhDanhSachKiem(SearchWarningDeviceList dto) {

        String sql;

        List lst;
        sql = "SELECT NEWID() as Id\n" +
                "      ,N'Tháng '+Cast(Month as nvarchar) as TbaName\n" +
                "      ,'' as Type\n" +
                "      ,'' as TbaId\n" +
                "      ,'' as NameDD\n" +
                "      ,'' as ValueErrorCT\n" +
                "      ,'' as ValueErrorCTYes\n" +
                "      ,'' as ErrorScoreTUTI\n" +
                "      ,'' as ErrorOriginTUTI\n" +
                "      ,'' as ErrorScoreTUTIYes\n" +
                "      ,'' as ErrorOriginTUTIYes\n" +
                "      ,'' as Month\n" +
                "      ,'' as MonthTimeShow\n" +
                "  FROM [QLDD].[dbo].[WarningDevice] where 1=1";


        if (dto.getType() != null ) {
            sql += " AND Type = '" + dto.getType().toString() + "'";
        }
        if (dto.getMonth() != null ) {
            sql += " AND Month = '" + dto.getMonth().toString() + "'";
        }


        sql += " GROUP BY Month  ORDER BY Month ";

        lst = jdbcTemplate.query(sql,new BeanPropertyRowMapper<SearchWarningDeviceList>(SearchWarningDeviceList.class));

        return  lst;
    }
}

