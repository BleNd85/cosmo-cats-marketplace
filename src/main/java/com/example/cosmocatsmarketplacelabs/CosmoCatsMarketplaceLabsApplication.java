package com.example.cosmocatsmarketplacelabs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class CosmoCatsMarketplaceLabsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CosmoCatsMarketplaceLabsApplication.class, args);
    }
}
