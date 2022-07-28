package com.hugo.businesssystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.hugo.businesssystem.entities.pk.OrderItemPK;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tb_order_item")
@JsonPropertyOrder({"id", "price", "quantity", "subTotal"})
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return Objects.equals(id, orderItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
