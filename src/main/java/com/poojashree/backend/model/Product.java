package com.poojashree.backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "products")
public class Product {

    @Id
    private String id;

    private String name;
    private String description;
    private Double price;
    private Double discountPrice;
    private String category;
    private String subCategory;
    private String targetAudience;

    // 👇 THIS WAS MISSING!
    private List<String> sizes;

    private List<String> imageUrls;

    // Optional: Keep this if you use it for other details
    private Map<String, String> attributes;
}