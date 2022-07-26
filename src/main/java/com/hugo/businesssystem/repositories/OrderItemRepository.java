package com.hugo.businesssystem.repositories;

import com.hugo.businesssystem.entities.OrderItem;
import com.hugo.businesssystem.entities.pk.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}
