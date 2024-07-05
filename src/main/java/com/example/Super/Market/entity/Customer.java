package com.example.Super.Market.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Table(name = "customer")
@Entity
@Getter
@Setter
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private int customerId;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "phone_no")
    private long phoneNo;

//    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
//    private PurchaseHeader purchaseHeader;
}
