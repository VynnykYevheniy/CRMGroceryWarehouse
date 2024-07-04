package org.yvynnyk.crmgrocerywarehouse.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.yvynnyk.crmgrocerywarehouse.dto.ProductDTO;
import org.yvynnyk.crmgrocerywarehouse.dto.ProductShortDTO;
import org.yvynnyk.crmgrocerywarehouse.mapper.ProductMapper;
import org.yvynnyk.crmgrocerywarehouse.mapper.ProductShortMapper;
import org.yvynnyk.crmgrocerywarehouse.model.Product;
import org.yvynnyk.crmgrocerywarehouse.repository.ProductRepository;
import org.yvynnyk.crmgrocerywarehouse.service.ProductService;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;
	private final ProductMapper productMapper;
	private final ProductShortMapper productShortMapper;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository, ProductMapper productMapper, ProductShortMapper productShortMapper) {
		this.productRepository = productRepository;
		this.productMapper = productMapper;
		this.productShortMapper = productShortMapper;
	}


	@Override
	public void save(ProductDTO productDTO) {
		Product product = productMapper.toEntity(productDTO);
		productMapper.toDTO(productRepository.save(product));
	}

	@Override
	public List<ProductShortDTO> findAll() {
		return productShortMapper.toDTO(productRepository.findAll());
	}

	@Override
	public ProductDTO findById(Long id) {
		return productMapper.toDTO(productRepository.findAllById(id)
				.orElseThrow(() -> new RuntimeException("Not found product by Id: " + id)));
	}

	@Override
	public void deleteById(Long id) {
		productRepository.deleteById(id);
	}

	@Override
	public List<ProductDTO> search(String name, Double minPrice, Double maxPrice) {
		return productMapper.toDTO(productRepository.findByNameAndPriceRange(
				name,
				minPrice == 0 ? null : minPrice,
				maxPrice == 0 ? null : maxPrice
		));
	}

	@Override
	public void saveAll(List<ProductDTO> products) {
		productRepository.saveAll(productMapper.toEntity(products));
	}
}