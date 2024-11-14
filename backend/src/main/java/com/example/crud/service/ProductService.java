package com.example.crud.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crud.entity.Products;
import com.example.crud.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    private ProductRepository repository;

    public Products saveProduct(Products product) {
        return repository.save(product);
    }

    public List<Products> saveProducts(List<Products> products) {
        return repository.saveAll(products);
    }

    public List<Products> getProducts() {
        return repository.findAll();
    }

    public Products getProductById(int id) {
        return repository.findById(id).orElse(null);
    }

    public String deleteProduct(int id) {
        repository.deleteById(id);
        return "Product Removed!" + id;
    }   

    public Products updateProduct(Products product) {
        Products existingProduct = repository.findById(product.getId()).orElse(null);
        existingProduct.setDateCreated(product.getDateCreated());
        existingProduct.setCategory(product.getCategory());
        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setStatus(product.getStatus());
        existingProduct.setAction(product.getAction());
        return repository.save(existingProduct);
    }     
}
