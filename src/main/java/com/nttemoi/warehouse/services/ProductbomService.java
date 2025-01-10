package com.nttemoi.warehouse.services;

import com.nttemoi.warehouse.dtos.ProductbomDTO;
import org.springframework.data.domain.Page;

public interface ProductbomService {

    Page<ProductbomDTO> findAllByProductIdDTO(Long productId, int page, int size);
    Page<ProductbomDTO> findAllByProductIdAndSortDTO(Long productId, int page, int size, String order, String orderBy);
    Page<ProductbomDTO> findByKeywordAndProductIdDTO(String keyword, Long productId, int page, int size);
}
