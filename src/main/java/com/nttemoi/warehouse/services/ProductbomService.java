package com.nttemoi.warehouse.services;

import com.nttemoi.warehouse.dtos.ProductbomDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductbomService {
    Page<ProductbomDTO> findAll(int page, int size);
    List<ProductbomDTO> findAll();
    ProductbomDTO findById(Long id);
    void save(ProductbomDTO productbomDTO);
    void deleteById(Long id);
    Page<ProductbomDTO> findAllAndSort(int page, int size, String order, String orderBy);
    Page<ProductbomDTO> findAllByProductId(Long productId, int page, int size);
    Page<ProductbomDTO> findAllByProductIdAndSort(Long productId, int page, int size, String order, String orderBy);
    Page<ProductbomDTO> findByKeywordAndProductId(String keyword, Long productId, int page, int size);
}
