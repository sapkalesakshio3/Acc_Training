package com.s3attachments.s3attachment.v1;

import java.util.List; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class S3Controller {
	@Autowired
	private FileService fservice;
	
	@PostMapping("/upload")
	public String saveFile(@RequestParam("file") MultipartFile file){
		return fservice.saveFile(file);
	}
	
	@GetMapping("/download/{filename}")
	public byte[] downloadFile(@PathVariable("filename") String filename)
	{
		return fservice.downloadFile(filename);
	}
	
	@DeleteMapping("/delete/{filename}")
	public String deleteFile(@PathVariable("filename") String fileName)
	{
		return fservice.deleteFile(fileName);
	}
	
	@GetMapping("/allFile")
	public List<String> ListAllFiles(){ 
		return fservice.ListAllFiles();
	}
}
