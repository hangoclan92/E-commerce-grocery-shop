package edu.hanu.ecomshop.service;

import edu.hanu.ecomshop.model.Category;

import java.util.List;


public interface CategoryService {

    void save(Category category);
    List<Category> findAll();
}
