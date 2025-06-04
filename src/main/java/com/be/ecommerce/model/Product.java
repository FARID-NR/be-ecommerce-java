package com.be.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID productId;
    private String productName;
    private String image;
    private String description;
    private Integer quantity;
    private double price;
    private double specialPrice;
    private double discount;

    @ManyToOne
    @JoinColumn(name = "categoryId")
    private Category category;
    private UUID sellerId;
}
