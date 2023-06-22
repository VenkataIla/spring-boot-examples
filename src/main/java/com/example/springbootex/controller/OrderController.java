package com.example.springbootex.controller;

import com.example.springbootex.dto.OrderRequest;
import com.example.springbootex.modal.Customer;
import com.example.springbootex.repo.CustomerRepository;
import com.example.springbootex.repo.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrderController {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/placeOrder")
    public Customer placeOrder(@RequestBody OrderRequest request){
        return customerRepository.save(request.getCustomer());
    }

    @GetMapping("/orders")
    public List<Customer> allOrders()
    {
        return customerRepository.findAll();
    }

}
