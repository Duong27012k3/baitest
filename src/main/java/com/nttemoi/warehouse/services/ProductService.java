package com.nttemoi.warehouse.services;

import com.nttemoi.warehouse.dtos.ProductDTO;
import com.nttemoi.warehouse.entities.Product;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Page<ProductDTO> findAllDTO(int page, int size);
    Page<ProductDTO> findByKeyword(String keyword, int page, int size);
    Product findById(Long id);
    void saveDTO(ProductDTO productDTO);
    void updatePublishedStatus(Long id, boolean published);
    void deleteByIdDTO(Long id);
    Page<ProductDTO> findAllAndSortDTO(int page, int size, String order, String orderBy);
    Page<ProductDTO> findByKeywordAndSortDTO(String keyword, int page, int size, String order, String orderBy);
    Page<Product> findAll ();
    ProductDTO findByIdDTO(Long id);
}
