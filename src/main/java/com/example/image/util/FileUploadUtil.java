package com.example.image.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtil {
	
	public String saveFile(String fileName, MultipartFile multipartFile) {
		Path uploadPath = Paths.get("File-Upload");
		
		if (Files.notExists(uploadPath)) {
			try {
				Files.createDirectories(uploadPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		String fileCode = RandomStringUtils.randomAlphabetic(8);
		
		try (InputStream inputStream = multipartFile.getInputStream()) {
			Path filePath = uploadPath.resolve(fileCode + "-" + fileName);
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileCode;
	}

}
