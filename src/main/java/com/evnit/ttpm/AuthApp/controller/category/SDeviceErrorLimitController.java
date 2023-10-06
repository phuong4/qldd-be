package com.evnit.ttpm.AuthApp.controller.category;

import com.evnit.ttpm.AuthApp.entity.category.*;
import com.evnit.ttpm.AuthApp.model.category.DeviceErrorLimit.*;
import com.evnit.ttpm.AuthApp.service.category.SCategoryDeviceErrorLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category/deviceErrorLimit/")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true", maxAge = 3600)
public class SDeviceErrorLimitController {
    @Autowired
    SCategoryDeviceErrorLimitService service;

    //    @GetMapping("congToMB/{exactLevel}")
//    public ResponseEntity<?> GetDeviceErrorCTMB(@PathVariable("exactLevel")Integer exactLevel){
//        return ResponseEntity.ok(service.getCongToMB(exactLevel));
//    }
    @GetMapping("congTo")
    public ResponseEntity<?> getCTErrorLimit() {
        return ResponseEntity.ok(service.getCongToErrorLimit());
    }

    @PostMapping("congTo")
    public ResponseEntity<?> updateCongToErrorLimit(@RequestBody List<CongToErrorLimit> data) {
        return ResponseEntity.ok(service.updateCongToErrorLimit(data));
    }

    //    @GetMapping("congToMT/{exactLevel}")
//    public ResponseEntity<?> GetDeviceErrorCTMT(@PathVariable("exactLevel")Integer exactLevel){
//        return ResponseEntity.ok(service.getCongToMT(exactLevel));
//    }
//    @GetMapping("congToMN/{exactLevel}")
//    public ResponseEntity<?> GetDeviceErrorCTMN(@PathVariable("exactLevel")Integer exactLevel){
//        return ResponseEntity.ok(service.getCongToMN(exactLevel));
//    }
    @GetMapping("deviceTI")
    public ResponseEntity<?> GetDeviceTI() {
        return ResponseEntity.ok(service.getDeviceTI());
    }

    @GetMapping("deviceTU")
    public ResponseEntity<?> GetDeviceErrorCTMB() {
        return ResponseEntity.ok(service.getDeviceTU());
    }

    //    @PostMapping("congToMB/{exactLevel}")
//    public ResponseEntity<?> CreateCongToMB(@PathVariable("exactLevel")Integer exactLevel, @RequestBody List<CongToErrorLimitMB> data){
//        return ResponseEntity.ok(service.updateCongToMB(exactLevel,data));
//    }
//    @PostMapping("congToMT/{exactLevel}")
//    public ResponseEntity<?> CreateCongToMT(@PathVariable("exactLevel")Integer exactLevel, @RequestBody List<CongToErrorLimitMT> data){
//        return ResponseEntity.ok(service.updateCongToMT(exactLevel,data));
//    }
//    @PostMapping("congToMN/{exactLevel}")
//    public ResponseEntity<?> CreateCongToMN(@PathVariable("exactLevel")Integer exactLevel, @RequestBody List<CongToErrorLimitMN> data){
//
//        return ResponseEntity.ok(service.updateCongToMN(exactLevel,data));
//    }
    @PostMapping("deviceTI")
    public ResponseEntity<?> CreateDeviceTI(@RequestBody List<TIErrorLimit> data) {
        return ResponseEntity.ok(service.updateTI(data));
    }

    @PostMapping("deviceTU")
    public ResponseEntity<?> CreateDeviceTU(@RequestBody List<TUErrorLimit> data) {

        return ResponseEntity.ok(service.updateTU(data));
    }

}
