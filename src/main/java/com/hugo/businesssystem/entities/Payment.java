package com.hugo.businesssystem.entities;

import java.io.Serializable;

import java.time.LocalDateTime;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_payment")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Payment implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "instant_pay", columnDefinition ="TIMESTAMP")
    private LocalDateTime instantPay;

    @JsonIgnore
    @OneToOne(mappedBy = "payment", cascade = CascadeType.ALL)
    private Order order;

    public Payment(Long id, LocalDateTime instantPay) {
        this.id = id;
        this.instantPay = instantPay;
    }
}
