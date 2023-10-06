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
import com.evnit.ttpm.AuthApp.entity.device.ti.ViewSetTI;
import com.evnit.ttpm.AuthApp.entity.device.ti.ViewTI;
import com.evnit.ttpm.AuthApp.entity.device.ti.ViewTIDevice;
import com.evnit.ttpm.AuthApp.entity.device.ti.ZAG_TI;
import com.evnit.ttpm.AuthApp.entity.device.tu.*;
import com.evnit.ttpm.AuthApp.mapper.DeviceMapper;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.model.device.cuon.CuonCreateDto;
import com.evnit.ttpm.AuthApp.model.device.ti.SearchTIList;
import com.evnit.ttpm.AuthApp.model.device.ti.SetTICreateDto;
import com.evnit.ttpm.AuthApp.model.device.ti.TICreateDto;
import com.evnit.ttpm.AuthApp.model.device.tu.SearchTUList;
import com.evnit.ttpm.AuthApp.model.device.tu.TUCreateDto;
import com.evnit.ttpm.AuthApp.repository.category.A_ASSETRepository;
import com.evnit.ttpm.AuthApp.repository.category.S_ATTRIBUTE_GROUP_ASSOBJRepository;
import com.evnit.ttpm.AuthApp.repository.device.A_ASSETLINKRepository;
import com.evnit.ttpm.AuthApp.repository.device.cuon.ZAGCuonRepository;
import com.evnit.ttpm.AuthApp.repository.device.ti.ViewSetTIRepository;
import com.evnit.ttpm.AuthApp.repository.device.ti.ViewTIDeviceRepository;
import com.evnit.ttpm.AuthApp.repository.device.ti.ViewTIRepository;
import com.evnit.ttpm.AuthApp.repository.device.ti.ZAG_TIRepository;
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
public class DeviceTIService {
    private final ViewTUChildrenRepository tuChildrenRepository;
    private final EntityManager entityManager;
    private final ViewTIDeviceRepository tiDeviceRepository;
    private final ViewTIRepository viewTIRepository;
    private final A_ASSETRepository assetRepository;
    private final A_ASSETLINKRepository assetlinkRepository;
    private final ZAG_TIRepository zagTiRepository;
    private final ZAGCuonRepository zagCuonRepository;
    private final S_ATTRIBUTE_GROUP_ASSOBJRepository sAttributeGroupAssobjRepository;
    private final ZAGCuonRepository cuonRepository;
    private final ViewSetTIRepository viewSetTIRepository;
    @Autowired
    JdbcTemplate jdbcTemplate;
    private final ModelMapper mapper;

    public DeviceTIService(ViewTUChildrenRepository tuChildrenRepository, EntityManager entityManager,
                           ViewTIDeviceRepository tiDeviceRepository, ViewTIRepository viewTIRepository, A_ASSETRepository assetRepository, A_ASSETLINKRepository assetlinkRepository,
                           ZAG_TIRepository zagTiRepository, ZAGCuonRepository zagCuonRepository, S_ATTRIBUTE_GROUP_ASSOBJRepository sAttributeGroupAssobjRepository,
                           ZAGCuonRepository cuonRepository, ViewSetTIRepository viewSetTIRepository, DeviceMapper mapper) {
        this.tuChildrenRepository = tuChildrenRepository;
        this.entityManager = entityManager;
        this.tiDeviceRepository = tiDeviceRepository;
        this.viewTIRepository = viewTIRepository;
        this.assetRepository = assetRepository;
        this.assetlinkRepository = assetlinkRepository;
        this.zagTiRepository = zagTiRepository;
        this.zagCuonRepository = zagCuonRepository;
        this.sAttributeGroupAssobjRepository = sAttributeGroupAssobjRepository;
        this.cuonRepository = cuonRepository;
        this.viewSetTIRepository = viewSetTIRepository;
        this.mapper = mapper.getModelMapper();
    }

    public Page<ViewTuChildren> findByPaging(Pageable pageable, Specification<ViewTuChildren> spec) {
        return tuChildrenRepository.findAll(spec, pageable);
    }

    public ResponseData getTIOdd(SearchTIList dto) {
        ResponseData res = new ResponseData();
        try {
            var setTu = viewSetTIRepository.findById(dto.getSetId()).orElse(null);
            assert setTu != null;
            var tuFinded = setTu.getTiList().stream().filter(x -> x.getAssetId().equals(dto.getTiId())).collect(Collectors.toList());
            setTu.setTiList(tuFinded);
            res.setState(ResponseData.STATE.OK.toString());
            res.setMessage(ResponseData.MESSAGE.SUCCESS.toString());
            res.setData(setTu);
        } catch (Exception ex) {
            res.setState(ResponseData.STATE.FAIL.toString());
            res.setMessage(ResponseData.MESSAGE.ERROR.toString());
        }
        return res;
    }

    public Page<ViewTIDevice> findTIDevicePaging(Pageable pageable, Specification<ViewTIDevice> spec) {
        return tiDeviceRepository.findAll(spec, pageable);
    }

