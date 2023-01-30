package com.paginationproject.pagination.v1;

import java.util.List; 
import org.springframework.data.domain.Page;

public interface ServiceInterface {
	
	List<Product> getAllProducts();
	
	List<Product> getFilteredProduct(String filters);
	
	Page<Product> getPage(Integer pno,Integer psize);
	

}
