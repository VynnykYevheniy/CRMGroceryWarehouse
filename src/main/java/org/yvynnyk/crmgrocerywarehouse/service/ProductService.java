package org.yvynnyk.crmgrocerywarehouse.service;

import org.yvynnyk.crmgrocerywarehouse.dto.ProductDTO;
import org.yvynnyk.crmgrocerywarehouse.dto.ProductShortDTO;

import java.util.List;

public interface ProductService {

	void save(ProductDTO productDTO);

	List<ProductShortDTO> findAll();

	ProductDTO findById(Long id);

	void deleteById(Long id);

	List<ProductDTO> search(String name, Double minPrice, Double maxPrice);

	void saveAll(List<ProductDTO> products);
}