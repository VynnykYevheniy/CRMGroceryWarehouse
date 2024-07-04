package org.yvynnyk.crmgrocerywarehouse.mapper;

import org.springframework.stereotype.Component;
import org.yvynnyk.crmgrocerywarehouse.dto.ProductDTO;
import org.yvynnyk.crmgrocerywarehouse.model.Product;

import java.util.List;


@Component
public class ProductMapper {

	public List<ProductDTO> toDTO(List<Product> products) {
		return products.stream()
				.map(this::toDTO)
				.toList();
	}

	public List<Product> toEntity(List<ProductDTO> productDTOS) {
		return productDTOS.stream()
				.map(this::toEntity)
				.toList();
	}

	public ProductDTO toDTO(Product product) {
		if (product == null) {
			return null;
		}
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(product.getId());
		productDTO.setName(product.getName());
		productDTO.setDescription(product.getDescription());
		productDTO.setCountry(product.getCountry());
		productDTO.setPrice(product.getPrice());
		productDTO.setQuantity(product.getQuantity());
		return productDTO;
	}

	public Product toEntity(ProductDTO productDTO) {
		if (productDTO == null) {
			return null;
		}
		Product product = new Product();
		product.setId(productDTO.getId());
		product.setName(productDTO.getName());
		product.setDescription(productDTO.getDescription());
		product.setCountry(productDTO.getCountry());
		product.setPrice(productDTO.getPrice());
		product.setQuantity(productDTO.getQuantity());
		return product;
	}
}