package com.ostk.poc.productinfoservice.service;

import com.ostk.poc.productinfoservice.model.Product;
import com.ostk.poc.productinfoservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public Iterable<Product> findProducts() {
        return repository.findAll();
    }

    public Product findProduct(Integer id) {
        return repository.findById(id).get();
    }
}
