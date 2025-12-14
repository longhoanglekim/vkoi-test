package com.example.demotest.service.impl;

import com.example.demotest.constant.OrderStatus;
import com.example.demotest.dto.CreateOrderRequest;
import com.example.demotest.dto.Item;
import com.example.demotest.dto.OrderResponse;
import com.example.demotest.exception.BusinessException;
import com.example.demotest.model.Order;
import com.example.demotest.model.OrderProduct;
import com.example.demotest.model.Product;
import com.example.demotest.model.User;
import com.example.demotest.repository.OrderRepository;
import com.example.demotest.repository.ProductRepository;
import com.example.demotest.repository.UserRepository;
import com.example.demotest.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    @Override
    public OrderResponse createOrder(CreateOrderRequest createOrderRequest) throws BusinessException {
        Order order = new Order();
        order.setOrderDate(LocalDateTime.now());
        User user = getUser(createOrderRequest);
        order.setUser(user);
        order.setNote(createOrderRequest.getNote());
        order.setOrderStatus(OrderStatus.PENDING);
        BigDecimal itemsPrice = new BigDecimal(0);
        for (Item item : createOrderRequest.getItems()) {
            OrderProduct orderProduct = new OrderProduct();
            Product product = getProduct(item);
            orderProduct.setProduct(product);
            orderProduct.setQuantity(item.getQuantity());
            itemsPrice = itemsPrice.add(orderProduct.getProduct().getPrice().multiply(BigDecimal.valueOf(item.getQuantity())));
        }
        order.setItemsPrice(itemsPrice);
        orderRepository.save(order);
        return OrderResponse.builder()
                .orderId(order.getId())
                .status(order.getOrderStatus())
                .totalPrice(order.getItemsPrice())
                .userId(user.getId())
                .note(order.getNote())
                .build();
    }

    private Product getProduct(Item item) {
        Product product;
        try {
            Optional<Product> optionalProduct = productRepository.findById(item.getProductId());
            if (optionalProduct.isPresent()) {
                product = optionalProduct.get();
            } else {
                throw new BusinessException("Cannot find the product with id " + item.getProductId(), HttpStatus.NOT_FOUND);
            }
        } catch (BusinessException e) {
            throw e;
        }
        return product;
    }

    private User getUser(CreateOrderRequest createOrderRequest) {
        User user;
        try {
            Optional<User> optionalUser = userRepository.findById(createOrderRequest.getUserId());
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
            }  else {
                throw new BusinessException("Cannot find the user with id " + createOrderRequest.getUserId() + ".", HttpStatus.NOT_FOUND);
            }
        } catch (BusinessException e) {
            throw e;
        }
        return user;
    }
}
