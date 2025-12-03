package com.example.order.dto;

import lombok.Data;

@Data // Generates Getters, Setters, etc.
public class ProductDTO {
    private Long id;
    private String name;
    private Double price;
}

