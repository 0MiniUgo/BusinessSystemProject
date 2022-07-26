package com.hugo.businesssystem.repositories;

import com.hugo.businesssystem.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
