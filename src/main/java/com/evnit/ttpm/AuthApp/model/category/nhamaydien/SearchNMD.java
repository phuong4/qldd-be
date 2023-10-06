package com.evnit.ttpm.AuthApp.model.category.nhamaydien;

import com.evnit.ttpm.AuthApp.model.payload.PagingParam;

import java.util.List;


public class SearchNMD extends PagingParam {
    private List<String> typeNM;
    public List<String> getTypeNM(){return typeNM;}
    public  void setTypeNM(List<String> typeNM){this.typeNM=typeNM;}

    private String tenNM;
    public String getTenNM(){return tenNM;}
    public  void setTenNM(String tenNM){this.tenNM=tenNM;}

    private String maNM;
    public String getMaNM(){return maNM;}
    public  void setMaNM(String maNM){this.maNM=maNM;}

    private String cityName;
    public String getCityName(){return  cityName;}
    public  void setCityName(String cityName){this.cityName=cityName;}

    private Integer city;
    public Integer getCity(){return  city;}
    public  void setCity(Integer city){this.city=city;}

    private List<Integer> regions;
    public List<Integer> getRegions() {
        return regions;
    }
    public void setRegions(List<Integer> regions) {
        this.regions = regions;
    }

    private Integer ownershipUnit;
    public Integer getOwnershipUnit() {
        return ownershipUnit;
    }
    public void setOwnershipUnit(Integer ownershipUnit) {
        this.ownershipUnit = ownershipUnit;
    }

    private List<Integer> status;
    public List<Integer> getStatus() {
        return status;
    }
    public void setStatus(List<Integer> status) {
        this.status = status;
    }

    private Integer congSuatMW;
    public Integer getCongSuatMW() {
        return congSuatMW;
    }
    public void setCongSuatMW(Integer congSuatMW) {
        this.congSuatMW = congSuatMW;
    }

    private Integer congSuatMwp;
    public Integer getCongSuatMwp() {
        return congSuatMwp;
    }
    public void setCongSuatMwp(Integer congSuatMwp) {
        this.congSuatMwp = congSuatMwp;
    }

    private Integer congSuatMaxMW;
    public Integer getCongSuatMaxMW() {
        return congSuatMaxMW;
    }
    public void setCongSuatMaxMW(Integer congSuatMaxMW) {
        this.congSuatMaxMW = congSuatMaxMW;
    }
}
