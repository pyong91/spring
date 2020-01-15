package com.kh.sts13.repository;

import java.util.List;

import com.kh.sts13.entity.ProductDto;

public interface ProductDao {
	void insert(ProductDto productDto);
	List<ProductDto> search(String name);
}
