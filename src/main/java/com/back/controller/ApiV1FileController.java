package com.back.controller;

import com.back.serivce.ApiV1FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/file")
public class ApiV1FileController {
    private final ApiV1FileService apiV1FileService;

    @PostMapping
    public ResponseEntity<String> uploadFile(MultipartFile multipartFile) {
        return ResponseEntity.ok(apiV1FileService.uploadFile(multipartFile));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteFile(@RequestParam String fileName) {
        System.out.println("FileName :" + fileName);
        apiV1FileService.deleteFile(fileName);
        return ResponseEntity.ok(fileName);
    }
}
