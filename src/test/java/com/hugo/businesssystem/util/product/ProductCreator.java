package com.hugo.businesssystem.util.product;

import com.hugo.businesssystem.entities.Product;
import com.hugo.businesssystem.entities.dto.ProductDTO;

public class ProductCreator {
    public static Product createProductToBeSaved(){
        return Product.builder()
                .name("Dipirona")
                .price(15.00)
                .build();
    }
    public static Product createValidProduct(){
        return Product.builder()
                .id(1L)
                .name("Dipirona")
                .price(15.00)
                .build();
    }
    public static ProductDTO createValidProductDTO(){
        return ProductDTO.builder()
                .name("Dipirona")
                .price(15.00)
                .build();
    }
}
