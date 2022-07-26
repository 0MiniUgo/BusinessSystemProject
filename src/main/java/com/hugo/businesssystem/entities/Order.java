package com.hugo.businesssystem.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tb_order")
@Data
@NoArgsConstructor
public class Order implements Serializable {

    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @NonNull
    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @OneToMany(mappedBy = "id.order", cascade = CascadeType.ALL)
    private Set<OrderItem> items = new HashSet<>();

    public Order(Long id, @NonNull Client client, @NonNull Payment payment) {
        this.id = id;
        this.client = client;
        this.payment = payment;
    }
}
