package com.evnit.ttpm.AuthApp.service.device;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.category.*;
import com.evnit.ttpm.AuthApp.entity.device.ViewDiemDo;
import com.evnit.ttpm.AuthApp.entity.device.View_HTDD;
import com.evnit.ttpm.AuthApp.entity.device.congto.ViewCongTo;
import com.evnit.ttpm.AuthApp.entity.device.congto.ZAG_CongTo;
import com.evnit.ttpm.AuthApp.entity.device.cuon.*;
import com.evnit.ttpm.AuthApp.entity.device.ti.*;
import com.evnit.ttpm.AuthApp.entity.device.tu.*;
import com.evnit.ttpm.AuthApp.exception.NotFoundException;
import com.evnit.ttpm.AuthApp.mapper.DeviceMapper;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.model.category.tbargl.TbaRglCrudDto;
import com.evnit.ttpm.AuthApp.model.device.common.DeviceCrudDto;
import com.evnit.ttpm.AuthApp.model.device.common.DeviceDto;
import com.evnit.ttpm.AuthApp.model.device.common.SearchHTDDListDto;
import com.evnit.ttpm.AuthApp.model.device.common.ViewHTDDDto;
import com.evnit.ttpm.AuthApp.model.device.congto.*;
import com.evnit.ttpm.AuthApp.model.device.cuon.CuonCreateDto;
import com.evnit.ttpm.AuthApp.model.device.diemdo.DeviceDiemDoList;
import com.evnit.ttpm.AuthApp.model.device.diemdo.ZAGDiemDo;
import com.evnit.ttpm.AuthApp.model.device.ti.*;
import com.evnit.ttpm.AuthApp.model.device.tu.*;
import com.evnit.ttpm.AuthApp.model.filter.SearchFilter;
import com.evnit.ttpm.AuthApp.model.filter.SearchQuery;
import com.evnit.ttpm.AuthApp.repository.category.A_ASSETRepository;
import com.evnit.ttpm.AuthApp.repository.category.S_ATTRIBUTE_GROUP_ASSOBJRepository;
import com.evnit.ttpm.AuthApp.repository.category.ZAG_DIEMDORepository;
import com.evnit.ttpm.AuthApp.repository.device.A_ASSETLINKRepository;
import com.evnit.ttpm.AuthApp.repository.device.ViewDiemDoRepository;
import com.evnit.ttpm.AuthApp.repository.device.ViewGetAssetDetailRepo;
import com.evnit.ttpm.AuthApp.repository.device.ViewHTDDRepository;
import com.evnit.ttpm.AuthApp.repository.device.congto.ViewCongToRepository;
import com.evnit.ttpm.AuthApp.repository.device.congto.ZAGCongToRepository;
import com.evnit.ttpm.AuthApp.repository.device.cuon.CuonAssetTempRepo;
import com.evnit.ttpm.AuthApp.repository.device.cuon.ViewCuonRepository;
import com.evnit.ttpm.AuthApp.repository.device.cuon.ZAGCuonRepository;
import com.evnit.ttpm.AuthApp.repository.device.cuon.ZagCuonTempRepo;
import com.evnit.ttpm.AuthApp.repository.device.ti.ViewSetTIRepository;
import com.evnit.ttpm.AuthApp.repository.device.ti.ZAG_TIRepository;
import com.evnit.ttpm.AuthApp.repository.device.tu.ViewSetTURepository;
import com.evnit.ttpm.AuthApp.repository.device.tu.ZAGTURepository;
import com.evnit.ttpm.AuthApp.service.system.WonderHisService;
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

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DeviceServiceImpl implements DeviceService {
    @Autowired
    AAssetService assetService;
    @Autowired
    ZAGCongToRepository zagCongToRepository;
    @Autowired
    ZAGTURepository zagTURepository;
    @Autowired
    ZAG_TIRepository zagTIRepository;
    @Autowired
    S_ATTRIBUTE_GROUP_ASSOBJRepository attrRepository;
    @Autowired
    ZAGCuonRepository zagCuonRepository;
    @Autowired
    A_ASSETRepository assetRepository;
    @Autowired
    A_ASSETLINKRepository assetlinkRepository;
    @Autowired
    ViewGetAssetDetailRepo viewGetAssetDetailRepo;
    @Autowired
    WonderHisService _wonderHisService;

    private final ViewDiemDoRepository viewDiemDoRepository;
    private final ViewCongToRepository viewCongToRepository;
    private final ViewHTDDRepository htddRepository;
    private final ZAG_DIEMDORepository diemdoRepository;
    private final ZAG_TIRepository tiRepository;
    private final DeviceTUService deviceTUService;
    private final DeviceTIService deviceTIService;
    private final CuonService cuonService;
    private final ModelMapper mapper;
    private final ViewSetTIRepository viewSetTIRepository;
    private final ViewSetTURepository viewSetTURepository;
    private final CuonAssetTempRepo cuonAssetTempRepo;
    private final ZagCuonTempRepo zagCuonTempRepo;
    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    public DeviceServiceImpl(ViewDiemDoRepository viewDiemDoRepository, ViewCongToRepository viewCongToRepository,
                             A_ASSETLINKRepository assetlinkRepository, ZAG_DIEMDORepository diemdoRepository,
                             ZAG_TIRepository tiRepository, ViewHTDDRepository viewHTDDRepository,
                             DeviceTUService deviceTUService, ViewCuonRepository viewCuonRepository,
                             CuonService cuonService, DeviceMapper deviceProfile, ViewHTDDRepository htddRepository,
                             DeviceTIService deviceTIService, ViewSetTIRepository viewSetTIRepository,
                             ViewSetTURepository viewSetTURepository, CuonAssetTempRepo cuonAssetTempRepo, ZagCuonTempRepo zagCuonTempRepo) {
        this.viewDiemDoRepository = viewDiemDoRepository;
        this.viewCongToRepository = viewCongToRepository;
        this.assetlinkRepository = assetlinkRepository;
        this.diemdoRepository = diemdoRepository;
        this.tiRepository = tiRepository;
        this.deviceTUService = deviceTUService;
        this.cuonService = cuonService;
        this.htddRepository = htddRepository;
        this.mapper = deviceProfile.getModelMapper();
        this.deviceTIService = deviceTIService;
        this.viewSetTIRepository = viewSetTIRepository;
        this.viewSetTURepository = viewSetTURepository;
        this.cuonAssetTempRepo = cuonAssetTempRepo;
        this.zagCuonTempRepo = zagCuonTempRepo;
    }

    @Transactional
    public ResponseData getDevicesByCategory(String categoryId, String assetId) {
        ResponseData response = new ResponseData();
        try {
            if (categoryId.equals(A_ASSET.CategoryId.DIEMDO.toString())) {
                DeviceDiemDoList listDiemDo = new DeviceDiemDoList();
                ViewDiemDo diemDoEntity = viewDiemDoRepository.findById(assetId).orElse(new ViewDiemDo());
                mapper.map(diemDoEntity, listDiemDo);
                response.setData(listDiemDo);
            }
            if (categoryId.equals(A_ASSET.CategoryId.DIEMDO.toString())) {
                DeviceCongToListDto listCongTo = new DeviceCongToListDto();
                ViewCongTo assetEntity = viewCongToRepository.findById(assetId).orElse(new ViewCongTo());
                mapper.map(assetEntity, listCongTo);
                response.setData(listCongTo);
            }
            if (categoryId.equals(A_ASSET.CategoryId.TU.toString())) {
                DeviceCongToListDto listCongTo = new DeviceCongToListDto();
                ViewCongTo assetEntity = viewCongToRepository.findById(assetId).orElse(new ViewCongTo());
                mapper.map(assetEntity, listCongTo);
                response.setData(listCongTo);
            }
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setState(ResponseData.STATE.OK.toString());
        } catch (Exception ignored) {
            response.setData(null);
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setState(ResponseData.STATE.FAIL.toString());
        }
        return response;
    }

    public ResponseData getCongToKD(String transId, String strCongTo, String categoryId) {
        ResponseData response = new ResponseData();

        try {
            strCongTo = strCongTo.replace(",", "','");
            strCongTo = "('" + strCongTo + "')";
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());

            String sql;
            List lst; //List<SListGroupAll> lst;
            sql = "SELECT '' as 'accredDetailId', [assetId] ,[assetId_Parent] ,[assetDesc] ,[serialNum] ,[isDelete], '' as transId, cast('true' as bit) as accred_Result , 0 as selected  FROM [QLDD].[dbo].[A_ASSET] where CATEGORYID = '" + categoryId + "' and ASSETID_PARENT is not null and ISDELETE = 0 and [ASSETID] not in " + strCongTo;
            lst = jdbcTemplate.queryForList(sql);
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(lst);
            //response.setData(result);
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ex.getMessage());
            response.setData(null);
        }

        return response;
    }

    @Override
    public ResponseData getListCongTo(SearchCongToList dto) {
        ResponseData response = new ResponseData();
        SearchQuery searchQuery = new SearchQuery();

        //SỐ thiết bị đã nghiệm thu
        var countTB = this.TotalMonthDanhDanhSachKiem(dto);

        List<DeviceCongToListDto> listCongTo = new ArrayList<DeviceCongToListDto>();
        SearchFilter searchFilter1 = new SearchFilter("ISDELETE", "=", false);
        SearchFilter searchFilter2 = new SearchFilter("CATEGORYID", "=", A_ASSET.CategoryId.CONGTO.toString());
        SearchFilter searchFilter3 = new SearchFilter();
        List<SearchFilter> searchFilters = new ArrayList<>();
        if (dto.getTypeId() != null && !dto.getTypeId().isEmpty()) {
            searchFilter3 = new SearchFilter("TYPEIDSTR", "IN", dto.getTypeId());
            searchFilters.add(searchFilter3);
        }

        if (dto.getAssetIdParent() != null && !dto.getAssetIdParent().isEmpty()) {
            if (dto.getAssetIdParent().contains("unknow")) {
                searchFilter3 = new SearchFilter("ASSETID_PARENT", "IsNull", null);
                searchFilters.add(searchFilter3);
            } else {
                searchFilter3 = new SearchFilter("ASSETID_PARENT", "IN", dto.getAssetIdParent());
                searchFilters.add(searchFilter3);
            }
        }
        if (dto.getPowerId() != null && !dto.getPowerId().isEmpty()) {
            searchFilter3 = new SearchFilter("POWERID", "IN", dto.getPowerId());
            searchFilters.add(searchFilter3);
        }
        if (dto.getParentStatus() != null && !dto.getParentStatus().isEmpty()) {
            searchFilter3 = new SearchFilter("TRANGTHAINMD", "IN", dto.getParentStatus());
            searchFilters.add(searchFilter3);
        }

        if (dto.getDeviceStatus() != null && !dto.getDeviceStatus().isEmpty()) {
            searchFilter3 = new SearchFilter("DIEMDOSTATUS", "IN", dto.getDeviceStatus());
            searchFilters.add(searchFilter3);
        }
        if (dto.getIgnoreCTId() != null && !dto.getIgnoreCTId().isEmpty()) {
            searchFilter3 = new SearchFilter("ASSETID", "NotIn", dto.getIgnoreCTId());
            searchFilters.add(searchFilter3);
        }
        if (dto.getNameTUCuon() != null && !dto.getNameTUCuon().isEmpty()) {
            searchFilter3 = new SearchFilter("ASSETDESC", "LIKE", dto.getNameTUCuon());
            searchFilters.add(searchFilter3);
        }
        if (dto.getTinhChat() != null && !dto.getTinhChat().isEmpty()) {
            searchFilter3 = new SearchFilter("tinhChat", "IN", dto.getTinhChat());
            searchFilters.add(searchFilter3);
        }
        if (dto.getDiemDoName() != null && !dto.getDiemDoName().isEmpty()) {
            searchFilter3 = new SearchFilter("ASSETDESC", "LIKE", dto.getDiemDoName());
            searchFilters.add(searchFilter3);
        }
        if (dto.getLoaiCto() != null && !dto.getLoaiCto().isEmpty()) {
            searchFilter3 = new SearchFilter("loaiCto", "IN", dto.getLoaiCto());
            searchFilters.add(searchFilter3);
        }
        if (dto.getSerialNum() != null && !dto.getSerialNum().isEmpty()) {
            searchFilter3 = new SearchFilter("SERIALNUM", "LIKE", dto.getSerialNum());
            searchFilters.add(searchFilter3);
        }
        Pageable pageable = PageRequest.of(dto.getPage(), dto.getLimit(), dto.getSortExpression());
        searchFilters.add(searchFilter1);
        searchFilters.add(searchFilter2);
        searchQuery.setSearchFilters(searchFilters);
        Specification<ViewCongTo> spec = SpecificationUtil.bySearchQuery(searchQuery, ViewCongTo.class);
        try {
            var pageResult = assetService.findAllPaging(spec, pageable);
            var dtoResult = pageResult.getContent().stream().map(item -> mapper.map(item, DeviceCongToListDto.class)).collect(Collectors.toList());

            if (!dtoResult.isEmpty())
                dtoResult.get(0).setCountTB(countTB);

            Page<DeviceCongToListDto> result = new PageImpl<DeviceCongToListDto>(dtoResult, pageable, pageResult.getTotalElements());
            response.setData(result);
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setState(ResponseData.STATE.OK.toString());


        } catch (Exception ignored) {
            response.setData(null);
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setState(ResponseData.STATE.FAIL.toString());
        }
        return response;
    }

    //Dòng tổng thiết bị đã nghiệm thu
    private int TotalMonthDanhDanhSachKiem(SearchCongToList dto) {

        String sql;

        sql = "SELECT COUNT(ASSETID) as CountTB  FROM [dbo].[VIEW_CONGTO] where DIEMDO_STATUS = 2";

        if (dto.getTypeId() != null && !dto.getTypeId().isEmpty()) {
            sql += " AND TYPEIDSTR IN ('" + String.join("','", dto.getTypeId()) + "')";
        }

        if (dto.getAssetIdParent() != null && !dto.getAssetIdParent().isEmpty()) {
            sql += " AND POWER_ID IN ('" + String.join("','", dto.getAssetIdParent()) + "')";
        }

        if (dto.getParentStatus() != null && !dto.getParentStatus().isEmpty()) {
            sql += " AND TRANGTHAINMD IN ('" + String.join("','", dto.getParentStatus()) + "')";
        }

        if (dto.getDeviceStatus() != null && !dto.getDeviceStatus().isEmpty()) {
            sql += " AND DIEMDO_STATUS IN ('" + String.join("','", dto.getDeviceStatus()) + "')";
        }
        if (dto.getNameTUCuon() != null && !dto.getNameTUCuon().isEmpty()) {
            sql += "AND ASSETDESC LIKE '%" + dto.getNameTUCuon() + "%'";
        }
        if (dto.getTinhChat() != null && !dto.getTinhChat().isEmpty()) {
            sql += " AND TINH_CHAT IN ('" + String.join("','", dto.getTinhChat()) + "')";
        }
        if (dto.getDiemDoName() != null && !dto.getDiemDoName().isEmpty()) {
            sql += "AND ASSETDESC LIKE '%" + dto.getDiemDoName() + "%'";
        }
        if (dto.getLoaiCto() != null && !dto.getLoaiCto().isEmpty()) {
            sql += " AND LOAI_CTO IN ('" + String.join("','", dto.getLoaiCto()) + "')";
        }
        if (dto.getSerialNum() != null && !dto.getSerialNum().isEmpty()) {
            sql += "AND SERIALNUM LIKE '%" + dto.getSerialNum() + "%'";
        }

        List<Integer> lst = new ArrayList<>();
        lst = jdbcTemplate.queryForList(sql, Integer.class);

        if (!lst.isEmpty() && lst.size() > 0) {
            int result = lst.get(0);
            return result;
        } else
            return 0;
    }

    @Override
    public ResponseData getDiemDoById(String id) {
        ResponseData res = new ResponseData();
        try {
            var diemDo = viewDiemDoRepository.findById(id).orElse(null);
            if (diemDo != null) {
                res.setData(diemDo);
                res.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
                res.setState(ResponseData.STATE.OK.toString());
            } else {
                res.setMessage(ResponseData.MESSAGE.ERROR.toString());
                res.setState(ResponseData.STATE.FAIL.toString());
            }
        } catch (Exception ignored) {
            res.setMessage(ResponseData.MESSAGE.ERROR.toString());
            res.setState(ResponseData.STATE.FAIL.toString());
        }
        return res;
    }

    @Override
    public ResponseData getSetTUByIdAndIdsCuon(String assetId, List<String> cuonIds) {
        ResponseData response = new ResponseData();
        try {
            List<ViewTU> listDeviceTU = new ArrayList<>();
            for (String id : cuonIds) {
                ViewCuon cuonEntity = cuonService.findCuonById(id).orElse(null);
                if (cuonEntity != null) {
                    ViewTU tu = deviceTUService.findViewTUById(cuonEntity.getAssetIdParent());

                    // Create a copy of tu
                    ViewTU tuCopy = new ViewTU();// You should create a copy method in your ViewTU class
                    mapper.map(tu, tuCopy);
                    tuCopy.setListCuon(new ArrayList<>());
                    tuCopy.addCuonToList(cuonEntity);

                    var tuExists = listDeviceTU.stream().filter(x -> x.getAssetId().equals(tuCopy.getAssetId())).findFirst().orElse(null);

                    if (tuExists != null) {
                        tuExists.addCuonToList(cuonEntity);
                    } else {
                        listDeviceTU.add(tuCopy); // Add the copy to the list
                    }
                }
            }
            SetTUDetailDto setTUDetailDto = new SetTUDetailDto();
            var setTUAsset = deviceTUService.findSetTUById(assetId);
            mapper.map(setTUAsset, setTUDetailDto);
            if (listDeviceTU.size() > 1) {
                setTUDetailDto.setSetType(1);
            } else {
                setTUDetailDto.setSetType(3);
            }
            setTUDetailDto.setTuList(listDeviceTU);
            response.setData(setTUDetailDto);
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setState(ResponseData.STATE.OK.toString());
        } catch (Exception ignored) {
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setData(ResponseData.STATE.FAIL.toString());
        }
        return response;
    }

    @Override
    public ResponseData getSetTIByIdAndIdsCuon(String assetId, List<String> cuonIds) {
        ResponseData response = new ResponseData();
        try {
            List<ViewTI> listDeviceTI = new ArrayList<>();
            for (String id : cuonIds) {
                ViewCuonTI cuonEntity = cuonService.findCuonTIById(id).orElse(null);
                if (cuonEntity != null) {
                    ViewTI ti = deviceTIService.findViewTIById(cuonEntity.getAssetIdParent());

                    // Create a copy of tu
                    ViewTI tiCopy = new ViewTI();// You should create a copy method in your ViewTU class
                    mapper.map(ti, tiCopy);
                    tiCopy.setListCuon(new ArrayList<>());
                    tiCopy.addCuonToList(cuonEntity);

                    var tuExists = listDeviceTI.stream().filter(x -> x.getAssetId().equals(tiCopy.getAssetId())).findFirst().orElse(null);

                    if (tuExists != null) {
                        tuExists.addCuonToList(cuonEntity);
                    } else {
                        listDeviceTI.add(tiCopy); // Add the copy to the list
                    }
                }
            }
            SetTIDetailDto setTIDetailDto = new SetTIDetailDto();
            var setTIAsset = deviceTIService.findSetTIById(assetId);
            mapper.map(setTIAsset, setTIDetailDto);
            if (listDeviceTI.size() > 1) {
                setTIDetailDto.setSetType(1);
            } else {
                setTIDetailDto.setSetType(3);
            }
            setTIDetailDto.setTiList(listDeviceTI);
            response.setData(setTIDetailDto);
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setState(ResponseData.STATE.OK.toString());
        } catch (Exception ignored) {
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setData(ResponseData.STATE.FAIL.toString());
        }
        return response;
    }

    @Override
    public ResponseData getCongToBySerialNum(String serialNum) {
        ResponseData response = new ResponseData();
        try {
            ViewCongTo entity = assetService.getCongToBySerialNum(serialNum).orElse(new ViewCongTo());
            var result = mapper.map(entity, DeviceCongToListDto.class);
            response.setData(result);
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setState(ResponseData.STATE.OK.toString());
        } catch (Exception ex) {
            response.setData(null);
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setState(ResponseData.STATE.FAIL.toString());
        }
        return response;
    }

    @Override
    public ResponseData getTUBySerialNum(String serialNum) {
        ResponseData response = new ResponseData();
        try {
            ViewTU entity = assetService.getTUBySerialNum(serialNum).orElse(new ViewTU());

            response.setData(entity);
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setState(ResponseData.STATE.OK.toString());
        } catch (Exception ex) {
            response.setData(null);
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setState(ResponseData.STATE.FAIL.toString());
        }
        return response;
    }

    @Override
    public ResponseData getTIBySerialNum(String serialNum) {
        ResponseData response = new ResponseData();
        try {
            ViewTI entity = assetService.getTIBySerialNum(serialNum).orElse(new ViewTI());
            response.setData(entity);
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setState(ResponseData.STATE.OK.toString());
        } catch (Exception ex) {
            response.setData(null);
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setState(ResponseData.STATE.FAIL.toString());
        }
        return response;
    }

    @Override
    public ResponseData getListTU(SearchTUList dto) {
        ResponseData response = new ResponseData();
        SearchQuery searchQuery = new SearchQuery();
        //SỐ thiết bị đã nghiệm thu
        var countTB = this.TotalDemNghiemThuTU(dto);
        var countBoTB = this.TotalDemNghiemThuBoTU(dto);
        List<DeviceTuDetailDto> listCongTo = new ArrayList<DeviceTuDetailDto>();
        //SearchFilter searchFilter2 = new SearchFilter("CATEGORYID", "LIKE", A_ASSET.CategoryId.TU.toString());
        SearchFilter searchFilter = new SearchFilter();
        List<SearchFilter> searchFilters = new ArrayList<>();
        searchFilter = new SearchFilter("ISDELETE", "=", false);
        searchFilters.add(searchFilter);
        if (dto.getPowerSystemType() != null && !dto.getPowerSystemType().isEmpty()) {
            searchFilter = new SearchFilter("powerSystemType", "IN", dto.getPowerSystemType());
            searchFilters.add(searchFilter);
        }
        if (dto.getPowerSystemName() != null && !dto.getPowerSystemName().isEmpty()) {
            searchFilter = new SearchFilter("powerSystemId", "IN", dto.getPowerSystemName());
            searchFilters.add(searchFilter);
        }
        if (dto.getPowerSystemStatus() != null && !dto.getPowerSystemStatus().isEmpty()) {
            searchFilter = new SearchFilter("powerSystemStatus", "IN", dto.getPowerSystemStatus());
            searchFilters.add(searchFilter);
        }
        if (dto.getStatusTU1() != null && !dto.getStatusTU1().isEmpty()) {
            searchFilter = new SearchFilter("statusTU1", "IN", dto.getStatusTU1());
            searchFilters.add(searchFilter);
        }
        if (dto.getStatusTU2() != null && !dto.getStatusTU2().isEmpty()) {
            searchFilter = new SearchFilter("statusTU", "IN", dto.getStatusTU2());
            searchFilters.add(searchFilter);
        }
        if (dto.getNameTUCuon() != null && !dto.getNameTUCuon().isEmpty()) {
            searchFilter = new SearchFilter("nameTUCuon", "LIKE", dto.getNameTUCuon());
            searchFilters.add(searchFilter);
        }
        if (dto.getTinhChat() != null && !dto.getTinhChat().isEmpty()) {
            searchFilter = new SearchFilter("tinhChat", "IN", dto.getTinhChat());
            searchFilters.add(searchFilter);
        }
        if (dto.getDiemDoName() != null && !dto.getDiemDoName().isEmpty()) {
            searchFilter = new SearchFilter("diemDoName", "LIKE", dto.getDiemDoName());
            searchFilters.add(searchFilter);
        }
        if (dto.getMachNThu() != null && !dto.getMachNThu().isEmpty()) {
            for (String machNThu : dto.getMachNThu()) {
                searchFilter = new SearchFilter("machNThu", "LIKE", machNThu, "OR");
                searchFilters.add(searchFilter);
            }
        }
        if (dto.getPManufacturerId() != null && !dto.getPManufacturerId().isEmpty()) {
            searchFilter = new SearchFilter("pManufacturerId", "IN", dto.getPManufacturerId());
            searchFilters.add(searchFilter);
        }
        if (dto.getSerialNum() != null && !dto.getSerialNum().isEmpty()) {
            searchFilter = new SearchFilter("serialNum", "LIKE", dto.getSerialNum());
            searchFilters.add(searchFilter);
        }
        Pageable pageable = PageRequest.of(dto.getPage(), dto.getLimit(), dto.getSortExpression());
        searchFilters.add(searchFilter);
        //searchFilters.add(searchFilter2);
        searchQuery.setSearchFilters(searchFilters);
        Specification<ViewTUDetail> spec = SpecificationUtil.bySearchQuery(searchQuery, ViewTUDetail.class);
        try {
            var pageResult = assetService.findTuDetailPaging(spec, pageable);
            var dtoResult = pageResult.getContent().stream().map(item -> mapper.map(item, DeviceTuDetailDto.class)).collect(Collectors.toList());
            if (!dtoResult.isEmpty()) {
                dtoResult.get(0).setCountTB(countTB);
                dtoResult.get(0).setCountBoTB(countBoTB);
            }
            Page<DeviceTuDetailDto> result = new PageImpl<DeviceTuDetailDto>(dtoResult, pageable, pageResult.getTotalElements());
            response.setData(result);
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setState(ResponseData.STATE.OK.toString());
        } catch (Exception ignored) {
            response.setData(null);
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setState(ResponseData.STATE.FAIL.toString());
        }
        return response;
    }

    @Override
    public ResponseData getListTI(SearchTIList dto) {
        ResponseData response = new ResponseData();
        SearchQuery searchQuery = new SearchQuery();
        //SỐ thiết bị đã nghiệm thu
        var countTB = this.TotalDemNghiemThu(dto);
        var countBoTB = this.TotalDemNghiemThuBoTI(dto);
        List<DeviceTuDetailDto> listCongTo = new ArrayList<DeviceTuDetailDto>();
        //SearchFilter searchFilter2 = new SearchFilter("CATEGORYID", "LIKE", A_ASSET.CategoryId.TU.toString());
        SearchFilter searchFilter = new SearchFilter();
        List<SearchFilter> searchFilters = new ArrayList<>();
        searchFilter = new SearchFilter("ISDELETE", "=", false);
        searchFilters.add(searchFilter);
        if (dto.getPowerSystemType() != null && !dto.getPowerSystemType().isEmpty()) {
            searchFilter = new SearchFilter("powerSystemType", "IN", dto.getPowerSystemType());
            searchFilters.add(searchFilter);
        }
        if (dto.getPowerSystemName() != null && !dto.getPowerSystemName().isEmpty()) {
            searchFilter = new SearchFilter("powerSystemId", "IN", dto.getPowerSystemName());
            searchFilters.add(searchFilter);
        }
        if (dto.getPowerSystemStatus() != null && !dto.getPowerSystemStatus().isEmpty()) {
            searchFilter = new SearchFilter("powerSystemStatus", "IN", dto.getPowerSystemStatus());
            searchFilters.add(searchFilter);
        }
        if (dto.getStatusTU1() != null && !dto.getStatusTU1().isEmpty()) {
            searchFilter = new SearchFilter("statusTU1", "IN", dto.getStatusTU1());
            searchFilters.add(searchFilter);
        }
        if (dto.getStatusTU2() != null && !dto.getStatusTU2().isEmpty()) {
            searchFilter = new SearchFilter("statusTU", "IN", dto.getStatusTU2());
            searchFilters.add(searchFilter);
        }
        if (dto.getNameTUCuon() != null && !dto.getNameTUCuon().isEmpty()) {
            searchFilter = new SearchFilter("nameTUCuon", "LIKE", dto.getNameTUCuon());
            searchFilters.add(searchFilter);
        }
        if (dto.getTinhChat() != null && !dto.getTinhChat().isEmpty()) {
            searchFilter = new SearchFilter("tinhChat", "IN", dto.getTinhChat());
            searchFilters.add(searchFilter);
        }
        if (dto.getDiemDoName() != null && !dto.getDiemDoName().isEmpty()) {
            searchFilter = new SearchFilter("diemDoName", "LIKE", dto.getDiemDoName());
            searchFilters.add(searchFilter);
        }
        if (dto.getMachNThu() != null && !dto.getMachNThu().isEmpty()) {
            for (String machNThu : dto.getMachNThu()) {
                searchFilter = new SearchFilter("machNThu", "LIKE", machNThu, "OR");
                searchFilters.add(searchFilter);
            }
        }
        if (dto.getPManufacturerId() != null && !dto.getPManufacturerId().isEmpty()) {
            searchFilter = new SearchFilter("pManufacturerId", "IN", dto.getPManufacturerId());
            searchFilters.add(searchFilter);
        }
        if (dto.getSerialNum() != null && !dto.getSerialNum().isEmpty()) {
            searchFilter = new SearchFilter("serialNum", "LIKE", dto.getSerialNum());
            searchFilters.add(searchFilter);
        }
        Pageable pageable = PageRequest.of(dto.getPage(), dto.getLimit(), dto.getSortExpression());
        searchFilters.add(searchFilter);
        //searchFilters.add(searchFilter2);
        searchQuery.setSearchFilters(searchFilters);
        Specification<ViewTIDetail> spec = SpecificationUtil.bySearchQuery(searchQuery, ViewTIDetail.class);
        try {
            var pageResult = assetService.findTiDetailPaging(spec, pageable);
            var dtoResult = pageResult.getContent().stream().map(item -> mapper.map(item, DeviceTIDetailDto.class)).collect(Collectors.toList());
            if (!dtoResult.isEmpty()) {
                dtoResult.get(0).setCountTB(countTB);
                dtoResult.get(0).setCountBoTB(countBoTB);
            }
            Page<DeviceTIDetailDto> result = new PageImpl<DeviceTIDetailDto>(dtoResult, pageable, pageResult.getTotalElements());
            response.setData(result);
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setState(ResponseData.STATE.OK.toString());
        } catch (Exception ignored) {
            response.setData(null);
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setState(ResponseData.STATE.FAIL.toString());
        }
        return response;
    }

    private int TotalDemNghiemThuTU(SearchTUList dto) {


        String sql;

        sql = "with x as (SELECT COUNT(ASSETID) as TB  FROM [dbo].[VIEWTU] where STATUS_TU = 2 or STATUS_TU = 4";

        if (dto.getPowerSystemType() != null && !dto.getPowerSystemType().isEmpty()) {
            sql += " AND POWERSYSTEMTYPE IN ('" + String.join("','", dto.getPowerSystemType()) + "')";
        }

        if (dto.getPowerSystemName() != null && !dto.getPowerSystemName().isEmpty()) {
            sql += " AND POWERSYSTEMID IN ('" + String.join("','", dto.getPowerSystemName()) + "')";
        }

        if (dto.getPowerSystemStatus() != null && !dto.getPowerSystemStatus().isEmpty()) {
            sql += " AND POWERSYSTEMSTATUS IN ('" + String.join("','", dto.getPowerSystemStatus()) + "')";
        }

        if (dto.getStatusTU1() != null && !dto.getStatusTU1().isEmpty()) {
            sql += " AND STATUS_TU_1 IN (" + dto.getStatusTU1().stream().map(String::valueOf).collect(Collectors.joining(",")) + ")";
        }
        if (dto.getStatusTU2() != null && !dto.getStatusTU2().isEmpty()) {
            sql += " AND STATUS_TU IN (" + dto.getStatusTU2().stream().map(String::valueOf).collect(Collectors.joining(",")) + ")";
        }
        if (dto.getNameTUCuon() != null && !dto.getNameTUCuon().isEmpty()) {
            sql += "AND NAMETU_CUON LIKE '%" + dto.getNameTUCuon() + "%'";
        }
        if (dto.getTinhChat() != null && !dto.getTinhChat().isEmpty()) {
            sql += " AND TINH_CHAT IN ('" + String.join("','", dto.getTinhChat()) + "')";
        }
        if (dto.getDiemDoName() != null && !dto.getDiemDoName().isEmpty()) {
            sql += "AND DIEMDONAME LIKE '%" + dto.getDiemDoName() + "%'";
        }
        if (dto.getMachNThu() != null && !dto.getMachNThu().isEmpty()) {
            sql += " AND MACH_NTHU IN ('" + String.join("','", dto.getMachNThu()) + "')";
        }
        if (dto.getPManufacturerId() != null && !dto.getPManufacturerId().isEmpty()) {
            sql += " AND P_MANUFACTURERID IN ('" + String.join("','", dto.getPManufacturerId()) + "')";
        }
        if (dto.getSerialNum() != null && !dto.getSerialNum().isEmpty()) {
            sql += "AND SERIALNUM LIKE '%" + dto.getSerialNum() + "%'";
        }
        sql += " group by ASSETID) select count(TB) as CountTB from x";
        List<Integer> lst = new ArrayList<>();
        lst = jdbcTemplate.queryForList(sql, Integer.class);

        if (!lst.isEmpty() && lst.size() > 0) {
            int result = lst.get(0);
            return result;
        } else
            return 0;
    }

    private int TotalDemNghiemThuBoTU(SearchTUList dto) {


        String sql;

        sql = "with x as (SELECT COUNT(ASSETID_PARENT) as BOTB  FROM [dbo].[VIEWTU] where STATUS_TU = 2 or STATUS_TU = 4";

        if (dto.getPowerSystemType() != null && !dto.getPowerSystemType().isEmpty()) {
            sql += " AND POWERSYSTEMTYPE IN ('" + String.join("','", dto.getPowerSystemType()) + "')";
        }

        if (dto.getPowerSystemName() != null && !dto.getPowerSystemName().isEmpty()) {
            sql += " AND POWERSYSTEMID IN ('" + String.join("','", dto.getPowerSystemName()) + "')";
        }

        if (dto.getPowerSystemStatus() != null && !dto.getPowerSystemStatus().isEmpty()) {
            sql += " AND POWERSYSTEMSTATUS IN ('" + String.join("','", dto.getPowerSystemStatus()) + "')";
        }

        if (dto.getStatusTU1() != null && !dto.getStatusTU1().isEmpty()) {
            sql += " AND STATUS_TU_1 IN (" + dto.getStatusTU1().stream().map(String::valueOf).collect(Collectors.joining(",")) + ")";
        }
        if (dto.getStatusTU2() != null && !dto.getStatusTU2().isEmpty()) {
            sql += " AND STATUS_TU IN (" + dto.getStatusTU2().stream().map(String::valueOf).collect(Collectors.joining(",")) + ")";
        }
        if (dto.getNameTUCuon() != null && !dto.getNameTUCuon().isEmpty()) {
            sql += "AND NAMETU_CUON LIKE '%" + dto.getNameTUCuon() + "%'";
        }
        if (dto.getTinhChat() != null && !dto.getTinhChat().isEmpty()) {
            sql += " AND TINH_CHAT IN ('" + String.join("','", dto.getTinhChat()) + "')";
        }
        if (dto.getDiemDoName() != null && !dto.getDiemDoName().isEmpty()) {
            sql += "AND DIEMDONAME LIKE '%" + dto.getDiemDoName() + "%'";
        }
        if (dto.getMachNThu() != null && !dto.getMachNThu().isEmpty()) {
            sql += " AND MACH_NTHU IN ('" + String.join("','", dto.getMachNThu()) + "')";
        }
        if (dto.getPManufacturerId() != null && !dto.getPManufacturerId().isEmpty()) {
            sql += " AND P_MANUFACTURERID IN ('" + String.join("','", dto.getPManufacturerId()) + "')";
        }
        if (dto.getSerialNum() != null && !dto.getSerialNum().isEmpty()) {
            sql += "AND SERIALNUM LIKE '%" + dto.getSerialNum() + "%'";
        }
        sql += " group by ASSETID_PARENT) select count(BOTB) as CountBoTB from x";

        List<Integer> lst = new ArrayList<>();
        lst = jdbcTemplate.queryForList(sql, Integer.class);

        if (!lst.isEmpty() && lst.size() > 0) {
            int result = lst.get(0);
            return result;
        } else
            return 0;
    }

    private int TotalDemNghiemThu(SearchTIList dto) {

        String sql;

        sql = "with x as (SELECT COUNT(ASSETID) as TB  FROM [dbo].[VIEWTI] where STATUS_TU = 2 or STATUS_TU = 4";

        if (dto.getPowerSystemType() != null && !dto.getPowerSystemType().isEmpty()) {
            sql += " AND POWERSYSTEMTYPE IN ('" + String.join("','", dto.getPowerSystemType()) + "')";
        }

        if (dto.getPowerSystemName() != null && !dto.getPowerSystemName().isEmpty()) {
            sql += " AND POWERSYSTEMID IN ('" + String.join("','", dto.getPowerSystemName()) + "')";
        }

        if (dto.getPowerSystemStatus() != null && !dto.getPowerSystemStatus().isEmpty()) {
            sql += " AND POWERSYSTEMSTATUS IN ('" + String.join("','", dto.getPowerSystemStatus()) + "')";
        }

        if (dto.getStatusTU1() != null && !dto.getStatusTU1().isEmpty()) {
            sql += " AND STATUS_TU_1 IN (" + dto.getStatusTU1().stream().map(String::valueOf).collect(Collectors.joining(",")) + ")";
        }
        if (dto.getStatusTU2() != null && !dto.getStatusTU2().isEmpty()) {
            sql += " AND STATUS_TU IN (" + dto.getStatusTU2().stream().map(String::valueOf).collect(Collectors.joining(",")) + ")";
        }
        if (dto.getNameTUCuon() != null && !dto.getNameTUCuon().isEmpty()) {
            sql += "AND NAMETU_CUON LIKE '%" + dto.getNameTUCuon() + "%'";
        }
        if (dto.getTinhChat() != null && !dto.getTinhChat().isEmpty()) {
            sql += " AND TINH_CHAT IN ('" + String.join("','", dto.getTinhChat()) + "')";
        }
        if (dto.getDiemDoName() != null && !dto.getDiemDoName().isEmpty()) {
            sql += "AND DIEMDONAME LIKE '%" + dto.getDiemDoName() + "%'";
        }
        if (dto.getMachNThu() != null && !dto.getMachNThu().isEmpty()) {
            sql += " AND MACH_NTHU IN ('" + String.join("','", dto.getMachNThu()) + "')";
        }
        if (dto.getPManufacturerId() != null && !dto.getPManufacturerId().isEmpty()) {
            sql += " AND P_MANUFACTURERID IN ('" + String.join("','", dto.getPManufacturerId()) + "')";
        }
        if (dto.getSerialNum() != null && !dto.getSerialNum().isEmpty()) {
            sql += "AND SERIALNUM LIKE '%" + dto.getSerialNum() + "%'";
        }
        sql += " group by ASSETID) select count(TB) as CountTB from x";

        List<Integer> lst = new ArrayList<>();
        lst = jdbcTemplate.queryForList(sql, Integer.class);

        if (!lst.isEmpty() && lst.size() > 0) {
            int result = lst.get(0);
            return result;
        } else
            return 0;
    }

    private int TotalDemNghiemThuBoTI(SearchTIList dto) {


        String sql;

        sql = "with x as (SELECT COUNT(ASSETID) as BOTB  FROM [dbo].[VIEWTI] where STATUS_TU = 2 or STATUS_TU = 4";

        if (dto.getPowerSystemType() != null && !dto.getPowerSystemType().isEmpty()) {
            sql += " AND POWERSYSTEMTYPE IN ('" + String.join("','", dto.getPowerSystemType()) + "')";
        }

        if (dto.getPowerSystemName() != null && !dto.getPowerSystemName().isEmpty()) {
            sql += " AND POWERSYSTEMID IN ('" + String.join("','", dto.getPowerSystemName()) + "')";
        }

        if (dto.getPowerSystemStatus() != null && !dto.getPowerSystemStatus().isEmpty()) {
            sql += " AND POWERSYSTEMSTATUS IN ('" + String.join("','", dto.getPowerSystemStatus()) + "')";
        }

        if (dto.getStatusTU1() != null && !dto.getStatusTU1().isEmpty()) {
            sql += " AND STATUS_TU_1 IN (" + dto.getStatusTU1().stream().map(String::valueOf).collect(Collectors.joining(",")) + ")";
        }
        if (dto.getStatusTU2() != null && !dto.getStatusTU2().isEmpty()) {
            sql += " AND STATUS_TU IN (" + dto.getStatusTU2().stream().map(String::valueOf).collect(Collectors.joining(",")) + ")";
        }
        if (dto.getNameTUCuon() != null && !dto.getNameTUCuon().isEmpty()) {
            sql += "AND NAMETU_CUON LIKE '%" + dto.getNameTUCuon() + "%'";
        }
        if (dto.getTinhChat() != null && !dto.getTinhChat().isEmpty()) {
            sql += " AND TINH_CHAT IN ('" + String.join("','", dto.getTinhChat()) + "')";
        }
        if (dto.getDiemDoName() != null && !dto.getDiemDoName().isEmpty()) {
            sql += "AND DIEMDONAME LIKE '%" + dto.getDiemDoName() + "%'";
        }
        if (dto.getMachNThu() != null && !dto.getMachNThu().isEmpty()) {
            sql += " AND MACH_NTHU IN ('" + String.join("','", dto.getMachNThu()) + "')";
        }
        if (dto.getPManufacturerId() != null && !dto.getPManufacturerId().isEmpty()) {
            sql += " AND P_MANUFACTURERID IN ('" + String.join("','", dto.getPManufacturerId()) + "')";
        }
        if (dto.getSerialNum() != null && !dto.getSerialNum().isEmpty()) {
            sql += "AND SERIALNUM LIKE '%" + dto.getSerialNum() + "%'";
        }
        sql += " group by ASSETID_PARENT) select count(BOTB) as CountBoTB from x";

        List<Integer> lst = new ArrayList<>();
        lst = jdbcTemplate.queryForList(sql, Integer.class);

        if (!lst.isEmpty() && lst.size() > 0) {
            int result = lst.get(0);
            return result;
        } else
            return 0;
    }

    @Transactional
    @Override
    public ResponseData createDevice(DeviceCrudDto dto, String categoryId) throws Exception {
        ResponseData response = new ResponseData();
        try {
            var checkSerialNum = assetService.findBySerialNum(dto.getSerialNum().trim(), categoryId);
            if (checkSerialNum != null && checkSerialNum.getSERIALNUM().equals(dto.getSerialNum())) {
                response.setState(ResponseData.STATE.FAIL.toString());
                response.setMessage("Số chế tạo đã tồn tại");
                response.setData(null);
                return response;
            }
            var uuid = UUID.randomUUID().toString();
            A_ASSET asset = new A_ASSET();
            mapper.map(dto, asset);
            asset.setASSETID(uuid);
            asset.setCATEGORYID(categoryId);
            asset.setUSER_CR_DTIME(new Date());
            asset.setISDELETE(false);
            asset.setISSAVE(false);
            var assetEntity = assetService.createAsset(asset);
            //save cong to
            S_ATTRIBUTE_GROUP_ASSOB attr = new S_ATTRIBUTE_GROUP_ASSOB();
            if (categoryId.equals(A_ASSET.CategoryId.DIEMDO.toString())) {
                var congToEntity = crudDiemDo(dto.getDiemDo(), uuid, null);
                attr.setATTRDATAID(congToEntity.getATTRDATAID());
                attr.setATTRGROUPID(A_ASSET.CategoryId.DIEMDO.toString());

            }
            if (categoryId.equals(A_ASSET.CategoryId.CONGTO.toString())) {
                var congToEntity = crudCongTo(dto.getCongTo(), uuid, null);
                attr.setATTRDATAID(congToEntity.getAttrDataId());
                attr.setATTRGROUPID(A_ASSET.CategoryId.CONGTO.toString());

            }
            //save asset_obj
            attr.setOBJID(assetEntity.getASSETID());
            attr.setOBJTYPEID("A");
            attr.setATTRGROUPORD(1);
            attrRepository.saveAndFlush(attr);
            // var result = this.getDevicesByCategory(categoryId, uuid);
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(assetEntity.getASSETID());
        } catch (Exception ignored) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setData(null);
            throw new Exception("");
        }
        return response;
    }


    private ZAG_CongTo crudCongTo(ZAGCongTo dto, String uuid, ZAG_CongTo congToEntity) {
        ZAG_CongTo congTo = new ZAG_CongTo();
        if (congToEntity != null) {
            congTo = congToEntity;
        }
        congTo.setAttrDataId(uuid);
        congTo.setObjTypeId("A");
        congTo.setObjId(uuid);
        congTo.setI(dto.getI());
        congTo.setU(dto.getU());
        congTo.setHsn(dto.getHsn());
        congTo.setCcxWh(dto.getCcxWh());
        congTo.setChiNMD(dto.getChiNMD());
        congTo.setCcxVarh(dto.getCcxVarh());
        congTo.setKieuCto(dto.getKieuCto());
        congTo.setLapTrinh(dto.getLapTrinh());
        congTo.setLoaiCto(dto.getLoaiCto());
        congTo.setTinhChat(dto.getTinhChat());
        congTo.setTsbTU(dto.getTsbTU());
        congTo.setTsbTI(dto.getTsbTI());
        return zagCongToRepository.saveAndFlush(congTo);

    }

    @Override
    public ResponseData updateDevice(String id, DeviceCrudDto dto, String categoryId) {
        ResponseData response = new ResponseData();
        String sql1;
        var strASSETID = dto.getAssetRoot().split(",");
        for (String assetId : strASSETID) {
            sql1 = "select  1 from Q_PQOBJ_USER where ASSETID =? and USERID=? and ISQL=?";
            List lst = jdbcTemplate.queryForList(sql1, assetId, dto.getUserMDFId(), true);
            if (lst == null || lst.isEmpty())
                return new ResponseData(ResponseData.STATE.FAIL.toString(), "Không có quyền cập nhật công tơ");
        }
        String s;
        s = "select 1 from A_ASSET WHERE  CATEGORYID = 'CONGTO' and SERIALNUM = ? and ASSETID!=?";
        List lst = jdbcTemplate.queryForList(s, dto.getSerialNum(), id);
        if (lst != null && !lst.isEmpty())
            return new ResponseData(ResponseData.STATE.FAIL.toString(), "Số chế tạo công tơ không được trùng");
        try {
            String sql2;
            sql2 = "select  * from VIEW_CONGTO where ASSETID = '" + id + "'";
            List<DeviceCrudDto> lst2 = jdbcTemplate.query(sql2, new BeanPropertyRowMapper<DeviceCrudDto>(DeviceCrudDto.class));

            var entityBySerialNum = assetService.findByAssetId(id, categoryId);
            if (Objects.equals(entityBySerialNum.getSERIALNUM(), dto.getSerialNum().trim()) && !entityBySerialNum.getASSETID().equals(id)) {
                response.setState(ResponseData.STATE.FAIL.toString());
                response.setMessage("Số chế tạo đã tồn tại");
                response.setData(null);
                return response;
            }
            A_ASSET asset = assetService.findById(id).orElseThrow(() -> new NotFoundException("Không tìm thấy công tơ"));
            asset.setTYPEID(dto.getTypeId());
            asset.setASSETID_PARENT(dto.getAssetIdParent());
            asset.setCATEGORYID(categoryId);
            asset.setUSER_CR_ID(dto.getUserCRId());
            asset.setINSPECTION_STAMPS(dto.getInspectionStamps());
            asset.setUSER_CR_DTIME(new Date());
            asset.setASSETNOTE(dto.getAssetNote());
            asset.setORGID(dto.getOrgId());
            asset.setCYCLEACCREDITATION(dto.getCycleAccreditation());
            asset.setSERIALNUM(dto.getSerialNum());
            asset.setISDELETE(false);
            asset.setASSETNOTE(dto.getAssetNote());
            var assetEntity = assetService.createAsset(asset);
            //save cong to
            if (categoryId.equals(A_ASSET.CategoryId.CONGTO.toString())) {
                ZAG_CongTo congTo = zagCongToRepository.findById(dto.getAttrDataId()).orElseThrow(() -> new NotFoundException("Không tìm thấy công tơ"));
                crudCongTo(dto.getCongTo(), dto.getAttrDataId(), congTo);
            }
            var result = this.getDevicesByCategory(categoryId, id);

            //lưu his

            DeviceCrudDto dto1 = mapper.map(lst2.get(0), DeviceCrudDto.class);

            dto1.setSerialNum(lst2.get(0).getSerialNum());
            dto1.setInspectionStamps(lst2.get(0).getInspectionStamps());
            dto1.setOrgId(lst2.get(0).getOrgId());
            dto1.setCycleAccreditation(lst2.get(0).getCycleAccreditation());

            List<String> ignoredFields = new ArrayList<>() {{
                add("userid");
                add("transid");
                add("assetidparent");
                add("assetdesc");
                add("usermdfid");
                add("issave");
                add("dateaccreditation");
                add("objtypeid");
                add("congto");
                add("objid");
                add("inspectionstamps");
                add("orgid");
                add("cycleaccreditation");

            }};
            Map<String, String> variableNameMapping = new HashMap<>();
            variableNameMapping.put("serialnum", "Số chế tạo");
            variableNameMapping.put("chuky", "Chu kỳ KĐ");
            variableNameMapping.put("laptrinh", "Lập trình");
            variableNameMapping.put("city", "Tỉnh/Thành phố");
            variableNameMapping.put("chinmd", "Chì niêm mạch đo");
            variableNameMapping.put("orgidstr", "Đơn vị sở hữu");
            variableNameMapping.put("tempstr", "Tem KĐ");
            variableNameMapping.put("loaictostr", "Loại công tơ");
            variableNameMapping.put("kieuctostr", "Kiểu công tơ");
            variableNameMapping.put("istr", "Dòng diện- I(A)");
            variableNameMapping.put("ustr", "Điện áp- U(V)");
            variableNameMapping.put("ccxvarhstr", "Cấp chính xác (Varh)");
            variableNameMapping.put("ccxwhstr", "Cấp chính xác (Wh)");
            variableNameMapping.put("tsbtistr", "Tỷ số biến TI");
            variableNameMapping.put("tsbtustr", "Tỷ số biến TU");
            variableNameMapping.put("hsnstr", "Hệ số nhân");
            variableNameMapping.put("assetnote", "Mô tả");
            _wonderHisService.UpdateHis(id, dto, dto1, ignoredFields, variableNameMapping, dto.getUserMDFId(), "DEVICE_CONGTO", "UPDATE");

            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(result.getData());
        } catch (Exception ignored) {
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(null);
        }
        return response;
    }

    @Override
    public ResponseData deleteDevice(String id, String categoryId) {
        ResponseData response = new ResponseData();
        try {
            var assetEntity = assetService.findById(id).orElseThrow(() -> new NotFoundException("Cong to khong ton tai"));
            assetEntity.setISDELETE(true);
            assetService.createAsset(assetEntity);
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(assetEntity.getASSETID());
        } catch (Exception ignored) {
            response.setState(ResponseData.STATE.OK.toString());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setData(null);
        }
        return response;
    }

    private ZAG_DIEMDO crudDiemDo(ZAGDiemDo dto, String uuid, ZAG_DIEMDO entity) {
        ZAG_DIEMDO diemDo = new ZAG_DIEMDO();
        if (entity != null) {
            diemDo = entity;
        }
        diemDo.setATTRDATAID(uuid);
        diemDo.setOBJTYPEID("A");
        diemDo.setOBJID(uuid);
        diemDo.setDTAC_XNK(dto.getdTacXNK());
        diemDo.setXNK(dto.getXnk());
        diemDo.setDVI_GNHAN1(dto.getdViGNhan1());
        diemDo.setDVI_GNHAN2(dto.getdViGNhan2());
        diemDo.setNTHU_TAI(dto.getnThuTai());
        diemDo.setNTHU_TINH(dto.getnThuTinh());
        diemDo.setNTHU_TTSL(dto.getnThuTTSL());
        diemDo.setTINH_CHAT1(dto.getTinhChat1());
        diemDo.setTINH_CHAT2(dto.getTinhChat2());
        return diemdoRepository.saveAndFlush(diemDo);
    }


    @Override
    public Boolean validateCongToSerialNum(String serialNum, String congToId) {
        var entityBySerialNum = assetService.findBySerialNum(serialNum, A_ASSET.CategoryId.CONGTO.toString());
        if (entityBySerialNum != null && Objects.equals(entityBySerialNum.getSERIALNUM(), serialNum) && !entityBySerialNum.getASSETID().equals(congToId)) {
            return true;
        }
        return false;
    }

    //get tu device
    @Override
    public ResponseData getListTUChild(SearchTUList dto) {
        ResponseData response = new ResponseData();
        SearchQuery searchQuery = new SearchQuery();
        List<SearchFilter> searchFilters = new ArrayList<>();
        SearchFilter searchFilter = new SearchFilter("isDelete", "=", false);
        searchFilters.add(searchFilter);
        if (dto.getAssetIdParent() != null && !dto.getAssetIdParent().isEmpty()) {
            searchFilter = new SearchFilter("powerSystemId", "=", dto.getAssetIdParent());
            searchFilters.add(searchFilter);
        }
        if (dto.getStatusBTU() != null && !dto.getStatusBTU().isEmpty() && dto.getStatusBTU().equalsIgnoreCase("recycle")) {
            searchFilter = new SearchFilter("statusBTU", "<", 0);
            searchFilters.add(searchFilter);
        }
        if (dto.getStatusBTU() != null && !dto.getStatusBTU().isEmpty() && dto.getStatusBTU().equalsIgnoreCase("using")) {
            searchFilter = new SearchFilter("statusBTU", ">", 0, "OR");
            searchFilters.add(searchFilter);
            searchFilter = new SearchFilter("statusBTU", "=", 0, "OR");
            searchFilters.add(searchFilter);
        }
        if (dto.getStatusTU() != null && !dto.getStatusTU().isEmpty() && dto.getStatusTU().equalsIgnoreCase("recycle")) {
            searchFilter = new SearchFilter("statusTU", "<", 0);
            searchFilters.add(searchFilter);
        }
        if (dto.getIgnoreTUId() != null && !dto.getIgnoreTUId().isEmpty()) {
            searchFilter = new SearchFilter("idTU", "NotIn", dto.getIgnoreTUId());
            searchFilters.add(searchFilter);
        }
        if (dto.getStatusTU() != null && !dto.getStatusTU().isEmpty() && dto.getStatusTU().equalsIgnoreCase("using")) {
            searchFilter = new SearchFilter("statusTU", ">", 0);
            searchFilters.add(searchFilter);
        }
        if (dto.getStatusTU() != null && !dto.getStatusTU().isEmpty() && dto.getStatusTU().equalsIgnoreCase("cancel")) {
            searchFilter = new SearchFilter("statusTU", "=", 0);
            searchFilters.add(searchFilter);
        }
        if (dto.getSetType() != null) {
            searchFilter = new SearchFilter("setType", "=", dto.getSetType());
            searchFilters.add(searchFilter);
        }
        Pageable pageable = PageRequest.of(dto.getPage(), dto.getLimit(), dto.getSortExpression());
        searchQuery.setSearchFilters(searchFilters);
        Specification<ViewTUDevice> spec = SpecificationUtil.bySearchQuery(searchQuery, ViewTUDevice.class);
        try {
            var pageResult = deviceTUService.findTUDevicePaging(pageable, spec);
            response.setData(pageResult);
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setState(ResponseData.STATE.OK.toString());
        } catch (Exception ignored) {
            response.setData(null);
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setState(ResponseData.STATE.FAIL.toString());
        }
        return response;
    }

    @Override
    public ResponseData getListTIChild(SearchTIList dto) {
        ResponseData response = new ResponseData();
        SearchQuery searchQuery = new SearchQuery();
        List<SearchFilter> searchFilters = new ArrayList<>();
        SearchFilter searchFilter = new SearchFilter("isDelete", "=", false);
        searchFilters.add(searchFilter);
        if (dto.getAssetIdParent() != null && !dto.getAssetIdParent().isEmpty()) {
            searchFilter = new SearchFilter("powerSystemId", "=", dto.getAssetIdParent());
            searchFilters.add(searchFilter);
        }
        if (dto.getSetType() != null) {
            searchFilter = new SearchFilter("setType", "=", dto.getSetType());
            searchFilters.add(searchFilter);
        }
        if (dto.getIgnoreTIId() != null && !dto.getIgnoreTIId().isEmpty()) {
            searchFilter = new SearchFilter("idTI", "NotIn", dto.getIgnoreTIId());
            searchFilters.add(searchFilter);
        }
        if (dto.getStatusBTI() != null && !dto.getStatusBTI().isEmpty()) {
            if (dto.getStatusBTI().equalsIgnoreCase("recycle")) {
                searchFilter = new SearchFilter("statusBTI", "<", 0);
                searchFilters.add(searchFilter);
            }
            if (dto.getStatusBTI().equalsIgnoreCase("using")) {
                searchFilter = new SearchFilter("statusBTI", ">", 0, "OR");
                searchFilters.add(searchFilter);
                searchFilter = new SearchFilter("statusBTI", "=", 0, "OR");
                searchFilters.add(searchFilter);
            }
        }
        if (dto.getStatusTI() != null && !dto.getStatusTI().isEmpty()) {
            if (dto.getStatusTI().equalsIgnoreCase("recycle")) {
                searchFilter = new SearchFilter("statusTI", "<", 0);
                searchFilters.add(searchFilter);
            }
            if (dto.getStatusTI().equalsIgnoreCase("using")) {
                searchFilter = new SearchFilter("statusTI", ">", 0);
                searchFilters.add(searchFilter);

            }
            if (dto.getStatusTI().equalsIgnoreCase("cancel")) {
                searchFilter = new SearchFilter("statusTI", "=", 0);
                searchFilters.add(searchFilter);
            }
        }

        Pageable pageable = PageRequest.of(dto.getPage(), dto.getLimit(), dto.getSortExpression());
        searchQuery.setSearchFilters(searchFilters);
        Specification<ViewTIDevice> spec = SpecificationUtil.bySearchQuery(searchQuery, ViewTIDevice.class);
        try {
            var pageResult = deviceTIService.findTIDevicePaging(pageable, spec);
            response.setData(pageResult);
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setState(ResponseData.STATE.OK.toString());
        } catch (Exception ignored) {
            response.setData(null);
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setState(ResponseData.STATE.FAIL.toString());
        }
        return response;
    }

    @Override
    public ResponseData getHTDDByDiemDoId(SearchHTDDListDto dto) {
        ResponseData response = new ResponseData();
        SearchQuery searchQuery = new SearchQuery();
        List<DeviceCongToListDto> listCongTo = new ArrayList<DeviceCongToListDto>();
        SearchFilter searchFilter = new SearchFilter();
        List<SearchFilter> searchFilters = new ArrayList<>();
        if (dto.getAssetIdParent() != null && !dto.getAssetIdParent().isEmpty()) {
            searchFilter = new SearchFilter("idPower", "IN", dto.getAssetIdParent());
            searchFilters.add(searchFilter);
        }
        if (dto.getStatusDiemDo() != null && !dto.getStatusDiemDo().isEmpty()) {
            searchFilter = new SearchFilter("statusDiemDo", "IN", dto.getStatusDiemDo());
            searchFilters.add(searchFilter);
        }
        if (dto.getDiemDoId() != null && !dto.getDiemDoId().isEmpty()) {
            searchFilter = new SearchFilter("idDiemDo", "=", dto.getDiemDoId());
            searchFilters.add(searchFilter);
        }
        if (dto.getIgnoreDiemDoId() != null && !dto.getIgnoreDiemDoId().isEmpty()) {
            searchFilter = new SearchFilter("idDiemDo", "NotIn", dto.getIgnoreDiemDoId());
            searchFilters.add(searchFilter);
        }
        Pageable pageable = PageRequest.of(dto.getPage(), dto.getLimit(), dto.getSortExpression());
        searchQuery.setSearchFilters(searchFilters);
        Specification<View_HTDD> spec = SpecificationUtil.bySearchQuery(searchQuery, View_HTDD.class);
        try {
            var pageResult = htddRepository.findAll(spec, pageable);
            var dtoResult = pageResult.getContent().stream().map(item -> mapper.map(item, ViewHTDDDto.class)).collect(Collectors.toList());
            Page<ViewHTDDDto> result = new PageImpl<ViewHTDDDto>(dtoResult, pageable, pageResult.getTotalElements());
            response.setData(result);
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setState(ResponseData.STATE.OK.toString());
        } catch (Exception ignored) {
            response.setData(null);
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setState(ResponseData.STATE.FAIL.toString());
        }
        return response;
    }

    @Override
    public ResponseData deleteHTDDByDiemDoId(String idDiemDo) {
        ResponseData response = new ResponseData();
        try {
            var viewHTDD = htddRepository.findById(idDiemDo).orElse(null);
            //store to delete htdd


        } catch (Exception ex) {

        }
        return response;
    }

    @Transactional
    @Override
    public ResponseData deleteRecordTemp(BigInteger transId) {
        ResponseData data = new ResponseData();
        data.setState(ResponseData.STATE.OK.toString());
        data.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
        try {
            List<A_ASSET> listRecordTemp = assetService.findRecordsTemp(transId);
            for (A_ASSET item : listRecordTemp) {
                List<S_ATTRIBUTE_GROUP_ASSOB> saList = attrRepository.findByObjId(item.getASSETID());
                if (saList != null && saList.size() > 0) {
                    var sa = saList.get(0);
                    switch (item.getCATEGORYID()) {
                        case "DIEMDO":
                            diemdoRepository.deleteById(sa.getATTRDATAID());
                            assetlinkRepository.deleteAllByAssetIdLink(item.getASSETID());
                            break;
                        case "CONGTO":
                            zagCongToRepository.deleteById(sa.getATTRDATAID());
                            break;
                        case "TU":
                            zagTURepository.deleteById(sa.getATTRDATAID());
                            break;
                        case "TI":
                            zagTIRepository.deleteById(sa.getATTRDATAID());
                            break;
                        case "CUON":
                            zagCuonRepository.deleteById(sa.getATTRDATAID());
                            assetlinkRepository.deleteAllByAssetId(item.getASSETID());
                            break;
                    }
                    attrRepository.delete(sa);
                }
                assetRepository.deleteById(item.getASSETID());

            }
        } catch (Exception ignored) {
            throw new RuntimeException(ignored);
        }
        return data;
    }

    @Transactional
    @Override
    public ResponseData deleteDiemDo(String id) throws Exception {
        ResponseData res = new ResponseData();
        try {
            var sa = attrRepository.findByObjId(id).stream().findFirst().get();
            var attrId = sa.getATTRDATAID();
            //delete zag
            diemdoRepository.deleteById(attrId);
            //delete sa
            attrRepository.delete(sa);
            //delete a_link

            assetlinkRepository.deleteAllByAssetIdLink(id);
            //delete asset
            assetRepository.deleteById(id);
            res.setData(id);
            res.setState(ResponseData.STATE.OK.toString());
            res.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        return res;
    }

    @Override
    public ResponseData deleteCongTo(String id) throws Exception {
        ResponseData res = new ResponseData();
        try {
            var sa = attrRepository.findByObjId(id).stream().findFirst().get();
            var attrId = sa.getATTRDATAID();
            //delete zag
            zagCongToRepository.deleteById(attrId);
            //delete sa
            attrRepository.delete(sa);
            //delete asset
            assetRepository.deleteById(id);
            res.setData(id);
            res.setState(ResponseData.STATE.OK.toString());
            res.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        return res;
    }
//    public ResponseData deleteTUle(String id) throws Exception {
//        ResponseData res = new ResponseData();
//    }

    @Override
    @Transactional
    public ResponseData deleteTU(String id) throws Exception {
        ResponseData res = new ResponseData();
        try {
//            var sa = attrRepository.findByObjId(id).stream().findFirst().get();
//            var attrId = sa.getATTRDATAID();
            //get tu child
            var listTU = assetRepository.findByParent(id);
            //find and delete cuon
            for (A_ASSET tu : listTU) {
                var listCuon = assetRepository.findByParent(tu.getASSETID());
                var listId = listCuon.stream().map(A_ASSET::getASSETID).collect(Collectors.toList());
                var listAttr = attrRepository.findAllByOBJID(listId);
                var listZag = zagCuonRepository.findAllById(listAttr.stream().map(S_ATTRIBUTE_GROUP_ASSOB::getATTRDATAID).collect(Collectors.toList()));
                zagCuonRepository.deleteAll(listZag);
                attrRepository.deleteAll(listAttr);
                assetlinkRepository.deleteAllByAssetId(listId);
                assetRepository.deleteAll(listCuon);
            }
            //find and delete tu child
            var listAttr = attrRepository.findAllByOBJID(listTU.stream().map(A_ASSET::getASSETID).collect(Collectors.toList()));
            var listZag = zagTURepository.findAllById(listAttr.stream().map(S_ATTRIBUTE_GROUP_ASSOB::getATTRDATAID).collect(Collectors.toList()));
            zagTURepository.deleteAll(listZag);
            attrRepository.deleteAll(listAttr);
            assetRepository.deleteAll(listTU);
            //delete parent
            assetRepository.deleteById(id);
            res.setData(id);
            res.setState(ResponseData.STATE.OK.toString());
            res.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        return res;
    }

    @Override
    @Transactional
    public ResponseData deleteTI(String id) throws Exception {
        ResponseData res = new ResponseData();
        try {
            //var sa = attrRepository.findByObjId(id).stream().findFirst().get();
            //get tu child
            var listTU = assetRepository.findByParentId(id);
            //find and delete cuon
            for (A_ASSET tu : listTU) {
                var listCuon = assetRepository.findByParentId(tu.getASSETID());
                var listId = listCuon.stream().map(A_ASSET::getASSETID).collect(Collectors.toList());
                var listAttr = attrRepository.findAllByOBJID(listId);
                var listZag = zagCuonRepository.findAllById(listAttr.stream().map(S_ATTRIBUTE_GROUP_ASSOB::getATTRDATAID).collect(Collectors.toList()));
                zagCuonRepository.deleteAll(listZag);
                attrRepository.deleteAll(listAttr);
                assetlinkRepository.deleteAllByAssetId(listId);
                assetRepository.deleteAll(listCuon);
            }
            //find and delete tu child
            var listAttr = attrRepository.findAllByOBJID(listTU.stream().map(A_ASSET::getASSETID).collect(Collectors.toList()));
            var listZag = zagTIRepository.findAllById(listAttr.stream().map(S_ATTRIBUTE_GROUP_ASSOB::getATTRDATAID).collect(Collectors.toList()));
            zagTIRepository.deleteAll(listZag);
            attrRepository.deleteAll(listAttr);
            assetRepository.deleteAll(listTU);
            //delete parent
            assetRepository.deleteById(id);
            res.setData(id);
            res.setState(ResponseData.STATE.OK.toString());
            res.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        return res;
    }

    @Override
    @Transactional
    public ResponseData updateCongTo(CustomUserDetails userDetails, CongToCrudDto dto) throws Exception {
        ResponseData res = new ResponseData();
        try {
            res.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            res.setState(ResponseData.STATE.OK.toString());
            var typeCreate = dto.getTypeCreate();
            if (typeCreate.equals("UPDATE")) {
                var congToUpdate = assetRepository.findById(dto.getAssetId()).orElse(new A_ASSET());
                res.setData(congToUpdate);
            } else {
                var findCongToReplace = assetRepository.findById(dto.getAssetIdChange()).orElse(null);
                if (dto.getCongTo() != null) {
                    assert findCongToReplace != null;
//            var congTo = mapper.map(findCongToReplace, A_ASSET.class);
                    var congTo = mapper.map(dto, A_ASSET.class);
                    var assetId = congTo.getASSETID();
                    if (assetId == null) {
                        assetId = UUID.randomUUID().toString();
                        congTo.setUSER_CR_DTIME(new Date());
                        congTo.setUSER_CR_ID(userDetails.getUserid());
                    } else {
                        congTo.setUSER_MDF_DTIME(new Date());
                        congTo.setUSER_MDF_ID(userDetails.getUserid());
                    }
                    if (findCongToReplace.getASSETID_PARENT() != null) {
                        congTo.setUSESTATUS_LAST_ID(findCongToReplace.getUSESTATUS_LAST_ID());
                        congTo.setASSETID_PARENT(findCongToReplace.getASSETID_PARENT());
                        congTo.setASSETDESC(findCongToReplace.getASSETDESC());
                        congTo.setASSETROOT(findCongToReplace.getASSETROOT());
                    }
                    congTo.setASSETID(assetId);
                    congTo.setTYPEID("CTO");
                    congTo.setCATEGORYID(A_ASSET.CategoryId.CONGTO.toString());
                    congTo.setISDELETE(false);
                    congTo.setISSAVE(true);
                    var congToRs = assetRepository.save(congTo);
                    var zagId = dto.getCongTo().getAttrDataId();
                    var zagCongTo = mapper.map(dto.getCongTo(), ZAG_CongTo.class);
                    if (zagId == null) {
                        zagId = UUID.randomUUID().toString();
                    }
                    zagCongTo.setAttrDataId(zagId);
                    zagCongTo.setObjId(assetId);
                    zagCongTo.setObjTypeId("A");
                    zagCongToRepository.save(zagCongTo);
                    SAttrGroupAssObjId saId = new SAttrGroupAssObjId();
                    saId.setOBJID(assetId);
                    saId.setATTRGROUPID("CONGTO");
                    saId.setOBJTYPEID("A");
                    var saList = attrRepository.findById(saId).orElse(null);
                    if (saList == null) {
                        S_ATTRIBUTE_GROUP_ASSOB sa = new S_ATTRIBUTE_GROUP_ASSOB();
                        sa.setOBJID(assetId);
                        sa.setOBJTYPEID("A");
                        sa.setATTRDATAID(zagId);
                        sa.setATTRGROUPID(A_ASSET.CategoryId.CONGTO.toString());
                        sa.setATTRGROUPORD(1);
                        attrRepository.save(sa);
                    }

                    res.setData(congToRs);
                    findCongToReplace.setASSETID_PARENT(null);
                    findCongToReplace.setASSETDESC(null);
                    var saCongToReplace = attrRepository.findByObjId(findCongToReplace.getASSETID());
                    if (saCongToReplace != null && saCongToReplace.size() > 0) {
                        var zagCongToReplace = zagCongToRepository.findById(saCongToReplace.get(0).getATTRDATAID()).orElse(null);
                        assert zagCongToReplace != null;
                        zagCongToReplace.setTinhChat(null);
                    }
                    findCongToReplace.setUSESTATUS_LAST_ID(null);
                    assetRepository.save(findCongToReplace);
                } else {
                    findCongToReplace.setASSETID_PARENT(null);
                    findCongToReplace.setASSETDESC(null);
                    var congToRs = assetRepository.save(findCongToReplace);
                    res.setData(null);
                }

            }
        } catch (Exception ex) {
            throw new Exception(ex);
        }
        return res;
    }

    @Override
    public ViewCuon getCuonById(String id) {
        ResponseData res = new ResponseData();
        try {
            var cuonEntity = cuonService.findCuonById(id).orElseThrow(() -> new NotFoundException("Không tìm thấy cuộn"));
            return cuonEntity;
        } catch (Exception ignored) {
            return null;
        }
    }

    @Override
    public ResponseData getSetTUById(String id) {
        ResponseData res = new ResponseData();
        try {
            var setTu = viewSetTURepository.findById(id).orElse(null);
            assert setTu != null;
            var listSort = setTu.getTuList().stream().sorted(Comparator.comparing(ViewTUPre::getAssetORD)).collect(Collectors.toList());
            setTu.setTuList(listSort);
            res.setState(ResponseData.STATE.OK.toString());
            res.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            res.setData(setTu);
        } catch (Exception ex) {
            res.setState(ResponseData.STATE.FAIL.toString());
            res.setMessage(ResponseData.MESSAGE.ERROR.toString());
        }
        return res;
    }

    @Override
    public ResponseData getSetTIById(String id) {
        ResponseData res = new ResponseData();
        try {
            var setTi = viewSetTIRepository.findById(id).orElse(null);
            assert setTi != null;
            var listSort = setTi.getTiList().stream().sorted(Comparator.comparing(ViewTI::getAssetORD)).collect(Collectors.toList());
            setTi.setTiList(listSort);
            res.setData(setTi);
            res.setState(ResponseData.STATE.OK.toString());
            res.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
        } catch (Exception ex) {
            res.setState(ResponseData.STATE.FAIL.toString());
            res.setMessage(ResponseData.MESSAGE.ERROR.toString() + ex);
        }
        return res;
    }


    @Transactional
    public A_ASSET createAsset(DeviceCrudDto dto, String categoryType) {
        A_ASSET assetTU = new A_ASSET();
        var uuid = UUID.randomUUID().toString();
        mapper.map(dto, assetTU);
        assetTU.setASSETID(uuid);
        assetTU.setCATEGORYID(categoryType);
        assetTU.setISDELETE(false);
        assetTU.setISSAVE(false);
        return assetService.createAsset(assetTU);
    }

    public ResponseData createOrUpdateDiemDo(DeviceCrudDto diemDoDto, CustomUserDetails userDetails) throws Exception {
        ResponseData res = new ResponseData();
        try {
            var currentUser = userDetails.getUserid();
            var dateCreateOrUpdate = new Date();
            var diemDoAsset = mapper.map(diemDoDto, A_ASSET.class);
            var assetId = diemDoAsset.getASSETID();
            if (assetId == null) {
                assetId = UUID.randomUUID().toString();
                diemDoAsset.setUSER_CR_ID(currentUser);
                diemDoAsset.setUSER_CR_DTIME(dateCreateOrUpdate);
                diemDoAsset.setISSAVE(false);
            } else {
                diemDoAsset.setUSER_MDF_ID(currentUser);
                diemDoAsset.setUSER_MDF_DTIME(dateCreateOrUpdate);
                diemDoAsset.setISSAVE(true);
            }
            diemDoAsset.setUSESTATUS_LAST_DTIME(new Date());
            diemDoAsset.setCATEGORYID(A_ASSET.CategoryId.DIEMDO.toString());
            diemDoAsset.setTYPEID("DDO");
            diemDoAsset.setASSETID(assetId);
            diemDoAsset.setISDELETE(false);
            //create or update asset
            var assetEntity = assetRepository.saveAndFlush(diemDoAsset);
            var diemDoZag = mapper.map(diemDoDto.getDiemDo(), ZAG_DIEMDO.class);
            var diemDoZagDto = diemDoDto.getDiemDo();
            var diemDoZagId = diemDoDto.getDiemDo().getAttrDataId();
            if (diemDoZagId == null) {
                diemDoZagId = UUID.randomUUID().toString();
                diemDoZag.setOBJTYPEID("A");
                diemDoZag.setOBJID(assetId);
            }
            diemDoZag.setOBJTYPEID("A");
            diemDoZag.setDTAC_XNK(diemDoZagDto.getdTacXNK());
            diemDoZag.setXNK(diemDoZagDto.getXnk());
            diemDoZag.setDVI_GNHAN1(diemDoZagDto.getdViGNhan1());
            diemDoZag.setDVI_GNHAN2(diemDoZagDto.getdViGNhan2());
            diemDoZag.setNTHU_TAI(diemDoZagDto.getnThuTai());
            diemDoZag.setNTHU_TINH(diemDoZagDto.getnThuTinh());
            diemDoZag.setNTHU_TTSL(diemDoZagDto.getnThuTTSL());
            diemDoZag.setTINH_CHAT1(diemDoZagDto.getTinhChat1());
            diemDoZag.setTINH_CHAT2(diemDoZagDto.getTinhChat2());
            diemDoZag.setATTRDATAID(diemDoZagId);
            //create or update zag
            diemdoRepository.save(diemDoZag);
            //save S_ATTRIBUTE
            var saEntity = attrRepository.findByObjId(assetId).stream().findFirst().orElse(null);
            if (saEntity == null) {
                S_ATTRIBUTE_GROUP_ASSOB sa = new S_ATTRIBUTE_GROUP_ASSOB();
                sa.setOBJID(assetId);
                sa.setATTRDATAID(diemDoZagId);
                sa.setOBJTYPEID("A");
                sa.setATTRGROUPORD(1);
                sa.setATTRGROUPID(A_ASSET.CategoryId.DIEMDO.toString());
                attrRepository.save(sa);
            }
            res.setData(assetEntity);
            res.setState(ResponseData.STATE.OK.toString());
            res.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
        } catch (Exception ex) {
            res.setState(ResponseData.STATE.FAIL.toString());
            res.setData(ResponseData.MESSAGE.ERROR.toString());
            throw new Exception("An error to create or update diemdo");
        }
        return res;
    }

    public ResponseData createOrUpdateCongTo(DeviceCrudDto congToDto, CustomUserDetails userDetails) throws Exception {
        ResponseData res = new ResponseData();
        try {
            var currentUser = userDetails.getUserid();
            var createOrUpdateTime = new Date();
            var congToAsset = mapper.map(congToDto, A_ASSET.class);
            var assetId = congToAsset.getASSETID();
            if (assetId == null) {
                var checkExistsSerialNum = assetService.findBySerialNum(congToAsset.getSERIALNUM(), A_ASSET.CategoryId.CONGTO.toString());
                if (checkExistsSerialNum != null) {
                    res.setState(ResponseData.STATE.FAIL.toString());
                    res.setState(ResponseData.MESSAGE.ERROR.toString());
                    return res;
                } else {
                    assetId = UUID.randomUUID().toString();
                    congToAsset.setUSER_CR_ID(currentUser);
                    congToAsset.setUSER_CR_DTIME(createOrUpdateTime);
                    congToAsset.setISSAVE(false);
                }
            } else {
                congToAsset.setUSER_MDF_ID(currentUser);
                congToAsset.setUSER_MDF_DTIME(createOrUpdateTime);
                congToAsset.setISSAVE(true);
            }
            congToAsset.setCATEGORYID(A_ASSET.CategoryId.CONGTO.toString());
            congToAsset.setTYPEID("CTO");
            congToAsset.setUSESTATUS_LAST_DTIME(new Date());
            congToAsset.setASSETID(assetId);
            congToAsset.setISDELETE(false);
            //create or update asset
            var assetEntity = assetRepository.saveAndFlush(congToAsset);
            var congTo = congToDto.getCongTo();
            var zag = mapper.map(congTo, ZAG_CongTo.class);

            var zagId = congTo.getAttrDataId();
            if (zagId == null) {
                zagId = UUID.randomUUID().toString();
                zag.setObjTypeId("A");
                zag.setObjId(assetId);
            }
            zag.setAttrDataId(zagId);
            //create or update zag
            zagCongToRepository.save(zag);
            //save S_ATTRIBUTE
            S_ATTRIBUTE_GROUP_ASSOB sa = new S_ATTRIBUTE_GROUP_ASSOB();
            var saLink = attrRepository.findByObjId(assetId);
            if (saLink.size() < 1) {
                sa.setOBJID(assetId);
                sa.setATTRDATAID(zagId);
                sa.setOBJTYPEID("A");
                sa.setATTRGROUPORD(1);
                sa.setATTRGROUPID(A_ASSET.CategoryId.CONGTO.toString());
                attrRepository.save(sa);
            }
            res.setData(assetEntity);
            res.setState(ResponseData.STATE.OK.toString());
            res.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
        } catch (Exception exception) {
            res.setState(ResponseData.STATE.FAIL.toString());
            res.setData(ResponseData.MESSAGE.ERROR.toString());
            throw new Exception("An error to create or update cong to");
        }
        return res;
    }

    @Override
    @Transactional
    public ResponseData createOrUpdateTU(SetTUCreateDto setTuDto, CustomUserDetails userDetails, String diemDoId) throws Exception {
        ResponseData res = new ResponseData();
        var currentUser = userDetails.getUserid();
        var createOrUpdateDate = new Date();
        try {
            //save set tu
            var assetSetTu = mapper.map(setTuDto, A_ASSET.class);
            var setTuId = assetSetTu.getASSETID();
            if (setTuId == null) {
                setTuId = UUID.randomUUID().toString();
                assetSetTu.setUSER_CR_ID(currentUser);
                assetSetTu.setUSER_CR_DTIME(createOrUpdateDate);
                assetSetTu.setISSAVE(false);

            } else {
                assetSetTu.setUSER_MDF_ID(currentUser);
                assetSetTu.setUSER_MDF_DTIME(createOrUpdateDate);
                assetSetTu.setISSAVE(true);
            }
            assetSetTu.setASSETID(setTuId);
            assetSetTu.setCATEGORYID(A_ASSET.CategoryId.BTU.toString());
            assetSetTu.setTYPEID(A_ASSET.CategoryId.BTU.toString());
            assetSetTu.setISDELETE(false);
            var setType = setTuDto.getTuList().size() > 1 ? 1 : 3;
            assetSetTu.setSetType(setType);
            var setTUEntity = assetRepository.save(assetSetTu);
            for (TUCreateDto tuDto : setTuDto.getTuList()) {
                var tuId = tuDto.getAssetId();
                if (tuId == null) {
                    tuId = UUID.randomUUID().toString();
                    tuDto.setUserCRId(currentUser);
                    tuDto.setUserCRDTime(createOrUpdateDate);
                    tuDto.setIsSave(false);
                } else {
                    tuDto.setUserMDFId(currentUser);
                    tuDto.setUserMDFDTime(createOrUpdateDate);
                    tuDto.setIsSave(true);
                }
                tuDto.setAssetId(tuId);
                tuDto.setCategoryId(A_ASSET.CategoryId.TU.toString());
                tuDto.setTypeId(A_ASSET.CategoryId.TU.toString());
                tuDto.setAssetIdParent(setTuId);
                tuDto.setOrgId(setTUEntity.getORGID());
                tuDto.setAssetDesc(setTUEntity.getASSETDESC());
                tuDto.setTransId(setTUEntity.getTransId());
                var assetTu = mapper.map(tuDto, A_ASSET.class);
                assetTu.setASSETROOT(setTUEntity.getASSETID_PARENT());
                assetTu.setISDELETE(false);
                assetTu.setSetType(setType);
                var assetTUEntity = assetRepository.saveAndFlush(assetTu);
                //save zag
                var zagEntity = mapper.map(tuDto.getZagtu(), ZAG_TU.class);
                var attrId = zagEntity.getAttrDataId();
                if (attrId == null) {
                    attrId = UUID.randomUUID().toString();
                }
                zagEntity.setAttrDataId(attrId);
                zagEntity.setObjId(tuId);
                zagEntity.setObjTypeId("A");
                zagTURepository.saveAndFlush(zagEntity);
                //find and save sa
                S_ATTRIBUTE_GROUP_ASSOB sa = new S_ATTRIBUTE_GROUP_ASSOB();
                var saList = attrRepository.findByObjId(tuId);
                if (saList.size() < 1) {
                    sa.setATTRDATAID(attrId);
                    sa.setOBJID(tuId);
                    sa.setOBJTYPEID("A");
                    sa.setATTRGROUPID(A_ASSET.CategoryId.TU.toString());
                    sa.setATTRGROUPORD(1);
                    attrRepository.saveAndFlush(sa);
                }
                //save cuon
                for (CuonCreateDto cuonDto : tuDto.getListCuon()) {
                    //save asset
                    var cuonId = cuonDto.getAssetId();
                    var cuonAsset = mapper.map(cuonDto, A_ASSET.class);
                    if (cuonId == null) {
                        cuonId = UUID.randomUUID().toString();
                        cuonAsset.setUSER_CR_ID(currentUser);
                        cuonAsset.setUSER_CR_DTIME(createOrUpdateDate);
                        cuonAsset.setISSAVE(false);

                    } else {
                        cuonAsset.setUSER_MDF_ID(currentUser);
                        cuonAsset.setUSER_MDF_DTIME(createOrUpdateDate);
                        cuonAsset.setISSAVE(true);

                    }
                    cuonAsset.setASSETID(cuonId);
                    cuonAsset.setCATEGORYID(A_ASSET.CategoryId.CUON.toString());
                    cuonAsset.setTYPEID(A_ASSET.CategoryId.CUON.toString());
                    cuonAsset.setASSETID_PARENT(tuId);
                    cuonAsset.setUSER_CR_DTIME(createOrUpdateDate);
                    cuonAsset.setUSER_CR_ID(currentUser);
                    cuonAsset.setORGID(setTUEntity.getORGID());
                    cuonAsset.setTransId(setTUEntity.getTransId());
                    cuonAsset.setISDELETE(false);
                    var cuonEntity = assetRepository.saveAndFlush(cuonAsset);
                    //save zag
                    var cuonZag = mapper.map(cuonDto.getCuon(), ZAG_CUON.class);
                    var zagCuonId = cuonZag.getAttrDataId();
                    if (zagCuonId == null) {
                        zagCuonId = UUID.randomUUID().toString();
                    }
                    cuonZag.setObjId(cuonId);
                    var machNThu = cuonDto.getCuon().getMachNThu();
                    if (machNThu != null && machNThu.size() > 0) {
                        cuonZag.setMachNThu(String.join(",", machNThu));
                    }
                    cuonZag.setObjTypeId("A");
                    cuonZag.setAttrDataId(zagCuonId);
                    zagCuonRepository.saveAndFlush(cuonZag);
                    //save sa
                    var saListC = attrRepository.findByObjId(cuonId);
                    if (saListC.size() < 1) {
                        sa.setATTRDATAID(zagCuonId);
                        sa.setATTRGROUPID(A_ASSET.CategoryId.CUON.toString());
                        sa.setATTRGROUPORD(1);
                        sa.setOBJTYPEID("A");
                        sa.setOBJID(cuonId);
                        attrRepository.save(sa);
                    }
                    if (!diemDoId.equals("0")) {
                        //gan voi diem do
                        var assetLink = new AssetLinkId(diemDoId, cuonId);
                        var existsById = assetlinkRepository.existsById(assetLink);
                        if (!existsById) {
                            A_ASSET_LINK al = new A_ASSET_LINK();
                            al.setAssetId(cuonId);
                            al.setAssetIdLink(diemDoId);
                            assetlinkRepository.save(al);
                        }
                    }
                }
            }
            //delete cuon
            var listCuonDelete = setTuDto.getListCuonDelete();
            if (listCuonDelete != null && listCuonDelete.size() > 0) {
                for (String cuonId : setTuDto.getListCuonDelete()) {
                    AssetLinkId idSet = new AssetLinkId(cuonId, diemDoId);
                    var id = assetlinkRepository.findById(idSet).orElse(null);
                    if (id != null) {
                        assetlinkRepository.deleteById(idSet);
                    }
                    var saList = attrRepository.findByObjId(cuonId);
                    if (saList != null && saList.size() > 0) {
                        var sa = saList.get(0);
                        zagCuonRepository.deleteById(sa.getATTRDATAID());
                        attrRepository.delete(sa);
                        assetRepository.deleteById(cuonId);
                    }
                }
            }
            res.setData(setTUEntity);
            res.setState(ResponseData.STATE.OK.toString());
            res.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
        } catch (Exception ex) {
            res.setState(ResponseData.STATE.FAIL.toString());
            res.setMessage(ResponseData.MESSAGE.ERROR.toString());
            throw new Exception(ex);
        }
        return res;
    }

    @Override
    @Transactional
    public ResponseData updateTU(SetTUCreateDto setTuDto, CustomUserDetails userDetails, String diemDoId) throws Exception {
        ResponseData res = new ResponseData();
        var currentUser = userDetails.getUserid();
        String sql1;
        sql1 = "select  1 from Q_PQOBJ_USER where ASSETID =? and USERID=? and ISQL=?";
        List lst = jdbcTemplate.queryForList(sql1, setTuDto.getAssetIdParent(), currentUser, true);
        if (lst == null || lst.isEmpty()) {
            res.setMessage("Không có quyền cập nhật TU");
            res.setState(ResponseData.STATE.FAIL.toString());
            return res;
        }

        String sql2;
        sql2 = "select 1 from A_ASSET WHERE  ASSETDESC = ? and ASSETID_PARENT = ? and ASSETID !=?";
        List lst2 = jdbcTemplate.queryForList(sql2, setTuDto.getAssetDesc(), setTuDto.getAssetIdParent(), setTuDto.getAssetId());
        if (lst2 != null && !lst2.isEmpty())
            return new ResponseData(ResponseData.STATE.FAIL.toString(), "Cùng 1 NMĐ/ TBA/ RGL, tên bộ TU không được trùng");

        String sql3;

        sql3 = "select 1 from A_ASSET WHERE SERIALNUM = ? and P_MANUFACTURERID = ? and ASSETID !=?";
        for (TUCreateDto tuDto : setTuDto.getTuList()) {
            List lst3 = jdbcTemplate.queryForList(sql3, tuDto.getSerialNum(), tuDto.getpManufacturerId(), tuDto.getAssetId());

            if (lst3 != null && !lst3.isEmpty())
                return new ResponseData(ResponseData.STATE.FAIL.toString(), "Số chế tạo TU không được trùng");
        }

        var setTu = viewSetTURepository.findById(setTuDto.getAssetId()).orElse(null);
        assert setTu != null;
        var listSort = setTu.getTuList().stream().sorted(Comparator.comparing(ViewTUPre::getAssetORD)).collect(Collectors.toList());
        for(var i =0;i<setTu.getTuList().size();i++){
            var listSort1 = setTu.getTuList().get(i).getListCuon().stream().sorted(Comparator.comparing(ViewCuon::getAssetDesc)).collect(Collectors.toList());
            setTu.getTuList().get(i).setListCuon(listSort1);
        }
        setTu.setTuList(listSort);

        var createOrUpdateDate = new Date();
        try {
            //save set tu
            var assetSetTu = mapper.map(setTuDto, A_ASSET.class);
            var setTuId = assetSetTu.getASSETID();

            assetSetTu.setUSER_MDF_DTIME(createOrUpdateDate);
            assetSetTu.setUSER_MDF_ID(currentUser);
            assetSetTu.setISDELETE(false);
            assetSetTu.setCATEGORYID(A_ASSET.CategoryId.BTU.toString());
            assetSetTu.setTYPEID(A_ASSET.CategoryId.BTU.toString());
            var setType = setTuDto.getTuList().size() > 1 ? 1 : 3;
            assetSetTu.setSetType(setType);
            assetRepository.save(assetSetTu);

            for (TUCreateDto tuDto : setTuDto.getTuList()) {
                var tuId = tuDto.getAssetId();
                if (tuId == null) {
                    tuId = UUID.randomUUID().toString();
                    tuDto.setUserCRId(currentUser);
                    tuDto.setUserCRDTime(createOrUpdateDate);
                } else {
                    tuDto.setUserMDFId(currentUser);
                    tuDto.setUserMDFDTime(createOrUpdateDate);
                }
                tuDto.setAssetId(tuId);
                tuDto.setCategoryId(A_ASSET.CategoryId.TU.toString());
                tuDto.setTypeId(A_ASSET.CategoryId.TU.toString());
                tuDto.setAssetIdParent(setTuId);
                tuDto.setOrgId(assetSetTu.getORGID());
                tuDto.setAssetDesc(assetSetTu.getASSETDESC());
                tuDto.setTransId(assetSetTu.getTransId());
                tuDto.setIsSave(assetSetTu.getISSAVE());
                tuDto.setAssetRoot(setTuDto.getAssetIdParent());
                var assetTu = mapper.map(tuDto, A_ASSET.class);
                assetTu.setISDELETE(false);
                assetTu.setSetType(setType);
                var assetTUEntity = assetRepository.saveAndFlush(assetTu);
                //save zag
                var zagEntity = mapper.map(tuDto.getZagtu(), ZAG_TU.class);
                var attrId = zagEntity.getAttrDataId();
                if (attrId == null) {
                    attrId = UUID.randomUUID().toString();
                }
                zagEntity.setAttrDataId(attrId);
                zagEntity.setObjId(tuId);
                zagEntity.setObjTypeId("A");
                zagTURepository.saveAndFlush(zagEntity);
                //find and save sa
                S_ATTRIBUTE_GROUP_ASSOB sa = new S_ATTRIBUTE_GROUP_ASSOB();
                var saList = attrRepository.findByObjId(tuId);
                if (saList.size() < 1) {
                    sa.setATTRDATAID(attrId);
                    sa.setOBJID(tuId);
                    sa.setOBJTYPEID("A");
                    sa.setATTRGROUPID(A_ASSET.CategoryId.TU.toString());
                    sa.setATTRGROUPORD(1);
                    attrRepository.saveAndFlush(sa);
                }
                //save cuon
                for (CuonCreateDto cuonDto : tuDto.getListCuon()) {
                    //save asset
                    var cuonId = cuonDto.getAssetId();
                    var cuonAsset = mapper.map(cuonDto, A_ASSET.class);
                    if (cuonId == null) {
                        cuonId = UUID.randomUUID().toString();
                        cuonAsset.setUSER_CR_ID(currentUser);
                        cuonAsset.setUSER_CR_DTIME(createOrUpdateDate);
                    } else {
                        cuonAsset.setUSER_MDF_ID(currentUser);
                        cuonAsset.setUSER_MDF_DTIME(createOrUpdateDate);
                    }
                    cuonAsset.setASSETID(cuonId);
                    cuonAsset.setCATEGORYID(A_ASSET.CategoryId.CUON.toString());
                    cuonAsset.setTYPEID(A_ASSET.CategoryId.CUON.toString());
                    cuonAsset.setASSETID_PARENT(tuId);
                    cuonAsset.setUSER_CR_DTIME(createOrUpdateDate);

                    cuonAsset.setUSER_CR_ID(currentUser);
                    cuonAsset.setORGID(assetSetTu.getORGID());
                    cuonAsset.setTransId(assetSetTu.getTransId());
                    cuonAsset.setISSAVE(assetSetTu.getISSAVE());
                    cuonAsset.setISDELETE(false);
                    var cuonEntity = assetRepository.saveAndFlush(cuonAsset);
                    //save zag
                    var cuonZag = mapper.map(cuonDto.getCuon(), ZAG_CUON.class);
                    var zagCuonId = cuonZag.getAttrDataId();
                    if (zagCuonId == null) {
                        zagCuonId = UUID.randomUUID().toString();
                    }
                    var machNThu = cuonDto.getCuon().getMachNThu();
                    if (machNThu != null && machNThu.size() > 0) {
                        cuonZag.setMachNThu(String.join(",", machNThu));
                    }
                    cuonZag.setObjId(cuonId);
                    cuonZag.setObjTypeId("A");
                    cuonZag.setAttrDataId(zagCuonId);
                    zagCuonRepository.saveAndFlush(cuonZag);
                    //save sa
                    var saListC = attrRepository.findByObjId(cuonId);
                    if (saListC.size() < 1) {
                        sa.setATTRDATAID(zagCuonId);
                        sa.setATTRGROUPID(A_ASSET.CategoryId.CUON.toString());
                        sa.setATTRGROUPORD(1);
                        sa.setOBJTYPEID("A");
                        sa.setOBJID(cuonId);
                        attrRepository.save(sa);
                    }
                    var listDiemDo = diemDoId.split(",");

                    if (listDiemDo.length > 0) {
                        //gan voi diem do
                        for (String idDiemDo : listDiemDo) {
                            var assetLink = new AssetLinkId(idDiemDo, cuonId);
                            var existsById = assetlinkRepository.existsById(assetLink);
                            if (!existsById) {
                                A_ASSET_LINK al = new A_ASSET_LINK();
                                al.setAssetId(cuonId);
                                al.setAssetIdLink(idDiemDo);
                                assetlinkRepository.save(al);
                            }
                        }
                    }
                }
            }
            if (setTuDto.getListCuonDelete().size() > 0) {
                for (String cuonId : setTuDto.getListCuonDelete()) {
                    AssetLinkId idSet = new AssetLinkId(cuonId, diemDoId);
                    var id = assetlinkRepository.findById(idSet).orElse(null);
                    var idCuon = assetRepository.findById(String.valueOf(idSet)).orElse(null);
                    if (id != null) {
                        assetlinkRepository.deleteById(idSet);
                        //assetRepository.deleteById(cuonId);
                    }
                }
            }
            //lưu his


            List<String> ignoredFields = new ArrayList<>() {{
                add("assetidparent");
                add("categoryid");
                add("typeid");
                add("usermdfid");
                add("usermdfdtime");
                add("attrdataid");
                add("objtypeid");
                add("objid");
                add("assetroot");
                add("settype");
                add("pmanufacturerid");
                add("pinstalldate");
                add("pduyetmau");
                add("isdelete");
                add("orgid");
                add("listcuon");
                add("usercrid");
                add("usercrdtime"); add("ccx");
                add("machnthu"); add("dungluong");
                add("diemdoname"); add("cuondnoi");
                add("tinhchat"); add("tsb");
                add("nature"); add("inspectionstamps");
                add("statusmax"); add("kieutu");
                add("diemdoassets"); add("nationalfact");



            }};
            Map<String, String> variableNameMapping = new HashMap<>();
            variableNameMapping.put("tempstr", "Tem KĐ");
            variableNameMapping.put("pduyetmaustr", "Phê duyệt mẫu");
            variableNameMapping.put("kieutustr", "Kiểu");
            variableNameMapping.put("cycleaccreditation", "Chu kỳ KĐ");
            variableNameMapping.put("nhasx", "Nhà sản xuất");
            variableNameMapping.put("nuocsx", "Nước sản xuất");
            variableNameMapping.put("serialnum", "Số chế tạo");
            variableNameMapping.put("assetdesc", "Tên bộ thiết bị");
            variableNameMapping.put("dvsh", "Đơn vị sở hữu");
            for (var i = 0; i < setTuDto.getTuList1().size(); i++) {
                for (var j = 0; j < setTuDto.getTuList1().get(i).getListCuon().size(); j++) {
                    variableNameMapping.put("tsbstr", "Tỷ số biến");
                    variableNameMapping.put("cuondaunoistr", "Cuộn đầu nối");
                    variableNameMapping.put("ccxstr", "Cấp chính xác");
                    variableNameMapping.put("dlstr", "Dung lượng");
                    variableNameMapping.put("mntstr", "Mạch nhị thứ");
                    variableNameMapping.put("assetnote", "Mô tả");
                    _wonderHisService.UpdateHisTUTI(setTuDto.getTuList1().get(i).getListCuon().get(j).getAssetId(),
                            setTuDto.getTuList1().get(i), setTu.getTuList().get(i),
                            setTuDto.getTuList1().get(i).getListCuon().get(j), setTu.getTuList().get(i).getListCuon().get(j),
                            ignoredFields, variableNameMapping, userDetails.getUserid(), "DEVICE_TU", "UPDATE");
                }
            }
            res.setData(assetSetTu);
            res.setState(ResponseData.STATE.OK.toString());
            res.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
        } catch (Exception ex) {
            res.setState(ResponseData.STATE.FAIL.toString());
            res.setMessage(ResponseData.MESSAGE.ERROR.toString());
            throw new Exception(ex);
        }
        return res;
    }

    @Override
    @Transactional
    public ResponseData updateTI(SetTICreateDto setTIDto, CustomUserDetails userDetails, String diemDoId) throws Exception {
        ResponseData res = new ResponseData();
        var currentUser = userDetails.getUserid();
        String sql1;
        sql1 = "select  1 from Q_PQOBJ_USER where ASSETID =? and USERID=? and ISQL=?";
        List lst = jdbcTemplate.queryForList(sql1, setTIDto.getAssetIdParent(), currentUser, true);
        if (lst == null || lst.isEmpty()) {
            res.setMessage("Không có quyền cập nhật TI");
            res.setState(ResponseData.STATE.FAIL.toString());
            return res;
        }
        String sql2;
        sql2 = "select 1 from A_ASSET WHERE  ASSETDESC = ? and ASSETID_PARENT = ? and ASSETID !=?";
        List lst2 = jdbcTemplate.queryForList(sql2, setTIDto.getAssetDesc(), setTIDto.getAssetIdParent(), setTIDto.getAssetId());
        if (lst2 != null && !lst2.isEmpty())
            return new ResponseData(ResponseData.STATE.FAIL.toString(), "Cùng 1 NMĐ/ TBA/ RGL, tên bộ TI không được trùng");

        String sql3;

        sql3 = "select 1 from A_ASSET WHERE SERIALNUM = ? and P_MANUFACTURERID = ? and ASSETID !=?";
        for (TICreateDto tiDto : setTIDto.getTiList()) {
            List lst3 = jdbcTemplate.queryForList(sql3, tiDto.getSerialNum(), tiDto.getpManufacturerId(), tiDto.getAssetId());

            if (lst3 != null && !lst3.isEmpty())
                return new ResponseData(ResponseData.STATE.FAIL.toString(), "Số chế tạo TI không được trùng");
        }
        var setTu = viewSetTIRepository.findById(setTIDto.getAssetId()).orElse(null);
        assert setTu != null;
        var listSort = setTu.getTiList().stream().sorted(Comparator.comparing(ViewTI::getAssetORD)).collect(Collectors.toList());
        for(var i =0;i<setTu.getTiList().size();i++){
            var listSort1 = setTu.getTiList().get(i).getListCuon().stream().sorted(Comparator.comparing(ViewCuonTI::getAssetDesc)).collect(Collectors.toList());
            setTu.getTiList().get(i).setListCuon(listSort1);
        }
        setTu.setTiList(listSort);

        var createOrUpdateDate = new Date();
        try {
            //save set tu
            var assetSetTI = mapper.map(setTIDto, A_ASSET.class);
            var setTiId = assetSetTI.getASSETID();

            assetSetTI.setUSER_MDF_DTIME(createOrUpdateDate);
            assetSetTI.setUSER_MDF_ID(currentUser);
            assetSetTI.setISDELETE(false);
            assetSetTI.setCATEGORYID(A_ASSET.CategoryId.BTI.toString());
            assetSetTI.setTYPEID(A_ASSET.CategoryId.BTI.toString());
            var setType = setTIDto.getTiList().size() > 1 ? 1 : 3;
            assetSetTI.setSetType(setType);
            assetRepository.save(assetSetTI);

            for (TICreateDto tiDto : setTIDto.getTiList()) {
                var tiId = tiDto.getAssetId();
                if (tiId == null) {
                    tiId = UUID.randomUUID().toString();
                    tiDto.setUserCRDTime(createOrUpdateDate);
                    tiDto.setUserCRId(currentUser);
                } else {
                    tiDto.setUserMDFDTime(createOrUpdateDate);
                    tiDto.setUserMDFId(currentUser);
                }
                tiDto.setAssetId(tiId);
                tiDto.setCategoryId(A_ASSET.CategoryId.TI.toString());
                tiDto.setTypeId(A_ASSET.CategoryId.TI.toString());
                tiDto.setAssetIdParent(setTiId);
                tiDto.setAssetDesc(assetSetTI.getASSETDESC());
                tiDto.setOrgId(assetSetTI.getORGID());
                tiDto.setTransId(setTIDto.getTransId());
                tiDto.setIsSave(setTIDto.getIsSave());
                tiDto.setAssetRoot(setTIDto.getAssetIdParent());
                var assetTu = mapper.map(tiDto, A_ASSET.class);
                assetTu.setSetType(setType);
                assetTu.setISDELETE(false);
                //save asset tu
                var assetTUEntity = assetRepository.save(assetTu);
                //save zag
                var zagEntity = mapper.map(tiDto.getZagti(), ZAG_TI.class);
                var attrId = zagEntity.getAttrDataId();
                if (attrId == null) {
                    attrId = UUID.randomUUID().toString();
                }
                zagEntity.setAttrDataId(attrId);
                zagEntity.setObjId(tiId);
                zagEntity.setObjTypeId("A");
                tiRepository.save(zagEntity);
                //save sa
                S_ATTRIBUTE_GROUP_ASSOB sa = new S_ATTRIBUTE_GROUP_ASSOB();
                var saList = attrRepository.findByObjId(assetTUEntity.getASSETID());
                if (saList.size() < 1) {
                    sa.setATTRDATAID(attrId);
                    sa.setOBJID(tiId);
                    sa.setOBJTYPEID("A");
                    sa.setATTRGROUPID(A_ASSET.CategoryId.TI.toString());
                    sa.setATTRGROUPORD(1);
                    attrRepository.save(sa);
                }
                //save list cuon
                for (CuonCreateDto cuonDto : tiDto.getListCuon()) {
                    //save asset
                    var cuonAssetId = cuonDto.getAssetId();
                    if (cuonAssetId == null) {
                        cuonAssetId = UUID.randomUUID().toString();
                        cuonDto.setUserCRDTime(createOrUpdateDate);
                        cuonDto.setUserCRId(currentUser);
                    } else {
                        cuonDto.setUserMDFDTime(createOrUpdateDate);
                        cuonDto.setUserMDFId(currentUser);
                    }
                    var cuonAsset = mapper.map(cuonDto, A_ASSET.class);
                    cuonAsset.setASSETID(cuonAssetId);
                    cuonAsset.setCATEGORYID(A_ASSET.CategoryId.CUON.toString());
                    cuonAsset.setTYPEID(A_ASSET.CategoryId.CUON.toString());
                    cuonAsset.setASSETID_PARENT(tiId);
                    cuonAsset.setORGID(assetSetTI.getORGID());
                    cuonAsset.setISDELETE(false);
                    cuonAsset.setTransId(setTIDto.getTransId());
                    cuonAsset.setISSAVE(setTIDto.getIsSave());
                    var cuonEntity = assetRepository.save(cuonAsset);
                    cuonEntity.setISDELETE(false);
                    //save zag
                    var cuonZag = mapper.map(cuonDto.getCuon(), ZAG_CUON.class);
                    var zagId = cuonZag.getAttrDataId();
                    if (zagId == null) {
                        zagId = UUID.randomUUID().toString();
                    }
                    var machNThu = cuonDto.getCuon().getMachNThu();
                    if (machNThu != null && machNThu.size() > 0) {
                        cuonZag.setMachNThu(String.join(",", machNThu));
                    }
                    cuonZag.setObjId(cuonAssetId);
                    cuonZag.setObjTypeId("A");
                    cuonZag.setAttrDataId(zagId);
                    zagCuonRepository.save(cuonZag);
                    //save sa
                    var saListC = attrRepository.findByObjId(cuonAssetId);
                    if (saListC.size() < 1) {
                        sa.setATTRDATAID(zagId);
                        sa.setATTRGROUPID(A_ASSET.CategoryId.CUON.toString());
                        sa.setATTRGROUPORD(1);
                        sa.setOBJTYPEID("A");
                        sa.setOBJID(cuonAssetId);
                        attrRepository.save(sa);
                    }
                    var listDiemDo = diemDoId.split(",");
                    if (listDiemDo.length > 0) {
                        //gan voi diem do
                        for (String idDiemDo : listDiemDo) {
                            var assetLink = new AssetLinkId(idDiemDo, cuonAssetId);
                            var existsById = assetlinkRepository.existsById(assetLink);
                            if (!existsById) {
                                A_ASSET_LINK al = new A_ASSET_LINK();
                                al.setAssetId(cuonAssetId);
                                al.setAssetIdLink(idDiemDo);
                                assetlinkRepository.save(al);
                            }
                        }
                    }
                }
            }
            //delete cuon
//            if (setTIDto.getListCuonDelete().size() > 0) {
//                for (String cuonId : setTIDto.getListCuonDelete()) {
//                    AssetLinkId idSet = new AssetLinkId(cuonId, diemDoId);
//                    var id = assetlinkRepository.findById(idSet).orElse(null);
//                    if (id != null) {
//                        assetlinkRepository.deleteById(idSet);
//                    }
//                }
//            }


            List<String> ignoredFields = new ArrayList<>() {{
                add("assetidparent");
                add("categoryid");
                add("typeid");
                add("usermdfid");
                add("usermdfdtime");
                add("attrdataid");
                add("objtypeid");
                add("objid");
                add("assetroot");
                add("settype");
                add("pmanufacturerid");
                add("pinstalldate");
                add("pduyetmau");
                add("isdelete");
                add("orgid");
                add("listcuon");
                add("usercrid");
                add("usercrdtime"); add("ccx");
                add("machnthu"); add("dungluong");
                add("diemdoname"); add("cuondnoi");
                add("tinhchat"); add("tsb");
                add("nature"); add("inspectionstamps");
                add("statusmax"); add("kieuti");
                add("diemdoassets"); add("nationalfact");
                add("usestatuslastdtime"); add("dateoperation");
                add("usestatuslastdtime"); add("powertypeid");
                add("powerid"); add("powertypeid");
                add("powerid"); add("powertypeid");





            }};
            Map<String, String> variableNameMapping = new HashMap<>();
            variableNameMapping.put("tempstr", "Tem KĐ");
            variableNameMapping.put("pduyetmaustr", "Phê duyệt mẫu");
            variableNameMapping.put("kieutistr", "Kiểu");
            variableNameMapping.put("cycleaccreditation", "Chu kỳ KĐ");
            variableNameMapping.put("nhasx", "Nhà sản xuất");
            variableNameMapping.put("nuocsx", "Nước sản xuất");
            variableNameMapping.put("serialnum", "Số chế tạo");
            variableNameMapping.put("assetdesc", "Tên bộ thiết bị");
            variableNameMapping.put("dvsh", "Đơn vị sở hữu");
            for (var i = 0; i < setTIDto.getTiList1().size(); i++) {
                for (var j = 0; j < setTIDto.getTiList1().get(i).getListCuon().size(); j++) {
                    variableNameMapping.put("tsbstr", "Tỷ số biến");
                    variableNameMapping.put("cuondaunoistr", "Cuộn đầu nối");
                    variableNameMapping.put("ccxstr", "Cấp chính xác");
                    variableNameMapping.put("dlstr", "Dung lượng");
                    variableNameMapping.put("mntstr", "Mạch nhị thứ");
                    variableNameMapping.put("assetnote", "Mô tả");
                    _wonderHisService.UpdateHisTUTI(setTIDto.getTiList1().get(i).getListCuon().get(j).getAssetId(),
                            setTIDto.getTiList1().get(i), setTu.getTiList().get(i),
                            setTIDto.getTiList1().get(i).getListCuon().get(j), setTu.getTiList().get(i).getListCuon().get(j),
                            ignoredFields, variableNameMapping, userDetails.getUserid(), "DEVICE_TI", "UPDATE");
                }
            }
            res.setData(assetSetTI);
            res.setState(ResponseData.STATE.OK.toString());
            res.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
        } catch (Exception ex) {
            res.setState(ResponseData.STATE.FAIL.toString());
            res.setMessage(ResponseData.MESSAGE.ERROR.toString());
            throw new Exception(ex);
        }
        return res;
    }

    @Override
    @Transactional
    public ResponseData createOrUpdateTI(SetTICreateDto setTIDto, CustomUserDetails userDetails, String diemDoId) throws Exception {
        ResponseData res = new ResponseData();
        var currentUser = userDetails.getUserid();
        var createOrUpdateDate = new Date();
        try {
            //save set tu
            var assetSetTI = mapper.map(setTIDto, A_ASSET.class);
            var setTiId = assetSetTI.getASSETID();
            if (setTiId == null) {
                setTiId = UUID.randomUUID().toString();
                assetSetTI.setASSETID(setTiId);
                assetSetTI.setUSER_CR_DTIME(createOrUpdateDate);
                assetSetTI.setUSER_CR_ID(currentUser);
                assetSetTI.setISSAVE(false);
            } else {
                assetSetTI.setUSER_MDF_DTIME(createOrUpdateDate);
                assetSetTI.setUSER_MDF_ID(currentUser);
                assetSetTI.setISSAVE(true);
            }
            assetSetTI.setISDELETE(false);
            assetSetTI.setCATEGORYID(A_ASSET.CategoryId.BTI.toString());
            assetSetTI.setTYPEID(A_ASSET.CategoryId.BTI.toString());
            var setTIEntity = assetRepository.save(assetSetTI);
            for (TICreateDto tiDto : setTIDto.getTiList()) {
                var tiId = tiDto.getAssetId();
                if (tiId == null) {
                    tiId = UUID.randomUUID().toString();
                    tiDto.setUserCRDTime(createOrUpdateDate);
                    tiDto.setUserCRId(currentUser);
                    tiDto.setIsSave(false);

                } else {
                    tiDto.setUserMDFDTime(createOrUpdateDate);
                    tiDto.setUserMDFId(currentUser);
                    tiDto.setIsSave(true);

                }
                tiDto.setAssetId(tiId);
                tiDto.setCategoryId(A_ASSET.CategoryId.TI.toString());
                tiDto.setTypeId(A_ASSET.CategoryId.TI.toString());
                tiDto.setAssetIdParent(setTiId);
                tiDto.setAssetDesc(setTIEntity.getASSETDESC());
                tiDto.setOrgId(setTIEntity.getORGID());
                tiDto.setTransId(setTIEntity.getTransId());
                var assetTu = mapper.map(tiDto, A_ASSET.class);
                assetTu.setISDELETE(false);
                assetTu.setASSETROOT(setTIEntity.getASSETID_PARENT());
                //save asset tu
                var assetTUEntity = assetRepository.save(assetTu);
                //save zag
                var zagEntity = mapper.map(tiDto.getZagti(), ZAG_TI.class);
                var attrId = zagEntity.getAttrDataId();
                if (attrId == null) {
                    attrId = UUID.randomUUID().toString();
                }
                zagEntity.setAttrDataId(attrId);
                zagEntity.setObjId(tiId);
                zagEntity.setObjTypeId("A");
                tiRepository.save(zagEntity);
                //save sa
                S_ATTRIBUTE_GROUP_ASSOB sa = new S_ATTRIBUTE_GROUP_ASSOB();
                var saList = attrRepository.findByObjId(assetTUEntity.getASSETID());
                if (saList.size() < 1) {
                    sa.setATTRDATAID(attrId);
                    sa.setOBJID(tiId);
                    sa.setOBJTYPEID("A");
                    sa.setATTRGROUPID(A_ASSET.CategoryId.TI.toString());
                    sa.setATTRGROUPORD(1);
                    attrRepository.save(sa);
                }
                //save list cuon
                for (CuonCreateDto cuonDto : tiDto.getListCuon()) {
                    //save asset
                    var cuonAssetId = cuonDto.getAssetId();
                    if (cuonAssetId == null) {
                        cuonAssetId = UUID.randomUUID().toString();
                        cuonDto.setUserCRDTime(createOrUpdateDate);
                        cuonDto.setUserCRId(currentUser);
                        cuonDto.setIsSave(false);
                    } else {
                        cuonDto.setUserMDFDTime(createOrUpdateDate);
                        cuonDto.setUserMDFId(currentUser);
                        cuonDto.setIsSave(true);
                    }
                    var cuonAsset = mapper.map(cuonDto, A_ASSET.class);
                    cuonAsset.setASSETID(cuonAssetId);
                    cuonAsset.setCATEGORYID(A_ASSET.CategoryId.CUON.toString());
                    cuonAsset.setTYPEID(A_ASSET.CategoryId.CUON.toString());
                    cuonAsset.setASSETID_PARENT(tiId);
                    cuonAsset.setORGID(setTIEntity.getORGID());
                    cuonAsset.setISDELETE(false);
                    cuonAsset.setTransId(setTIEntity.getTransId());

                    var cuonEntity = assetRepository.save(cuonAsset);
                    cuonEntity.setISDELETE(false);
                    //save zag
                    var cuonZag = mapper.map(cuonDto.getCuon(), ZAG_CUON.class);
                    var zagId = cuonZag.getAttrDataId();
                    if (zagId == null) {
                        zagId = UUID.randomUUID().toString();
                    }
                    var machNThu = cuonDto.getCuon().getMachNThu();
                    if (machNThu != null && machNThu.size() > 0) {
                        cuonZag.setMachNThu(String.join(",", machNThu));
                    }
                    cuonZag.setObjId(cuonAssetId);
                    cuonZag.setObjTypeId("A");
                    cuonZag.setAttrDataId(zagId);
                    zagCuonRepository.save(cuonZag);
                    //save sa
                    var saListC = attrRepository.findByObjId(cuonAssetId);
                    if (saListC.size() < 1) {
                        sa.setATTRDATAID(zagId);
                        sa.setATTRGROUPID(A_ASSET.CategoryId.CUON.toString());
                        sa.setATTRGROUPORD(1);
                        sa.setOBJTYPEID("A");
                        sa.setOBJID(cuonAssetId);
                        attrRepository.save(sa);
                    }
                    if (!diemDoId.equals("0")) {
                        //gan voi diem do
                        var alinkId = new AssetLinkId(cuonAssetId, diemDoId);
                        var existsById = assetlinkRepository.existsById(alinkId);
                        if (!existsById) {
                            A_ASSET_LINK al = new A_ASSET_LINK();
                            al.setAssetIdLink(diemDoId);
                            al.setAssetId(cuonAssetId);
                            assetlinkRepository.save(al);
                        }
                    }
                }
            }
            //delete cuon
            var listCuonDelete = setTIDto.getListCuonDelete();
            if (listCuonDelete != null && listCuonDelete.size() > 0) {
                for (String cuonId : setTIDto.getListCuonDelete()) {
                    AssetLinkId idSet = new AssetLinkId(cuonId, diemDoId);
                    var id = assetlinkRepository.findById(idSet).orElse(null);
                    if (id != null) {
                        assetlinkRepository.deleteById(idSet);
                    }
                    var saList = attrRepository.findByObjId(cuonId);
                    if (saList != null && saList.size() > 0) {
                        var sa = saList.get(0);
                        zagCuonRepository.deleteById(sa.getATTRDATAID());
                        attrRepository.delete(sa);
                        assetRepository.deleteById(cuonId);
                    }
                }
            }
            res.setData(assetSetTI);
            res.setState(ResponseData.STATE.OK.toString());
            res.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
        } catch (Exception ex) {
            res.setState(ResponseData.STATE.FAIL.toString());
            res.setMessage(ResponseData.MESSAGE.ERROR.toString());
            throw new Exception(ex);
        }
        return res;
    }

    @Override
    public ResponseData updateStatusDiemDo(String id, String status) {
        ResponseData res = new ResponseData();
        try {
            var diemDoEntity = assetRepository.findById(id).orElse(null);
            var childs = assetRepository.findByParentId(id);
            if (diemDoEntity != null) {
                diemDoEntity.setUSESTATUS_LAST_ID(status);
                assetRepository.save(diemDoEntity);
                res.setState(ResponseData.STATE.OK.toString());
                res.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
                res.setData(diemDoEntity);
                if (childs.size() > 1) {
                    for (A_ASSET asset : childs) {
                        asset.setUSESTATUS_LAST_ID(status);
                        assetRepository.save(asset);
                    }
                }
            } else {
                res.setState(ResponseData.STATE.FAIL.toString());
                res.setMessage(ResponseData.MESSAGE.ERROR.toString());
            }
        } catch (Exception ex) {
            res.setState(ResponseData.STATE.FAIL.toString());
            res.setMessage(ResponseData.MESSAGE.ERROR.toString());
        }
        return res;
    }

    @Transactional
    @Override
    public ResponseData createDevices(DeviceDto dto, CustomUserDetails userDetails) throws Exception {
        try {
            ResponseData response = new ResponseData();
            var diemDoDto = dto.getDiemDo();
            var congToDto = dto.getCongTo();
            var setTUDtos = dto.getListSetTU();
            var setTIDtos = dto.getListSetTI();
            var res = createOrUpdateDiemDo(diemDoDto, userDetails);
            A_ASSET diemDoEntity = (A_ASSET) res.getData();
            congToDto.setAssetIdParent(diemDoEntity.getASSETID());
            congToDto.setAssetRoot(diemDoEntity.getASSETID_PARENT());
            createOrUpdateCongTo(congToDto, userDetails);
            for (SetTUCreateDto setTuDto : setTUDtos) {
                createOrUpdateTU(setTuDto, userDetails, diemDoEntity.getASSETID());
            }
            for (SetTICreateDto setTiDto : setTIDtos) {
                createOrUpdateTI(setTiDto, userDetails, diemDoEntity.getASSETID());
            }

            //View_HTDD listRs = htddRepository.findById(diemDoEntity.getASSETID()).orElse(new View_HTDD());
            response.setData(diemDoEntity.getASSETID());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setState(ResponseData.STATE.OK.toString());
            return response;
        } catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    @Override
    public ResponseData checkDevice(List<CheckDeviceDto> lstDevice) {
        ResponseData response = new ResponseData();
        try {
            for (CheckDeviceDto item : lstDevice) {
                String sql = "";
                List lst;
                //Lấy từ bảng chi tiết
                var assetId = item.getAssetId();
                if (assetId != null) {
                    if (item.getCategory().equals("CONGTO"))
                        sql = "SELECT ASSETID FROM [dbo].[A_ASSET] where CATEGORYID = '" + item.getCategory() + "' and SERIALNUM = '" + item.getSerialNum().trim() + "' and ASSETID != '" + assetId + "'";
                    else if (item.getCategory().equals("BTU") || item.getCategory().equals("BTI"))
                        sql = "SELECT ASSETID FROM [dbo].[A_ASSET] where CATEGORYID = '" + item.getCategory() + "' and ASSETDESC = '" + item.getSerialNum().trim() + "' and ASSETID_PARENT = '" + item.getPmanufacturerId() + "' and ASSETID != '" + assetId + "'";
//                    else if (item.getCategory().equals("TSB"))
//                        sql = "SELECT ASSETID FROM [dbo].[VIEWCUON] where CATEGORYID = '" + "CUON" + "' and TSB = '" + item.getSerialNum() + "' and TUID ='" + item.getAssetIdParent() + "'";
                    else if (item.getCategory().equals("Cuon")) {
                        sql = "SELECT ASSETID FROM [dbo].[VIEWCUON] where CATEGORYID = '" + "CUON" + "' and ASSETDESC = N'" + item.getSerialNum() + "' and TUID ='" + item.getAssetIdParent() + "' and ASSETID != '" + assetId + "'";
                        lst = jdbcTemplate.queryForList(sql);
                        if (lst.size() > 0) {
                            response.setData(item.getPmanufacturerId() + " " + item.getSerialNum() + " đã tồn tại trong hệ thống.");
                            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
                            response.setState(ResponseData.STATE.FAIL.toString());
                            return response;
                        }
                        sql = "";
                    } else if (item.getCategory().equals("BTUNAME")) {
                        var category = "BTU";
                        sql = "SELECT ASSETID FROM [dbo].[A_ASSET] where CATEGORYID = '" + category + "' and ASSETDESC = N'" + item.getAssetDesc() + "' and ASSETID ='" + item.getAssetIdParent() + "'";
                        lst = jdbcTemplate.queryForList(sql);
                        if (lst.size() < 1) {
                            response.setData("TU" + " " + item.getSerialNum() + " không trùng tên bộ thiết bị.");
                            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
                            response.setState(ResponseData.STATE.FAIL.toString());
                            return response;
                        }
                        sql = "SELECT ASSETID FROM [dbo].[A_ASSET] where CATEGORYID = '" + category + "' and ORGID = N'" + item.getOrgId() + "' and ASSETID ='" + item.getAssetIdParent() + "'";
                        lst = jdbcTemplate.queryForList(sql);
                        if (lst.size() < 1) {
                            response.setData("TU" + " " + item.getSerialNum() + " không trùng đơn vị sở hữu.");
                            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
                            response.setState(ResponseData.STATE.FAIL.toString());
                            return response;
                        }
                        sql = "";
                    } else if (item.getCategory().equals("BTINAME")) {
                        var category = "BTI";
                        sql = "SELECT ASSETID FROM [dbo].[A_ASSET] where CATEGORYID = '" + category + "' and ASSETDESC = N'" + item.getAssetDesc() + "' and ASSETID ='" + item.getAssetIdParent() + "'";
                        lst = jdbcTemplate.queryForList(sql);
                        if (lst.size() < 1) {
                            response.setData("TI" + " " + item.getSerialNum() + " không trùng tên bộ thiết bị.");
                            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
                            response.setState(ResponseData.STATE.FAIL.toString());
                            return response;
                        }
                        sql = "SELECT ASSETID FROM [dbo].[A_ASSET] where CATEGORYID = '" + category + "' and ORGID = N'" + item.getOrgId() + "' and ASSETID ='" + item.getAssetIdParent() + "'";
                        lst = jdbcTemplate.queryForList(sql);
                        if (lst.size() < 1) {
                            response.setData("TI" + " " + item.getSerialNum() + " không trùng đơn vị sở hữu.");
                            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
                            response.setState(ResponseData.STATE.FAIL.toString());
                            return response;
                        }
                        sql = "";
                    } else if (item.getCategory().equals("BTUSETTYPE")) {
                        String categoryId = "TU";
                        sql = "SELECT COUNT(ASSETID) FROM [dbo].[A_ASSET] where CATEGORYID = '" + categoryId + "' and ASSETID_PARENT ='" + item.getAssetIdParent() + "'";
                        Integer setType = jdbcTemplate.queryForObject(sql, Integer.class);
                        if (setType != null && setType > 1) {
                            setType = 1;
                        }
                        assert setType != null;
                        if (!setType.toString().equals(item.getPmanufacturerId())) {
                            response.setData("TU" + " " + item.getSerialNum() + " không trùng loại bộ thiết bị.");
                            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
                            response.setState(ResponseData.STATE.FAIL.toString());
                            return response;
                        }
                        sql = "";
                    } else if (item.getCategory().equals("BTISETTYPE")) {
                        String categoryId = "TI";
                        sql = "SELECT COUNT(ASSETID) FROM [dbo].[A_ASSET] where CATEGORYID = '" + categoryId + "' and ASSETID_PARENT ='" + item.getAssetIdParent() + "'";
                        Integer setType = jdbcTemplate.queryForObject(sql, Integer.class);
                        if (setType != null && setType > 1) {
                            setType = 1;
                        }
                        assert setType != null;
                        if (!setType.toString().equals(item.getPmanufacturerId())) {
                            response.setData("TI" + " " + item.getSerialNum() + " không trùng loại bộ thiết bị.");
                            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
                            response.setState(ResponseData.STATE.FAIL.toString());
                            return response;
                        }
                        sql = "";
                    } else
                        sql = "SELECT ASSETID FROM [dbo].[A_ASSET] where CATEGORYID = '" + item.getCategory() + "' and SERIALNUM = '" + item.getSerialNum().trim() + "' and P_MANUFACTURERID = '" + item.getPmanufacturerId() + "' and ASSETID != '" + assetId + "'";
                    if (!sql.isEmpty()) {
                        lst = jdbcTemplate.queryForList(sql);
                        if (lst.size() > 0) {
                            response.setData(item.getCategory() + " " + item.getSerialNum() + " đã tồn tại trong hệ thống.");
                            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
                            response.setState(ResponseData.STATE.FAIL.toString());
                            return response;
                        }
                    }
                } else {
                    var serialNum = item.getSerialNum();
                    if (item.getCategory().equals("CONGTO"))
                        sql = "SELECT ASSETID FROM [dbo].[A_ASSET] where CATEGORYID = '" + item.getCategory() + "' and SERIALNUM = '" + serialNum.trim() + "'";
                    else if (item.getCategory().equals("BTU") || item.getCategory().equals("BTI"))
                        sql = "SELECT ASSETID FROM [dbo].[A_ASSET] where CATEGORYID = '" + item.getCategory() + "' and ASSETDESC = '" + serialNum.trim() + "' and ASSETID_PARENT = '" + item.getPmanufacturerId() + "'";
//
//                        else if (item.getCategory().equals("TSB"))
//                            sql = "SELECT ASSETID FROM [dbo].[VIEWCUON] where CATEGORYID = '" + "CUON" + "' and TSB = '" + item.getSerialNum() + "' and TUID ='" + item.getAssetIdParent() + "'";
                    if (item.getCategory().equals("Cuon") && item.getAssetId() == null) {
                        sql = "SELECT ASSETID FROM [dbo].[VIEWCUON] where CATEGORYID = '" + "CUON" + "' and ASSETDESC = N'" + serialNum + "' and TUID ='" + item.getAssetIdParent() + "'";
                        lst = jdbcTemplate.queryForList(sql);
                        if (lst.size() > 0) {
                            response.setData(item.getPmanufacturerId() + " " + item.getSerialNum() + " đã tồn tại trong hệ thống.");
                            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
                            response.setState(ResponseData.STATE.FAIL.toString());
                            return response;
                        }
                        sql = "";
                    } else if (item.getCategory().equals("BTUNAME")) {
                        var category = "BTU";
                        sql = "SELECT ASSETID FROM [dbo].[A_ASSET] where CATEGORYID = '" + category + "' and ASSETDESC = N'" + item.getAssetDesc() + "' and ASSETID ='" + item.getAssetIdParent() + "'";
                        lst = jdbcTemplate.queryForList(sql);
                        if (lst.size() < 1) {
                            response.setData("TU" + " " + item.getSerialNum() + " không trùng tên bộ thiết bị.");
                            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
                            response.setState(ResponseData.STATE.FAIL.toString());
                            return response;
                        }
                        sql = "SELECT ASSETID FROM [dbo].[A_ASSET] where CATEGORYID = '" + category + "' and ORGID = N'" + item.getOrgId() + "' and ASSETID ='" + item.getAssetIdParent() + "'";
                        lst = jdbcTemplate.queryForList(sql);
                        var orgId = item.getOrgId();
                        if (lst.size() < 1 && orgId != null) {
                            response.setData("TU" + " " + item.getSerialNum() + " không trùng đơn vị sở hữu.");
                            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
                            response.setState(ResponseData.STATE.FAIL.toString());
                            return response;
                        }
                        sql = "";
                    } else if (item.getCategory().equals("BTINAME")) {
                        var category = "BTI";
                        sql = "SELECT ASSETID FROM [dbo].[A_ASSET] where CATEGORYID = '" + category + "' and ASSETDESC = N'" + item.getAssetDesc() + "' and ASSETID ='" + item.getAssetIdParent() + "'";
                        lst = jdbcTemplate.queryForList(sql);
                        if (lst.size() < 1) {
                            response.setData("TI" + " " + item.getSerialNum() + " không trùng tên bộ thiết bị.");
                            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
                            response.setState(ResponseData.STATE.FAIL.toString());
                            return response;
                        }
                        sql = "SELECT ASSETID FROM [dbo].[A_ASSET] where CATEGORYID = '" + category + "' and ORGID = N'" + item.getOrgId() + "' and ASSETID ='" + item.getAssetIdParent() + "'";
                        lst = jdbcTemplate.queryForList(sql);
                        var orgId = item.getOrgId();
                        if (lst.size() < 1 && orgId != null) {
                            response.setData("TI" + " " + item.getSerialNum() + " không trùng đơn vị sở hữu.");
                            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
                            response.setState(ResponseData.STATE.FAIL.toString());
                            return response;
                        }
                        sql = "";
                    } else if (item.getCategory().equals("BTUSETTYPE")) {
                        String categoryId = "TU";
                        sql = "SELECT COUNT(ASSETID) FROM [dbo].[A_ASSET] where CATEGORYID = '" + categoryId + "' and ASSETID_PARENT ='" + item.getAssetIdParent() + "'";
                        Integer setType = jdbcTemplate.queryForObject(sql, Integer.class);
                        if (setType != null && setType > 1) {
                            setType = 1;
                        }
                        assert setType != null;
                        if (!setType.toString().equals(item.getPmanufacturerId())) {
                            response.setData("TU" + " " + item.getSerialNum() + " không trùng loại bộ thiết bị.");
                            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
                            response.setState(ResponseData.STATE.FAIL.toString());
                            return response;
                        }
                        sql = "";
                    } else if (item.getCategory().equals("BTISETTYPE")) {
                        String categoryId = "TI";
                        sql = "SELECT COUNT(ASSETID) FROM [dbo].[A_ASSET] where CATEGORYID = '" + categoryId + "' and ASSETID_PARENT ='" + item.getAssetIdParent() + "'";
                        Integer setType = jdbcTemplate.queryForObject(sql, Integer.class);
                        if (setType != null && setType > 1) {
                            setType = 1;
                        }
                        assert setType != null;
                        if (!setType.toString().equals(item.getPmanufacturerId())) {
                            response.setData("TI" + " " + item.getSerialNum() + " không trùng loại bộ thiết bị.");
                            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
                            response.setState(ResponseData.STATE.FAIL.toString());
                            return response;
                        }
                        sql = "";

                    } else
                        sql = "SELECT ASSETID FROM [dbo].[A_ASSET] where CATEGORYID = '" + item.getCategory() + "' and SERIALNUM = '" + item.getSerialNum().trim() + "' and P_MANUFACTURERID = '" + item.getPmanufacturerId() + "'";
                    if (!sql.isEmpty()) {
                        lst = jdbcTemplate.queryForList(sql);
                        if (lst.size() > 0) {
                            response.setData(item.getCategory() + " " + item.getSerialNum() + " đã tồn tại trong hệ thống.");
                            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
                            response.setState(ResponseData.STATE.FAIL.toString());
                            return response;
                        }
                    }

                }
            }

            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setState(ResponseData.STATE.OK.toString());
            return response;
        } catch (Exception ex) {
            response.setState(ResponseData.STATE.FAIL.toString());
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
        }

        return response;
    }

    @Override
    public ResponseData getAllSetTU(SearchTUList dto) {
        ResponseData response = new ResponseData();
        SearchQuery searchQuery = new SearchQuery();
        response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
        response.setState(ResponseData.STATE.OK.toString());
        SearchFilter searchFilter = new SearchFilter();
        List<SearchFilter> searchFilters = new ArrayList<>();
        if (dto.getAssetIdParent() != null && !dto.getAssetIdParent().isEmpty()) {
            searchFilter = new SearchFilter("assetIdParent", "IN", dto.getAssetIdParent());
            searchFilters.add(searchFilter);
        }
        Pageable pageable = PageRequest.of(dto.getPage(), dto.getLimit(), dto.getSortExpression());
        searchFilters.add(searchFilter);
        searchQuery.setSearchFilters(searchFilters);
        Specification<ViewSetTU> spec = SpecificationUtil.bySearchQuery(searchQuery, ViewSetTU.class);
        try {
            var pageResult = viewSetTURepository.findAll(spec, pageable);
            response.setData(pageResult.getContent());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setState(ResponseData.STATE.OK.toString());
        } catch (Exception ignored) {
            response.setData(null);
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setState(ResponseData.STATE.FAIL.toString());
        }
        return response;
    }

    @Override
    public ResponseData getAllSetTI(SearchTIList dto) {
        ResponseData response = new ResponseData();
        SearchQuery searchQuery = new SearchQuery();
        response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
        response.setState(ResponseData.STATE.OK.toString());
        SearchFilter searchFilter = new SearchFilter();
        List<SearchFilter> searchFilters = new ArrayList<>();
        if (dto.getAssetIdParent() != null && !dto.getAssetIdParent().isEmpty()) {
            searchFilter = new SearchFilter("assetIdParent", "IN", dto.getAssetIdParent());
            searchFilters.add(searchFilter);
        }
        Pageable pageable = PageRequest.of(dto.getPage(), dto.getLimit(), dto.getSortExpression());
        searchFilters.add(searchFilter);
        searchQuery.setSearchFilters(searchFilters);
        Specification<ViewSetTI> spec = SpecificationUtil.bySearchQuery(searchQuery, ViewSetTI.class);
        try {
            var pageResult = viewSetTIRepository.findAll(spec, pageable);
            response.setData(pageResult.getContent());
            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            response.setState(ResponseData.STATE.OK.toString());
        } catch (Exception ignored) {
            response.setData(null);
            response.setMessage(ResponseData.MESSAGE.ERROR.toString());
            response.setState(ResponseData.STATE.FAIL.toString());
        }
        return response;
    }

    @Override
    public ResponseData getAssetDetail(String idDiemDo) {
        ResponseData res = new ResponseData();
        res.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
        res.setState(ResponseData.STATE.OK.toString());
        try {
            var listAssetDetail = viewGetAssetDetailRepo.findByIdDiemDo(idDiemDo);
            res.setData(listAssetDetail);
        } catch (Exception ex) {
            res.setMessage(ResponseData.MESSAGE.ERROR.toString());
            res.setState(ResponseData.STATE.FAIL.toString());
        }
        return res;
    }

    @Override
    @Transactional
    public ResponseData deleteCuonByIds(String cuonIdStr) {
        ResponseData res = new ResponseData();
        res.setState(ResponseData.STATE.OK.toString());
        res.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
        if (cuonIdStr != null) {
            var listCuon = cuonIdStr.split(",");
            for (String cuonId : listCuon) {
                var cuonAsset = assetRepository.findById(cuonId).orElse(null);
                if (cuonAsset == null) {
                    break;
                }
                //save cuonAsset
                var cuonTempId = UUID.randomUUID().toString();
                var cuonTemp = new CuonAssetTemp().mapperCuon(cuonAsset);
                cuonTemp.setUSER_CR_DTIME(new Date());
                cuonTemp.setId(cuonTempId);
                cuonAssetTempRepo.save(cuonTemp);
                List<S_ATTRIBUTE_GROUP_ASSOB> sa = attrRepository.findByObjId(cuonId);
                if (sa.size() > 0) {
                    ZAG_CUON zag = zagCuonRepository.findById(sa.get(0).getATTRDATAID()).orElse(new ZAG_CUON());
                    ZagCuonTemp zagCuonTemp = new ZagCuonTemp().mapperZag(zag);
                    zagCuonTemp.setId(cuonTempId);
                    zagCuonTempRepo.save(zagCuonTemp);
                    zagCuonRepository.deleteById(sa.get(0).getATTRDATAID());
                    attrRepository.delete(sa.get(0));
                }
                //delete cuon
                if (assetlinkRepository.existsByAssetId(cuonId)) {
                    assetlinkRepository.deleteAllByAssetId(cuonId);
                }
                if (assetRepository.existsById(cuonId)) {
                    assetRepository.deleteById(cuonId);
                }
            }
        }
        return res;
    }
//    @Transactional
//    @Override
//    public ResponseData createAllDevice(DeviceDto dto, CustomUserDetails user) {
//        ResponseData response = new ResponseData();
//        try {
//            var diemDoDto = dto.getDiemDo();
//            var transId = diemDoDto.getTransId();
//            diemDoDto.setTypeId("DDO");
//            var congToDto = dto.getCongTo();
//            congToDto.setTypeId("CTO");
//            var tuDto = dto.getListTU();
//            var tiDto = dto.getListTI();
//            diemDoDto.setUserCRId(user.getUserid());
//            diemDoDto.setUserCRDTime(new Date());
//            congToDto.setUserCRId(user.getUserid());
//            congToDto.setUserCRDTime(new Date());
//            congToDto.setTransId(transId);
//            var diemDoResult = createDevice(diemDoDto, A_ASSET.CategoryId.DIEMDO.toString());
//            congToDto.setAssetIdParent(diemDoResult.getData().toString());
//            var congToResult = createDevice(congToDto, A_ASSET.CategoryId.CONGTO.toString());
//            if (congToResult.getState().equals(ResponseData.STATE.FAIL.toString())) {
//                throw new Exception("Số chế tạo công tơ đã tồn tại");
//            }
//            tuDto.forEach(tu -> {
//                tu.setTypeId("BTU");
//                tu.setUserCRId(user.getUserid());
//                tu.setUserCRDTime(new Date());
//                tu.setOrgId(tu.getOrgId());
//                tu.setTransId(transId);
//                var tuId = createTU(tu, diemDoResult.getData().toString());
//            });
//            tiDto.forEach(ti -> {
//                ti.setTypeId("BTI");
//                ti.setUserCRId(user.getUserid());
//                ti.setUserCRDTime(new Date());
//                ti.setOrgId(ti.getOrgId());
//                var tiId = createTI(ti, diemDoResult.getData().toString());
//            });
//            View_HTDD listRs = htddRepository.findById(diemDoResult.getData().toString()).orElse(new View_HTDD());
//            response.setData(listRs);
//            response.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
//            response.setState(ResponseData.STATE.OK.toString());
//        } catch (Exception ignored) {
//            response.setMessage(ResponseData.MESSAGE.ERROR.toString() + ignored.getMessage());
//            response.setMessage(ResponseData.STATE.FAIL.toString());
//        }
//        return response;
//    }
}






