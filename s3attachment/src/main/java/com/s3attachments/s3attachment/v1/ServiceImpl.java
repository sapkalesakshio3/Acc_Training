package com.s3attachments.s3attachment.v1;

import java.io.File; 
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ListObjectsV2Result;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

@Service
public class ServiceImpl implements FileService {
	
	@Value("${bucketName}")
	private String bucketname;
	
	private final AmazonS3 s3;
	public ServiceImpl(AmazonS3 s3) {
		this.s3=s3;
	}

	@Override
	public String saveFile(MultipartFile file) {
		String originalFileName=file.getOriginalFilename();
		try {
			File file1 = convertMultipartFile(file);
			PutObjectResult putresult = s3.putObject(bucketname,originalFileName,file1);
			return putresult.getContentMd5();
		}catch(IOException e) {
			//e.printStackTrace();
			throw new RuntimeException(e);
		}
		
		
	}

	@Override
	public byte[] downloadFile(String filename) {
		S3Object object = s3.getObject(bucketname,filename);
		S3ObjectInputStream objectContent =object.getObjectContent();
		try {
			 return IOUtils.toByteArray(objectContent);
		}catch(IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String deleteFile(String filename) {
		s3.deleteObject(bucketname, filename);
		return "File deleted";
	}

	@Override
	public List<String> ListAllFiles() {
		ListObjectsV2Result v2Result =s3.listObjectsV2(bucketname);
		return v2Result.getObjectSummaries().stream().map(o->o.getKey()).collect(Collectors.toList());
		//return null;
	}
	
	private File convertMultipartFile(MultipartFile file) throws IOException
	{
		File convFile=new File(file.getOriginalFilename());
		FileOutputStream fos=new FileOutputStream(convFile);
		fos.write(file.getBytes());
		fos.close();
		return convFile;
		
	}

}
