package com.tuana9a.dao;

import com.tuana9a.model.Product;

public class ProductDAO extends BasicDAO<Product> {
    public ProductDAO() {
        super("product", Product.class);
    }
}
