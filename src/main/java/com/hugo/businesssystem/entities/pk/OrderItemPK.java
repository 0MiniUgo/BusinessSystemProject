package com.hugo.businesssystem.entities.pk;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hugo.businesssystem.entities.Order;
import com.hugo.businesssystem.entities.Product;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Embeddable
public class OrderItemPK implements Serializable {

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "order_id")
    private Order order;
}
