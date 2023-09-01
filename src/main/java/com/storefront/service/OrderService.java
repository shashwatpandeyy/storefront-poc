package com.storefront.service;

import com.storefront.util.Utility;
import com.storefront.util.Utility.Regex;
import com.storefront.dto.Order;
import com.storefront.repository.Respository;
import jakarta.xml.bind.ValidationException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.regex.Pattern;

@Service
public class OrderService {

    public String save(Order order) throws ValidationException {
        verifyOrder(order);
        String orderId = Utility.generateOrderId();
        order.setId(orderId);
        order.setOrderDate(LocalDateTime.now());
        order.setTotalAmount(BigDecimal.valueOf(Respository.itemMap.get(order.getItemId()).getPrice()));
        Respository.orderMap.put(orderId, order);
        return orderId;
    }

    private void verifyOrder(Order order) throws ValidationException {
        if (order.getFullName() == null ||
                order.getEmail() == null ||
                order.getPhoneNumber() == null ||
                order.getCreditCardNumber() == null ||
                order.getItemId() == null) {
            throw new ValidationException("Some required fields are missing!", "FIELD_ERROR");
        }
        if (!Pattern.matches(Regex.NAME, order.getFullName())) {
            throw new ValidationException("Name should only contain alphabet or spaces!", "NAME_ERROR");
        }
        if (!Pattern.matches(Regex.EMAIL, order.getEmail())) {
            throw new ValidationException("Email is not valid!", "EMAIL_ERROR");
        }
        if (!Pattern.matches(Regex.PHONE_NUMBER, order.getPhoneNumber())) {
            throw new ValidationException("Phone should 10 digit long in a format xxx-xxx-xxxx", "CREDIT_CARD_ERROR");
        }
        if (!Pattern.matches(Regex.CREDIT_CARD, order.getCreditCardNumber())) {
            throw new ValidationException("Credit card information is wrong!", "PHONE_NUMBER_ERROR");
        }
        if (!Respository.itemMap.containsKey(order.getItemId())) {
            throw new ValidationException("Item does not exists!", "ITEM_ERROR");
        }

    }

    public Order getOrderById(String orderId) throws IllegalArgumentException {
        if (Respository.orderMap.containsKey(orderId)) {
            return Respository.orderMap.get(orderId);
        } else {
            throw new IllegalArgumentException("Order id " + orderId + " does not exists!");
        }
    }
}
