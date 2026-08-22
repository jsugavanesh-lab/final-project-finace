package com.example.demo.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.PutMapping;


import com.example.demo.FoodOrder;

@RestController
public class FoodOrderController {
	private List<FoodOrder> orders = new ArrayList<>();
	
    @PostMapping("/orders")
    public FoodOrder createOrder(@RequestBody FoodOrder order) {
        return order;
    }
    @GetMapping("/orders")
    public String getAllOrders() {
        return "All food orders";
    }
    @PostMapping("/orders1")
    public FoodOrder createOrder1(@RequestBody FoodOrder order) {
        orders.add(order);
        return order;
    }
    @GetMapping("/orders1")
    public List<FoodOrder> getAllOrders1() {
        return orders;
    }
    @GetMapping("/orders/{orderId}")
    public FoodOrder getOrderById(@PathVariable int orderId) {

        for (FoodOrder order : orders) {
            if (order.getOrderId() == orderId) {
                return order;
            }
        }

        return null;
    }
    @DeleteMapping("/orders/{orderId}")
    public String deleteOrder(@PathVariable int orderId) {

        for (FoodOrder order : orders) {
            if (order.getOrderId() == orderId) {
                orders.remove(order);
                return "Order deleted successfully";
            }
        }

        return "Order not found";
    }
    @PutMapping("/orders/{orderId}")
    public FoodOrder updateOrder(@PathVariable int orderId,
                                 @RequestBody FoodOrder updatedOrder) {

        for (FoodOrder order : orders) {

            if (order.getOrderId() == orderId) {

                order.setCustomerName(updatedOrder.getCustomerName());
                order.setFoodName(updatedOrder.getFoodName());
                order.setQuantity(updatedOrder.getQuantity());
                order.setPrice(updatedOrder.getPrice());

                return order;
            }
        }

        return null;
    }
}
