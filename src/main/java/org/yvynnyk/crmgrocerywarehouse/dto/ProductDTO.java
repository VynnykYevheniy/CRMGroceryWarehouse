package org.yvynnyk.crmgrocerywarehouse.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTO {
	private Long id;
	private String name;
	private String description;
	private String country;
	private Double price;
	private Integer quantity;

	public ProductDTO(String name, String description, String country, Double price, Integer quantity) {
		this.name = name;
		this.description = description;
		this.country = country;
		this.price = price;
		this.quantity = quantity;
	}
}