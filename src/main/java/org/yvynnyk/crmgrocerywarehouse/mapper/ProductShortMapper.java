package org.yvynnyk.crmgrocerywarehouse.mapper;

import org.springframework.stereotype.Component;
import org.yvynnyk.crmgrocerywarehouse.dto.ProductShortDTO;
import org.yvynnyk.crmgrocerywarehouse.model.Product;

import java.util.List;

@Component
public class ProductShortMapper {
	public List<ProductShortDTO> toDTO(List<Product> products) {
		return products.stream()
				.map(this::toDTO)
				.toList();
	}

	public ProductShortDTO toDTO(Product product) {
		if (product == null) {
			return null;
		}
		ProductShortDTO productShortDTO = new ProductShortDTO();
		productShortDTO.setId(product.getId());
		productShortDTO.setName(product.getName());
		productShortDTO.setPrice(product.getPrice());
		return productShortDTO;
	}
}
