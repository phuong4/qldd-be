package com.evnit.ttpm.AuthApp.controller.lookup;

import com.evnit.ttpm.AuthApp.service.lookup.LookupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/api/lookup/")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true", maxAge = 3600)
public class LookupController {
    @Autowired
    LookupService lookupService;
    @GetMapping("company-tree")
    public ResponseEntity<?> GetCompanyTree(){
        return ResponseEntity.ok(lookupService.getCompaniesTree());
    }

    @GetMapping("company-tree-level12")
    public ResponseEntity<?> GetCompanyTreeLevel12(){
        return ResponseEntity.ok(lookupService.getCompaniesTreeLevel12());
    }

    @GetMapping("company")
    public ResponseEntity<?> GetCompany(){
        return ResponseEntity.ok(lookupService.getCompanies());
    }
    @GetMapping("cities")
    public ResponseEntity<?> GetCities(){
        return ResponseEntity.ok(lookupService.getCity());
    }
    @GetMapping("tba")
    public ResponseEntity<?> GetTBA(){
        return ResponseEntity.ok(lookupService.getTBA());
    }
    @GetMapping("electric-factory")
    public ResponseEntity<?> GetElectricFactory(){
        return ResponseEntity.ok(lookupService.getElectricFactory());
    }
    @GetMapping("deliveryUnit")
    public ResponseEntity<?> GetDeliveryUnit(){
        return ResponseEntity.ok(lookupService.getDeliveryUnit());
    }
    @GetMapping("deliveryUnitDD")
    public ResponseEntity<?> GetDeliveryUnitDD(){
        return ResponseEntity.ok(lookupService.getDeliveryUnitDD());
    }
    @GetMapping("tc")
    public ResponseEntity<?> GetTC(){
        return ResponseEntity.ok(lookupService.getTC());
    }
    @GetMapping("xnk")
    public ResponseEntity<?> GetXNK(){
        return ResponseEntity.ok(lookupService.getXNK());
    }
    @GetMapping("lhnmd")
    public ResponseEntity<?> GetLHNMD(){
        return ResponseEntity.ok(lookupService.getLHNMD());
    }
    @GetMapping("dtxnk")
    public ResponseEntity<?> GetDTXNK(){
        return ResponseEntity.ok(lookupService.getDTXNK());
    }
    @GetMapping("user")
    public ResponseEntity<?> GetUser(){
        return ResponseEntity.ok(lookupService.getUser());
    }
    @GetMapping("role")
    public ResponseEntity<?> GetRole(String UserId){
        return ResponseEntity.ok(lookupService.getRole(UserId));
    }
    @GetMapping("event")
    public ResponseEntity<?> GetEvent(String TbaId,String DiemDoId){
        return ResponseEntity.ok(lookupService.getEvent(TbaId,DiemDoId));
    }
    @GetMapping("dateAnalysis")
    public ResponseEntity<?> GetDateAnalysis(String TbaId,String DiemDoId,String EventId){
        return ResponseEntity.ok(lookupService.getDateAnalysis(TbaId,DiemDoId,EventId));
    }
    @GetMapping("pttsm")
    public ResponseEntity<?> GetPttsm(String TbaId, String DiemDoId, String EventId, String Time){
        return ResponseEntity.ok(lookupService.getPttsm(TbaId,DiemDoId,EventId,Time));
    }
    @GetMapping("tt")
    public ResponseEntity<?> GetTT(){
        return ResponseEntity.ok(lookupService.getTT());
    }
    @GetMapping("type/{typeParent}")
    public ResponseEntity<?> GetListType(@PathVariable("typeParent") String typeParent){
        return ResponseEntity.ok(lookupService.getListType(typeParent));
    }
    @GetMapping("accreditation/{id}")
    public ResponseEntity<?> getAccreditationByAssetId(@PathVariable String id){
        return ResponseEntity.ok(lookupService.getListAccreditationByAssetId(id));
    }
}
