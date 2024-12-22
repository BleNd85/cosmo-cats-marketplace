package com.example.cosmocatsmarketplacelabs.repository.entity;

import com.example.cosmocatsmarketplacelabs.domain.ProductDetails;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_entries")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_entry_id_seq")
    @SequenceGenerator(name = "order_entry_id_seq", sequenceName = "order_entry_id_seq")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @Column(name = "quantity", nullable = false)
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private OrderEntity order;
}