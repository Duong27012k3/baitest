package com.nttemoi.warehouse.services;

import com.nttemoi.warehouse.dtos.SupplierDTO;
import com.nttemoi.warehouse.entities.Supplier;
import com.nttemoi.warehouse.entities.Tutorial;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SupplierService {
//    Page<Supplier> findAll (int page, int size);
//
//    Page <Supplier> findByKeyword (String keyword, int page, int size);
//
//    Supplier findById (Long id);
//
//    void save (Supplier supplier);
//
//    void updatePublishedStatus (Long id, boolean published);
//
//    void deleteById (Long id);
//
//
//    Page <Supplier> findAllAndSort (int page, int size, String order, String orderBy);
//
//    Page <Supplier> findByKeywordAndSort (String keyword, int page, int size, String order, String orderBy);
    Page<SupplierDTO> findAll(int page, int size);
    Page<SupplierDTO> findByKeyword(String keyword, int page, int size);
    SupplierDTO findById(Long id);
    void save(SupplierDTO supplierDTO);
    void updatePublishedStatus(Long id, boolean published);
    void deleteById(Long id);
    Page<SupplierDTO> findAllAndSort(int page, int size, String order, String orderBy);
    Page<SupplierDTO> findByKeywordAndSort(String keyword, int page, int size, String order, String orderBy);
}
