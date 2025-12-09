package com.example.product.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "products")
@Data               // Generates all Getters, Setters, toString, etc.
@NoArgsConstructor  // Generates empty constructor
@AllArgsConstructor // Generates constructor with all fields

public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Double price;
    private String description;
}


