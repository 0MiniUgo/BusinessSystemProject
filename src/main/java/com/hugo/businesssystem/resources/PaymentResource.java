package com.hugo.businesssystem.resources;

import com.hugo.businesssystem.entities.Payment;
import com.hugo.businesssystem.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/payments")
public class PaymentResource {

    @Autowired
    private PaymentService service;

    @GetMapping
    public ResponseEntity<List<Payment>> findAll(){
        List<Payment> payments = service.findAll();

        return ResponseEntity.ok(payments);
    }
}
