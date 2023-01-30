package com.paginationproject.pagination.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl implements ServiceInterface{

	@Autowired
	private ProductRepository prepo;
	@Override
	public List<Product> getAllProducts() {
		
		return (List<Product>) prepo.findAll();
	}

	@Override
	public List<Product> getFilteredProduct(String filters) {
		return prepo.findByProductLine(filters);
	}

	@Override
	public Page<Product> getPage(Integer pno, Integer psize) {
		Pageable paging =PageRequest.of(pno, psize);
		return prepo.findAll(paging);
	}
	
	

}
