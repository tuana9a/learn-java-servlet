package com.tuana9a.dao;

import com.tuana9a.model.Category;

public class CategoryDAO extends BasicDAO<Category> {
    public CategoryDAO() {
        super("category", Category.class);
    }
}
