package com.example.crud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud.entity.Products; 

public interface ProductRepository extends JpaRepository<Products, Integer> {

}


