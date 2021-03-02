package com.ostk.poc.productinfoservice.repository;

import com.ostk.poc.productinfoservice.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {
}
