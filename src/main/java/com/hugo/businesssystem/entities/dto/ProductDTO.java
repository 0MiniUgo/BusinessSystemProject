package com.hugo.businesssystem.entities.dto;

import com.hugo.businesssystem.entities.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDTO {

    private String name;
    private Double price;

    public ProductDTO(Product product){
        this.name = product.getName();
        this.price = product.getPrice();
    }
}
