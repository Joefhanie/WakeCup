
package com.example.crud.categoryController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.crud.categoryEntity.Category;
import com.example.crud.categoryService.CategoryService;


@RestController
public class CategoryController {

    @Autowired
    private CategoryService service;

    @PostMapping("/category")    
    public Category addCategory(@RequestBody Category category) {
        return service.saveCategory(category);
    }

    @PostMapping("/addCategories")
    public List<Category> addCategory(@RequestBody List<Category> category) {
        return service.saveCategory(category);
    }

    @GetMapping("/category")    
    public List<Category> findAllCategory() {
        return service.getCategory();
    }

    @GetMapping("/category/{id}")
    public Category findCategoryById(@PathVariable int id) {
        return service.getCategoryById(id);
    }

    @PutMapping("/updateCategory")
    public Category updateCategory(@RequestBody Category category) {
        return service.updateCategory(category);
    }

    @DeleteMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable int id) {
        return service.deleteCategory(id);
    }
}