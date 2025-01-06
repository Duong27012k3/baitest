package com.nttemoi.warehouse.dtos;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Long id;
    private String name;
    private String type;
    private String unit;
    private double weight;
    private double size;
    private boolean published;
    private SupplierDTO supplierDTO;
    private List<ProductbomDTO> productbomlist;
}
