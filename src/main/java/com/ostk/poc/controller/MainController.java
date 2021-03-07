package com.ostk.poc.controller;

import com.ostk.poc.exception.AppException;
import com.ostk.poc.model.Product;
import com.ostk.poc.payload.ProductRequest;
import com.ostk.poc.repository.ProductRepository;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Products", description = "The Ostk Product API")
public class MainController {
  @Autowired
  private ProductRepository productRepository;

  /**
   * Get all products
   * @return List of Products
   */
  @Operation(summary = "Get All Ostk Products", description = "Returns the list of ostk products")
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "successful operation",
                  content = @Content(array = @ArraySchema(schema = @Schema(implementation = Product.class)))) })
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
  @ApiResponses(value = {
          @ApiResponse(responseCode = "200", description = "successful operation",
                  content = @Content(schema = @Schema(implementation = Product.class))),
          @ApiResponse(responseCode = "404", description = "Product not found") })
  @GetMapping("/products/{id}")
  public Product getProduct(@PathVariable Long id) {
    Optional<Product> product = productRepository.findById(id);
    if (!product.isPresent()) {
      throw new AppException("Product not found.");
    }
    return product.get();
  }
}
