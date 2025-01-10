package com.nttemoi.warehouse.services.impl;

import com.nttemoi.warehouse.dtos.ProductbomDTO;
import com.nttemoi.warehouse.entities.Productbom;
import com.nttemoi.warehouse.repositories.ProductbomRepository;
import com.nttemoi.warehouse.services.ProductbomService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductbomServiceImpl implements ProductbomService {
    private final ProductbomRepository productbomRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductbomServiceImpl(ProductbomRepository productbomRepository, ModelMapper modelMapper) {
        this.productbomRepository = productbomRepository;
        this.modelMapper = modelMapper;
    }

    private ProductbomDTO convertToDTO(Productbom productbom) {
        return modelMapper.map(productbom, ProductbomDTO.class);
    }

    private Productbom convertToEntity(ProductbomDTO productbomDTO) {
        return modelMapper.map(productbomDTO, Productbom.class);
    }

    @Override
    public Page<ProductbomDTO> findAll(int page, int size) {
        return productbomRepository.findAll(PageRequest.of(page, size, Sort.by("name")))
                .map(this::convertToDTO);
    }

    @Override
    public List<ProductbomDTO> findAll() {
        return productbomRepository.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public ProductbomDTO findById(Long id) {
        return productbomRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    @Override
    public void save(ProductbomDTO productbomDTO) {
        Productbom productbom = convertToEntity(productbomDTO);
        productbomRepository.save(productbom);
    }

    @Override
    public void deleteById(Long id) {
        productbomRepository.deleteById(id);
    }

    @Override
    public Page<ProductbomDTO> findAllAndSort(int page, int size, String order, String orderBy) {
        Sort sort = order.equals("asc") ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        return productbomRepository.findAll(PageRequest.of(page, size, sort)).map(this::convertToDTO);
    }

    @Override
    public Page<ProductbomDTO> findAllByProductId(Long productId, int page, int size) {
        return productbomRepository.findByProductId(productId, PageRequest.of(page, size)).map(this::convertToDTO);
    }

    @Override
    public Page<ProductbomDTO> findAllByProductIdAndSort(Long productId, int page, int size, String order, String orderBy) {
        Sort sort = order.equals("asc") ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        return productbomRepository.findByProductId(productId, PageRequest.of(page, size, sort)).map(this::convertToDTO);
    }

    @Override
    public Page<ProductbomDTO> findByKeywordAndProductId(String keyword, Long productId, int page, int size) {
        return productbomRepository.findByNameLikeAndProductId("%" + keyword + "%", productId, PageRequest.of(page, size)).map(this::convertToDTO);
    }
}
