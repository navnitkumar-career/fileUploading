package com.file.impl;

import io.github.pixee.security.Filenames;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.file.services.FileService;

@Service
public class FileServiceIplementation implements FileService {

	@Override
	public String uploadImage(String path, MultipartFile multipartFile) {
		// TODO Auto-generated method stub
		String name = Filenames.toSimpleFileName(multipartFile.getOriginalFilename());
		String randomID = UUID.randomUUID().toString();
		String newFileName = randomID.concat(name.substring(name.lastIndexOf(".")));

		String filePath = path + File.separator + newFileName;

		File file = new File(path);
		if (!file.exists()) {
			file.mkdir();
		}
		try {
			Files.copy(multipartFile.getInputStream(), Paths.get(filePath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return name;
	}

	@Override
	public InputStream getResouces(String path, String fileName) {
		String fullPath = path + File.separator + fileName;

		try {
			InputStream inputStream = new FileInputStream(fullPath);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
