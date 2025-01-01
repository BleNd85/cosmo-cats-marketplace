package com.example.cosmocatsmarketplacelabs.config;

import com.example.cosmocatsmarketplacelabs.service.mapper.CosmicCatServiceMapper;
import com.example.cosmocatsmarketplacelabs.service.mapper.OrderServiceMapper;
import com.example.cosmocatsmarketplacelabs.service.mapper.ProductServiceMapper;
import org.mapstruct.factory.Mappers;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class MappersTestConfiguration {
    @Bean
    public ProductServiceMapper productMapper() {
        return Mappers.getMapper(ProductServiceMapper.class);
    }

    @Bean
    public CosmicCatServiceMapper cosmicCatMapper() {
        return Mappers.getMapper(CosmicCatServiceMapper.class);
    }

    @Bean
    public OrderServiceMapper orderServiceMapper() {
        return Mappers.getMapper(OrderServiceMapper.class);
    }
}
