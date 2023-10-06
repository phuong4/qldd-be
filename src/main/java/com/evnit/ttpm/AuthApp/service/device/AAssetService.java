package com.evnit.ttpm.AuthApp.service.device;

import com.evnit.ttpm.AuthApp.entity.category.A_ASSET;
import com.evnit.ttpm.AuthApp.entity.device.congto.ViewCongTo;
import com.evnit.ttpm.AuthApp.entity.device.ti.ViewTI;
import com.evnit.ttpm.AuthApp.entity.device.ti.ViewTIDetail;
import com.evnit.ttpm.AuthApp.entity.device.tu.ViewTU;
import com.evnit.ttpm.AuthApp.entity.device.tu.ViewTUDetail;
import com.evnit.ttpm.AuthApp.repository.category.A_ASSETRepository;
import com.evnit.ttpm.AuthApp.repository.device.congto.ViewCongToRepository;
import com.evnit.ttpm.AuthApp.repository.device.ti.ViewTIDetailRepository;
import com.evnit.ttpm.AuthApp.repository.device.ti.ViewTIRepository;
import com.evnit.ttpm.AuthApp.repository.device.tu.ViewTUDetailRepository;
import com.evnit.ttpm.AuthApp.repository.device.tu.ViewTURepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AAssetService {
    @Autowired
    A_ASSETRepository assetRepository;
    @Autowired
    ViewCongToRepository congToRepository;
    @Autowired
    ViewTUDetailRepository viewTUDetailRepository;
    @Autowired
    ViewTIDetailRepository viewTIDetailRepository;
    @Autowired
    ViewTIRepository viewTIRepository;
    private final ViewTURepository viewTURepository;
    @Autowired
    ViewTURepository tuRepository;
    @Autowired
    public AAssetService(ViewTURepository viewTURepository) {
        this.viewTURepository = viewTURepository;
    }

    public A_ASSET createAsset(A_ASSET entity) {
        try {
            return assetRepository.saveAndFlush(entity);
        } catch (Exception ignored) {
            throw new IllegalThreadStateException("Faild to save a_asset" + ignored.getMessage());
        }
    }

    public Optional<A_ASSET> findById(String id) {
        try {
            return assetRepository.findByIdNotDelete(id);
        } catch (Exception ignored) {
            throw new IllegalThreadStateException("Faild to save a_asset" + ignored.getMessage());
        }
    }

    public ViewCongTo findByCategoryType(String categoryType, String assetId) {
        return congToRepository.findByASSETIDAndCATEGORYID(assetId, categoryType);
    }

    public List<ViewCongTo> findAllByCategoryType(String categoryType) {
        return congToRepository.findAllByCATEGORYID(categoryType).stream()
                .filter(x -> (Objects.nonNull(x.getISDELETE()) && !x.getISDELETE())).collect(Collectors.toList());
    }

    public Page<ViewCongTo> findAllPaging(Specification<ViewCongTo> spec, Pageable pageable) {
        return congToRepository.findAll(spec, pageable);
    }
    public Page<ViewTU> findTuPaging(Specification<ViewTU> spec, Pageable pageable){
        return viewTURepository.findAll(spec, pageable);
    }
    public Page<ViewTUDetail> findTuDetailPaging(Specification<ViewTUDetail> spec, Pageable pageable){
        return viewTUDetailRepository.findAll(spec, pageable);
    }
    public Page<ViewTIDetail> findTiDetailPaging(Specification<ViewTIDetail> spec, Pageable pageable){
        return viewTIDetailRepository.findAll(spec, pageable);
    }
    public Optional<ViewCongTo> getCongToBySerialNum(String serialNum) {
        return congToRepository.findBySerialNumber(serialNum);
    }
    public Optional<ViewTU> getTUBySerialNum(String serialNum) {
        return tuRepository.findBySerialNumber(serialNum);
    }
    public Optional<ViewTI> getTIBySerialNum(String serialNum) {
        return viewTIRepository.findBySerialNumber(serialNum);
    }

    public A_ASSET findBySerialNum(String serialNum,String categoryId) {
        return assetRepository.findBySerialNum(serialNum,categoryId).orElse(null);
    }

    public A_ASSET findByAssetId(String assetId,String categoryId) {
        return assetRepository.findByAssetId(assetId,categoryId).orElse(null);
    }

    public A_ASSET findDeviceByIdAndType(String categoryType,String assetId) {
        return assetRepository.findByASSETIDAndCATEGORYIDAndISDELETEIsFalse(assetId,categoryType).orElse(null);
    }
    public List<A_ASSET> findRecordsTemp(BigInteger transId) {
        return assetRepository.findByIsSave(transId);
    }
}