    public List<ViewTI> findViewTIByParentId(String parentId) {
        return viewTIRepository.findByParentId(parentId);
    }

    public ViewTI findViewTIById(String id) {
        return viewTIRepository.findViewTiById(id).orElse(null);
    }

    public ViewSetTI findSetTIById(String assetId) {
        return viewSetTIRepository.findById(assetId).orElse(null);
    }

    public ResponseData updateSetTi(SetTICreateDto dto, CustomUserDetails userDetails) throws Exception {
        ResponseData data = new ResponseData();
        var currentUser = userDetails.getUserid();
        var updatedDate = new Date();
        try {
            var setTIChange = viewSetTIRepository.findById(dto.getAssetIdChange()).orElse(null);
            assert setTIChange != null;
            var listTIChange = setTIChange.getTiList();
            assert listTIChange != null;

            if (dto.getGroup()) {
                var tiListDto = dto.getTiList();
                if (tiListDto != null && tiListDto.size() > 0) {
                    //find tu remove from diem do
                    var tiF = listTIChange.stream().filter(x -> x.getAssetORD() == 1).findFirst().orElse(null);
                    assert tiF != null;
                    var listCuonF = tiF.getListCuon();
                    Set<String> listDiemDo1 = new HashSet<>();
                    for (ViewCuonTI viewCuonTI : listCuonF) {
                        var listDiemDo = viewCuonTI.getDiemDoAssets().stream().map(ViewDiemDoTest::getAssetId).collect(Collectors.toList());
                        listDiemDo1.addAll(listDiemDo);
                    }
                    var setTIEntity = mapper.map(dto, A_ASSET.class);
                    setTIEntity.setCATEGORYID(A_ASSET.CategoryId.BTI.toString());
                    setTIEntity.setTYPEID(A_ASSET.CategoryId.BTI.toString());
                    setTIEntity.setISDELETE(false);
                    setTIEntity.setISSAVE(true);
                    var setType = tiListDto.size() > 1 ? 1 : 3;
                    setTIEntity.setSetType(setType);
                    var setTiId = setTIEntity.getASSETID();
                    if (setTiId == null) {
                        setTiId = UUID.randomUUID().toString();
                        setTIEntity.setASSETID(setTiId);
                    }
                    assetRepository.save(setTIEntity);
                    //get list diem do
                    Set<String> listDiemDo2 = new HashSet<>();
                    Set<String> listDiemDo3 = new HashSet<>();
                    if (tiListDto.size() > 1) {
                        var tiS = listTIChange.stream().filter(x -> x.getAssetORD() == 1).findFirst().orElse(null);
                        assert tiS != null;
                        var listCuonS = tiS.getListCuon();
                        for (ViewCuonTI viewCuonTI : listCuonS) {
                            var listDiemDo = viewCuonTI.getDiemDoAssets().stream().map(ViewDiemDoTest::getAssetId).collect(Collectors.toList());
                            listDiemDo2.addAll(listDiemDo);
                        }
                        var tiT = listTIChange.stream().filter(x -> x.getAssetORD() == 1).findFirst().orElse(null);
                        assert tiT != null;
                        var listCuonT = tiS.getListCuon();
                        for (ViewCuonTI viewCuonTI : listCuonT) {
                            var listDiemDo = viewCuonTI.getDiemDoAssets().stream().map(ViewDiemDoTest::getAssetId).collect(Collectors.toList());
                            listDiemDo3.addAll(listDiemDo);
                        }
                    }
                    if (!dto.getCheckReplace()) {
                        //remove
                        data.setData(setTIChange);
                        return data;
                    }
                    // save ti
                    for (TICreateDto tiDto : dto.getTiList()) {
                        tiDto.setAssetRoot(setTIEntity.getASSETID_PARENT());
                        var listDiemDoFinal = listDiemDo1;
                        if (tiDto.getAssetORD() == 2) {
                            listDiemDoFinal = listDiemDo2;
                        }
                        if (tiDto.getAssetORD() == 3) {
                            listDiemDoFinal = listDiemDo3;
                        }
                        //save asset ti
                        saveTIAndChangeDiemDo(tiDto, setTIEntity, currentUser, updatedDate, listDiemDoFinal);
                    }
                    if (setTIChange != null && !setTIChange.getAssetId().equals(dto.getAssetId())) {

                        String sql;
                        sql = "EXEC [dbo].[sp_Delete_A_ASSETLINK] @assetId = N'" + dto.getAssetIdChange() + "', @group = N'" + 0 + "'";
                        jdbcTemplate.execute(sql);
                    }
                    data.setData(setTIEntity);
                }
            } else {

                var setTIEntity = assetRepository.findById(setTIChange.getAssetId()).orElse(new A_ASSET());
                // save ti
                var ti = setTIChange.getTiList().stream().filter(x -> x.getAssetId().equals(dto.getAssetIdChangeChild())).findFirst().orElse(null);
                assert ti != null;
                var listCuon = ti.getListCuon();
                Set<String> listDiemDo1 = new HashSet<>();
                for (ViewCuonTI viewCuon : listCuon) {
                    var listDiemDo = viewCuon.getDiemDoAssets().stream().map(ViewDiemDoTest::getAssetId).collect(Collectors.toList());
                    listDiemDo1.addAll(listDiemDo);
                }
                var tuChange = assetRepository.findById(ti.getAssetId()).orElse(null);
//                if (tuChange != null) {
//                    tuChange.setASSETID_PARENT(null);
//                    assetRepository.save(tuChange);
//                }
                var tiListDto = dto.getTiList();
                if (tiListDto != null && tiListDto.size() > 0) {
                    for (TICreateDto tuDto : dto.getTiList()) {
                        var listDiemDoFinal = listDiemDo1;
                        var tiId = tuDto;
                        if (tuChange != null && !tuChange.getASSETID().equals(tiId.getAssetId())) {
                            tuChange.setASSETID_PARENT(null);
                            assetRepository.save(tuChange);
                            String sql;
                            sql = "EXEC [dbo].[sp_Delete_A_ASSETLINK] @assetId = N'" + dto.getAssetIdChangeChild() + "', @group = N'" + 1 + "'";
                            jdbcTemplate.execute(sql);
                        }
                        tuDto.setAssetRoot(ti.getAssetRoot());
                        tuDto.setAssetORD(ti.getAssetORD());
                        var tiEntity = saveTIAndChangeDiemDo(tuDto, setTIEntity, currentUser, updatedDate, listDiemDoFinal);
                        data.setData(tiEntity);
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

    private A_ASSET saveTIAndChangeDiemDo(TICreateDto tiDto, A_ASSET setTIEntity, String currentUser, Date updatedDate, Set<String> listDiemDoFinal) {
        //save asset ti
        var tiEntity = mapper.map(tiDto, A_ASSET.class);
        var tiId = tiEntity.getASSETID();
        if (tiId == null) {
            tiId = UUID.randomUUID().toString();
            tiEntity.setASSETID(tiId);
            tiEntity.setASSETDESC(setTIEntity.getASSETDESC());
            tiEntity.setUSER_CR_ID(currentUser);
            tiEntity.setUSER_CR_DTIME(updatedDate);
        } else {
            tiEntity.setUSER_MDF_ID(currentUser);
            tiEntity.setUSER_MDF_DTIME(updatedDate);
        }
        tiEntity.setASSETID_PARENT(setTIEntity.getASSETID());
        tiEntity.setORGID(setTIEntity.getORGID());
        tiEntity.setISDELETE(false);
        tiEntity.setISSAVE(true);
        tiEntity.setCATEGORYID(A_ASSET.CategoryId.TI.toString());
        tiEntity.setTYPEID(A_ASSET.CategoryId.TI.toString());
        tiEntity.setSetType(setTIEntity.getSetType());
        assetRepository.save(tiEntity);
        //save zag
        var zagTI = mapper.map(tiDto.getZagti(), ZAG_TI.class);
        var zagId = zagTI.getAttrDataId();
        if (zagId == null) {
            zagId = UUID.randomUUID().toString();
            zagTI.setAttrDataId(zagId);
            zagTI.setObjTypeId("A");
            zagTI.setObjId(tiEntity.getASSETID());
        }
        zagTiRepository.save(zagTI);
        //find S_ATTRIBUTE
        var saEntity = sAttributeGroupAssobjRepository.findByObjId(tiId);
        S_ATTRIBUTE_GROUP_ASSOB sa = new S_ATTRIBUTE_GROUP_ASSOB();
        if (saEntity.size() < 1) {
            sa.setATTRDATAID(zagId);
            sa.setOBJID(tiId);
            sa.setATTRGROUPID(A_ASSET.CategoryId.TI.toString());
            sa.setATTRGROUPORD(1);
            sa.setOBJTYPEID("A");
            sAttributeGroupAssobjRepository.save(sa);
        }
        var listCuon = assetRepository.findByParentId(tiId);
        if (listCuon != null && listCuon.size() > 0) {
            listCuon.sort(Comparator.comparing(A_ASSET::getASSETORD));
            for (A_ASSET cuon : listCuon) {
                var assetOrd = cuon.getASSETORD() + 1;
                cuon.setASSETORD(assetOrd);
                assetRepository.save(cuon);
            }
        }
        //save cuon
        for (CuonCreateDto cuonDto : tiDto.getListCuon()) {
            //save asset
            var cuonEntity = mapper.map(cuonDto, A_ASSET.class);
            var cuonId = cuonEntity.getASSETID();
            if (cuonId == null) {
                cuonId = UUID.randomUUID().toString();
                cuonEntity.setASSETID(cuonId);
                cuonEntity.setASSETID_PARENT(tiId);

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
        }
        return tiEntity;
    }
}
