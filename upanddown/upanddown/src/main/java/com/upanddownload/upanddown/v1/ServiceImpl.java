package com.upanddownload.upanddown.v1;

import java.io.IOException;  
import java.util.stream.Stream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;



@Service
public class ServiceImpl implements AttachmentService {

	@Autowired
	private AttachmentRepository arepo;
	
	@Override
	public Attachment saveAttachment(MultipartFile file) throws IOException {
		// TODO Auto-generated method stub
		
		String fileName=StringUtils.cleanPath(file.getOriginalFilename());
		Attachment Attachment = new Attachment(fileName, file.getContentType(), file.getBytes());
		
		return arepo.save(Attachment);
		
	}

	@Override
	public Attachment getAttachment(String fileId) {
		// TODO Auto-generated method stub
		return arepo.findById(fileId).get();
	}

	 public Stream<Attachment> getAllFiles() {
		    return arepo.findAll().stream();
		  }
	

}
