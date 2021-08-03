package com.tuana9a.dao;

import com.tuana9a.models.SimpleModel;

public class SimpleModelDao extends BaseDao<SimpleModel> {
    private static final SimpleModelDao instance = new SimpleModelDao();

    private SimpleModelDao() {
        super("product", SimpleModel.class);
    }

    public static SimpleModelDao getInstance() {
        return instance;
    }
}
