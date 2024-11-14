package com.example.crud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.entity.Products;
import com.example.crud.service.ProductService;


@RestController
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/product")    
    public Products addProduct(@RequestBody Products product) {
        return service.saveProduct(product);
    }

    @PostMapping("/products")
    public List<Products> addProducts(@RequestBody List<Products> products) {
        return service.saveProducts(products);
    }

    @GetMapping("/products")    
    public List<Products> findAllProducts() {
        return service.getProducts();
    }

    @GetMapping("/product/{id}")
    public Products findProductById(@PathVariable int id) {
        return service.getProductById(id);
    }

    @PutMapping("/update")
    public Products updateProduct(@RequestBody Products product) {
        return service.updateProduct(product);
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        return service.deleteProduct(id);
    }
}
