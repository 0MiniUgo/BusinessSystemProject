package com.hugo.businesssystem.entities.dto;

import com.hugo.businesssystem.entities.Client;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OrderDTO {

    private Long clientId;

    Set<OrderItemDTO> items = new HashSet<>();


}
