package com.hugo.businesssystem.repositories;

import com.hugo.businesssystem.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
