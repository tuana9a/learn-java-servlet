package com.tuana9a.dao;

import com.tuana9a.model.Brand;

public class BrandDAO extends BasicDAO<Brand>{
    public BrandDAO(String table) {
        super("brand", Brand.class);
    }
}
