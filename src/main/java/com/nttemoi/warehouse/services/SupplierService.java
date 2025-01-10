package com.nttemoi.warehouse.services;

import com.nttemoi.warehouse.dtos.SupplierDTO;
import com.nttemoi.warehouse.entities.Supplier;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SupplierService {
    Page<SupplierDTO> findAll(int page, int size);
    Page<SupplierDTO> findByKeyword(String keyword, int page, int size);
    SupplierDTO findByIdDTO(Long id);
    Supplier findById (Long id);
    void save(SupplierDTO supplierDTO);
    void updatePublishedStatus(Long id, boolean status);
    void deleteById(Long id);
    Page<SupplierDTO> findAllAndSort(int page, int size, String order, String orderBy);
    Page<SupplierDTO> findByKeywordAndSort(String keyword, int page, int size, String order, String orderBy);
    List<SupplierDTO> findAll();
}
