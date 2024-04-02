package com.example.image.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.image.dto.FileUploadResponse;
import com.example.image.util.FileUploadUtil;

@RestController
public class FileUploadController {
	
	@PostMapping("/uploadFile")
	public ResponseEntity<FileUploadResponse> uplodaFile(@RequestParam("file") MultipartFile multipartFile) {
		System.out.println("Multipart File Name => " + multipartFile.getOriginalFilename());
		String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
		var fileUploadUtil = new FileUploadUtil();
		String fileCode = fileUploadUtil.saveFile(fileName, multipartFile);
		var response = new FileUploadResponse();
		response.setFileName(fileName);
		response.setDownloadUri("/downloadFile/" + fileCode);
		response.setSize(multipartFile.getSize());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
