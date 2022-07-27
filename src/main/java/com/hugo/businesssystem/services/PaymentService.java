package com.hugo.businesssystem.services;

import com.hugo.businesssystem.entities.Payment;
import com.hugo.businesssystem.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    public List<Payment> findAll(){
        return repository.findAll();
    }
}
