package com.nttemoi.warehouse.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table (name = "products")
public class Product {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 100)
    private String type;

    @Column(nullable = false,length = 100)
    private String unit;

    @Column(nullable = false,length = 100)
    private double weight;

    @Column(nullable = false,length = 100)
    private double size;

    @Column(nullable = false)
    private boolean published ;


    @CreationTimestamp
    @Column (updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @OneToMany (mappedBy = "product",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Productbom> productbomlist = new ArrayList<>();

    public void setProductboms(List<Productbom> productboms) {
    }
}
