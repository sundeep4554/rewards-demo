package com.example.rewards.rewardsdemo.service;

import com.example.rewards.rewardsdemo.domain.Customers;
import com.example.rewards.rewardsdemo.domain.Transactions;
import com.example.rewards.rewardsdemo.repository.CustomersRepository;
import com.example.rewards.rewardsdemo.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.groupingBy;
import static java.util.stream.Collectors.summingInt;

@Service
public class CustomerService {

    @Autowired
    CustomersRepository customersRepository;

    @Autowired
    TransactionRepository transactionRepository;

    public List<Customers> getAllcustomers() {
        List<Customers> customers = customersRepository.findAll();
        customers.forEach(item->{
            Map<String, Integer> getMonthlySum = new HashMap<String, Integer>();
            Map<String, Integer> result = new HashMap<String, Integer>();
           Integer totalCustRewards =0;
            item.getTransactions().forEach(transaction -> {
                transaction.setRewardPoints(calculateRewards(transaction.getTotal()));
                getMonthlySum.put(transaction.getInsertDate().toString(),transaction.getRewardPoints());
            });

            for (Map.Entry<String, Integer> entry  : getMonthlySum.entrySet()) {
                String key = entry.getKey().split("-")[0] + "/" + entry.getKey().split("-")[1];
                Integer value = entry.getValue();
                Integer oldValue = result.get(key) != null ? result.get(key) : 0;
                result.put(key, oldValue + value);
                totalCustRewards+=value;
            }
            item.setMonthlyTotal(result);
            item.setTotalcustrewards(totalCustRewards);
        });

        return customers;
    }

    public List<Transactions> getALLTransaction() {
        List<Transactions> transactions = transactionRepository.findAll();
        transactions.stream().forEach(item->{

            item.setRewardPoints(calculateRewards(item.getTotal()));
        });
           return  transactions;
    }


    private Integer calculateRewards(Integer total){
        Integer rewardsPoints =0;
        if(total>50 && total<=100){
             rewardsPoints =  total-50;
        }
        else if(total>100){
            rewardsPoints =50+(total-100)*2;
        }
        else{
            rewardsPoints =0;
        }
    return  rewardsPoints;
    }

}
