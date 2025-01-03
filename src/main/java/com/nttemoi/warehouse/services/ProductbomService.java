package com.nttemoi.warehouse.services;



import com.nttemoi.warehouse.entities.Productbom;

import java.util.List;

public interface ProductbomService {
    List<Productbom> findAll ();

    Productbom findById (Long id);

    void save (Productbom productbom);

    void deleteById (Long id);
}
