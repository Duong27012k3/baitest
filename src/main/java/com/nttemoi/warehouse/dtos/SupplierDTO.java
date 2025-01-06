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
public class SupplierDTO {
    private long id;
    private String name;
    private String address;
    private String phone;
    private String email;
    private boolean published;
    private List<ProductDTO> products;
    public SupplierDTO(Long id, String name, String address, String phone, String email, boolean published) {
    }
}
