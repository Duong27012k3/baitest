package com.nttemoi.warehouse.services.impl;

import com.nttemoi.warehouse.dtos.ProductDTO;
import com.nttemoi.warehouse.dtos.ProductbomDTO;
import com.nttemoi.warehouse.dtos.SupplierDTO;
import com.nttemoi.warehouse.entities.Product;
import com.nttemoi.warehouse.entities.Productbom;
import com.nttemoi.warehouse.entities.Supplier;
import com.nttemoi.warehouse.repositories.ProductRepository;
import com.nttemoi.warehouse.repositories.ProductbomRepository;
import com.nttemoi.warehouse.services.ProductService;
import com.nttemoi.warehouse.services.ProductbomService;
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
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;
    private final SupplierService supplierService;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, ModelMapper modelMapper, SupplierService supplierService) {
        this.productRepository = productRepository;
        this.modelMapper = modelMapper;
        this.supplierService = supplierService;
    }

    private ProductDTO convertToDTO(Product product) {
        ProductDTO productDTO = modelMapper.map(product, ProductDTO.class);
        if (product.getSupplier() != null) {
            productDTO.setSupplierDTO(modelMapper.map(product.getSupplier(), SupplierDTO.class));
        }
        // Kiểm tra và ánh xạ danh sách productbomlist
        if (product.getProductbomlist() != null) {
            List<ProductbomDTO> productbomDTOList = product.getProductbomlist().stream()
                    .map(productbom -> modelMapper.map(productbom, ProductbomDTO.class))
                    .collect(Collectors.toList());
            productDTO.setProductbomlist(productbomDTOList);
        }

        return productDTO;
    }

    private Product convertToEntity(ProductDTO productDTO) {
        return modelMapper.map(productDTO, Product.class);
    }

    @Override
    public Page<ProductDTO> findAllDTO(int page, int size) {
        return productRepository.findAll(PageRequest.of(page, size, Sort.by("name")))
                .map(this::convertToDTO);
    }

    @Override
    public Page<Product> findAll () {
        return productRepository.findAll(PageRequest.of(0, 150));
    }

    @Override
    public Page<ProductDTO> findByKeyword(String keyword, int page, int size) {
        return productRepository.findByNameLikeOrTypeLike("%" + keyword + "%", "%" + keyword + "%", PageRequest.of(page, size))
                .map(this::convertToDTO);
    }

    @Override
    public ProductDTO findByIdDTO(Long id) {
        return productRepository.findById(id).map(this::convertToDTO).orElse(null);
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void saveDTO(ProductDTO productDTO) {
        Product product = convertToEntity(productDTO);
        if (productDTO.getSupplierDTO() != null && productDTO.getSupplierDTO().getId() != null) {
            SupplierDTO supplier = supplierService.findByIdDTO(productDTO.getSupplierDTO().getId());
            product.setSupplier(modelMapper.map(supplier, Supplier.class));
        }
        if (product.getProductbomlist() != null) {
            for (Productbom productbom : product.getProductbomlist()) {
                // Gán Product vào từng Productbom
                productbom.setProduct(product);
            }
        }
        productRepository.save(product);
    }

    @Override
    public void updatePublishedStatus(Long id, boolean published) {
        productRepository.updatePublishedStatus(id, published);
    }

    @Override
    public void deleteByIdDTO(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Page<ProductDTO> findAllAndSortDTO(int page, int size, String order, String orderBy) {
        Sort sort = order.equals("asc") ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        return productRepository.findAll(PageRequest.of(page, size, sort)).map(this::convertToDTO);
    }

    @Override
    public Page<ProductDTO> findByKeywordAndSortDTO(String keyword, int page, int size, String order, String orderBy) {
        Sort sort = order.equals("asc") ? Sort.by(orderBy).ascending() : Sort.by(orderBy).descending();
        return productRepository.findByNameLikeOrTypeLike("%" + keyword + "%", "%" + keyword + "%", PageRequest.of(page, size, sort))
                .map(this::convertToDTO);
    }
}
