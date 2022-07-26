package com.hugo.businesssystem.config;

import com.hugo.businesssystem.entities.Client;
import com.hugo.businesssystem.entities.Payment;
import com.hugo.businesssystem.entities.Product;
import com.hugo.businesssystem.repositories.ClientRepository;
import com.hugo.businesssystem.repositories.PaymentRepository;
import com.hugo.businesssystem.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Configuration
@Profile("test")
public class DevConfig implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public void run(String... args) throws Exception {

        List<Client> clients = clientRepository.findAll();
        List<Product> products = productRepository.findAll();
        List<Payment> payments = paymentRepository.findAll();

        for (Client client : clients) {
            System.out.println(client);
        }
        System.out.println();

        for(Product product : products){
            System.out.println(product);
        }
        System.out.println();

        for(Payment payment : payments){
            System.out.println(payment);
        }
    }
}
