package org.yvynnyk.crmgrocerywarehouse.controller;

import org.springframework.web.bind.annotation.*;
import org.yvynnyk.crmgrocerywarehouse.dto.ProductDTO;
import org.yvynnyk.crmgrocerywarehouse.dto.ProductShortDTO;
import org.yvynnyk.crmgrocerywarehouse.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
public class ProductController {
	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}

	@GetMapping
	public List<ProductShortDTO> getAll() {
		return productService.findAll();
	}

	@GetMapping("/{id}")
	public ProductDTO getById(@PathVariable Long id) {
		return productService.findById(id);
	}

	@PostMapping
	public void save(@RequestBody ProductDTO productDTO) {
		productService.save(productDTO);
	}

	@PostMapping("/{id}")
	public void deleteById(@PathVariable Long id) {
		productService.deleteById(id);
	}

	@GetMapping("/search")
	public List<ProductDTO> search(
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "minPrice", defaultValue = "0") Double minPrice,
			@RequestParam(name = "maxPrice", defaultValue = "0") Double maxPrice) {
		return productService.search(name, minPrice, maxPrice);
	}

	@PostMapping("/update")
	public void update(@RequestBody ProductDTO productDTO) {
		productService.save(productDTO);
	}
}
