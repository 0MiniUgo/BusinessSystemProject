package com.hugo.businesssystem.entities;

import com.hugo.businesssystem.entities.pk.OrderItemPK;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {

    @EmbeddedId
    private OrderItemPK id = new OrderItemPK();

    private Integer quantity;
    private Double price;

    public OrderItem(Order order, Product product, Integer quantity){
        id.setOrder(order);
        id.setProduct(product);
        this.price = product.getPrice();
        this.quantity = quantity;
    }

    public Double getSubTotal(){
        return quantity * price;
    }
}
