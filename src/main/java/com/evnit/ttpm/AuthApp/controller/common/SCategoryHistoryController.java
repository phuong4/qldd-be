package com.evnit.ttpm.AuthApp.controller.common;

import com.evnit.ttpm.AuthApp.annotation.CurrentUser;
import com.evnit.ttpm.AuthApp.entity.category.SCategoryHistory;
import com.evnit.ttpm.AuthApp.model.admin.CustomUserDetails;
import com.evnit.ttpm.AuthApp.service.common.SCategoryHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/common/history")
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true", maxAge = 3600)
public class SCategoryHistoryController {
    @Autowired
    SCategoryHistoryService sCategoryHistoryService;

    @GetMapping("/{categoryType}/{categoryId}")
    public ResponseEntity<?> GetCategoryHistory(@PathVariable String categoryType,@PathVariable Integer categoryId) {
        return ResponseEntity.ok(sCategoryHistoryService.getAll(categoryType,categoryId));
    }

    @PostMapping
    public ResponseEntity<?> CreateCategoryHistory(@CurrentUser CustomUserDetails userDetails, @RequestBody SCategoryHistory sCategoryHistory) {
        sCategoryHistory.setUsername(userDetails.getUsername());
        return ResponseEntity.ok(sCategoryHistoryService.create(sCategoryHistory));
    }
}
