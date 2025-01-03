package com.nttemoi.warehouse.services.impl;

import com.nttemoi.warehouse.entities.Productbom;
import com.nttemoi.warehouse.repositories.ProductbomRepository;
import com.nttemoi.warehouse.services.ProductbomService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductbomServiceImpl implements ProductbomService {
    private final ProductbomRepository productbomRepository;
    public ProductbomServiceImpl(ProductbomRepository productbomRepository) {
        this.productbomRepository = productbomRepository;
    }
    @Override
    public List<Productbom> findAll () {
        return productbomRepository.findAll();
    }

    @Override
    public Productbom findById (Long id) {
        return productbomRepository.findById(id).orElse(null);
    }

    @Override
    public void save (Productbom productbom) {
        productbomRepository.save(productbom);
    }

    @Override
    public void deleteById (Long id) {
        productbomRepository.deleteById(id);
    }
}
