package com.hugo.businesssystem.config;

import com.hugo.businesssystem.entities.*;
import com.hugo.businesssystem.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

@Configuration
@Profile("test")
@EnableJpaRepositories(basePackageClasses = ClientRepository.class)
public class DevConfig implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public void run(String... args) throws Exception {

        List<Client> clients = clientRepository.findAll();
        List<Product> products = productRepository.findAll();
        List<Payment> payments = paymentRepository.findAll();

        Client client = clientRepository.findById(3L).get();
        Payment payment = paymentRepository.findById(1L).get();
        Product product = productRepository.findById(5L).get();
        Order order = new Order(null , client, payment);
        orderRepository.save(order);
        OrderItem orderItem = new OrderItem(order, product,3);
        orderItemRepository.save(orderItem);

        /*for (Client client : clients) {
            System.out.println(client);
        }
        System.out.println();

        for(Product product : products){
            System.out.println(product);
        }
        System.out.println();

        for(Payment payment : payments){
            System.out.println(payment);
        }*/
    }
}
