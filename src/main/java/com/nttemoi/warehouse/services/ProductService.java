package com.nttemoi.warehouse.services;

import com.nttemoi.warehouse.dtos.ProductDTO;
import com.nttemoi.warehouse.entities.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Page<ProductDTO> findAllDTO(int page, int size);
    Page<ProductDTO> findByKeyword(String keyword, int page, int size);
    Product findById(Long id);
    void save(ProductDTO productDTO);
    void updatePublishedStatus(Long id, boolean published);
    void deleteById(Long id);
    Page<ProductDTO> findAllAndSort(int page, int size, String order, String orderBy);
    Page<ProductDTO> findByKeywordAndSort(String keyword, int page, int size, String order, String orderBy);
    Page<Product> findAll ();
    ProductDTO findDTOById(Long id);
}
