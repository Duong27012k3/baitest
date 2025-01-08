package com.nttemoi.warehouse.dtos;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;

    @NotBlank(message = "Type cannot be blank")
    @Size(min = 2, max = 100, message = "Type must be between 2 and 100 characters")
    private String type;

    @NotBlank(message = "Unit cannot be blank")
    @Size(min = 2, max = 100, message = "Unit must be between 2 and 100 characters")
    private String unit;

    @Positive(message = "Weight must be positive")
    private double weight;

    @Positive(message = "Size must be positive")
    private double size;

    private boolean published;

    @NotNull(message = "Supplier must not be null")
    private SupplierDTO supplierDTO;

    @NotNull(message = "ProductBOM list cannot be null")
    @Size(min = 1, message = "ProductBOM list must have at least one item")
    private List<ProductbomDTO> productbomlist;
}
