package com.nttemoi.warehouse.services.impl;

import com.nttemoi.warehouse.entities.Product;
import com.nttemoi.warehouse.entities.Productbom;
import com.nttemoi.warehouse.repositories.ProductbomRepository;
import com.nttemoi.warehouse.services.ProductbomService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service

public class ProductbomServiceImpl implements ProductbomService {
    private final ProductbomRepository productbomRepository;

    public ProductbomServiceImpl(ProductbomRepository productbomRepository) {
        this.productbomRepository = productbomRepository;
    }

    @Override
    public Page<Productbom> findAll(int page, int size) {
        return productbomRepository.findAll(PageRequest.of(page, size, Sort.by("name")));
    }
//    @Override
//    public Page<Productbom> findByKeyword(String keyword, int page, int size) {
//
//    }

//    @Override
//    public Page<Productbom> findByKeyword(String keyword, int page, int size) {
//        return productbomRepository.findByNameLikeOrUnitLikeAndProductId("%" + keyword + "%", "%" + keyword + "%", PageRequest.of(page, size));
//    }

    @Override
    public List<Productbom> findAll() {
        return productbomRepository.findAll();
    }

    @Override
    public Productbom findById(Long id) {
        return productbomRepository.findById(id).orElse(null);
    }

    @Override
    public void save(Productbom productbom) {
        productbomRepository.save(productbom);
    }

    @Override
    public void deleteById(Long id) {
        productbomRepository.deleteById(id);
    }

    @Override
    public Page<Productbom> findAllAndSort(int page, int size, String order, String orderBy) {
        if (order.equals("asc")) {
            return productbomRepository.findAll(PageRequest.of(page, size, Sort.by(orderBy).ascending()));
        } else {
            return productbomRepository.findAll(PageRequest.of(page, size, Sort.by(orderBy).descending()));
        }
    }

    @Override
    public Page<Productbom> findAllByProductId(Long productId, int page, int size) {
        return productbomRepository.findByProductId(productId, PageRequest.of(page, size));
    }

    @Override
    public Page<Productbom> findAllByProductIdAndSort(Long productId, int page, int size, String order, String orderBy) {
        if (order.equals("asc")) {
            return productbomRepository.findByProductId(productId, PageRequest.of(page, size, Sort.by(orderBy).ascending()));
        } else {
            return productbomRepository.findByProductId(productId, PageRequest.of(page, size, Sort.by(orderBy).descending()));
        }
    }

//    @Override
//    public Page<Productbom> findByKeyword(String keyword, int page, int size) {
//
//        return productbomRepository.findProductbomByNameLike(keyword, PageRequest.of(page, size, Sort.by("name")));
//    }

    @Override
    public Page<Productbom> findByKeywordAndProductId(String keyword, Long productId, int page, int size) {
        return productbomRepository.findByNameLikeAndProductId("%" + keyword + "%", productId, PageRequest.of(page, size));
    }

//    @Override
//    public Page<Productbom> findByKeywordAndProductIdAndSort(String keyword, Long productId, int page, int size, String order, String orderBy) {
//        if (order.equals("asc")) {
//            return productbomRepository.findByNameLikeOrUnitLikeAndProductId("%" + keyword + "%", "%" + keyword + "%", productId, PageRequest.of(page, size, Sort.by(orderBy).ascending()));
//        } else {
//            return productbomRepository.findByNameLikeOrUnitLikeAndProductId("%" + keyword + "%", "%" + keyword + "%", productId, PageRequest.of(page, size, Sort.by(orderBy).descending()));
//        }
//    }
}
