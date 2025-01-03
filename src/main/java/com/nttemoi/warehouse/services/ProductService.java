package com.nttemoi.warehouse.services;

import com.nttemoi.warehouse.entities.Product;
import com.nttemoi.warehouse.entities.Supplier;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Page<Product> findAll (int page, int size);

    Page <Product> findByKeyword (String keyword, int page, int size);

    Product findById (Long id);

    void save (Product product);

    void updatePublishedStatus (Long id, boolean published);

    void deleteById (Long id);

    Page <Product> findAllAndSort (int page, int size, String order, String orderBy);

    Page <Product> findByKeywordAndSort (String keyword, int page, int size, String order, String orderBy);
}
