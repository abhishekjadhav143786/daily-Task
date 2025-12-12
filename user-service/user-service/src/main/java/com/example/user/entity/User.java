package com.example.user.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "users") // 'users' avoids conflict with SQL keyword 'user'
@Data               // Generates Getters, Setters, toString, etc.
@NoArgsConstructor  // Generates empty constructor
 @AllArgsConstructor// Generates constructor with all fields
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String address;
}