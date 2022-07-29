package com.hugo.businesssystem.services;

import com.hugo.businesssystem.entities.*;
import com.hugo.businesssystem.entities.dto.OrderDTO;
import com.hugo.businesssystem.entities.dto.OrderItemDTO;
import com.hugo.businesssystem.repositories.*;
import com.hugo.businesssystem.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OrderService {


    @Autowired
    private OrderRepository repository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Order> findAll(){
        return repository.findAll();
    }

    public Order findById(Long id){
        Optional<Order> order = repository.findById(id);

        return order.orElseThrow(() -> new ResourceNotFoundException(id));
    }

    public Order insert(OrderDTO orderDTO){

        Order order = toEntity(orderDTO);

        return repository.save(order);
    }

    private Order toEntity(OrderDTO orderDTO){
        Client client = clientRepository.findById(orderDTO.getClientId()).get();
        Payment payment = new Payment(null, LocalDateTime.now());
        Order order = new Order(null, client, payment);
        System.out.println(order.getId() + "---" + order.getClient().getName());

        paymentRepository.save(payment);

        Set<OrderItem> orderItems = new HashSet<>();
        for(OrderItemDTO orderItemDTO : orderDTO.getItems()){

            Product product = productRepository.findById(orderItemDTO.getProductId()).get();

            OrderItem orderItem = new OrderItem(order, product, orderItemDTO.getQuantity());
            orderItems.add(orderItem);
        }

        order.setItems(orderItems);

        return order;
    }
}
