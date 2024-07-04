package org.yvynnyk.crmgrocerywarehouse.util;

import lombok.extern.java.Log;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.yvynnyk.crmgrocerywarehouse.dto.ProductDTO;
import org.yvynnyk.crmgrocerywarehouse.service.ProductService;

import java.util.Arrays;
import java.util.List;

@Component
@Log
public class DataLoader implements CommandLineRunner {

	private final ProductService productService;

	public DataLoader(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public void run(String... args) {
		if (productService.findAll().isEmpty()) {
			List<ProductDTO> products = Arrays.asList(
					new ProductDTO("Apple", "Fresh and juicy red apples", "USA", 1.99, 100),
					new ProductDTO("Banana", "Ripe yellow bananas", "Ecuador", 0.99, 200),
					new ProductDTO("Orange", "Sweet and tangy oranges", "Spain", 2.49, 150),
					new ProductDTO("Tomato", "Organic vine-ripened tomatoes", "Italy", 3.99, 80),
					new ProductDTO("Broccoli", "Fresh and crisp broccoli", "Mexico", 2.99, 60)
			);
			productService.saveAll(products);
			log.info("Products initialization completed.");
		} else {
			log.info("Database is not empty. Skipping initialization.");
		}
	}
}

