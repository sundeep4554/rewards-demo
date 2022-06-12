package com.example.rewards.rewardsdemo.controllers;

import com.example.rewards.rewardsdemo.domain.Customers;
import com.example.rewards.rewardsdemo.domain.Transactions;
import com.example.rewards.rewardsdemo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Access;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;



    @GetMapping("/customers")
    public List<Customers> getAllcustomers(){
        return customerService.getAllcustomers();
    }


    @GetMapping("/transactions")
    public List<Transactions> getALLTransaction(){
        return customerService.getALLTransaction();
    }
}
