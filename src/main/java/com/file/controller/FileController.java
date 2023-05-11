package com.file.controller;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.file.payload.FileResponse;
import com.file.services.FileService;

@RestController
@RequestMapping("/file")
public class FileController {

	@Autowired
	private FileService fileService;

	@Value("${project.image}")
	private String path;

	@PostMapping("/upload")
	public ResponseEntity<FileResponse> fileUpload(@RequestParam("fileImage") MultipartFile image) {
		String fileName = null;
		try {
			fileName = this.fileService.uploadImage(path, image);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(new FileResponse(null, "file is not uploaded"), HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<>(new FileResponse(fileName, "file uploaded successfully"), HttpStatus.OK);
	}
	@GetMapping(value="/profiles/{imageName}",produces = MediaType.IMAGE_JPEG_VALUE)
	public void dawnloadLogin(@PathVariable("imageName") String imageName,HttpServletResponse response) throws IOException
	{
		InputStream resouces = this.fileService.getResouces(path, imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		StreamUtils.copy(resouces,response.getOutputStream());
	}
}
