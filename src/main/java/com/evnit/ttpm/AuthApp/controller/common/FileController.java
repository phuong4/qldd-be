package com.evnit.ttpm.AuthApp.controller.common;

import com.evnit.ttpm.AuthApp.annotation.CurrentUser;
import com.evnit.ttpm.AuthApp.entity.file.AF_OTHER;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.model.common.FileUploadDataDto;
import com.evnit.ttpm.AuthApp.service.common.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.spring.web.json.Json;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/file")
public class FileController {
    @Autowired
    FileService fileService;

    @PostMapping("")
    public ResponseEntity<?> uploadFile(@CurrentUser CustomUserDetails currentUser, @RequestParam("files") List<MultipartFile> files, @RequestBody Map<String, AF_OTHER> afOther) {
        if (files.isEmpty()) {
            return ResponseEntity.badRequest().body("Please select files");
        }
        try {
//            afOther.setUserId(currentUser.getUserid());
//            afOther.setCrdTime(new Date());
//            fileService.saveFiles(files, afOther);
        } catch (Exception ex) {

        }
        return ResponseEntity.ok(null);
    }


    //AnhPN
    @GetMapping("")
    @ResponseBody
    public ResponseEntity<InputStreamResource> getFile(@CurrentUser CustomUserDetails currentUser, @RequestParam("fileId") String fileId) throws IOException {
        return fileService.getFileById(fileId);
    }

    //AnhPN
    @PostMapping("/UploadBase64")
    public ResponseEntity<?> uploadFileBase64(@CurrentUser CustomUserDetails currentUser, @RequestBody FileUploadDataDto fileData) {
        if (fileData == null) {
            return ResponseEntity.badRequest().body("Please select files");
        }
        try {
            return ResponseEntity.ok(fileService.UpLoadFileBase64(fileData, currentUser.getUserid()));
        } catch (Exception ex) {
        }
        return ResponseEntity.ok(null);
    }    //AnhPN\
}
