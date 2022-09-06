package com.hugo.businesssystem.util.order;

import com.hugo.businesssystem.entities.Order;
import com.hugo.businesssystem.entities.OrderItem;
import com.hugo.businesssystem.entities.dto.OrderDTO;
import com.hugo.businesssystem.util.client.ClientCreator;
import com.hugo.businesssystem.util.orderItem.OrderItemCreator;
import com.hugo.businesssystem.util.payment.PaymentCreator;
import com.hugo.businesssystem.util.product.ProductCreator;

import java.util.Set;


public class OrderCreator {
    public static Order createValidOrder(){

        Order order = new Order( 1L,
                ClientCreator.createValidClient(),
                PaymentCreator.createValidPayment());

        OrderItem orderItem = new OrderItem(order,
                ProductCreator.createValidProduct()
                        ,3);
        order.getItems().add(orderItem);

        return order;
    }

    public static OrderDTO createValidOrderDTO(){

        return new OrderDTO(1L, Set.of(OrderItemCreator.creteValidOrderItemDTO()));
    }
}
