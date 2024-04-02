package com.example.image.util;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

public class FileDownloadUtil {
	
	private Path foundFile;

	public Resource fileDownload(String fileCode) {
		Path downloadPath = Paths.get("File-Upload");
		System.out.println("************" + Files.isDirectory(downloadPath));
		if (Files.isDirectory(downloadPath)) {
			try {
				Files.list(downloadPath).forEach(file -> {
					if (file.getFileName().toString().startsWith(fileCode)) {
						foundFile = file;
						return;
					}
				});
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (foundFile != null) {
			try {
				return new UrlResource(foundFile.toUri());
			} catch (MalformedURLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
}
