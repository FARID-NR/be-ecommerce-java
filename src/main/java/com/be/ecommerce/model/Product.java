package com.be.ecommerce.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
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

    @NotBlank
    @Size(min = 3, message = "Nama product minimal 3 character")
    private String productName;
    private String image;

    @NotBlank
    @Size(min = 3, message = "Description minimal 3 character")
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
