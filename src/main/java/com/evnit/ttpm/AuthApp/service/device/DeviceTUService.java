package com.evnit.ttpm.AuthApp.service.device;

import com.evnit.ttpm.AuthApp.entity.admin.ResponseData;
import com.evnit.ttpm.AuthApp.entity.category.A_ASSET;
import com.evnit.ttpm.AuthApp.entity.category.A_ASSET_LINK;
import com.evnit.ttpm.AuthApp.entity.category.AssetLinkId;
import com.evnit.ttpm.AuthApp.entity.category.S_ATTRIBUTE_GROUP_ASSOB;
import com.evnit.ttpm.AuthApp.entity.device.cuon.ViewCuon;
import com.evnit.ttpm.AuthApp.entity.device.cuon.ViewCuonTI;
import com.evnit.ttpm.AuthApp.entity.device.cuon.ZAG_CUON;
import com.evnit.ttpm.AuthApp.entity.device.diemdo.ViewDiemDoTest;
import com.evnit.ttpm.AuthApp.entity.device.ti.ZAG_TI;
import com.evnit.ttpm.AuthApp.entity.device.tu.*;
import com.evnit.ttpm.AuthApp.mapper.DeviceMapper;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.model.device.common.SetDeviceDto;
import com.evnit.ttpm.AuthApp.model.device.cuon.CuonCreateDto;
import com.evnit.ttpm.AuthApp.model.device.ti.TICreateDto;
import com.evnit.ttpm.AuthApp.model.device.tu.SearchTUList;
import com.evnit.ttpm.AuthApp.model.device.tu.SetTUCreateDto;
import com.evnit.ttpm.AuthApp.model.device.tu.TUCreateDto;
import com.evnit.ttpm.AuthApp.repository.category.A_ASSETRepository;
import com.evnit.ttpm.AuthApp.repository.category.S_ATTRIBUTE_GROUP_ASSOBJRepository;
import com.evnit.ttpm.AuthApp.repository.device.A_ASSETLINKRepository;
import com.evnit.ttpm.AuthApp.repository.device.cuon.ZAGCuonRepository;
import com.evnit.ttpm.AuthApp.repository.device.tu.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DeviceTUService {
    private final ViewTUChildrenRepository tuChildrenRepository;
    private final EntityManager entityManager;
    private final ViewTUDeviceRepository tuDeviceRepository;
    private final ViewTURepository viewTURepository;
    private final A_ASSETRepository assetRepository;
    private final A_ASSETLINKRepository assetlinkRepository;
    private final ZAGTURepository zagtuRepository;
    private final ZAGCuonRepository zagCuonRepository;
    private final S_ATTRIBUTE_GROUP_ASSOBJRepository sAttributeGroupAssobjRepository;
    private final ZAGCuonRepository cuonRepository;
    private final ViewSetTURepository viewSetTURepository;
    @Autowired
    JdbcTemplate jdbcTemplate;
    private final ModelMapper mapper;

    public DeviceTUService(ViewTUChildrenRepository tuChildrenRepository, EntityManager entityManager, ViewTUDeviceRepository tuDeviceRepository,
                           ViewTURepository viewTURepository, A_ASSETRepository assetRepository, A_ASSETLINKRepository assetlinkRepository,
                           ZAGTURepository zagtuRepository, ZAGCuonRepository zagCuonRepository, S_ATTRIBUTE_GROUP_ASSOBJRepository sAttributeGroupAssobjRepository,
                           ZAGCuonRepository cuonRepository, ViewSetTURepository viewSetTURepository, DeviceMapper mapper) {
        this.tuChildrenRepository = tuChildrenRepository;
        this.entityManager = entityManager;
        this.tuDeviceRepository = tuDeviceRepository;
        this.viewTURepository = viewTURepository;
        this.assetRepository = assetRepository;
        this.assetlinkRepository = assetlinkRepository;
        this.zagtuRepository = zagtuRepository;
        this.zagCuonRepository = zagCuonRepository;
        this.sAttributeGroupAssobjRepository = sAttributeGroupAssobjRepository;
        this.cuonRepository = cuonRepository;
        this.viewSetTURepository = viewSetTURepository;
        this.mapper = mapper.getModelMapper();
    }

    public ResponseData getTUOdd(SearchTUList dto) {
        ResponseData res = new ResponseData();
        try {
            var setTu = viewSetTURepository.findById(dto.getSetId()).orElse(null);
            assert setTu != null;
            var tuFinded = setTu.getTuList().stream().filter(x -> x.getAssetId().equals(dto.getTuId())).collect(Collectors.toList());
            setTu.setTuList(tuFinded);
            res.setState(ResponseData.STATE.OK.toString());
            res.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            res.setData(setTu);
        } catch (Exception ex) {
            res.setState(ResponseData.STATE.FAIL.toString());
            res.setMessage(ResponseData.MESSAGE.ERROR.toString());
        }
        return res;
    }

    public Page<ViewTuChildren> findByPaging(Pageable pageable, Specification<ViewTuChildren> spec) {
        return tuChildrenRepository.findAll(spec, pageable);
    }

    public Page<ViewTUDevice> findTUDevicePaging(Pageable pageable, Specification<ViewTUDevice> spec) {
        return tuDeviceRepository.findAll(spec, pageable);
    }

    public List<ViewTU> findViewTUByParentId(String parentId) {
        return viewTURepository.findByParentId(parentId);
    }

    public ViewTU findViewTUById(String id) {
        return viewTURepository.findViewTuById(id).orElse(null);
    }

    public ViewSetTU findSetTUById(String assetId) {
        return viewSetTURepository.findById(assetId).orElse(null);
    }

    @Transactional
    public ResponseData updateSetTu(SetTUCreateDto dto, CustomUserDetails userDetails) throws Exception {
        SetDeviceDto resData = new SetDeviceDto();
        ResponseData data = new ResponseData();
        data.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
        data.setState(ResponseData.STATE.OK.toString());
        var currentUser = userDetails.getUserid();
        var updatedDate = new Date();
        try {
            //find tu remove from diem do
            var setTUChange = viewSetTURepository.findById(dto.getAssetIdChange()).orElse(null);
            assert setTUChange != null;
            var listTUChange = setTUChange.getTuList();
            assert listTUChange != null;
            if (dto.getGroup()) {
                var tuListDto = dto.getTuList();
                if (tuListDto != null && tuListDto.size() > 0) {
                    var tiF = listTUChange.stream().filter(x -> x.getAssetORD() == 1).findFirst().orElse(null);
                    assert tiF != null;
                    var listCuonF = tiF.getListCuon();
                    Set<String> listDiemDo1 = new HashSet<>();
                    for (ViewCuon viewCuon : listCuonF) {
                        var listDiemDo = viewCuon.getDiemDoAssets().stream().map(ViewDiemDoTest::getAssetId).collect(Collectors.toList());
                        listDiemDo1.addAll(listDiemDo);
                    }
                    var setTUEntity = mapper.map(dto, A_ASSET.class);
                    setTUEntity.setCATEGORYID(A_ASSET.CategoryId.BTU.toString());
                    setTUEntity.setTYPEID(A_ASSET.CategoryId.BTU.toString());


                    setTUEntity.setISDELETE(false);
                    setTUEntity.setISSAVE(true);
                    var setTiId = setTUEntity.getASSETID();
                    if (setTiId == null) {
                        setTiId = UUID.randomUUID().toString();
                        setTUEntity.setASSETID(setTiId);
                    }
                    var setType = tuListDto.size() > 1 ? 1 : 3;
                    setTUEntity.setSetType(setType);
                    assetRepository.save(setTUEntity);
                    Set<String> listDiemDo2 = new HashSet<>();
                    Set<String> listDiemDo3 = new HashSet<>();
                    //get list diem do
                    if (tuListDto.size() > 1) {
                        var tiS = listTUChange.stream().filter(x -> x.getAssetORD() == 2).findFirst().orElse(null);
                        assert tiS != null;
                        var listCuonS = tiS.getListCuon();

                        for (ViewCuon viewCuon : listCuonS) {
                            var listDiemDo = viewCuon.getDiemDoAssets().stream().map(ViewDiemDoTest::getAssetId).collect(Collectors.toList());
                            listDiemDo2.addAll(listDiemDo);
                        }
                        var tiT = listTUChange.stream().filter(x -> x.getAssetORD() == 3).findFirst().orElse(null);
                        assert tiT != null;
                        var listCuonT = tiS.getListCuon();

                        for (ViewCuon viewCuon : listCuonT) {
                            var listDiemDo = viewCuon.getDiemDoAssets().stream().map(ViewDiemDoTest::getAssetId).collect(Collectors.toList());
                            listDiemDo3.addAll(listDiemDo);
                        }
                    }
                    if (!dto.getCheckReplace()) {
                        //remove
                        data.setData(setTUChange);
                        return data;
                    }
                    List<String> listCuonReplace = new ArrayList<>();
                    // save ti
                    for (TUCreateDto tuDto : dto.getTuList()) {
                        tuDto.setAssetRoot(setTUEntity.getASSETID_PARENT());
                        var listDiemDoFinal = listDiemDo1;
                        if (tuDto.getAssetORD() == 2) {
                            listDiemDoFinal = listDiemDo2;
                        }
                        if (tuDto.getAssetORD() == 3) {
                            listDiemDoFinal = listDiemDo3;
                        }
                        tuDto.setSetType(setType);
//                      save asset ti
                        saveTIAndChangeDiemDo(tuDto, setTUEntity, currentUser, updatedDate, listDiemDoFinal, listCuonReplace);
                    }
                    if (setTUChange != null && !setTUChange.getAssetId().equals(dto.getAssetId())) {
                        String sql;
                        sql = "EXEC [dbo].[sp_Delete_A_ASSETLINK] @assetId = N'" + dto.getAssetIdChange() + "', @group = N'" + 0 + "'";
                        jdbcTemplate.execute(sql);
                    }
                    mapper.map(setTUEntity, resData);
                    resData.setCuonIdsReplace(String.join(",", listCuonReplace));
                    data.setData(resData);
                }
            } else {
                var setTUEntity = assetRepository.findById(setTUChange.getAssetId()).orElse(new A_ASSET());
                // save ti
                var tu = listTUChange.stream().filter(x -> x.getAssetId().equals(dto.getAssetIdChangeChild())).findFirst().orElse(null);
                assert tu != null;
                var tuChange = assetRepository.findById(tu.getAssetId()).orElse(null);
                if (!dto.getCheckReplace()) {
                    //remove
                    data.setData(tuChange);
                    data.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
                    data.setState(ResponseData.STATE.OK.toString());
                    return data;
                }
                var listCuon = tu.getListCuon();
                Set<String> listDiemDo1 = new HashSet<>();
                for (ViewCuon viewCuon : listCuon) {
                    var listDiemDo = viewCuon.getDiemDoAssets().stream().map(ViewDiemDoTest::getAssetId).collect(Collectors.toList());
                    listDiemDo1.addAll(listDiemDo);
                }

                var tuListDto = dto.getTuList();
                if (tuListDto != null && tuListDto.size() > 0) {
                    List<String> listCuonReplace = new ArrayList<>();
                    for (TUCreateDto tuDto : dto.getTuList()) {
                        var listDiemDoFinal = listDiemDo1;
                        var tuId = tuDto;
                        if (tuChange != null && !tuChange.getASSETID().equals(tuId.getAssetId())) {
                            tuChange.setASSETID_PARENT(null);
                            assetRepository.save(tuChange);
                            String sql;
                            sql = "EXEC [dbo].[sp_Delete_A_ASSETLINK] @assetId = N'" + dto.getAssetIdChangeChild() + "', @group = N'" + 1 + "'";
                            jdbcTemplate.execute(sql);
                        }
                        tuDto.setAssetRoot(tu.getAssetRoot());
                        tuDto.setAssetORD(tu.getAssetORD());
                        var tuEntity = saveTIAndChangeDiemDo(tuDto, setTUEntity, currentUser, updatedDate, listDiemDoFinal, listCuonReplace);
                        data.setData(tuEntity);
                    }
                }
            }
            data.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            data.setState(ResponseData.STATE.OK.toString());
        } catch (Exception ignored) {
            throw new Exception("Error");
        }
        return data;
    }

    private A_ASSET saveTIAndChangeDiemDo(TUCreateDto tuDto, A_ASSET setTIEntity, String currentUser, Date updatedDate, Set<String> listDiemDoFinal, List<String> listCuonReplace) {
        //save asset ti
        var tuEntity = mapper.map(tuDto, A_ASSET.class);
        var tuId = tuEntity.getASSETID();
        if (tuId == null) {
            tuId = UUID.randomUUID().toString();
            tuEntity.setASSETID(tuId);
            tuEntity.setASSETDESC(setTIEntity.getASSETDESC());
            tuEntity.setUSER_CR_ID(currentUser);
            tuEntity.setUSER_CR_DTIME(updatedDate);
        } else {
            tuEntity.setUSER_MDF_ID(currentUser);
            tuEntity.setUSER_MDF_DTIME(updatedDate);
        }

        tuEntity.setASSETID_PARENT(setTIEntity.getASSETID());
        tuEntity.setORGID(setTIEntity.getORGID());
        tuEntity.setISDELETE(false);
        tuEntity.setISSAVE(true);
        tuEntity.setCATEGORYID(A_ASSET.CategoryId.TU.toString());
        tuEntity.setTYPEID(A_ASSET.CategoryId.TU.toString());
        tuEntity.setSetType(setTIEntity.getSetType());
        assetRepository.save(tuEntity);
        //save zag
        var zagTU = mapper.map(tuDto.getZagtu(), ZAG_TU.class);
        var zagId = zagTU.getAttrDataId();
        if (zagId == null) {
            zagId = UUID.randomUUID().toString();
            zagTU.setAttrDataId(zagId);
            zagTU.setObjTypeId("A");
            zagTU.setObjId(tuEntity.getASSETID());
        }
        zagtuRepository.save(zagTU);
        //find S_ATTRIBUTE
        var saEntity = sAttributeGroupAssobjRepository.findByObjId(tuId);
        S_ATTRIBUTE_GROUP_ASSOB sa = new S_ATTRIBUTE_GROUP_ASSOB();
        if (saEntity.size() < 1) {
            sa.setATTRDATAID(zagId);
            sa.setOBJID(tuId);
            sa.setATTRGROUPID(A_ASSET.CategoryId.TU.toString());
            sa.setATTRGROUPORD(1);
            sa.setOBJTYPEID("A");
            sAttributeGroupAssobjRepository.save(sa);
        }
        var listCuon = assetRepository.findByParentId(tuId);
        if (listCuon != null && listCuon.size() > 0) {
            listCuon.sort(Comparator.comparing(A_ASSET::getASSETORD));
            for (A_ASSET cuon : listCuon) {
                var assetOrd = cuon.getASSETORD() + 1;
                cuon.setASSETORD(assetOrd);
                assetRepository.save(cuon);
            }
        }
        //save cuon
        for (CuonCreateDto cuonDto : tuDto.getListCuon()) {
            //save asset
            var cuonEntity = mapper.map(cuonDto, A_ASSET.class);
            var cuonId = cuonEntity.getASSETID();
            if (cuonId == null) {
                cuonId = UUID.randomUUID().toString();
                cuonEntity.setASSETID(cuonId);
                cuonEntity.setASSETID_PARENT(tuId);
                cuonEntity.setUSER_CR_DTIME(updatedDate);
                cuonEntity.setUSER_CR_ID(currentUser);

            } else {
                cuonEntity.setUSER_MDF_DTIME(updatedDate);
                cuonEntity.setUSER_MDF_ID(currentUser);
            }
            cuonEntity.setCATEGORYID(A_ASSET.CategoryId.CUON.toString());
            cuonEntity.setTYPEID(A_ASSET.CategoryId.CUON.toString());
            cuonEntity.setORGID(setTIEntity.getORGID());
            cuonEntity.setISSAVE(true);
            cuonEntity.setISDELETE(false);

            assetRepository.save(cuonEntity);
            //save zag
            var zagCuon = mapper.map(cuonDto.getCuon(), ZAG_CUON.class);
            zagId = zagCuon.getAttrDataId();
            if (zagId == null) {
                zagId = UUID.randomUUID().toString();
                zagCuon.setAttrDataId(zagId);
                zagCuon.setObjId(cuonId);
                zagCuon.setObjTypeId("A");
            }
            var machNThu = cuonDto.getCuon().getMachNThu();
            if (machNThu != null && machNThu.size() > 0) {
                zagCuon.setMachNThu(String.join(",", machNThu));
            }
            zagCuonRepository.save(zagCuon);
            //find attr
            var saCuon = sAttributeGroupAssobjRepository.findByObjId(cuonId);
            if (saCuon.size() < 1) {
                sa.setATTRDATAID(zagId);
                sa.setOBJID(cuonId);
                sa.setOBJTYPEID("A");
                sa.setATTRGROUPID(A_ASSET.CategoryId.CUON.toString());
                sa.setATTRGROUPORD(1);
                sAttributeGroupAssobjRepository.save(sa);
            }
            //save diem do - cuon
            for (String diemDoId : listDiemDoFinal) {
                AssetLinkId id = new AssetLinkId(cuonId, diemDoId);
                var findKey = assetlinkRepository.existsById(id);
                if (!findKey) {
                    A_ASSET_LINK al = new A_ASSET_LINK();
                    al.setAssetId(cuonId);
                    al.setAssetIdLink(diemDoId);
                    assetlinkRepository.save(al);
                }
            }
            listCuonReplace.add(cuonId);
        }
        return tuEntity;
    }
}
