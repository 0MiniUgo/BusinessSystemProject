package com.hugo.businesssystem.entities.dto;

import com.hugo.businesssystem.entities.Client;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {

    private Long clientId;

    Set<OrderItemDTO> items = new HashSet<>();


}
