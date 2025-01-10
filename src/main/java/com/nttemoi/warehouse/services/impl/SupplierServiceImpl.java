package com.nttemoi.warehouse.services.impl;

import com.nttemoi.warehouse.dtos.SupplierDTO;
import com.nttemoi.warehouse.entities.Supplier;
import com.nttemoi.warehouse.repositories.SupplierRepository;
import com.nttemoi.warehouse.services.SupplierService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;

    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    @Autowired
    private ModelMapper modelMapper;

    public SupplierDTO convertToDTO(Supplier supplier) {
        return modelMapper.map(supplier, SupplierDTO.class);
    }

    public Supplier convertToEntity(SupplierDTO supplierDTO) {
        return modelMapper.map(supplierDTO, Supplier.class);
    }

    @Override
    public Page<SupplierDTO> findAll(int page, int size) {
        Page<Supplier> suppliers = supplierRepository.findAll(PageRequest.of(page, size, Sort.by("name")));
        return suppliers.map(supplier -> modelMapper.map(supplier, SupplierDTO.class));
    }
    @Override
    public List<SupplierDTO> findAll() {
        return supplierRepository.findAll().stream()
                .map(supplier -> modelMapper.map(supplier, SupplierDTO.class))
                .collect(Collectors.toList());
    }
    @Override
    public Page<SupplierDTO> findByKeyword(String keyword, int page, int size) {
        return supplierRepository.findByNameLikeOrPhoneLike("%" + keyword + "%", "%" + keyword + "%", PageRequest.of(page, size))
                .map(this::convertToDTO);
    }

    @Override
    public SupplierDTO findByIdDTO(Long id) {
        return supplierRepository.findById(id)
                .map(this::convertToDTO)
                .orElse(null);
    }

    @Override
    public Supplier findById (Long id) {
        return supplierRepository.findById(id).orElse(null);
    }

    @Override
    public void save(SupplierDTO supplierDTO) {
        supplierRepository.save(convertToEntity(supplierDTO));
    }

    @Override
    public void updatePublishedStatus(Long id, boolean status) {
        supplierRepository.updatePublishedStatus(id, status);
    }

    @Override
    public void deleteById(Long id) {
        supplierRepository.deleteById(id);
    }

    @Override
    public Page<SupplierDTO> findAllAndSort(int page, int size, String order, String orderBy) {
        Sort sort = order.equals("asc") ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        return supplierRepository.findAll(PageRequest.of(page, size, sort))
                .map(this::convertToDTO);
    }

    @Override
    public Page<SupplierDTO> findByKeywordAndSort(String keyword, int page, int size, String order, String orderBy) {
        Sort sort = order.equals("asc") ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        return supplierRepository.findByNameLikeOrPhoneLike("%" + keyword + "%", "%" + keyword + "%", PageRequest.of(page, size, sort))
                .map(this::convertToDTO);
    }
}
