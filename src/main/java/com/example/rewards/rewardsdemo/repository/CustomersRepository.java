package com.example.rewards.rewardsdemo.repository;

import com.example.rewards.rewardsdemo.domain.Customers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CustomersRepository extends JpaRepository<Customers,Integer> {
}
