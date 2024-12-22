package com.example.cosmocatsmarketplacelabs.domain;

import com.example.cosmocatsmarketplacelabs.domain.order.OrderDetails;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class CosmicCatDetails {
    Long id;
    UUID cosmicCatReference;
    String catName;
    String realName;
    String catMail;
    List<OrderDetails> orders;
}
