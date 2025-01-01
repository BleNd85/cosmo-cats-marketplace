package com.example.cosmocatsmarketplacelabs.repository.entity;

import com.example.cosmocatsmarketplacelabs.common.CategoryType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_seq")
    @SequenceGenerator(name = "product_id_seq", sequenceName = "product_id_seq", allocationSize = 50)
    private Long id;

    @NaturalId
    @Column(nullable = false, unique = true)
    private UUID productId;

    @Column(nullable = false)
    private Double price;

    @Column(nullable = false)
    private String name;

    @ElementCollection(targetClass = CategoryType.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "product_categories", joinColumns = @JoinColumn(name = "product_id"))
    @Enumerated(EnumType.ORDINAL)
    private List<CategoryType> categoryType = new ArrayList<>();

    @Column(nullable = false)
    private String description;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderEntryEntity> orderEntries;

}