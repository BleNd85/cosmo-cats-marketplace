package com.example.cosmocatsmarketplacelabs.repository.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_id_seq")
    @SequenceGenerator(name = "orders_id_seq", sequenceName = "orders_id_seq")
    private Long id;

    @NaturalId
    @Column(name = "order_id", unique = true, nullable = false)
    private UUID orderId;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderEntryEntity> orderItems;

    @ManyToOne
    @JoinColumn(name = "cosmic_cat_id", nullable = false)
    private CosmicCatEntity cosmicCat;
}