package com.nttemoi.warehouse.dtos;

import com.nttemoi.warehouse.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductbomDTO {
    private Long id;
    private ProductDTO productDTO;
    private String name;
    private long quantity;
    private String unit;
}
