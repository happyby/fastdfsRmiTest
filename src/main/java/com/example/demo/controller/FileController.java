package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.DefaultGenerateStorageClient;

@RestController
@RequestMapping("/file")
public class FileController {

	@Resource(name = "defaultGenerateStorageClient")
	private DefaultGenerateStorageClient fastdfsClient;
	@Value("${fastDfsGroup}")
	public String groupName;

	@RequestMapping("/upload")
	public String update(String groupName,String fileName,String path) {
		File file=new File(path);
		String stringPath=null;
		try {
			InputStream in =new FileInputStream(file);
			StorePath storePath = fastdfsClient.uploadFile(groupName,in ,
					file.length(), fileName.split("\\.")[1]);
			System.out.println(storePath.toString());
			stringPath=storePath.getFullPath();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return stringPath;
	}

}
