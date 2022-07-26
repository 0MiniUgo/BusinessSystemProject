package com.hugo.businesssystem.repositories;

import com.hugo.businesssystem.entities.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
