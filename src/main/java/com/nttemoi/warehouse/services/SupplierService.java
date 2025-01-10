package com.nttemoi.warehouse.services;

import com.nttemoi.warehouse.dtos.SupplierDTO;
import com.nttemoi.warehouse.entities.Supplier;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SupplierService {
    Page<SupplierDTO> findAllDTO(int page, int size);
    Page<SupplierDTO> findByKeywordDTO(String keyword, int page, int size);
    SupplierDTO findByIdDTO(Long id);
    Supplier findById (Long id);
    void saveDTO(SupplierDTO supplierDTO);
    void updatePublishedStatus(Long id, boolean status);
    void deleteByIdDTO(Long id);
    Page<SupplierDTO> findAllAndSortDTO(int page, int size, String order, String orderBy);
    Page<SupplierDTO> findByKeywordAndSortDTO(String keyword, int page, int size, String order, String orderBy);
    List<Supplier> findAll();
    List<SupplierDTO> findAllDTO();
}
