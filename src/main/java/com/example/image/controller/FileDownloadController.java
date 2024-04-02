package com.example.image.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.example.image.util.FileDownloadUtil;

@RestController
public class FileDownloadController {
	
	@GetMapping("/downloadFile/{fileCode}")
	public ResponseEntity<?> downloadFile(@PathVariable("fileCode") String fileCode) {
		FileDownloadUtil fileDownloadUtil = new FileDownloadUtil();
		Resource resource = fileDownloadUtil.fileDownload(fileCode);
		if (resource == null) {
			return new ResponseEntity<>("File not found", HttpStatus.NOT_FOUND);
		}
		return ResponseEntity
				.ok()
				.contentType(MediaType.parseMediaType("application/octet-stream"))
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

}
