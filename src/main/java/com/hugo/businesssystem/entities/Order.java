package com.hugo.businesssystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Client client;

    @NonNull
    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @JsonIgnore
    @OneToMany(mappedBy = "id.order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<OrderItem> items = new HashSet<>();


    public Order(Long id, @NonNull Client client, @NonNull Payment payment) {
        this.id = id;
        this.client = client;
        this.payment = payment;
    }
}
