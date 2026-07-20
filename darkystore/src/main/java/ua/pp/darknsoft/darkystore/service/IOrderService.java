package ua.pp.darknsoft.darkystore.service;

import ua.pp.darknsoft.darkystore.dto.OrderRequestDto;
import ua.pp.darknsoft.darkystore.dto.OrderResponseDto;
import ua.pp.darknsoft.darkystore.exception.ResourceNotFoundException;
import ua.pp.darknsoft.darkystore.model.Order;

import java.util.List;

public interface IOrderService {
    void createOrder(OrderRequestDto orderRequest) throws ResourceNotFoundException;

    List<OrderResponseDto> getCustomerOrders();

    List<OrderResponseDto> getAllPendingOrders();

    Order updateOrderStatus(Long orderId, String orderStatus);
}
