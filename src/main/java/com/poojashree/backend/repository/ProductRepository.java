package com.poojashree.backend.repository;

import com.poojashree.backend.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    // Find all products for a specific audience (e.g., "Women")
    List<Product> findByTargetAudience(String targetAudience);

    // Find by Category (e.g., "Jewelry")
    List<Product> findByCategory(String category);
}