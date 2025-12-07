package com.back.controller;

import com.back.serivce.ApiV2FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v2/file")
public class ApiV2FileController {
    private final ApiV2FileService fileService;

    @GetMapping
    public ResponseEntity<Map<String, String>> getPreSignedUrl(
            @RequestParam(name = "fileName")
            String fileName
    ) {
        return ResponseEntity.ok(fileService.getPreSignedUrl("images", fileName));
    }
}
