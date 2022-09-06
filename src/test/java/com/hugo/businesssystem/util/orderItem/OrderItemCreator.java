package com.hugo.businesssystem.util.orderItem;

import com.hugo.businesssystem.entities.OrderItem;
import com.hugo.businesssystem.entities.dto.OrderItemDTO;
import com.hugo.businesssystem.util.order.OrderCreator;
import com.hugo.businesssystem.util.product.ProductCreator;

public class OrderItemCreator {
    public static OrderItem creteValidOrderItem(){

        return new OrderItem(
                OrderCreator.createValidOrder(),
                ProductCreator.createValidProduct(),
                ProductCreator.createValidProduct().getPrice(),
                3
        );
    }

    public static OrderItemDTO creteValidOrderItemDTO(){

        return new OrderItemDTO(1L, 3);
    }
}
