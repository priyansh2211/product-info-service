package com.ostk.poc.controller;

import com.ostk.poc.exception.AppException;
import com.ostk.poc.model.Product;
import com.ostk.poc.payload.ApiResponse;
import com.ostk.poc.payload.ProductRequest;
import com.ostk.poc.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.swagger.v3.oas.annotations.Operation;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class MainController {
  @Autowired
  private ProductRepository productRepository;

  /**
   * Get all products
   * @return List of Products
   */
  @Operation(summary = "Get All Ostk Products", description = "Returns the list of ostk products")
  @GetMapping("/products")
  public List<Product> getProducts() {
    return productRepository.findAll();
  }

  /**
   * Get product by id
   * @param id
   * @return Product
   */
  @Operation(summary = "Get Ostk Products by Id", description = "Returns the ostk product")
  @GetMapping("/products/{id}")
  public Product getProduct(@PathVariable Long id) {
    Optional<Product> product = productRepository.findById(id);
    if (!product.isPresent()) {
      throw new AppException("Product not found.");
    }
    return product.get();
  }
}
