package com.example.rewards.rewardsdemo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Table(name="customers")
public class Customers {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @OneToMany(mappedBy="customerId", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    private Set<Transactions> transactions;
    @Transient
    private Map<String, Integer> monthlyTotal = new HashMap<>();
    @Transient
    private Integer totalcustrewards;
}
