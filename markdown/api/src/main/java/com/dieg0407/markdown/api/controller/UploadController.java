package com.dieg0407.markdown.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UploadController {

  @PostMapping("/upload")
  public ResponseEntity<Void> handleFileUpload(@RequestParam("file") MultipartFile markdownFile) {
    return ResponseEntity.ofNullable(null);
  }

}
