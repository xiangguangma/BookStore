package com.lanou.bookstore.category.dao.impl;

import com.lanou.bookstore.category.dao.CategoryDao;
import com.lanou.bookstore.category.service.CategoryException;
import com.lanou.bookstore.category.domain.Category;
import com.lanou.jdbc.GxQueryRunner;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by dllo on 17/9/22.
 */
public class CategoryDaoImpl implements CategoryDao {
    private QueryRunner qr = new GxQueryRunner();
    @Override
    public List<Category> findAll() {
        String sql = "select * from category";
        try {
            return qr.query(sql, new BeanListHandler<Category>(Category.class));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
