package com.example.crud.categoryRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.crud.categoryEntity.Category; 

public interface CategoryRepository extends JpaRepository<Category, Integer> {

}

