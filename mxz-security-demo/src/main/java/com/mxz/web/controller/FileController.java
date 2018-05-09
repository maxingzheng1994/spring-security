package com.mxz.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mxz.dto.FileInfo;

/*作者：马兴争
 *日期: 2018年4月13日
 *时间： 上午12:36:44
 **/
@RestController
@RequestMapping("/filer")
public class FileController {
	
	@PostMapping
	public FileInfo upload(MultipartFile file) throws IllegalStateException, IOException {
		System.out.println(file.getName());
		System.out.println(file.getOriginalFilename());
		File localfile = new File("path",new Date().getTime()+".txt");
		file.transferTo(localfile);
		return new FileInfo(localfile.getAbsolutePath());
	}
	
	@GetMapping("/{id}")
	public void download(@PathVariable String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try (
			InputStream inputStream = new FileInputStream(new File("path", id+".txt"));
			OutputStream outputStream = response.getOutputStream();) {
			
			response.setContentType("application/x-download");
			response.addHeader("Content-Disposition", "attachment;filename-test.txt");
			IOUtils.copy(inputStream, outputStream);
			outputStream.flush();
		}
	}
}
