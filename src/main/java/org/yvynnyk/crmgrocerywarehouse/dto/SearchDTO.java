package org.yvynnyk.crmgrocerywarehouse.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SearchDTO {
	private String name;
	private Double minPrice;
	private Double maxPrice;
}
