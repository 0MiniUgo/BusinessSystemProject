package com.hugo.businesssystem.services;

import com.hugo.businesssystem.entities.*;
import com.hugo.businesssystem.entities.dto.OrderDTO;
import com.hugo.businesssystem.entities.dto.OrderItemDTO;
import com.hugo.businesssystem.repositories.*;
import com.hugo.businesssystem.services.exceptions.DatabaseException;
import com.hugo.businesssystem.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderService {


    @Autowired
    private OrderRepository repository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PaymentRepository paymentRepository;

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

    public void delete(Long id){
        try {
            repository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException(e.getMessage());
        }catch (EmptyResultDataAccessException e){
            throw new ResourceNotFoundException(id);
        }
    }

    public Order update(Long id, OrderDTO orderDTO){
        try{
            Order order = repository.findById(id).get();
            updateData(order, orderDTO);
            return repository.save(order);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException(id);
        }
    }

    private void updateData(Order order, OrderDTO orderDTO) {
        Client client = clientRepository.findById(order.getId()).get();
        Set<OrderItem> orderItems = new HashSet<>();
        order.getItems().clear();

        for(OrderItemDTO orderItemDTO : orderDTO.getItems()){

            Product product = productRepository.getReferenceById(orderItemDTO.getProductId());

            OrderItem orderItem
                    = new OrderItem(order,
                    product,
                    product.getPrice(),
                    orderItemDTO.getQuantity());

            orderItems.add(orderItem);
        }
        order.setClient(client);
        order.getItems().addAll(orderItems);

    }

    private Order toEntity(OrderDTO orderDTO){
        Client client = clientRepository.findById(orderDTO.getClientId()).get();
        Payment payment = new Payment(null, LocalDateTime.now());
        Order order = new Order(null, client, payment);

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
