package com.upanddownload.upanddown.v1;

import org.springframework.data.jpa.repository.JpaRepository; 
import org.springframework.stereotype.Repository;

@Repository
public interface AttachmentRepository extends JpaRepository<Attachment,String> {

	Attachment save(Attachment attchment);

	
	
}
