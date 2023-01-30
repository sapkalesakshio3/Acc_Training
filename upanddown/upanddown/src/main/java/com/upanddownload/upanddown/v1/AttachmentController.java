package com.upanddownload.upanddown.v1;

import java.util.List;  
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import jakarta.annotation.Resource;

@RestController
public class AttachmentController {

	@Autowired
	private AttachmentService attachmentS;
	
	@PostMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file")MultipartFile file) {
		
		String message = "";
	    try {
	    	attachmentS.saveAttachment(file);
	    	 message = "Uploaded the file successfully: " + file.getOriginalFilename();
	    	 return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	    } catch (Exception e) {
	    	 message = "Could not upload the file: " + file.getOriginalFilename() + "!";
	         return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	       }
	     
	    }
	
	@GetMapping("/files")
	public ResponseEntity<List<ResponseData>> downloadFile(){
		List<ResponseData> files=attachmentS.getAllFiles().map(Attachment -> {	
		String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
         .path("/files/")
         .path(Attachment.getId())
         .toUriString();

		 
		 return new ResponseData(
		          Attachment.getFileName(),
		          fileDownloadUri,
		          Attachment.getFileType(),
		          Attachment.getData().length);
		    }).collect(Collectors.toList());
		 return ResponseEntity.status(HttpStatus.OK).body(files);
	}
	
	@GetMapping("/files/{id}")
	  public ResponseEntity<byte[]> getFile(@PathVariable String id) {
	    Attachment fileDB = attachmentS.getAttachment(id);

	    return ResponseEntity.ok()
	        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getFileName() + "\"")
	        .body(fileDB.getData());
}
}