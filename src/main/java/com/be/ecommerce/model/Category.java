package com.be.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Entity(name = "categories")
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    private UUID categoryId;
    private String categoryName;
}
