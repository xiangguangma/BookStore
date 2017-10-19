package com.lanou.bookstore.category.service.impl;

import com.lanou.bookstore.category.dao.CategoryDao;
import com.lanou.bookstore.category.dao.impl.CategoryDaoImpl;
import com.lanou.bookstore.category.domain.Category;
import com.lanou.bookstore.category.service.CategoryService;

import java.util.List;

/**
 * Created by dllo on 17/9/22.
 */
public class CatrgoryServiceImpl implements CategoryService {
    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public List<Category> findAll() {
        return categoryDao.findAll();
    }
}
