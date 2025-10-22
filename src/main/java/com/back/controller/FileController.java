package com.back.controller;

import com.back.serivce.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/file")
public class FileController {
    private final FileService fileService;

    @PostMapping
    public ResponseEntity<String> uploadFile(MultipartFile multipartFile) {
        return ResponseEntity.ok(fileService.uploadFile(multipartFile));
    }
}
