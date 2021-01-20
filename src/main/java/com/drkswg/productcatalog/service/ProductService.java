package com.drkswg.productcatalog.service;

import com.drkswg.productcatalog.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> findAll();
    public Product findById(int theId);
    public void save(Product theEmployee);
    public void deleteById(int theId);
    public List<Product> searchProducts(String theSearchName);
}
