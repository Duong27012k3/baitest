package com.nttemoi.warehouse.services;

import com.nttemoi.warehouse.dtos.ProductbomDTO;
import com.nttemoi.warehouse.entities.Productbom;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductbomService {
//    Page<Productbom> findAll(int page, int size);
//    List<Productbom> findAll();
//    Productbom findById(Long id);
//    void save(Productbom productbom);
//    void deleteById(Long id);
//    Page<Productbom> findAllAndSort(int page, int size, String order, String orderBy);
//    Page<Productbom> findAllByProductId(Long productId, int page, int size);
//    Page<Productbom> findAllByProductIdAndSort(Long productId, int page, int size, String order, String orderBy);
//    Page<Productbom> findByKeywordAndProductId(String keyword,Long productId, int page, int size);
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