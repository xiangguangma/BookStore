package com.lanou.bookstore.category.dao;

import com.lanou.bookstore.category.domain.Category;

import java.util.List;

/**
 * Created by dllo on 17/9/22.
 */
public interface CategoryDao {
    List<Category> findAll();
}
