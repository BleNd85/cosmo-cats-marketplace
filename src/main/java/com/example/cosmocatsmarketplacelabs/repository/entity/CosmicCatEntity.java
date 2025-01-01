package com.example.cosmocatsmarketplacelabs.repository.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cosmic_cats")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CosmicCatEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cosmic_cat_id_seq")
    @SequenceGenerator(name = "cosmic_cat_id_seq", sequenceName = "cosmic_cat_id_seq")
    private Long id;

    @NaturalId
    @Column(unique = true, nullable = false)
    private UUID cosmicCatId;

    @Column(name = "cat_name", nullable = false)
    private String catName;

    @Column(name = "real_name", nullable = false)
    private String realName;

    @Column(name = "cat_mail", nullable = false, unique = true)
    private String catMail;

    @OneToMany(mappedBy = "cosmicCat", cascade = CascadeType.PERSIST, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<OrderEntity> orders;
}