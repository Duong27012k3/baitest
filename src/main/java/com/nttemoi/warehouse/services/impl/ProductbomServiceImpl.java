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

    @Override
    public Page<ProductbomDTO> findAllByProductIdDTO(Long productId, int page, int size) {
        return productbomRepository.findByProductId(productId, PageRequest.of(page, size)).map(this::convertToDTO);
    }

    @Override
    public Page<ProductbomDTO> findAllByProductIdAndSortDTO(Long productId, int page, int size, String order, String orderBy) {
        Sort sort = order.equals("asc") ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        return productbomRepository.findByProductId(productId, PageRequest.of(page, size, sort)).map(this::convertToDTO);
    }

    @Override
    public Page<ProductbomDTO> findByKeywordAndProductIdDTO(String keyword, Long productId, int page, int size) {
        return productbomRepository.findByNameLikeAndProductId("%" + keyword + "%", productId, PageRequest.of(page, size)).map(this::convertToDTO);
    }
}
