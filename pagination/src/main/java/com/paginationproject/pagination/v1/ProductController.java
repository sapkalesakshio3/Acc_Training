package com.paginationproject.pagination.v1;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ProductController {

	@Autowired 
	private ServiceImpl simpl;
	
	@GetMapping("/products")
	public List<Product> getAllProduct() {
	   return simpl.getAllProducts();
	}
	
	@GetMapping("/products/filter")
	public List<Product> getFiltered(@RequestParam String shippingtype){
		return simpl.getFilteredProduct(shippingtype);
		
	}
	
	@GetMapping("/products/pagination")
	public Page<Product> getPage(@RequestParam(defaultValue="0") Integer PageNo,@RequestParam(defaultValue="5") Integer datasize)
	{
		return simpl.getPage(PageNo, datasize);
	}
	
	
}
