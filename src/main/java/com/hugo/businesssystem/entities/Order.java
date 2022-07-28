package com.hugo.businesssystem.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_order")
@Data
@NoArgsConstructor
@JsonPropertyOrder({"id", "client", "items", "total", "payment"})
public class Order implements Serializable {

    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NonNull
    @ManyToOne
    private Client client;

    @NonNull
    @OneToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    @OneToMany(mappedBy = "id.order", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<OrderItem> items = new HashSet<>();


    public Order(Long id, @NonNull Client client, @NonNull Payment payment) {
        this.id = id;
        this.client = client;
        this.payment = payment;
    }

    public Double getTotal(){
        Double sum = 0.0;
        for(OrderItem orderItem : items){
            sum += orderItem.getSubTotal();
        }
        return sum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return id.equals(order.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
