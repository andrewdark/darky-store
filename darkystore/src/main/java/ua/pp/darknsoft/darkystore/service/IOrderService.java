package ua.pp.darknsoft.darkystore.service;

import ua.pp.darknsoft.darkystore.dto.OrderRequestDto;
import ua.pp.darknsoft.darkystore.exception.ResourceNotFoundException;

public interface IOrderService {
    void createOrder(OrderRequestDto orderRequest) throws ResourceNotFoundException;
}
