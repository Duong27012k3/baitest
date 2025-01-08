package com.nttemoi.warehouse;

import com.nttemoi.warehouse.dtos.ProductDTO;
import com.nttemoi.warehouse.entities.Product;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class WarehouseApplication {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper;
    }

    public static void main (String[] args) {
        SpringApplication.run(WarehouseApplication.class, args);
    }

}
