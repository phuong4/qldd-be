package com.evnit.ttpm.AuthApp.mapper;

import com.evnit.ttpm.AuthApp.entity.category.A_ASSET;
import com.evnit.ttpm.AuthApp.entity.device.View_HTDD;
import com.evnit.ttpm.AuthApp.entity.device.congto.ViewCongTo;
import com.evnit.ttpm.AuthApp.entity.device.ti.ViewTIDetail;
import com.evnit.ttpm.AuthApp.entity.device.tu.ViewTU;
import com.evnit.ttpm.AuthApp.entity.device.congto.ZAG_CongTo;
import com.evnit.ttpm.AuthApp.entity.device.tu.ViewTUDetail;
import com.evnit.ttpm.AuthApp.entity.device.tu.ZAG_TU;
import com.evnit.ttpm.AuthApp.model.category.WarningDevice.WarningDeviceListDto;
import com.evnit.ttpm.AuthApp.model.category.nhamaydien.NhaMayDienListDto;
import com.evnit.ttpm.AuthApp.model.device.common.DeviceCrudDto;
import com.evnit.ttpm.AuthApp.model.device.common.ViewHTDDDto;
import com.evnit.ttpm.AuthApp.model.device.congto.CongToCrudDto;
import com.evnit.ttpm.AuthApp.model.device.congto.DeviceCongToListDto;
import com.evnit.ttpm.AuthApp.model.device.cuon.CuonCreateDto;
import com.evnit.ttpm.AuthApp.model.device.diemdo.DiemDoCrudDto;
import com.evnit.ttpm.AuthApp.model.device.ti.DeviceTIDetailDto;
import com.evnit.ttpm.AuthApp.model.device.ti.SetTICreateDto;
import com.evnit.ttpm.AuthApp.model.device.ti.TICreateDto;
import com.evnit.ttpm.AuthApp.model.device.tu.*;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.builder.ConfigurableConditionExpression;
import org.modelmapper.convention.MatchingStrategies;
import org.modelmapper.spi.MappingContext;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DeviceMapper {
    private final ModelMapper mapper;

    public DeviceMapper() {
        this.mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        configureMappings();
    }

    private void configureMappings() {
        mapper.createTypeMap(ViewCongTo.class, DeviceCongToListDto.class)
                .addMappings(this::configureCongTo);
        mapper.createTypeMap(ViewTU.class, DeviceTuDetailDto.class);
        mapper.createTypeMap(ZAG_CongTo.class, DeviceCongToListDto.class);
        mapper.createTypeMap(DiemDoCrudDto.class, DeviceCrudDto.class);
        mapper.createTypeMap(ZAGTU.class, ZAG_TU.class);
        mapper.createTypeMap(SetTUCreateDto.class, A_ASSET.class).addMappings(this::configureSetTuCrud);
        mapper.createTypeMap(SetTICreateDto.class, A_ASSET.class).addMappings(this::configureSetTiCrud);
        mapper.createTypeMap(CongToCrudDto.class, A_ASSET.class).addMappings(this::configureCongToCrud);

        mapper.createTypeMap(DeviceCrudDto.class, A_ASSET.class).addMappings(this::configureDeviceCrud);
        mapper.createTypeMap(A_ASSET.class, SetTUDetailDto.class).addMappings(this::configureSetTU);
        mapper.createTypeMap(TUCreateDto.class, A_ASSET.class).addMappings(this::configureTUCreate);
        mapper.createTypeMap(TICreateDto.class, A_ASSET.class).addMappings(this::configureTICreate);
        mapper.createTypeMap(CuonCreateDto.class, A_ASSET.class).addMappings(this::configureCuonCreate);
        mapper.createTypeMap(View_HTDD.class, ViewHTDDDto.class).addMappings(
                mapper -> mapper.using(statusConverterStr).map(View_HTDD::getStatusDiemDo, ViewHTDDDto::setStatusDiemDoStr)
        );
        mapper.createTypeMap(ViewTUDetail.class, DeviceTuDetailDto.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getSCategoryDonViSHByOwnership().getName(), DeviceTuDetailDto::setOwnershipUnitStr);
                    mapper.using(statusNMDConverterStr).map(src -> src.getPowerSystemStatus(), DeviceTuDetailDto::setPowerSystemStatusStr);
                    mapper.using(statusConverter).map(src -> src.getStatusTU(), DeviceTuDetailDto::setStatusTUStr);
                });
        mapper.createTypeMap(ViewTIDetail.class, DeviceTIDetailDto.class)
                .addMappings(mapper -> {
                    mapper.map(src -> src.getSCategoryDonViSHByOwnership().getName(), DeviceTIDetailDto::setOwnershipUnitStr);
                    mapper.using(statusNMDConverterStr).map(src -> src.getPowerSystemStatus(), DeviceTIDetailDto::setPowerSystemStatusStr);
                    mapper.using(statusConverter).map(src -> src.getStatusTU(), DeviceTIDetailDto::setStatusTUStr);
                });

        //.addMappings(mapper->mapper.map(src -> src.getSListAllById().getListdesc(),DeviceTuDetailDto::setPManufacturerIdStr))

    }

    public ModelMapper getModelMapper() {
        return this.mapper;
    }

    private final Converter<String, String> statusNMDConverterStr = new Converter<String, String>() {
        @Override
        public String convert(MappingContext<String, String> context) {

            String status = context.getSource();
            if (status != null) {
                switch (status) {
                    case "1":
                        return "Đang vận hành";
                    case "2":
                        return "Chưa vận hành";
                    case "0":
                        return "Huỷ";
                }
            }
            return "";
        }
    };
    private final Converter<String, String> statusConverterStr = new Converter<String, String>() {
        @Override
        public String convert(MappingContext<String, String> context) {

            String status = context.getSource();
            if (status != null) {
                switch (status) {
                    case "2":
                        return "Đã nghiệm thu";
                    case "1":
                        return "Chưa nghiệm thu";
                    case "3":
                        return "Kiểm tra lắp đặt";
                    case "4":
                        return "Đã nghiệm thu";
                    case "0":
                        return "Huỷ";
                }
            }
            return "";
        }
    };
    private final Converter<Integer, String> statusConverter = new Converter<Integer, String>() {
        @Override
        public String convert(MappingContext<Integer, String> context) {

            Integer status = context.getSource();
            if (status != null) {
                switch (status) {
                    case 2:
                        return "Đã nghiệm thu";
                    case 1:
                        return "Chưa nghiệm thu";
                    case 3:
                        return "Kiểm tra lắp đặt";
                    case 4:
                        return "Đã nghiệm thu";
                    case 0:
                        return "Huỷ";
                }
            }
            return "";
        }
    };
    private final Converter<Double, String> cycleConvert = new Converter<Double, String>() {
        @Override
        public String convert(MappingContext<Double, String> context) {

            Double cycle = context.getSource();
            if (cycle != null) {
                if (cycle == 1) {
                    return "1 năm";
                } else if (cycle == 3) {
                    return "3 năm";
                } else if (cycle == 5) {
                    return "5 năm";
                }
            }
            return "";
        }
    };

    private void configureCongTo(ConfigurableConditionExpression<ViewCongTo, DeviceCongToListDto> mapper) {
        mapper.map(ViewCongTo::getASSETID, DeviceCongToListDto::setAssetId);
        mapper.map(ViewCongTo::getASSETROOT, DeviceCongToListDto::setAssetRoot);
        mapper.map(ViewCongTo::getCATEGORYID, DeviceCongToListDto::setCategoryId);
        mapper.map(ViewCongTo::getASSETDESC, DeviceCongToListDto::setAssetDesc);
        mapper.map(ViewCongTo::getASSETID_PARENT, DeviceCongToListDto::setAssetIdParent);
        mapper.map(ViewCongTo::getASSETID_LINK, DeviceCongToListDto::setAssetIdLink);
        mapper.map(ViewCongTo::getASSETNOTE, DeviceCongToListDto::setAssetNote);
        mapper.map(ViewCongTo::getASSETORD, DeviceCongToListDto::setAssetORD);
        mapper.map(ViewCongTo::getDATEACCREDITATION, DeviceCongToListDto::setDateAccreditation);
        mapper.map(ViewCongTo::getDATEOPERATION, DeviceCongToListDto::setDateOperation);
        mapper.map(ViewCongTo::getP_INSTALLDATE, DeviceCongToListDto::setPInstallDate);
        mapper.map(ViewCongTo::getINSPECTION_STAMPS, DeviceCongToListDto::setInspectionStamps);
        mapper.map(ViewCongTo::getNATIONALFACT, DeviceCongToListDto::setNationalFact);
        mapper.map(ViewCongTo::getP_MANUFACTURERID, DeviceCongToListDto::setPManufacturerId);
        mapper.map(ViewCongTo::getLTrinh, DeviceCongToListDto::setLapTrinh);
        mapper.map(ViewCongTo::getTYPEID, DeviceCongToListDto::setTypeId);
        mapper.map(ViewCongTo::getORGID, DeviceCongToListDto::setOrgId);
        mapper.map(ViewCongTo::getSERIALNUM, DeviceCongToListDto::setSerialNum);
        mapper.map(ViewCongTo::getULEVELID, DeviceCongToListDto::setULevelId);
        mapper.map(ViewCongTo::getUSER_CR_DTIME, DeviceCongToListDto::setUserCRDTime);
        mapper.map(ViewCongTo::getUSER_CR_ID, DeviceCongToListDto::setUserCRId);
        mapper.map(ViewCongTo::getUSER_MDF_DTIME, DeviceCongToListDto::setUserMDFDTime);
        mapper.map(ViewCongTo::getUSER_MDF_ID, DeviceCongToListDto::setUserMDFId);
        mapper.map(ViewCongTo::getUSESTATUS_LAST_DTIME, DeviceCongToListDto::setUseStatusLastDTime);
        mapper.map(ViewCongTo::getUSESTATUS_LAST_ID, DeviceCongToListDto::setUseStatusLastId);
        mapper.map(ViewCongTo::getORGIDSTR, DeviceCongToListDto::setOrgIdStr);
        mapper.map(ViewCongTo::getTYPEIDSTR, DeviceCongToListDto::setTypeStr);
        mapper.map(ViewCongTo::getCYCLEACCREDITATION, DeviceCongToListDto::setCycleAccreditation);
        mapper.map(ViewCongTo::getPOWERID, DeviceCongToListDto::setPowerId);
        mapper.map(ViewCongTo::getPOWERNAME, DeviceCongToListDto::setPowerName);
        mapper.map(ViewCongTo::getDIEMDOSTATUS, DeviceCongToListDto::setDiemDoStatus);
        mapper.map(ViewCongTo::getDIEMDONAME, DeviceCongToListDto::setDiemDoName);
        mapper.map(ViewCongTo::getXNK, DeviceCongToListDto::setXnk);
        mapper.map(ViewCongTo::getAttrDataId, DeviceCongToListDto::setAttrDataId);
        mapper.map(ViewCongTo::getDATEACCREDITATIONNEXT, DeviceCongToListDto::setDateAccreditationNext);
        mapper.using(statusConverterStr)
                .map(ViewCongTo::getDIEMDOSTATUS, DeviceCongToListDto::setParentStatusStr);
        mapper.using(statusConverterStr)
                .map(ViewCongTo::getDIEMDOSTATUS, DeviceCongToListDto::setUseStatusLastName);
        mapper.using(statusNMDConverterStr)
                .map(ViewCongTo::getTRANGTHAINMD, DeviceCongToListDto::setTrangThaiNMD);
        mapper.using(cycleConvert).map(ViewCongTo::getCYCLEACCREDITATION, DeviceCongToListDto::setCycleAccreditationStr);

    }

    private void configureSetTU(ConfigurableConditionExpression<A_ASSET, SetTUDetailDto> mapper) {
        mapper.map(A_ASSET::getASSETID, SetTUDetailDto::setAssetId);
        mapper.map(A_ASSET::getCATEGORYID, SetTUDetailDto::setCategoryId);
        mapper.map(A_ASSET::getASSETDESC, SetTUDetailDto::setAssetDesc);
        mapper.map(A_ASSET::getASSETID_PARENT, SetTUDetailDto::setAssetIdParent);
        mapper.map(A_ASSET::getORGID, SetTUDetailDto::setOrgId);
    }

    private void configureDeviceCrud(ConfigurableConditionExpression<DeviceCrudDto, A_ASSET> mapper) {
        mapper.map(DeviceCrudDto::getAssetId, A_ASSET::setASSETID);
        mapper.map(DeviceCrudDto::getCategoryId, A_ASSET::setCATEGORYID);
        mapper.map(DeviceCrudDto::getAssetNote, A_ASSET::setASSETNOTE);
        mapper.map(DeviceCrudDto::getAssetIdParent, A_ASSET::setASSETID_PARENT);
        mapper.map(DeviceCrudDto::getAssetIdLink, A_ASSET::setASSETID_LINK);
        mapper.map(DeviceCrudDto::getAssetDesc, A_ASSET::setASSETDESC);
        mapper.map(DeviceCrudDto::getAssetORD, A_ASSET::setASSETORD);
        mapper.map(DeviceCrudDto::getDateAccreditation, A_ASSET::setDATEACCREDITATION);
        mapper.map(DeviceCrudDto::getDateOperation, A_ASSET::setDATEOPERATION);
        mapper.map(DeviceCrudDto::getPInstallDate, A_ASSET::setP_INSTALLDATE);
        mapper.map(DeviceCrudDto::getInspectionStamps, A_ASSET::setINSPECTION_STAMPS);
        mapper.map(DeviceCrudDto::getNationalFact, A_ASSET::setNATIONALFACT);
        mapper.map(DeviceCrudDto::getPManufacturerId, A_ASSET::setP_MANUFACTURERID);
        mapper.map(DeviceCrudDto::getTypeId, A_ASSET::setTYPEID);
        mapper.map(DeviceCrudDto::getOrgId, A_ASSET::setORGID);
        mapper.map(DeviceCrudDto::getSerialNum, A_ASSET::setSERIALNUM);
        mapper.map(DeviceCrudDto::getULevelId, A_ASSET::setULEVELID);
        mapper.map(DeviceCrudDto::getUserCRDTime, A_ASSET::setUSER_CR_DTIME);
        mapper.map(DeviceCrudDto::getUserCRId, A_ASSET::setUSER_CR_ID);
        mapper.map(DeviceCrudDto::getUserMDFDTime, A_ASSET::setUSER_MDF_DTIME);
        mapper.map(DeviceCrudDto::getUserMDFId, A_ASSET::setUSER_MDF_ID);
        mapper.map(DeviceCrudDto::getUseStatusLastDTime, A_ASSET::setUSESTATUS_LAST_DTIME);
        mapper.map(DeviceCrudDto::getUseStatusLastId, A_ASSET::setUSESTATUS_LAST_ID);
        mapper.map(DeviceCrudDto::getCycleAccreditation, A_ASSET::setCYCLEACCREDITATION);
        mapper.map(DeviceCrudDto::getIsSave, A_ASSET::setISSAVE);
        mapper.map(DeviceCrudDto::getTransId, A_ASSET::setTransId);
        mapper.map(DeviceCrudDto::getAssetRoot, A_ASSET::setASSETROOT);
    }

    private void configureCongToCrud(ConfigurableConditionExpression<CongToCrudDto, A_ASSET> mapper) {
        mapper.map(CongToCrudDto::getAssetId, A_ASSET::setASSETID);
        mapper.map(CongToCrudDto::getCategoryId, A_ASSET::setCATEGORYID);
        mapper.map(CongToCrudDto::getAssetNote, A_ASSET::setASSETNOTE);
        mapper.map(CongToCrudDto::getAssetIdParent, A_ASSET::setASSETID_PARENT);
        mapper.map(CongToCrudDto::getAssetIdLink, A_ASSET::setASSETID_LINK);
        mapper.map(CongToCrudDto::getAssetDesc, A_ASSET::setASSETDESC);
        mapper.map(CongToCrudDto::getAssetORD, A_ASSET::setASSETORD);
        mapper.map(CongToCrudDto::getDateAccreditation, A_ASSET::setDATEACCREDITATION);
        mapper.map(CongToCrudDto::getDateOperation, A_ASSET::setDATEOPERATION);
        mapper.map(CongToCrudDto::getpInstallDate, A_ASSET::setP_INSTALLDATE);
        mapper.map(CongToCrudDto::getInspectionStamps, A_ASSET::setINSPECTION_STAMPS);
        mapper.map(CongToCrudDto::getNationalFact, A_ASSET::setNATIONALFACT);
        mapper.map(CongToCrudDto::getpManufacturerId, A_ASSET::setP_MANUFACTURERID);
        mapper.map(CongToCrudDto::getTypeId, A_ASSET::setTYPEID);
        mapper.map(CongToCrudDto::getOrgId, A_ASSET::setORGID);
        mapper.map(CongToCrudDto::getSerialNum, A_ASSET::setSERIALNUM);
        mapper.map(CongToCrudDto::getULevelId, A_ASSET::setULEVELID);
        mapper.map(CongToCrudDto::getUserCRDTime, A_ASSET::setUSER_CR_DTIME);
        mapper.map(CongToCrudDto::getUserCRId, A_ASSET::setUSER_CR_ID);
        mapper.map(CongToCrudDto::getUserMDFDTime, A_ASSET::setUSER_MDF_DTIME);
        mapper.map(CongToCrudDto::getUserMDFId, A_ASSET::setUSER_MDF_ID);
        mapper.map(CongToCrudDto::getUseStatusLastDTime, A_ASSET::setUSESTATUS_LAST_DTIME);
        mapper.map(CongToCrudDto::getUseStatusLastId, A_ASSET::setUSESTATUS_LAST_ID);
        mapper.map(CongToCrudDto::getCycleAccreditation, A_ASSET::setCYCLEACCREDITATION);
        mapper.map(CongToCrudDto::getIsSave, A_ASSET::setISSAVE);
        mapper.map(CongToCrudDto::getAssetRoot, A_ASSET::setASSETROOT);
        mapper.map(CongToCrudDto::getTransId, A_ASSET::setTransId);
    }

    private void configureTUCreate(ConfigurableConditionExpression<TUCreateDto, A_ASSET> mapper) {
        mapper.map(TUCreateDto::getAssetId, A_ASSET::setASSETID);
        mapper.map(TUCreateDto::getCategoryId, A_ASSET::setCATEGORYID);
        mapper.map(TUCreateDto::getAssetNote, A_ASSET::setASSETNOTE);
        mapper.map(TUCreateDto::getAssetIdParent, A_ASSET::setASSETID_PARENT);
        mapper.map(TUCreateDto::getAssetIdLink, A_ASSET::setASSETID_LINK);
        mapper.map(TUCreateDto::getAssetDesc, A_ASSET::setASSETDESC);
        mapper.map(TUCreateDto::getAssetORD, A_ASSET::setASSETORD);
        mapper.map(TUCreateDto::getDateAccreditation, A_ASSET::setDATEACCREDITATION);
        mapper.map(TUCreateDto::getDateOperation, A_ASSET::setDATEOPERATION);
        mapper.map(TUCreateDto::getpInstallDate, A_ASSET::setP_INSTALLDATE);
        mapper.map(TUCreateDto::getInspectionStamps, A_ASSET::setINSPECTION_STAMPS);
        mapper.map(TUCreateDto::getNationalFact, A_ASSET::setNATIONALFACT);
        mapper.map(TUCreateDto::getpManufacturerId, A_ASSET::setP_MANUFACTURERID);
        mapper.map(TUCreateDto::getTypeId, A_ASSET::setTYPEID);
        mapper.map(TUCreateDto::getOrgId, A_ASSET::setORGID);
        mapper.map(TUCreateDto::getSerialNum, A_ASSET::setSERIALNUM);
        mapper.map(TUCreateDto::getuLevelId, A_ASSET::setULEVELID);
        mapper.map(TUCreateDto::getUserCRDTime, A_ASSET::setUSER_CR_DTIME);
        mapper.map(TUCreateDto::getUserCRId, A_ASSET::setUSER_CR_ID);
        mapper.map(TUCreateDto::getUserMDFDTime, A_ASSET::setUSER_MDF_DTIME);
        mapper.map(TUCreateDto::getUserMDFId, A_ASSET::setUSER_MDF_ID);
        mapper.map(TUCreateDto::getUseStatusLastDTime, A_ASSET::setUSESTATUS_LAST_DTIME);
        mapper.map(TUCreateDto::getUseStatusLastId, A_ASSET::setUSESTATUS_LAST_ID);
        mapper.map(TUCreateDto::getCycleAccreditation, A_ASSET::setCYCLEACCREDITATION);
        mapper.map(TUCreateDto::getAssetRoot, A_ASSET::setASSETROOT);
        mapper.map(TUCreateDto::getIsSave, A_ASSET::setISSAVE);
        mapper.map(TUCreateDto::getTransId, A_ASSET::setTransId);
        mapper.map(TUCreateDto::getSetType, A_ASSET::setSetType);
    }

    private void configureTICreate(ConfigurableConditionExpression<TICreateDto, A_ASSET> mapper) {
        mapper.map(TICreateDto::getAssetId, A_ASSET::setASSETID);
        mapper.map(TICreateDto::getSetType, A_ASSET::setSetType);
        mapper.map(TICreateDto::getAssetRoot, A_ASSET::setASSETROOT);
        mapper.map(TICreateDto::getCategoryId, A_ASSET::setCATEGORYID);
        mapper.map(TICreateDto::getAssetNote, A_ASSET::setASSETNOTE);
        mapper.map(TICreateDto::getAssetIdParent, A_ASSET::setASSETID_PARENT);
        mapper.map(TICreateDto::getAssetIdLink, A_ASSET::setASSETID_LINK);
        mapper.map(TICreateDto::getAssetDesc, A_ASSET::setASSETDESC);
        mapper.map(TICreateDto::getAssetORD, A_ASSET::setASSETORD);
        mapper.map(TICreateDto::getDateAccreditation, A_ASSET::setDATEACCREDITATION);
        mapper.map(TICreateDto::getDateOperation, A_ASSET::setDATEOPERATION);
        mapper.map(TICreateDto::getpInstallDate, A_ASSET::setP_INSTALLDATE);
        mapper.map(TICreateDto::getInspectionStamps, A_ASSET::setINSPECTION_STAMPS);
        mapper.map(TICreateDto::getNationalFact, A_ASSET::setNATIONALFACT);
        mapper.map(TICreateDto::getpManufacturerId, A_ASSET::setP_MANUFACTURERID);
        mapper.map(TICreateDto::getTypeId, A_ASSET::setTYPEID);
        mapper.map(TICreateDto::getOrgId, A_ASSET::setORGID);
        mapper.map(TICreateDto::getSerialNum, A_ASSET::setSERIALNUM);
        mapper.map(TICreateDto::getuLevelId, A_ASSET::setULEVELID);
        mapper.map(TICreateDto::getUserCRDTime, A_ASSET::setUSER_CR_DTIME);
        mapper.map(TICreateDto::getUserCRId, A_ASSET::setUSER_CR_ID);
        mapper.map(TICreateDto::getUserMDFDTime, A_ASSET::setUSER_MDF_DTIME);
        mapper.map(TICreateDto::getUserMDFId, A_ASSET::setUSER_MDF_ID);
        mapper.map(TICreateDto::getUseStatusLastDTime, A_ASSET::setUSESTATUS_LAST_DTIME);
        mapper.map(TICreateDto::getUseStatusLastId, A_ASSET::setUSESTATUS_LAST_ID);
        mapper.map(TICreateDto::getCycleAccreditation, A_ASSET::setCYCLEACCREDITATION);
        mapper.map(TICreateDto::getIsSave, A_ASSET::setISSAVE);
        mapper.map(TICreateDto::getTransId, A_ASSET::setTransId);
    }

    private void configureCuonCreate(ConfigurableConditionExpression<CuonCreateDto, A_ASSET> mapper) {
        mapper.map(CuonCreateDto::getAssetId, A_ASSET::setASSETID);
        mapper.map(CuonCreateDto::getCategoryId, A_ASSET::setCATEGORYID);
        mapper.map(CuonCreateDto::getAssetNote, A_ASSET::setASSETNOTE);
        mapper.map(CuonCreateDto::getAssetIdParent, A_ASSET::setASSETID_PARENT);
        mapper.map(CuonCreateDto::getAssetIdLink, A_ASSET::setASSETID_LINK);
        mapper.map(CuonCreateDto::getAssetDesc, A_ASSET::setASSETDESC);
        mapper.map(CuonCreateDto::getAssetORD, A_ASSET::setASSETORD);
        mapper.map(CuonCreateDto::getDateAccreditation, A_ASSET::setDATEACCREDITATION);
        mapper.map(CuonCreateDto::getDateOperation, A_ASSET::setDATEOPERATION);
        mapper.map(CuonCreateDto::getPInstallDate, A_ASSET::setP_INSTALLDATE);
        mapper.map(CuonCreateDto::getInspectionStamps, A_ASSET::setINSPECTION_STAMPS);
        mapper.map(CuonCreateDto::getTypeId, A_ASSET::setTYPEID);
        mapper.map(CuonCreateDto::getOrgId, A_ASSET::setORGID);
        mapper.map(CuonCreateDto::getULevelId, A_ASSET::setULEVELID);
        mapper.map(CuonCreateDto::getUserCRDTime, A_ASSET::setUSER_CR_DTIME);
        mapper.map(CuonCreateDto::getUserCRId, A_ASSET::setUSER_CR_ID);
        mapper.map(CuonCreateDto::getUserMDFDTime, A_ASSET::setUSER_MDF_DTIME);
        mapper.map(CuonCreateDto::getUserMDFId, A_ASSET::setUSER_MDF_ID);
        mapper.map(CuonCreateDto::getUseStatusLastDTime, A_ASSET::setUSESTATUS_LAST_DTIME);
        mapper.map(CuonCreateDto::getUseStatusLastId, A_ASSET::setUSESTATUS_LAST_ID);
        mapper.map(CuonCreateDto::getCycleAccreditation, A_ASSET::setCYCLEACCREDITATION);
        mapper.map(CuonCreateDto::getIsSave, A_ASSET::setISSAVE);
        mapper.map(CuonCreateDto::getTransId, A_ASSET::setTransId);
    }

    private void configureSetTuCrud(ConfigurableConditionExpression<SetTUCreateDto, A_ASSET> mapper) {
        mapper.map(SetTUCreateDto::getAssetId, A_ASSET::setASSETID);
        mapper.map(SetTUCreateDto::getCategoryId, A_ASSET::setCATEGORYID);
        mapper.map(SetTUCreateDto::getAssetNote, A_ASSET::setASSETNOTE);
        mapper.map(SetTUCreateDto::getAssetIdParent, A_ASSET::setASSETID_PARENT);
        mapper.map(SetTUCreateDto::getAssetIdLink, A_ASSET::setASSETID_LINK);
        mapper.map(SetTUCreateDto::getAssetDesc, A_ASSET::setASSETDESC);
        mapper.map(SetTUCreateDto::getAssetORD, A_ASSET::setASSETORD);
        mapper.map(SetTUCreateDto::getDateAccreditation, A_ASSET::setDATEACCREDITATION);
        mapper.map(SetTUCreateDto::getDateOperation, A_ASSET::setDATEOPERATION);
        mapper.map(SetTUCreateDto::getPInstallDate, A_ASSET::setP_INSTALLDATE);
        //mapper.map(SetTUCreateDto::getInspectionStamps, A_ASSET::setINSPECTION_STAMPS);
        mapper.map(SetTUCreateDto::getTypeId, A_ASSET::setTYPEID);
        mapper.map(SetTUCreateDto::getOrgId, A_ASSET::setORGID);
        mapper.map(SetTUCreateDto::getULevelId, A_ASSET::setULEVELID);
        mapper.map(SetTUCreateDto::getUserCRDTime, A_ASSET::setUSER_CR_DTIME);
        mapper.map(SetTUCreateDto::getUserCRId, A_ASSET::setUSER_CR_ID);
        mapper.map(SetTUCreateDto::getUserMDFDTime, A_ASSET::setUSER_MDF_DTIME);
        mapper.map(SetTUCreateDto::getUserMDFId, A_ASSET::setUSER_MDF_ID);
        mapper.map(SetTUCreateDto::getUseStatusLastDTime, A_ASSET::setUSESTATUS_LAST_DTIME);
        mapper.map(SetTUCreateDto::getUseStatusLastId, A_ASSET::setUSESTATUS_LAST_ID);
        //mapper.map(SetTUCreateDto::getCycleAccreditation, A_ASSET::setCYCLEACCREDITATION);
        mapper.map(SetTUCreateDto::getIsSave, A_ASSET::setISSAVE);
        mapper.map(SetTUCreateDto::getTransId, A_ASSET::setTransId);
    }

    private void configureSetTiCrud(ConfigurableConditionExpression<SetTICreateDto, A_ASSET> mapper) {
        mapper.map(SetTICreateDto::getAssetId, A_ASSET::setASSETID);
        mapper.map(SetTICreateDto::getCategoryId, A_ASSET::setCATEGORYID);
        mapper.map(SetTICreateDto::getAssetNote, A_ASSET::setASSETNOTE);
        mapper.map(SetTICreateDto::getAssetIdParent, A_ASSET::setASSETID_PARENT);
        mapper.map(SetTICreateDto::getAssetIdLink, A_ASSET::setASSETID_LINK);
        mapper.map(SetTICreateDto::getAssetDesc, A_ASSET::setASSETDESC);
        mapper.map(SetTICreateDto::getAssetORD, A_ASSET::setASSETORD);
        mapper.map(SetTICreateDto::getDateAccreditation, A_ASSET::setDATEACCREDITATION);
        mapper.map(SetTICreateDto::getDateOperation, A_ASSET::setDATEOPERATION);
        mapper.map(SetTICreateDto::getPInstallDate, A_ASSET::setP_INSTALLDATE);
        mapper.map(SetTICreateDto::getInspectionStamps, A_ASSET::setINSPECTION_STAMPS);
        mapper.map(SetTICreateDto::getTypeId, A_ASSET::setTYPEID);
        mapper.map(SetTICreateDto::getOrgId, A_ASSET::setORGID);
        mapper.map(SetTICreateDto::getULevelId, A_ASSET::setULEVELID);
        mapper.map(SetTICreateDto::getUserCRDTime, A_ASSET::setUSER_CR_DTIME);
        mapper.map(SetTICreateDto::getUserCRId, A_ASSET::setUSER_CR_ID);
        mapper.map(SetTICreateDto::getUserMDFDTime, A_ASSET::setUSER_MDF_DTIME);
        mapper.map(SetTICreateDto::getUserMDFId, A_ASSET::setUSER_MDF_ID);
        mapper.map(SetTICreateDto::getUseStatusLastDTime, A_ASSET::setUSESTATUS_LAST_DTIME);
        mapper.map(SetTICreateDto::getUseStatusLastId, A_ASSET::setUSESTATUS_LAST_ID);
        mapper.map(SetTICreateDto::getCycleAccreditation, A_ASSET::setCYCLEACCREDITATION);
        mapper.map(SetTICreateDto::getIsSave, A_ASSET::setISSAVE);
        mapper.map(SetTICreateDto::getTransId, A_ASSET::setTransId);
    }
}

