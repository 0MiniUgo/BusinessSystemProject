package com.hugo.businesssystem.resources;

import com.hugo.businesssystem.entities.Order;
import com.hugo.businesssystem.entities.dto.OrderDTO;
import com.hugo.businesssystem.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

    @Autowired
    private OrderService service;

    @GetMapping
    public ResponseEntity<List<Order>> findAll(){
        List<Order> orders = service.findAll();

        return ResponseEntity.ok(orders);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id){
        Order order = service.findById(id);

        return ResponseEntity.ok(order);
    }

    @PostMapping
    public ResponseEntity<Order> insert(@RequestBody OrderDTO orderDTO){
        Order order = service.insert(orderDTO);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}").buildAndExpand(order.getId()).toUri();

        return ResponseEntity.created(uri).body(order);
    }
}
