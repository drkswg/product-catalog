package com.drkswg.productcatalog.service;

import com.drkswg.productcatalog.dao.ProductRepository;
import com.drkswg.productcatalog.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImplementation implements ProductService {
    private EntityManager entityManager;
    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImplementation(ProductRepository theProductRepository,
                                        EntityManager theEntityManager) {
        productRepository = theProductRepository;
        entityManager = theEntityManager;
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAllByOrderByNameAsc();
    }

    @Override
    public Product findById(int theId) {
        Optional<Product> result = productRepository.findById(theId);
        Product theProduct;

        if(result.isPresent()) {
            theProduct = result.get();
        } else {
            throw new RuntimeException("Product id not found: "  + theId);
        }

        return theProduct;
    }

    @Override
    public void save(Product theProduct) {
        productRepository.save(theProduct);
    }

    @Override
    public void deleteById(int theId) {
        productRepository.deleteById(theId);
    }

    @Override
    public List<Product> searchProducts(String theSearchName) {
        Query theQuery;

        if (theSearchName != null && theSearchName.trim().length() > 0) {
            theQuery = entityManager.createQuery(
                    "from Product where lower(name) like :theName",
                    Product.class);
            theQuery.setParameter("theName", "%" + theSearchName.toLowerCase() + "%");
        } else {
            theQuery = entityManager.createQuery("from Product order by name asc", Product.class);
        }

        List<Product> products = theQuery.getResultList();

        return products;
    }
}
