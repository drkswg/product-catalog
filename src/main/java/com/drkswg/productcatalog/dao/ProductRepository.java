package com.drkswg.productcatalog.dao;

import com.drkswg.productcatalog.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    public List<Product> findAllByOrderByNameAsc();
}
