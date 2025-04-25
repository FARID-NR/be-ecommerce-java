package com.be.ecommerce.model;

import lombok.Data;

import java.util.UUID;

@Data
public class Category {
    private UUID categoryId;
    private String categoryName;
}
