package com.file.services;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;


public interface FileService {

	String uploadImage(String path,MultipartFile multipartFile) throws IOException;
	InputStream getResouces(String path,String fileName);
	
}
