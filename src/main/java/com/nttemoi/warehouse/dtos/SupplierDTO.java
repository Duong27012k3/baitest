package com.nttemoi.warehouse.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
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

public class SupplierDTO {
    private Long id;
    @NotBlank(message = "Name cannot be blank")
    @Size(max = 50, message = "Name must not exceed 50 characters")
    private String name;

    @NotBlank(message = "Address cannot be blank")
    @Size(max = 100, message = "Address must not exceed 100 characters")
    private String address;

    @NotBlank(message = "Phone cannot be blank")
    @Size(max = 15, message = "Phone must not exceed 15 characters")
    private String phone;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    @Size(max = 100, message = "Email must not exceed 100 characters")
    private String email;

    private boolean status;
    private List<ProductDTO> products;
}
