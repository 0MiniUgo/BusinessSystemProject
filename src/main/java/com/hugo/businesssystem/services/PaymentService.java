package com.hugo.businesssystem.services;

import com.hugo.businesssystem.entities.Payment;
import com.hugo.businesssystem.repositories.PaymentRepository;
import com.hugo.businesssystem.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository repository;

    public List<Payment> findAll(){
        return repository.findAll();
    }

    public Payment findById(Long id){
        Optional<Payment> payment = repository.findById(id);

        return payment.orElseThrow(() -> new ResourceNotFoundException(id));
    }
}
