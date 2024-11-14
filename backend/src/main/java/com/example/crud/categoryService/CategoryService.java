
package com.example.crud.categoryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crud.categoryEntity.Category;
import com.example.crud.categoryRepository.CategoryRepository;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository repository;

    public Category saveCategory(Category category) {
        return repository.save(category);
    }

    public List<Category> saveCategory(List<Category> category) {
        return repository.saveAll(category);
    }

    public List<Category> getCategory() {
        return repository.findAll();
    }

    public Category getCategoryById(int id) {
        return repository.findById(id).orElse(null);
    }

    public String deleteCategory(int id) {
        repository.deleteById(id);
        return "Category Removed!" + id;
    }   

    public Category updateCategory(Category category) {
        Category existingCategory = repository.findById(category.getId()).orElse(null);
        existingCategory.setDateCreated(category.getDateCreated());
        existingCategory.setName(category.getName());
        existingCategory.setDescription(category.getDescription());
        existingCategory.setStatus(category.getStatus());
        existingCategory.setAction(category.getAction());
        return repository.save(existingCategory);
    }   
}

