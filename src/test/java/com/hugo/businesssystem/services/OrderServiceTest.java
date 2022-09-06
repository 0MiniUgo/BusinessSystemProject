package com.hugo.businesssystem.services;

import com.hugo.businesssystem.entities.Order;
import com.hugo.businesssystem.entities.Payment;
import com.hugo.businesssystem.entities.dto.OrderDTO;
import com.hugo.businesssystem.repositories.ClientRepository;
import com.hugo.businesssystem.repositories.OrderRepository;
import com.hugo.businesssystem.repositories.PaymentRepository;
import com.hugo.businesssystem.repositories.ProductRepository;
import com.hugo.businesssystem.services.exceptions.ResourceNotFoundException;
import com.hugo.businesssystem.util.client.ClientCreator;
import com.hugo.businesssystem.util.order.OrderCreator;
import com.hugo.businesssystem.util.orderItem.OrderItemCreator;
import com.hugo.businesssystem.util.payment.PaymentCreator;
import com.hugo.businesssystem.util.product.ProductCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
class OrderServiceTest {

    @InjectMocks
    private OrderService orderService;
    @Mock
    private OrderRepository orderRepositoryMock;
    @Mock
    private ClientRepository clientRepositoryMock;
    @Mock
    private PaymentRepository paymentRepositoryMock;
    @Mock
    private ProductRepository productRepositoryMock;

    @BeforeEach
    void setUp(){

        List<Order> orders = List.of(OrderCreator.createValidOrder());
        BDDMockito.when(orderRepositoryMock.findAll())
                .thenReturn(orders);

        BDDMockito.when(orderRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.of(OrderCreator.createValidOrder()));

        BDDMockito.when(clientRepositoryMock.findById(ArgumentMatchers.anyLong()))
                        .thenReturn(Optional.of(ClientCreator.createValidClient()));
        BDDMockito.when(paymentRepositoryMock.save(ArgumentMatchers.any(Payment.class)))
                        .thenReturn(PaymentCreator.createValidPayment());
        BDDMockito.when(productRepositoryMock.getReferenceById(ArgumentMatchers.anyLong()))
                        .thenReturn(ProductCreator.createValidProduct());
        BDDMockito.when(productRepositoryMock.findById(ArgumentMatchers.anyLong()))
                        .thenReturn(Optional.of(ProductCreator.createValidProduct()));
        BDDMockito.when(orderRepositoryMock.save(ArgumentMatchers.any(Order.class)))
                .thenReturn(OrderCreator.createValidOrder());
    }

    @Test
    void findAll_ReturnListOfOrders_WhenSuccessful(){

        Long expectedId = OrderCreator.createValidOrder().getId();
        List<Order> orders = orderService.findAll();

        Assertions.assertThat(orders)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(orders.get(0).getId()).isEqualTo(expectedId);
    }
    @Test
    void findById_ReturnsOrder_WhenSuccessful(){

        Long expectedId = OrderCreator.createValidOrder().getId();
        Order order = orderService.findById(1L);

        Assertions.assertThat(order).isNotNull();
        Assertions.assertThat(order.getId()).isNotNull().isEqualTo(expectedId);
    }
    @Test
    void findById_ThrowsResourceNotFoundException_WhenOrderNotFound(){

        BDDMockito.when(orderRepositoryMock.findById(ArgumentMatchers.anyLong()))
                .thenReturn(Optional.empty());

        Assertions.assertThatExceptionOfType(ResourceNotFoundException.class)
                .isThrownBy(() -> orderService.findById(1L));
    }
    @Test
    void insert_ReturnsOrder_WhenSuccessful(){

        Order order = orderService.insert(OrderCreator.createValidOrderDTO());

        Assertions.assertThat(order)
                .isNotNull()
                .isEqualTo(OrderCreator.createValidOrder());
    }
    @Test
    void update_UpdatesProduct_WhenSuccessful(){

        Long id = OrderCreator.createValidOrder().getId();
        OrderDTO orderDTO = OrderCreator.createValidOrderDTO();

        Assertions.assertThat(orderService.update(id, orderDTO))
                .isNotNull()
                .isEqualTo(OrderCreator.createValidOrder());
    }
    @Test
    void delete_RemovesOrder_WhenSuccessful(){
        final Long id = 1L;
        orderService.delete(id);
        BDDMockito.verify(orderRepositoryMock).deleteById(id);
    }
}