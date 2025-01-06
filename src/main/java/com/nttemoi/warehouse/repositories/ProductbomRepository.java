package com.nttemoi.warehouse.repositories;

import com.nttemoi.warehouse.dtos.ProductbomDTO;
import com.nttemoi.warehouse.entities.Productbom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
@Transactional
public interface ProductbomRepository extends JpaRepository<Productbom, Long> {
    Page<Productbom> findByProductId(Long productId, Pageable pageable);
    Page<Productbom> findByNameLikeAndProductId(String name, Long productId, Pageable pageable);
}