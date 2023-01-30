package com.upanddownload.upanddown.v1;

import java.util.stream.Stream; 
import org.springframework.web.multipart.MultipartFile;

public interface AttachmentService {

	Attachment saveAttachment(MultipartFile file) throws Exception;

	Attachment getAttachment(String fileId);

	Stream<Attachment> getAllFiles();

}
