package com.hugo.businesssystem.entities.dto;

import com.hugo.businesssystem.entities.Product;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductDTO {

    private String name;
    private Double price;

    public ProductDTO(Product product){
        this.name = product.getName();
        this.price = product.getPrice();
    }
}
