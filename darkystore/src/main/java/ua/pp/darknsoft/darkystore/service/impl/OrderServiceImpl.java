package ua.pp.darknsoft.darkystore.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pp.darknsoft.darkystore.constants.ApplicationConstants;
import ua.pp.darknsoft.darkystore.dto.OrderItemDto;
import ua.pp.darknsoft.darkystore.dto.OrderRequestDto;
import ua.pp.darknsoft.darkystore.exception.ResourceNotFoundException;
import ua.pp.darknsoft.darkystore.model.Customer;
import ua.pp.darknsoft.darkystore.model.Order;
import ua.pp.darknsoft.darkystore.model.OrderItem;
import ua.pp.darknsoft.darkystore.model.Product;
import ua.pp.darknsoft.darkystore.repository.OrderRepository;
import ua.pp.darknsoft.darkystore.repository.ProductRepository;
import ua.pp.darknsoft.darkystore.service.IOrderService;
import ua.pp.darknsoft.darkystore.service.IProfileService;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderServiceImpl implements IOrderService {

    private final OrderRepository orderRepository;
    private final IProfileService profileService;
    private final ProductRepository productRepository;

    @Override
    @Transactional
    public void createOrder(OrderRequestDto orderRequest) {
        Customer customer = profileService.getAuthenticatedCustomer();
        // Create Order
        Order order = new Order();
        order.setCustomer(customer);
        BeanUtils.copyProperties(orderRequest, order);
        order.setOrderStatus(ApplicationConstants.ORDER_STATUS_CREATED);
        // Map OrderItems
        Set<Long> ids = orderRequest.items().stream().map(OrderItemDto::productId).collect(Collectors.toSet());
        List<Product> products = productRepository.findByProductIdIn(ids);
        if (products.isEmpty() || products.size() != ids.size()) {
            throw new ResourceNotFoundException("Product", "ProductIDs", ids.toString());
        }

        Set<OrderItem> orderItems = orderRequest.items().stream().map(item -> {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            Product product = products.stream()
                    .filter(prod -> item.productId().equals(prod.getProductId()))
                    .findFirst()
                    .orElseThrow(() -> new ResourceNotFoundException("Product", "ProductID",
                            item.productId().toString()));
            orderItem.setProduct(product);
            orderItem.setQuantity(item.quantity());
            orderItem.setPrice(item.price());
            return orderItem;
        }).collect(Collectors.toSet());
        order.setOrderItems(orderItems);
        orderRepository.save(order);
    }
}
