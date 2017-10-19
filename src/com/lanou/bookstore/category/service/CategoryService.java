package com.lanou.bookstore.category.service;

import com.lanou.bookstore.category.domain.Category;

import java.util.List;

/**
 * Created by dllo on 17/9/22.
 */
public interface CategoryService {
    List<Category> findAll();
}
