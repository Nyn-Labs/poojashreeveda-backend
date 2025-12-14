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
@CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ImageService imageService;

    // We use this to convert the JSON string back into a Java Object
    // We manually create the tool. No @Autowired needed!
    private final ObjectMapper objectMapper = new ObjectMapper();

    // 1. Add Product WITH Images
    @PostMapping("/add")
    public ResponseEntity<?> addProduct(
            @RequestParam("productData") String productData,  // The JSON data as a String
            @RequestParam("images") MultipartFile[] files     // The array of Image files
    ) throws IOException {

        // 1. Convert the String "productData" into a Product Object
        Product product = objectMapper.readValue(productData, Product.class);

        // 2. Upload images to Cloudinary and get URLs
        List<String> imageUrls = new ArrayList<>();

        for (MultipartFile file : files) {
            String url = imageService.uploadImage(file);
            imageUrls.add(url);
        }

        // 3. Save the URLs to the Product
        product.setImageUrls(imageUrls);

        // 4. Save to MongoDB
        Product savedProduct = productRepository.save(product);

        return ResponseEntity.ok(savedProduct);
    }

    // 2. Get All Products
    @GetMapping("/all")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // 3. Get Single Product by ID
    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable String id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 4. Delete Product
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable String id) {
        productRepository.deleteById(id);
        return ResponseEntity.ok("Product deleted successfully!");
    }
}