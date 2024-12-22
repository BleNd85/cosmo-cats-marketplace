package com.example.cosmocatsmarketplacelabs.config;

import com.example.cosmocatsmarketplacelabs.repository.implementation.NaturalIdRepositoryImpl;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EnableJpaRepositories(value = "com.example.cosmocatsmarketplacelabs.repository",
        repositoryBaseClass = NaturalIdRepositoryImpl.class)
public class JpaRepositoryConfiguration {
}
