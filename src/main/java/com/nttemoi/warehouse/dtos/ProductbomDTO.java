package com.nttemoi.warehouse.dtos;

import com.nttemoi.warehouse.entities.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    @NotBlank(message = "Name cannot be blank")
    @Size(max = 50, message = "Name must not exceed 100 characters")
    private String name;

    @Min(value = 1, message = "Quantity must be greater than 0")
    private long quantity;

    @NotBlank(message = "Unit cannot be blank")
    @Size(max = 100, message = "Unit must not exceed 50 characters")
    private String unit;

    @NotNull(message = "ProductDTO cannot be null")
    private ProductDTO productDTO;
}
