package com.hugo.businesssystem.repositories;

import com.hugo.businesssystem.entities.Order;
import com.hugo.businesssystem.entities.OrderItem;
import com.hugo.businesssystem.entities.pk.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
    List<OrderItem> findByIdOrder(Order order);
}
