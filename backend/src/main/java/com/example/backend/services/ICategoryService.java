package com.example.backend.services;


import com.example.backend.entities.Category;

import java.util.List;


public interface ICategoryService {

    List<Category> getAllCategories();
    Category getCategoryById(Long id);
}
