package com.nttemoi.warehouse.services.impl;

import com.nttemoi.warehouse.dtos.ProductDTO;
import com.nttemoi.warehouse.entities.Product;
import com.nttemoi.warehouse.entities.Tutorial;
import com.nttemoi.warehouse.repositories.ProductRepository;
import com.nttemoi.warehouse.services.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Page<ProductDTO> findAll(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size, Sort.by("name")))
                .map(this::convertToDTO);
    }

    @Override
    public Page<ProductDTO> findByKeyword(String keyword, int page, int size) {
        return productRepository.findByNameLikeOrTypeLike("%" + keyword + "%", "%" + keyword + "%", PageRequest.of(page, size))
                .map(this::convertToDTO);
    }

    @Override
    public ProductDTO findById(Long id) {
        return productRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    @Override
    public void save(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        productRepository.save(product);
    }

    @Override
    public void updatePublishedStatus(Long id, boolean published) {
        productRepository.updatePublishedStatus(id, published);
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<ProductDTO> findAllAndSort(int page, int size, String order, String orderBy) {
        Sort sort = order.equals("asc") ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        return productRepository.findAll(PageRequest.of(page, size, sort)).map(this::convertToDTO);
    }

    @Override
    public Page<ProductDTO> findByKeywordAndSort(String keyword, int page, int size, String order, String orderBy) {
        Sort sort = order.equals("asc") ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        return productRepository.findByNameLikeOrTypeLike("%" + keyword + "%", "%" + keyword + "%", PageRequest.of(page, size, sort))
                .map(this::convertToDTO);
    }

    private ProductDTO convertToDTO(Product product) {
        return modelMapper.map(product, ProductDTO.class);
    }

    private Product convertToEntity(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }

//    private final ProductRepository productRepository;
//    public ProductServiceImpl (ProductRepository productRepository) {
//        this.productRepository = productRepository;
//    }
//
//    @Override
//    public Page<Product> findAll (int page, int size) {
//        return productRepository.findAll(PageRequest.of(page, size,Sort.by("name")));
//    }
//
//
//    @Override
//    public Page <Product> findByKeyword (String keyword, int page, int size) {
//        return productRepository.findByNameLikeOrTypeLike("%" + keyword + "%", "%" + keyword + "%", PageRequest.of(page, size));
//    }
//
//    @Override
//    public Product findById (Long id) {
//        return productRepository.findById(id).orElse(null);
//    }
//
//    @Override
//    public void save (Product product) {
//        productRepository.save(product);
//    }
//
//    @Override
//    public void updatePublishedStatus (Long id, boolean published) {
//        productRepository.updatePublishedStatus(id, published);
//    }
//
//    @Override
//    public void deleteById (Long id) {
//        productRepository.deleteById(id);
//    }
//
//    @Override
//    public Page <Product> findAllAndSort (int page, int size, String order, String orderBy) {
//        if (order.equals("asc")) {
//            return productRepository.findAll(PageRequest.of(page, size, Sort.by(orderBy).ascending()));
//        }
//        else {
//            return productRepository.findAll(PageRequest.of(page, size, Sort.by(orderBy).descending()));
//        }
//    }
//
//    @Override
//    public Page <Product> findByKeywordAndSort (String keyword, int page, int size, String order, String orderBy) {
//        if (order.equals("asc")) {
//            return productRepository.findByNameLikeOrTypeLike("%" + keyword + "%", "%" + keyword + "%", PageRequest.of(page, size, Sort.by(orderBy).ascending()));
//        }
//        else {
//            return productRepository.findByNameLikeOrTypeLike("%" + keyword + "%", "%" + keyword + "%", PageRequest.of(page, size, Sort.by(orderBy).descending()));
//        }
//    }
}
