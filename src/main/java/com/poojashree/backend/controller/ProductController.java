package com.poojashree.backend.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poojashree.backend.model.Product;
import com.poojashree.backend.repository.ProductRepository;
import com.poojashree.backend.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ImageService imageService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    // ===============================
    // ADD PRODUCT WITH IMAGES
    // ===============================
    @PostMapping("/add")
    public ResponseEntity<?> addProduct(
            @RequestParam("productData") String productData,
            @RequestParam("images") MultipartFile[] files
    ) throws IOException {

        Product product = objectMapper.readValue(productData, Product.class);

        List<String> imageUrls = new ArrayList<>();
        for (MultipartFile file : files) {
            String url = imageService.uploadImage(file);
            imageUrls.add(url);
        }

        product.setImageUrls(imageUrls);
        Product savedProduct = productRepository.save(product);

        return ResponseEntity.ok(savedProduct);
    }

    // ===============================
    // GET ALL PRODUCTS
    // ===============================
    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // ===============================
    // GET PRODUCT BY ID
    // ===============================
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable String id) {
        Optional<Product> product = productRepository.findById(id);
        return product.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // ===============================
    // DELETE PRODUCT
    // ===============================
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok("Product deleted successfully");
    }
}
